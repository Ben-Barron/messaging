package com.benbarron.messaging;

import com.benbarron.messaging.client.Client;
import com.benbarron.messaging.messages.RequestMessage;
import com.benbarron.messaging.messages.ResponseEndMessage;
import com.benbarron.messaging.messages.ResponseErrorMessage;
import com.benbarron.messaging.messages.ResponseMessage;
import com.benbarron.util.Logger;
import com.benbarron.util.Reflection;
import com.benbarron.util.flow.Flows;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Stream;

class DefaultMessageBroker implements MessageBroker {

    private final Logger logger = Logger.get(MessageBroker.class);
    private final Map<Class, Collection<MessageHandler>> messageHandlers = new ConcurrentHashMap<>();
    private final MessageIdGenerator messageIdGenerator;

    public DefaultMessageBroker(MessageIdGenerator messageIdGenerator) {
        this.messageIdGenerator = messageIdGenerator;
    }

    @Override
    public void register(Client client) {
        client.messages()
            .ofType(RequestMessage.class)
            .subscribe(message -> handleRequestMessage(client, message),
                       throwable -> logger.error(throwable, "Client error. client={}", client),
                       () -> logger.debug("Client message stream complete (probably disconnected.) client={}", client));
    }

    @Override
    public void register(MessageHandler messageHandler) {
        Class handledType = Reflection.findFirstGenericInterfaceType(MessageHandler.class, messageHandler);
        Reflection.findAllAssignableTypes(handledType)
            .forEach(type ->
                 messageHandlers.computeIfAbsent(handledType, key -> new ConcurrentLinkedQueue<>())
                     .add(messageHandler));
    }

    // TODO: handle all type types of message payload here...we already have the handlers
    @SuppressWarnings("unchecked")
    private void handleRequestMessage(Client client, RequestMessage message) {
        Class messageType = message.getPayload().getClass();
        Collection<MessageHandler> handlers = messageHandlers.get(messageType);

        if (handlers == null || handlers.isEmpty()) {
            logger.error("No message handler found. messageType={}, client={}", messageType, client);
            return;
        }

        Stream<BufferedMessageReply> messageReplies = handlers.stream()
            .map(handler -> {
                BufferedMessageReply reply = new BufferedMessageReply();
                handler.handle(client, reply, message.getPayload());
                return reply;
            })
            .filter(reply -> !reply.isNoReply());

        Flows.flatten(messageReplies)
            .subscribe(replyMessage -> client.send(new ResponseMessage<>(messageIdGenerator.generate(),
                                                                         message.getConversationId(),
                                                                         message.getRequestId(),
                                                                         replyMessage)),
                       throwable -> client.send(new ResponseErrorMessage(messageIdGenerator.generate(),
                                                                         message.getConversationId(),
                                                                         message.getRequestId(),
                                                                         throwable.getMessage())),
                       () -> client.send(new ResponseEndMessage(message.getConversationId(),
                                                                message.getRequestId())));
    }
}

package com.benbarron.messaging.topic;

import com.benbarron.messaging.client.Client;
import com.benbarron.messaging.MessageHandler;
import com.benbarron.messaging.MessageReply;

class TopicSubscriptionMessageHandler implements MessageHandler<TopicSubscription> {

    private final TopicManager topicManager;

    public TopicSubscriptionMessageHandler(TopicManager topicManager) {
        this.topicManager = topicManager;
    }

    @Override
    public void handle(Client client, MessageReply reply, TopicSubscription message) {
        if (TopicSubscription.Type.SUBSCRIBE == message.getType()) {
            topicManager.register(client, message.getTopicName());
        } else if (TopicSubscription.Type.UNSUBSCRIBE == message.getType()) {
            topicManager.unregister(client, message.getTopicName());
        } else {
            reply.error("Invalid subscription type. type={}", message.getType());
            return;
        }

        reply.complete();
    }
}

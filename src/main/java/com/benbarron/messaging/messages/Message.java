package com.benbarron.messaging.messages;

import java.io.Serializable;

public class Message<T extends Serializable> implements Serializable {

    private final String messageId;
    private final String conversationId;
    private final T payload;

    public Message(String messageId,
                   String conversationId,
                   T payload) {

        this.messageId = messageId;
        this.conversationId = conversationId;
        this.payload = payload;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getConversationId() {
        return conversationId;
    }

    public T getPayload() {
        return payload;
    }
}

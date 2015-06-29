package com.benbarron.messaging.messages;

import java.io.Serializable;

public class TopicMessage<T extends Serializable> extends Message<T> {

    private final String topicName;

    public TopicMessage(String messageId,
                        String sessionId,
                        String topicName,
                        T payload) {

        super(messageId, sessionId, payload);
        this.topicName = topicName;
    }

    public String getTopicName() {
        return topicName;
    }
}

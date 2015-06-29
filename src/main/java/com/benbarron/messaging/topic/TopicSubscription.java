package com.benbarron.messaging.topic;

import java.io.Serializable;

public class TopicSubscription implements Serializable {

    private final String topicName;
    private final Type type;

    public TopicSubscription(String topicName, Type type) {
        this.topicName = topicName;
        this.type = type;
    }

    public String getTopicName() {
        return topicName;
    }

    public Type getType() {
        return type;
    }


    public enum Type {

        SUBSCRIBE,
        UNSUBSCRIBE;
    }
}

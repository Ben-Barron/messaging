package com.benbarron.messaging.topic;

import java.io.Serializable;

public interface TopicPublisher {

    void publish(Serializable message);
}

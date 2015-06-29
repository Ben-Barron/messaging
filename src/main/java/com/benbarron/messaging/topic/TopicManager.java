package com.benbarron.messaging.topic;

import com.benbarron.messaging.client.Client;

public interface TopicManager {

    TopicPublisher getPublisher(String topicName);

    void register(Client client, String topicName);

    void unregister(Client client, String topicName);
}

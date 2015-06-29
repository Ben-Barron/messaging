package com.benbarron.messaging.topic;

import com.benbarron.messaging.client.Client;
import com.benbarron.messaging.messages.TopicMessage;
import com.benbarron.util.Closeable;
import com.benbarron.util.CompositeKey;
import com.benbarron.util.flow.Producer;
import com.benbarron.util.flow.SimpleFlow;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class DefaultTopicManager implements TopicManager {

    private final Map<String, SimpleFlow<TopicMessage>> topics = new ConcurrentHashMap<>();
    private final Map<CompositeKey, Closeable> clientSubscriptions = new ConcurrentHashMap<>();

    @Override
    public TopicPublisher getPublisher(String topicName) {
        return message -> {
            Producer<TopicMessage> producer = topics.get(topicName);

            if (producer != null) {
                producer.next(new TopicMessage<>(null, null, topicName, message));
            }
        };
    }

    @Override
    public void register(Client client, String topicName) {
        CompositeKey subscriptionKey = CompositeKey.of(client, topicName);
        clientSubscriptions.computeIfAbsent(subscriptionKey, key -> {
            SimpleFlow<TopicMessage> topicFlow = topics.computeIfAbsent(topicName, key1 -> new SimpleFlow<>());
            return topicFlow.subscribe(client::send);
        });
    }

    @Override
    public void unregister(Client client, String topicName) {
        CompositeKey subscriptionKey = CompositeKey.of(client, topicName);
        Closeable subscription = clientSubscriptions.remove(subscriptionKey);

        if (subscription != null) {
            subscription.close();
        }
    }
}

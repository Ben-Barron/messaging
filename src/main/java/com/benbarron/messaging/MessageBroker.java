package com.benbarron.messaging;

import com.benbarron.messaging.client.Client;

public interface MessageBroker {

    void register(Client client);

    void register(MessageHandler messageHandler);
}

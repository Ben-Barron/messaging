package com.benbarron.messaging;

import com.benbarron.messaging.client.Client;

import java.io.Serializable;

public interface MessageHandler<T extends Serializable> {

    default void handle(Client client, T message) {
        throw new UnsupportedOperationException();
    }

    default void handle(Client client, MessageReply reply, T message) {
        reply.setNoReply();
        handle(client, message);
    }
}

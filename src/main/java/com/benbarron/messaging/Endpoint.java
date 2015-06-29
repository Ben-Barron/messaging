package com.benbarron.messaging;

import com.benbarron.messaging.messages.Message;
import com.benbarron.util.flow.Flow;

public interface Endpoint {

    Flow<Void> disconnected();

    boolean isConnected();

    Flow<Message> messages();

    void send(Message message);
}

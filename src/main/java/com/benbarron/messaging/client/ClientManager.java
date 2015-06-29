package com.benbarron.messaging.client;

import com.benbarron.messaging.Endpoint;

public interface ClientManager {

    Client register(Endpoint endpoint, LoginDetails loginDetails);
}

package com.benbarron.messaging.client;

import com.benbarron.messaging.Endpoint;

public interface Client extends Endpoint {

    String getId();

    boolean hasRole(String role);
}

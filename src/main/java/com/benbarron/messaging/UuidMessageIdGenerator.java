package com.benbarron.messaging;

import java.util.UUID;

class UuidMessageIdGenerator implements MessageIdGenerator {

    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}

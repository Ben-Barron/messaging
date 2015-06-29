package com.benbarron.messaging.messages;

import java.io.Serializable;

public class ResponseMessage<T extends Serializable> extends Message<T> {

    private final String requestId;

    public ResponseMessage(String responseMessageId,
                           String conversationId,
                           String requestId,
                           T responsePayload) {

        super(responseMessageId, conversationId, responsePayload);
        this.requestId = requestId;
    }

    public String getRequestId() {
        return requestId;
    }
}

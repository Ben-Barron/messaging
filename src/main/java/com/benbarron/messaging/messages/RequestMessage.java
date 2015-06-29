package com.benbarron.messaging.messages;

import java.io.Serializable;

public class RequestMessage<T extends Serializable> extends Message<T> {

    public RequestMessage(String requestId,
                          String conversationId,
                          T requestPayload) {

        super(requestId, conversationId, requestPayload);
    }

    public String getRequestId() {
        return getMessageId();
    }
}

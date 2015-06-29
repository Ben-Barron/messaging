package com.benbarron.messaging.messages;

public class ResponseEndMessage extends ResponseMessage<Empty> {

    public ResponseEndMessage(String conversationId,
                              String requestId) {

        super(null, conversationId, requestId, null);
    }
}

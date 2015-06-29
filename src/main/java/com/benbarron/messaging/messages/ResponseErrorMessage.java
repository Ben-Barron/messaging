package com.benbarron.messaging.messages;

public class ResponseErrorMessage extends ResponseMessage<String> {

    public ResponseErrorMessage(String responseMessageId,
                                String conversationId,
                                String requestId,
                                String errorMessage) {

        super(responseMessageId, conversationId, requestId, errorMessage);
    }

    public String getErrorMessage() {
        return getPayload();
    }
}

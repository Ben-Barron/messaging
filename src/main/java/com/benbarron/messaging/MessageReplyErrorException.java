package com.benbarron.messaging;

class MessageReplyErrorException extends Exception {

    public MessageReplyErrorException(String format, Object... params) {
        super(format, null, false, false);
    }
}

package com.benbarron.messaging;

import com.benbarron.util.flow.Producer;

import java.io.Serializable;

public interface MessageReply extends Producer<Serializable> {

    default void error(String format, Object... params) {
        error(new MessageReplyErrorException(format, params));
    }

    boolean isNoReply();

    void setNoReply();
}

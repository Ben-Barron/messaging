package com.benbarron.messaging;

import com.benbarron.util.Closeable;
import com.benbarron.util.flow.Flow;

import java.io.Serializable;
import java.util.function.Consumer;

class BufferedMessageReply implements Flow<Serializable>, MessageReply {

    @Override
    public Closeable subscribe(Consumer<Serializable> nextCallback, Consumer<Throwable> errorCallback, Runnable completeCallback) {
        return null;
    }

    @Override
    public boolean isNoReply() {
        return false;
    }

    @Override
    public void setNoReply() {

    }

    @Override
    public void next(Serializable message) {

    }

    @Override
    public void complete() {

    }

    @Override
    public void error(Throwable throwable) {

    }
}

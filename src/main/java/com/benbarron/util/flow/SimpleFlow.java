package com.benbarron.util.flow;

import com.benbarron.util.Closeable;

import java.util.function.Consumer;

public class SimpleFlow<T> implements Flow<T>, Producer<T> {

    @Override
    public Closeable subscribe(Consumer<T> nextCallback, Consumer<Throwable> errorCallback, Runnable completeCallback) {
        return null;
    }

    @Override
    public void complete() {

    }

    @Override
    public void error(Throwable throwable) {

    }

    @Override
    public void next(T item) {

    }
}

package com.benbarron.util.flow;

public interface Producer<T> {

    void complete();

    void error(Throwable throwable);

    void next(T item);
}

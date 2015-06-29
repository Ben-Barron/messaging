package com.benbarron.util.flow;

import com.benbarron.util.Closeable;

import java.util.function.Consumer;
import java.util.function.Function;

public interface Flow<T> {

    default <R> Flow<R> ofType(Class<R> type) {
        return null;
    }

    default <R> Flow<R> map(Function<T, R> mapper) {
        return null;
    }

    default Closeable subscribe(Consumer<T> nextCallback) {
        return () -> { };
    }

    Closeable subscribe(Consumer<T> nextCallback,
                        Consumer<Throwable> errorCallback,
                        Runnable completeCallback);
}

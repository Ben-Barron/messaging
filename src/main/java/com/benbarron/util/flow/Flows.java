package com.benbarron.util.flow;

import java.util.stream.Stream;

public interface Flows {

    static <T> Flow<T> flatten(Stream<? extends Flow<T>> stream) {
        return stream.findFirst().get();
    }
}

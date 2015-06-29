package com.benbarron.util;

@FunctionalInterface
public interface Closeable extends AutoCloseable {

    @Override
    void close();
}

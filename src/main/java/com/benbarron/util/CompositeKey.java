package com.benbarron.util;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public class CompositeKey {

    private final Object[] keys;

    private CompositeKey(Object... keys) {
        this.keys = keys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompositeKey that = (CompositeKey) o;

        return Arrays.equals(keys, that.keys);

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(keys);
    }


    public static CompositeKey of(Object... keys) {
        if (keys.length == 0) {
            throw new IllegalArgumentException("Keys length must be greater than 0");
        }

        if (Stream.of(keys).filter(Objects::isNull).count() > 0) {
            throw new NullPointerException("Cannot have null keys in a CompositeKey");
        }

        return new CompositeKey(keys);
    }
}

package com.benbarron.util;

import java.lang.reflect.Proxy;
import java.util.stream.Stream;

public interface Reflection {

    static <T> Class findFirstGenericInterfaceType(Class<T> genericInterface, T object) {
        return object.getClass();
    }

    static Stream<Class> findAllAssignableTypes(Class type) {
        return Stream.of(type);
    }

    static String toString(Object object) {
        return object.toString();
    }
}

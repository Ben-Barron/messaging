package com.benbarron.util;

public interface Logger {

    void debug(String format, Object... params);

    void error(String format, Object... params);

    void error(Throwable throwable, String format, Object... params);


    static Logger get(Class type) {
        return null;
    }
}

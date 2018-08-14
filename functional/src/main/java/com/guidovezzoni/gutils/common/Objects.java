package com.guidovezzoni.gutils.common;

/**
 * provides the same routine of {@link java.util.Objects} just for any SDK
 */
public class Objects {

    private Objects() {
    }

    public static boolean equals(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }

}

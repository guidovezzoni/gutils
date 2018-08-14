package com.guidovezzoni.gutils.functional;

/**
 * This is a functional interface and can be used for a lambda expression or a method reference.
 */
@FunctionalInterface
public interface ActionWithException1<T, E extends Exception> {
    void run(T t) throws E;
}

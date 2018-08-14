package com.guidovezzoni.gutils.functional;

/**
 * This is a functional interface and can be used for a lambda expression or a method reference.
 */
@FunctionalInterface
public interface Action1<T> {
    void run(T t);
}

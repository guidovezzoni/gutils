package com.guidovezzoni.gutils.functional;

/**
 * This is a functional interface and can be used for a lambda expression or a method reference.
 */
@FunctionalInterface
public interface Action2<T1, T2> {
    void run(T1 t1, T2 t2);
}

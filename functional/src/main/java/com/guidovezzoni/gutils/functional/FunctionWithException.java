package com.guidovezzoni.gutils.functional;

/**
 * This is a functional interface and can be used for a lambda expression or a method reference.
 */
@FunctionalInterface
public interface FunctionWithException<R, E extends Exception> {
    R run() throws E;
}

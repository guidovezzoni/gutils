package com.guidovezzoni.gutils.tuple;

/**
 * @implNote Initially this was meant to be an interface for mutable objects, but then it didn't seem
 * a great idea - also it was allowing changing the type of any item which is somehow confusing, so
 * it was restricted to an immutable-definition
 */
public interface Tuple {
    /**
     * arity of the tuple
     *
     * @return arity
     */
    int getArity();

    /**
     * get item at specific position - 0 based
     *
     * @param pos between 0 and {@link #getArity()}-1
     * @param <T> type of the requested item
     * @return instance requested
     */
    <T> T getItem(int pos);

    /**
     * Shallow copy - new instance of the tuple that contains the same fields of the calling instance.
     *
     * @param <T> tuple type
     * @return the new tuple
     */
    <T extends Tuple> T copy();
}

package com.guidovezzoni.gutils.tuple;

public interface Tuple {
    /**
     * arity of the tuple
     *
     * @return arity
     */
    int getArity();

    /**
     * get item at specific position
     *
     * @param pos between 0 and {@link #getArity()}-1
     * @param <T> type of the requested item
     * @return instance requested
     */
    <T> T getItem(int pos);

    /**
     * Set item at specific position
     *
     * @param t   instance of the item
     * @param pos between 0 and {@link #getArity()}-1
     * @param <T> type of the item
     */
    <T> void setItem(T t, int pos);

    /**
     * Shallow copy - new instance of the tuple that contains the same fields of the calling instance.
     *
     * @param <T> tuple type
     * @return the new tuple
     */
    <T extends Tuple> T copy();
}

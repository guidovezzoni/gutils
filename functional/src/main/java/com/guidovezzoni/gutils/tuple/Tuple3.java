package com.guidovezzoni.gutils.tuple;

import com.guidovezzoni.gutils.common.Objects;

@SuppressWarnings({"WeakerAccess", "unused"})
public class Tuple3<T0, T1, T2> extends BaseTuple implements Tuple {
    private static final int ARITY = 3;
    private final T0 t0;
    private final T1 t1;
    private final T2 t2;

    public Tuple3(T0 t0, T1 t1, T2 t2) {
        this.t0 = t0;
        this.t1 = t1;
        this.t2 = t2;
    }

    public static <T0, T1, T2> Tuple3<T0, T1, T2> of(T0 t0, T1 t1, T2 t2) {
        return new Tuple3<>(t0, t1, t2);
    }

    public T0 getItem0() {
        return t0;
    }

    public T1 getItem1() {
        return t1;
    }

    public T2 getItem2() {
        return t2;
    }

    @Override
    public int getArity() {
        return ARITY;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getItem(int pos) {
        switch (pos) {
            case 0:
                return (T) getItem0();
            case 1:
                return (T) getItem1();
            case 2:
                return (T) getItem2();
            default:
                throw new IndexOutOfBoundsException(String.valueOf(pos));
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Tuple3<T0, T1, T2> copy() {
        return Tuple3.of(t0, t1, t2);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof Tuple3)) {
            return false;
        }

        Tuple3<?, ?, ?> cast = (Tuple3<?, ?, ?>) o;
        return Objects.equals(cast.getItem0(), t0)
                && Objects.equals(cast.getItem1(), t1)
                && Objects.equals(cast.getItem2(), t2);
    }

    @Override
    public int hashCode() {
        return (t0 == null ? 0 : t0.hashCode())
                ^ (t1 == null ? 0 : t1.hashCode())
                ^ (t2 == null ? 0 : t2.hashCode());
    }

    @Override
    public String toString() {
        return Tuple3.class.getSimpleName() + "{" + t0.toString() + "," + t1.toString() + "," + t2.toString() + "}";
    }
}

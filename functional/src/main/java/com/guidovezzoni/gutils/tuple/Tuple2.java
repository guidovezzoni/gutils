package com.guidovezzoni.gutils.tuple;

import com.guidovezzoni.gutils.common.Objects;

@SuppressWarnings({"WeakerAccess", "unused"})
public class Tuple2<T0, T1> extends BaseTuple implements Tuple {
    private static final int ARITY = 2;
    private final T0 t0;
    private final T1 t1;

    public Tuple2(T0 t0, T1 t1) {
        this.t0 = t0;
        this.t1 = t1;
    }

    public static <T0, T1> Tuple2<T0, T1> of(T0 t0, T1 t1) {
        return new Tuple2<>(t0, t1);
    }

    public T0 getItem0() {
        return t0;
    }

    public T1 getItem1() {
        return t1;
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
            default:
                throw new IndexOutOfBoundsException(String.valueOf(pos));
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Tuple2<T0, T1> copy() {
        return Tuple2.of(t0, t1);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof Tuple2)) {
            return false;
        }

        Tuple2<?, ?> cast = (Tuple2<?, ?>) o;
        return Objects.equals(cast.getItem0(), t0) && Objects.equals(cast.getItem1(), t1);
    }

    @Override
    public int hashCode() {
        return (t0 == null ? 0 : t0.hashCode()) ^ (t1 == null ? 0 : t1.hashCode());
    }

    @Override
    public String toString() {
        return Tuple2.class.getSimpleName() + "{" + t0.toString() + "," + t1.toString() + "}";
    }
}

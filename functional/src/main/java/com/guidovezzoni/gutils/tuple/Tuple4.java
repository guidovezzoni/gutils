package com.guidovezzoni.gutils.tuple;

import com.guidovezzoni.gutils.common.Objects;

@SuppressWarnings({"WeakerAccess", "unused"})
public class Tuple4<T0, T1, T2, T3> extends BaseTuple implements Tuple {
    private static final int ARITY = 4;
    private T0 t0;
    private T1 t1;
    private T2 t2;
    private T3 t3;

    public Tuple4(T0 t0, T1 t1, T2 t2, T3 t3) {
        this.t0 = t0;
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
    }

    public static <T0, T1, T2, T3> Tuple4<T0, T1, T2, T3> of(T0 t0, T1 t1, T2 t2, T3 t3) {
        return new Tuple4<>(t0, t1, t2, t3);
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

    public T3 getItem3() {
        return t3;
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
            case 3:
                return (T) getItem3();
            default:
                throw new IndexOutOfBoundsException(String.valueOf(pos));
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> void setItem(T t, int pos) {
        switch (pos) {
            case 0:
                this.t0 = (T0) t;
                break;
            case 1:
                this.t1 = (T1) t;
                break;
            case 2:
                this.t2 = (T2) t;
                break;
            case 3:
                this.t3 = (T3) t;
                break;
            default:
                throw new IndexOutOfBoundsException(String.valueOf(pos));
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Tuple4<T0, T1, T2, T3> copy() {
        return new Tuple4<>(t0, t1, t2, t3);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof Tuple4)) {
            return false;
        }

        Tuple4<?, ?, ?, ?> cast = (Tuple4<?, ?, ?, ?>) o;
        return Objects.equals(cast.getItem0(), t0)
                && Objects.equals(cast.getItem1(), t1)
                && Objects.equals(cast.getItem2(), t2)
                && Objects.equals(cast.getItem3(), t3);
    }

    @Override
    public int hashCode() {
        return (t0 == null ? 0 : t0.hashCode())
                ^ (t1 == null ? 0 : t1.hashCode())
                ^ (t2 == null ? 0 : t2.hashCode())
                ^ (t3 == null ? 0 : t3.hashCode());
    }

    @Override
    public String toString() {
        return Tuple4.class.getSimpleName() + "{" + t0.toString() + "," + t1.toString() + "," + t2.toString() + "," + t3.toString() + "}";
    }
}

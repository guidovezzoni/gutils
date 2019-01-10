package com.guidovezzoni.gutils.tuple;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class Tuple3Test {

    private Tuple3<String, Integer, Boolean> sut;

    @Before
    public void setUp() {
        sut = Tuple3.of("first", 16, true);
    }

    @Test
    public void whenGetItem0ThenReturnFirst() {
        assertThat(sut.getItem0())
                .isEqualTo("first");
    }

    @Test
    public void whenGetItem1ThenReturnFirst() {
        assertThat(sut.getItem1())
                .isEqualTo(16);
    }

    @Test
    public void whenGetItem2ThenReturnThird() {
        assertThat(sut.getItem2())
                .isEqualTo(true);
    }

    @Test
    public void whenGetItemWith0ThenReturnFirst() {
        assertThat((String) sut.getItem(0))
                .isEqualTo("first");
    }

    @Test
    public void whenGetItemWith1ThenReturnFirst() {
        assertThat((Integer) sut.getItem(1))
                .isEqualTo(16);
    }

    @Test
    public void whenGetItemWith2ThenReturnThird() {
        assertThat((Boolean) sut.getItem(2))
                .isEqualTo(true);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetItem3ThenThrowError() {
        assertThat((Object) sut.getItem(3))
                .isNotNull();
    }

    @Test
    public void whenCopyThenANewShallowCopyIsReturned() {
        Tuple3 newCopy = sut.copy();

        assertThat(newCopy).isInstanceOf(sut.getClass());
        assertThat(newCopy == sut).isFalse();
        assertThat(newCopy.getArity()).isEqualTo(sut.getArity());
        assertThat(newCopy.getItem0()).isEqualTo(sut.getItem0());
        assertThat(newCopy.getItem1()).isEqualTo(sut.getItem1());
        assertThat(newCopy.getItem2()).isEqualTo(sut.getItem2());
    }

    @SuppressWarnings("EqualsWithItself")
    @Test
    public void testEqualsIsReflexive() {
        assertThat(sut.equals(sut)).isTrue();
    }

    @Test
    public void testEqualsIsSymmetric() {
        Tuple3 newCopy = Tuple3.of("first", 16, true);
        Tuple3 different = Tuple3.of(0.5345, 234, false);

        assertThat(sut.equals(newCopy)).isTrue();
        assertThat(newCopy.equals(sut)).isTrue();

        assertThat(sut.equals(different)).isFalse();
        assertThat(different.equals(sut)).isFalse();
    }

    @Test
    public void testEqualsIsTransitive() {
        Tuple3 copy1 = Tuple3.of("first", 16, true);
        Tuple3 copy2 = Tuple3.of("first", 16, true);

        assertThat(sut.equals(copy1)).isTrue();
        assertThat(copy1.equals(copy2)).isTrue();
        assertThat(sut.equals(copy2)).isTrue();
    }

    @Test
    public void testEqualsIsConsistent() {
        Tuple3 newCopy = Tuple3.of("first", 16, true);
        Tuple3 different = Tuple3.of(0.5345, 234, false);

        for (int ii = 0; ii < 1000; ii++) {
            assertThat(sut.equals(newCopy)).isTrue();
            assertThat(sut.equals(different)).isFalse();
        }
    }

    @SuppressWarnings("ObjectEqualsNull")
    @Test
    public void testEqualsOnNullIsFalse() {
        assertThat(sut.equals(null)).isFalse();
    }

    @Test
    public void testHashCodeIsConsistent() {
        int sutHash = sut.hashCode();

        for (int ii = 0; ii < 1000; ii++) {
            assertThat(sut.hashCode()).isEqualTo(sutHash);
        }
    }

    @Test
    public void testHashIdentityOnEqualObjects() {
        Tuple3 newCopy = Tuple3.of("first", 16, true);

        assertThat(sut).isEqualTo(newCopy);
        assertThat(sut.hashCode()).isEqualTo(newCopy.hashCode());
    }

    @Test
    public void testToString() {
        assertThat(sut.toString()).isEqualTo("Tuple3{first,16,true}");
    }
}

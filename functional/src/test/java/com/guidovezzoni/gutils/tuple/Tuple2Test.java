package com.guidovezzoni.gutils.tuple;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class Tuple2Test {

    private Tuple2<String, Integer> sut;

    @Before
    public void setUp() {
        sut = Tuple2.of("first", 16);
    }

    @Test
    public void whenGetItem0ThenReturnFirst() {
        assertThat(sut.getItem0())
                .isEqualTo("first");
    }

    @Test
    public void whenGetItem1ThenReturnSecond() {
        assertThat(sut.getItem1())
                .isEqualTo(16);
    }

    @Test
    public void whenGetItemWith0ThenReturnFirst() {
        assertThat((String) sut.getItem(0))
                .isEqualTo("first");
    }

    @Test
    public void whenGetItemWith1ThenReturnSecond() {
        assertThat((Integer) sut.getItem(1))
                .isEqualTo(16);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetItem2ThenThrowError() {
        assertThat((Object) sut.getItem(2))
                .isNotNull();
    }

    @Test
    public void whenCopyThenANewShallowCopyIsReturned() {
        Tuple2 newCopy = sut.copy();

        assertThat(newCopy).isInstanceOf(sut.getClass());
        assertThat(newCopy == sut).isFalse();
        assertThat(newCopy.getArity()).isEqualTo(sut.getArity());
        assertThat(newCopy.getItem0()).isEqualTo(sut.getItem0());
        assertThat(newCopy.getItem1()).isEqualTo(sut.getItem1());
    }

    @SuppressWarnings("EqualsWithItself")
    @Test
    public void testEqualsIsReflexive() {
        assertThat(sut.equals(sut)).isTrue();
    }

    @Test
    public void testEqualsIsSymmetric() {
        Tuple2 newCopy = Tuple2.of("first", 16);
        Tuple2 different = Tuple2.of(0.5345, 234);

        assertThat(sut.equals(newCopy)).isTrue();
        assertThat(newCopy.equals(sut)).isTrue();

        assertThat(sut.equals(different)).isFalse();
        assertThat(different.equals(sut)).isFalse();
    }

    @Test
    public void testEqualsIsTransitive() {
        Tuple2 copy1 = Tuple2.of("first", 16);
        Tuple2 copy2 = Tuple2.of("first", 16);

        assertThat(sut.equals(copy1)).isTrue();
        assertThat(copy1.equals(copy2)).isTrue();
        assertThat(sut.equals(copy2)).isTrue();
    }

    @Test
    public void testEqualsIsConsistent() {
        Tuple2 newCopy = Tuple2.of("first", 16);
        Tuple2 different = Tuple2.of(0.5345, 234);

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
        Tuple2 newCopy = Tuple2.of("first", 16);

        assertThat(sut).isEqualTo(newCopy);
        assertThat(sut.hashCode()).isEqualTo(newCopy.hashCode());
    }

    @Test
    public void testToString() {
        assertThat(sut.toString()).isEqualTo("Tuple2{first,16}");
    }
}
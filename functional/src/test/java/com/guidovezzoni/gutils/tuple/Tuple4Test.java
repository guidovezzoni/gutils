package com.guidovezzoni.gutils.tuple;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class Tuple4Test {

    private Tuple4<String, Integer, Boolean, Double> sut;

    @Before
    public void setUp() {
        sut = Tuple4.of("first", 16, true, 13.49);
    }

    @Test
    public void whenGetItem0ThenReturnFirst() {
        assertThat(sut.getItem(0))
                .isEqualTo("first");
    }

    @Test
    public void whenGetItem1ThenReturnFirst() {
        assertThat(sut.getItem(1))
                .isEqualTo(16);
    }

    @Test
    public void whenGetItem2ThenReturnThird() {
        assertThat(sut.getItem(2))
                .isEqualTo(true);
    }

    @Test
    public void whenGetItem3ThenReturnThird() {
        assertThat(sut.getItem(3))
                .isEqualTo(13.49);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetItem4ThenThrowError() {
        assertThat(sut.getItem(4))
                .isNotNull();
    }

    @Test
    public void whenSetItem0ThenReturnNewFirst() {
        sut.setItem(15.89, 0);

        assertThat(sut.getItem(0))
                .isEqualTo(15.89);
    }

    @Test
    public void whenSetItem1ThenReturnNewSecond() {
        sut.setItem(5457474.54, 1);

        assertThat(sut.getItem(1))
                .isEqualTo(5457474.54);
    }

    @Test
    public void whenSetItem2ThenReturnNewThird() {
        sut.setItem(5457474.54, 2);

        assertThat(sut.getItem(2))
                .isEqualTo(5457474.54);
    }

    @Test
    public void whenSetItem3ThenReturnNewThird() {
        sut.setItem("test", 3);

        assertThat(sut.getItem(3))
                .isEqualTo("test");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetItem4ThenThrowError() {
        sut.setItem(5457474.54, 4);
    }

    @Test
    public void whenCopyThenANewShallowCopyIsReturned() {
        Tuple4 newCopy = sut.copy();

        assertThat(newCopy).isInstanceOf(sut.getClass());
        assertThat(newCopy == sut).isFalse();
        assertThat(newCopy.getArity()).isEqualTo(sut.getArity());
        assertThat(newCopy.getItem0()).isEqualTo(sut.getItem0());
        assertThat(newCopy.getItem1()).isEqualTo(sut.getItem1());
        assertThat(newCopy.getItem2()).isEqualTo(sut.getItem2());
        assertThat(newCopy.getItem3()).isEqualTo(sut.getItem3());
    }

    @SuppressWarnings("EqualsWithItself")
    @Test
    public void testEqualsIsReflexive() {
        assertThat(sut.equals(sut)).isTrue();
    }

    @Test
    public void testEqualsIsSymmetric() {
        Tuple4 newCopy = Tuple4.of("first", 16, true, 13.49);
        Tuple4 different = Tuple4.of(0.5345, 234, false, false);

        assertThat(sut.equals(newCopy)).isTrue();
        assertThat(newCopy.equals(sut)).isTrue();

        assertThat(sut.equals(different)).isFalse();
        assertThat(different.equals(sut)).isFalse();
    }

    @Test
    public void testEqualsIsTransitive() {
        Tuple4 copy1 = Tuple4.of("first", 16, true, 13.49);
        Tuple4 copy2 = Tuple4.of("first", 16, true, 13.49);

        assertThat(sut.equals(copy1)).isTrue();
        assertThat(copy1.equals(copy2)).isTrue();
        assertThat(sut.equals(copy2)).isTrue();
    }

    @Test
    public void testEqualsIsConsistent() {
        Tuple4 newCopy = Tuple4.of("first", 16, true, 13.49);
        Tuple4 different = Tuple4.of(0.5345, 234, false, false);

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


}

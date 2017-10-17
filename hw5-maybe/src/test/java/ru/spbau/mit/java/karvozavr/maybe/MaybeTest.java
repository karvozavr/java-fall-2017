package ru.spbau.mit.java.karvozavr.maybe;

import org.junit.jupiter.api.Test;
import ru.spbau.mit.java.karvozavr.maybe.exception.MaybeIsNothingException;

import static org.junit.jupiter.api.Assertions.*;

class MaybeTest {

    @Test
    void testGetJust() {
        Maybe<String> m = Maybe.just("str");
        try {
            assertEquals("str", m.get());
        } catch (MaybeIsNothingException e) {
            fail(e);
        }
    }

    @Test
    void testGetNothing() {
        Maybe<String> m = Maybe.nothing();
        assertThrows(MaybeIsNothingException.class, m::get);
    }

    @Test
    void testIsPresentJust() {
        Maybe<String> m = Maybe.just("str");
        assertTrue(m.isPresent());
    }

    @Test
    void testIsPresentNothing() {
        Maybe<String> m = Maybe.nothing();
        assertFalse(m.isPresent());
    }

    @Test
    void testMap() {
        Maybe<String> m = Maybe.just("11");
        try {
            assertEquals(new Integer(11), m.map(Integer::parseInt).get());
        } catch (MaybeIsNothingException e) {
            fail(e);
        }
    }
}
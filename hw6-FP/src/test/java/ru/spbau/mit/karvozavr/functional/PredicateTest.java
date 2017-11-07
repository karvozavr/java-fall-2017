package ru.spbau.mit.karvozavr.functional;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class PredicateTest {

    @SuppressWarnings("unchecked")
    @Test
    void testAlwaysTrue() {
        assertThat(Predicate.ALWAYS_TRUE.apply(null), is(true));
        assertThat(Predicate.ALWAYS_TRUE.apply("Kitten"), is(true));
        assertThat(Predicate.ALWAYS_TRUE.apply(false), is(true));
        assertThat(Predicate.ALWAYS_TRUE.apply(true), is(true));
    }

    @SuppressWarnings("unchecked")
    @Test
    void testAlwaysFalse() {
        assertThat(Predicate.ALWAYS_FALSE.apply(null), is(false));
        assertThat(Predicate.ALWAYS_FALSE.apply("Kitten"), is(false));
        assertThat(Predicate.ALWAYS_FALSE.apply(false), is(false));
        assertThat(Predicate.ALWAYS_FALSE.apply(true), is(false));
    }

    @SuppressWarnings("unchecked")
    @Test
    void testNot() {
        assertThat(Predicate.ALWAYS_TRUE.not().apply(null), is(false));
        assertThat(Predicate.ALWAYS_FALSE.not().apply(null), is(true));
    }

    @SuppressWarnings("unchecked")
    @Test
    void testOr() {
        assertThat(Predicate.ALWAYS_TRUE.or(Predicate.ALWAYS_TRUE).apply(null), is(true));
        assertThat(Predicate.ALWAYS_TRUE.or(Predicate.ALWAYS_FALSE).apply(null), is(true));
        assertThat(Predicate.ALWAYS_FALSE.or(Predicate.ALWAYS_TRUE).apply(null), is(true));
        assertThat(Predicate.ALWAYS_FALSE.or(Predicate.ALWAYS_FALSE).apply(null), is(false));
    }

    @SuppressWarnings("unchecked")
    @Test
    void testAnd() {
        assertThat(Predicate.ALWAYS_TRUE.and(Predicate.ALWAYS_TRUE).apply(null), is(true));
        assertThat(Predicate.ALWAYS_TRUE.and(Predicate.ALWAYS_FALSE).apply(null), is(false));
        assertThat(Predicate.ALWAYS_FALSE.and(Predicate.ALWAYS_TRUE).apply(null), is(false));
        assertThat(Predicate.ALWAYS_FALSE.and(Predicate.ALWAYS_FALSE).apply(null), is(false));
    }
}
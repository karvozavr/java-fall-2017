package ru.spbau.mit.karvozavr.functional;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.Preconditions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class PredicateTest {

    @Test
    void testAlwaysTrue() {
        assertThat(Predicate.alwaysTrue().apply(null), is(true));
        assertThat(Predicate.alwaysTrue().apply("Kitten"), is(true));
        assertThat(Predicate.alwaysTrue().apply(false), is(true));
        assertThat(Predicate.alwaysTrue().apply(true), is(true));
    }

    @Test
    void testAlwaysFalse() {
        assertThat(Predicate.alwaysFalse().apply(null), is(false));
        assertThat(Predicate.alwaysFalse().apply("Kitten"), is(false));
        assertThat(Predicate.alwaysFalse().apply(false), is(false));
        assertThat(Predicate.alwaysFalse().apply(true), is(false));
    }

    @Test
    void testNot() {
        assertThat(Predicate.alwaysTrue().not().apply(null), is(false));
        assertThat(Predicate.alwaysFalse().not().apply(null), is(true));
    }

    @Test
    void testOr() {
        assertThat(Predicate.alwaysTrue().or(Predicate.alwaysTrue()).apply(null), is(true));
        assertThat(Predicate.alwaysTrue().or(Predicate.alwaysFalse()).apply(null), is(true));
        assertThat(Predicate.alwaysFalse().or(Predicate.alwaysTrue()).apply(null), is(true));
        assertThat(Predicate.alwaysFalse().or(Predicate.alwaysFalse()).apply(null), is(false));
    }

    @Test
    void testAnd() {
        assertThat(Predicate.alwaysTrue().and(Predicate.alwaysTrue()).apply(null), is(true));
        assertThat(Predicate.alwaysTrue().and(Predicate.alwaysFalse()).apply(null), is(false));
        assertThat(Predicate.alwaysFalse().and(Predicate.alwaysTrue()).apply(null), is(false));
        assertThat(Predicate.alwaysFalse().and(Predicate.alwaysFalse()).apply(null), is(false));
    }

    @Test
    void testCustomPredicate() {
        Predicate<Integer> isEven = x -> x % 2 == 0;
        Predicate<Integer> isOdd = isEven.not();

        for (int i = 0; i < 1000; i++) {
            assertThat(isEven.apply(i), is(i % 2 == 0));
            assertThat(isOdd.apply(i), is(i % 2 != 0));
            assertThat(isOdd.and(isEven).apply(i), is(false));
            assertThat(isOdd.or(isEven).apply(i), is(true));
        }
    }
}
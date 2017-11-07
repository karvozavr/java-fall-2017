package ru.spbau.mit.karvozavr.functional;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class Function1Test {

    @Test
    void testApplySameTypes() {
        final Function1<Integer, Integer> foo = argument -> argument * 2;
        final Integer arg1 = 2;
        final Integer arg2 = 2047;
        assertThat(foo.apply(arg1), equalTo(arg1 * 2));
        assertThat(foo.apply(arg2), equalTo(arg2 * 2));
    }

    @Test
    void testApplyDifferentTypes() {
        final Function1<Integer, String> foo = Object::toString;
        final Integer arg1 = 2;
        final Integer arg2 = 2047;
        assertThat(foo.apply(arg1), equalTo(arg1.toString()));
        assertThat(foo.apply(arg2), equalTo(arg2.toString()));
    }

    @Test
    void testComposeSmoke() {
        final Function1<Integer, Integer> foo = argument -> argument * 2;
        final Function1<Integer, String> bar = Object::toString;
        final Integer arg1 = 2;
        final Integer arg2 = 2047;
        assertThat(foo.compose(bar).apply(arg1), equalTo(Integer.toString(arg1 * 2)));
        assertThat(foo.compose(bar).apply(arg2), equalTo(Integer.toString(arg2 * 2)));
    }

    /**
     * Master created a composition of thousand functions. (C)
     */
    @Test
    void testComposeMany() {
        final Integer n = 20;
        Function1<Integer, Integer> foo = argument -> argument + 1;
        for (int i = 0; i < n; i++) {
            foo = foo.compose(foo);
        }
        assertThat(foo.apply(0), equalTo(1 << n));
    }
}
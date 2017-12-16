package ru.spbau.mit.karvozavr.functional;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class Function2Test {

    @Test
    void testApply() {
        final Function2<Integer, String, Double> foo = (Integer a, String s) -> a * Double.parseDouble(s);
        final Integer arg1 = 41;
        final String arg2 = "42.5";
        assertThat(foo.apply(arg1, arg2), equalTo(arg1 * Double.parseDouble(arg2)));
    }

    @Test
    void testCompose() {
        final Function2<Integer, String, Double> foo = (Integer a, String s) -> a * Double.parseDouble(s);
        final Function1<Double, Double> bar = argument -> argument * 2;
        final Integer arg1 = 41;
        final String arg2 = "42.5";
        assertThat(foo.compose(bar).apply(arg1, arg2), equalTo(arg1 * Double.parseDouble(arg2) * 2));
    }

    @Test
    void testBind1() {
        final Function2<Integer, String, Double> foo = (Integer a, String s) -> a * Double.parseDouble(s);
        final Integer arg1 = 41;
        final String arg2 = "42.5";
        final Function1<String, Double> bar = foo.bind1(arg1);
        assertThat(bar.apply(arg2), equalTo(arg1 * Double.parseDouble(arg2)));
    }

    @Test
    void testBind2() {
        final Function2<Integer, String, Double> foo = (Integer a, String s) -> a * Double.parseDouble(s);
        final Integer arg1 = 41;
        final String arg2 = "42.5";
        final Function1<Integer, Double> bar = foo.bind2(arg2);
        assertThat(bar.apply(arg1), equalTo(arg1 * Double.parseDouble(arg2)));
    }

    @Test
    void testCurry() {
        final Function2<Integer, String, Double> foo = (Integer a, String s) -> a * Double.parseDouble(s);
        final Integer arg1 = 41;
        final String arg2 = "42.5";
        final Function1<String, Double> bar = foo.curry(arg1);
        assertThat(bar.apply(arg2), equalTo(arg1 * Double.parseDouble(arg2)));
    }
}
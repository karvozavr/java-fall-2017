package ru.spbau.mit.karvozavr;

import org.junit.Test;
import ru.spbau.mit.karvozavr.exceptions.AmbiguousImplementationException;
import ru.spbau.mit.karvozavr.exceptions.ImplementationNotFoundException;
import ru.spbau.mit.karvozavr.exceptions.InjectionCycleException;
import ru.spbau.mit.karvozavr.testclasses.ClassWithOneClassDependency;
import ru.spbau.mit.karvozavr.testclasses.ClassWithOneInterfaceDependency;
import ru.spbau.mit.karvozavr.testclasses.ClassWithoutDependencies;
import ru.spbau.mit.karvozavr.testclasses.InterfaceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class InjectorTest {

    @Test
    public void injectorShouldInitializeClassWithoutDependencies()
            throws Exception {
        Object object = Injector.initialize(ClassWithoutDependencies.class.getName(), Collections.emptyList());
        assertTrue(object instanceof ClassWithoutDependencies);
    }

    @Test
    public void injectorShouldInitializeClassWithOneClassDependency()
            throws Exception {
        Object object = Injector.initialize(
                ClassWithOneClassDependency.class.getName(),
                Collections.singletonList(ClassWithoutDependencies.class.getName())
        );
        assertTrue(object instanceof ClassWithOneClassDependency);
        ClassWithOneClassDependency instance = (ClassWithOneClassDependency) object;
        assertTrue(instance.dependency != null);
    }

    @Test
    public void injectorShouldInitializeClassWithOneInterfaceDependency()
            throws Exception {
        Object object = Injector.initialize(
                ClassWithOneInterfaceDependency.class.getName(),
                Collections.singletonList(InterfaceImpl.class.getName())
        );
        assertTrue(object instanceof ClassWithOneInterfaceDependency);
        ClassWithOneInterfaceDependency instance = (ClassWithOneInterfaceDependency) object;
        assertTrue(instance.dependency instanceof InterfaceImpl);
    }

    @Test(expected = AmbiguousImplementationException.class)
    public void testAmbiguousImplementation()
            throws Exception {
        Object object = Injector.initialize(
                ClassWithOneInterfaceDependency.class.getName(),
                Arrays.asList(InterfaceImpl.class.getName(), InterfaceImpl.class.getName())
        );
    }

    @Test(expected = ImplementationNotFoundException.class)
    public void testImplementationNotFound()
            throws Exception {
        Object object = Injector.initialize(
                ClassWithOneInterfaceDependency.class.getName(),
                new ArrayList<>()
        );
    }

    @Test(expected = InjectionCycleException.class)
    public void testInjectionCycle() {

    }
}
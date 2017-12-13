package ru.spbau.mit.karvozavr;

import ru.spbau.mit.karvozavr.exceptions.AmbiguousImplementationException;
import ru.spbau.mit.karvozavr.exceptions.ImplementationNotFoundException;
import ru.spbau.mit.karvozavr.exceptions.InjectionCycleException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

public class Injector {

    /**
     * Create and initialize object of `rootClassName` class using classes from
     * `implementationClassNames` for concrete dependencies.
     */
    public static Object initialize(String rootClassName, List<String> implementationClassNames)
        throws Exception {
        return initializeImplementation(rootClassName, implementationClassNames, new ArrayList<>());
    }

    private static Object initializeImplementation(String rootClassName, List<String> implementationClassNames, List<Class<?>> dependencyStack)
            throws Exception {
        Class<?> rootClass = Class.forName(rootClassName);
        Constructor<?> rootConstructor = rootClass.getConstructors()[0];

        for (Parameter parameter : rootConstructor.getParameters()) {
            if (dependencyStack.contains(parameter.getType())) {
                throw new InjectionCycleException();
            }
        }

        ArrayList<Object> arguments = new ArrayList<>();
        for (Parameter parameter : rootConstructor.getParameters()) {
            boolean success = false;

            for (String dependency : implementationClassNames) {
                if (parameter.getType().isAssignableFrom(Class.forName(dependency))) {
                    if (success) {
                        throw new AmbiguousImplementationException();
                    }

                    try {
                        dependencyStack.add(parameter.getType());
                        arguments.add(initializeImplementation(dependency, implementationClassNames, dependencyStack));
                        success = true;
                    } catch (ImplementationNotFoundException exception) {
                        success = false;
                    } finally {
                        dependencyStack.remove(dependencyStack.size() - 1);
                    }
                }
            }

            if (!success) {
                throw new ImplementationNotFoundException();
            }
        }

        return rootConstructor.newInstance(arguments.toArray());
    }
}

package ru.spbau.mit.karvozavr;

import org.jetbrains.annotations.NotNull;
import ru.spbau.mit.karvozavr.exceptions.AmbiguousImplementationException;
import ru.spbau.mit.karvozavr.exceptions.ConstructorNotFoundException;
import ru.spbau.mit.karvozavr.exceptions.ImplementationNotFoundException;
import ru.spbau.mit.karvozavr.exceptions.InjectionCycleException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * Inject dependencies utility.
 */
public class Injector {

    /**
     * Create and initialize object of `rootClassName` class using classes from
     * `implementationClassNames` for concrete dependencies.
     *
     * @param rootClassName            name of the root class
     * @param implementationClassNames names of implementations
     * @return object of `rootClassName` class
     * @throws Exception if dependency can not be resolved or failed to get some class / constructor
     */
    @NotNull
    public static Object initialize(@NotNull String rootClassName,
                                    @NotNull List<String> implementationClassNames)
            throws Exception {
        return initializeImplementation(rootClassName, implementationClassNames, new ArrayList<>());
    }

    /**
     * initialize method implementation.
     */
    @NotNull
    private static Object initializeImplementation(@NotNull String rootClassName,
                                                   @NotNull List<String> implementationClassNames,
                                                   @NotNull List<Class<?>> dependencyStack)
            throws Exception {
        final Class<?> rootClass = Class.forName(rootClassName);

        // Get the constructor of root class.
        Constructor<?> rootConstructor;
        try {
            rootConstructor = rootClass.getConstructors()[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ConstructorNotFoundException();
        }

        // Check for dependency cycle.
        for (Parameter parameter : rootConstructor.getParameters()) {
            if (dependencyStack.contains(parameter.getType())) {
                throw new InjectionCycleException();
            }
        }

        ArrayList<Object> arguments = new ArrayList<>();

        // Try to get all the arguments from implementations.
        for (Parameter parameter : rootConstructor.getParameters()) {
            boolean success = false;

            for (String implementationName : implementationClassNames) {

                // If this implementation resolves our dependency.
                if (parameter.getType().isAssignableFrom(Class.forName(implementationName))) {

                    // Multiple possible implementations are not allowed.
                    if (success)
                        throw new AmbiguousImplementationException();

                    // Try recursively resolve dependency.
                    try {
                        dependencyStack.add(parameter.getType());
                        arguments.add(
                                initializeImplementation(
                                        implementationName,
                                        implementationClassNames,
                                        dependencyStack
                                ));
                        success = true;
                    } catch (ImplementationNotFoundException exception) {
                        success = false;
                    } finally {
                        dependencyStack.remove(dependencyStack.size() - 1);
                    }
                }
            }

            if (!success)
                throw new ImplementationNotFoundException();
        }

        return rootConstructor.newInstance(arguments.toArray());
    }
}

package ru.spbau.mit.java.karvozavr;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

public class ClassPrinter {

    public static void printStructure(Class<?> someClass) {
        System.out.println(renderStructure(someClass));
    }

    private static String renderStructure(Class<?> someClass) {
        StringBuilder result = new StringBuilder();

        result.append(someClass.getCanonicalName()).append("\n");

        for (Method method : someClass.getDeclaredMethods()) {
            result.append(renderMethod(method));
        }

        return result.toString();
    }

    protected ArrayList<List<Integer>> testMethod(ArrayList<? extends Boolean> arg1, double arg2) {
        return null;
    }

    public void VarArgsMethod(Integer... args) {

    }

    private static String renderMethod(Method method) {
        StringBuilder result = new StringBuilder();

        result.append(String.format("%s ", renderMethodModifiers(method)));
        result.append(String.format("%s ", method.getGenericReturnType().getTypeName()));

        result.append(String.format("%s (", method.getName()));
        result.append(String.format("%s) ", renderMethodParameters(method)));

        result.append("\n");
        return result.toString();
    }

    private static String renderMethodModifiers(Method method) {
        return Modifier.toString(method.getModifiers());
    }

    private static String renderMethodParameters(Method method) {
        ArrayList<String> args = new ArrayList<>();

        for (Parameter param : method.getParameters()) {
            args.add(renderParameter(param));
        }

        return String.join(", ", args);
    }

    private static String renderParameter(Parameter parameter) {
        if (parameter.isVarArgs()) {
            return String.format("%s... %s", parameter.getType().getTypeName(), parameter.getName());
        }
        return String.format("%s %s", parameter.getParameterizedType(), parameter.getName());
    }
}

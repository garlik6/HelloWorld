package ch6.dop;
import org.apache.commons.lang3.reflect.TypeUtils;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InjectSearcher {
    private static final ClassLocator classLocator = new ClassLocator();

    public static List<Class<?>> listClassesToInject(Object o, String field) throws NoSuchFieldException {
        List<Class<?>> list = new ArrayList<>();
        Class<?> currentClass = o.getClass();
        Field foundField = getField(currentClass, field);
        Type fieldType = foundField.getGenericType();
        for (Class<?> foundClass : classLocator.findAllClasses()) {
//            if (foundClass == IntIntGeneric.class)
            if (TypeUtils.isAssignable(foundClass, fieldType)) {
                list.add(foundClass);
            }
        }
        return list;
    }

    public static Field getField(Class<?> currentClass, String field) throws NoSuchFieldException {
        Field foundField = null;
        NoSuchFieldException exception = null;
        while (currentClass != Object.class && foundField == null) {
            try {
                foundField = currentClass.getDeclaredField(field);
            } catch (NoSuchFieldException e) {
                if (exception == null) {
                    exception = e;
                } else {
                    exception.addSuppressed(e);
                }
                currentClass = currentClass.getSuperclass();
            }
        }
        if (foundField == null && exception != null) {
            throw exception;
        }
        return foundField;
    }
}
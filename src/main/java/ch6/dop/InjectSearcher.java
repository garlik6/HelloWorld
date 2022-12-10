package ch6.dop;
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
                if (isAssignable(foundClass, fieldType)) {
                    list.add(foundClass);
                }
        }
        return list;
    }

    private static boolean isAssignable(Type type, Class<?> toClass) {
        if (type == null) {
            return toClass == null || !toClass.isPrimitive();
        }

        if (toClass == null) {
            return false;
        }
        if (toClass.equals(type)) {
            return true;
        }
        if (type instanceof Class<?>) {
            return toClass.isAssignableFrom((Class<?>) type);
        }
        if (type instanceof ParameterizedType) {
            return isAssignable(((ParameterizedType) type).getRawType(), toClass);
        }
        throw new IllegalStateException("found an unhandled type: " + type);
    }

    private static boolean isAssignable(Type type, ParameterizedType toParameterizedType,
                                        Map<TypeVariable<?>, Type> typeVarAssigns) {
        if (toParameterizedType.equals(type)) {
            return true;
        }
        Class<?> toClass = (Class<?>) toParameterizedType.getRawType();
        Map<TypeVariable<?>, Type> fromTypeVarAssigns = getTypeArguments(type, toClass, null);
        // null means the two types are not compatible
        if (fromTypeVarAssigns == null) {
            return false;
        }

        if (fromTypeVarAssigns.isEmpty()) {
            return true;
        }
        Map<TypeVariable<?>, Type> toTypeVarAssigns = getTypeArguments(toParameterizedType,
                toClass, typeVarAssigns);

        assert toTypeVarAssigns != null;
        for (TypeVariable<?> var : toTypeVarAssigns.keySet()) {
            Type toTypeArg = toTypeVarAssigns.get(var);
            Type fromTypeArg = fromTypeVarAssigns.get(var);

            if (fromTypeArg != null && toTypeArg != null
                    && !toTypeArg.equals(fromTypeArg)
                    && !(toTypeArg instanceof WildcardType && isAssignable(fromTypeArg, toTypeArg,
                    typeVarAssigns))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAssignable(Type type, Type toType) {
        return isAssignable(type, toType, null);
    }

    private static boolean isAssignable(Type type, Type toType,
                                        Map<TypeVariable<?>, Type> typeVarAssigns) {
        if (toType == null || toType instanceof Class<?>) {
            return isAssignable(type, (Class<?>) toType);
        }

        if (toType instanceof ParameterizedType) {
            return isAssignable(type, (ParameterizedType) toType, typeVarAssigns);
        }

        if (toType instanceof WildcardType) {
            return isAssignable(type, (WildcardType) toType, typeVarAssigns);
        }

        throw new IllegalStateException("found an unhandled type: " + toType);
    }

    private static boolean isAssignable(Type type, WildcardType toWildcardType,
                                        Map<TypeVariable<?>, Type> typeVarAssigns) {
        if (type == null) {
            return true;
        }

        if (toWildcardType == null) {
            return false;
        }

        if (toWildcardType.equals(type)) {
            return true;
        }

        Type[] toUpperBounds = toWildcardType.getUpperBounds();
        Type[] toLowerBounds = toWildcardType.getLowerBounds();


        for (Type toBound : toUpperBounds) {
            if (!isAssignable(type, toBound, typeVarAssigns)) {
                return false;
            }
        }
        for (Type toBound : toLowerBounds) {
            if (!isAssignable(toBound, type, typeVarAssigns)) {
                return false;
            }
        }
        return true;
    }

    private static Map<TypeVariable<?>, Type> getTypeArguments(Class<?> cls, Class<?> toClass,
                                                               Map<TypeVariable<?>, Type> subtypeVarAssigns) {
        if (!isAssignable(cls, toClass)) {
            return null;
        }
        HashMap<TypeVariable<?>, Type> typeVarAssigns = subtypeVarAssigns == null ? new HashMap<>()
                : new HashMap<>(subtypeVarAssigns);
        if (toClass.equals(cls)) {
            return typeVarAssigns;
        }
        return getTypeArguments(getClosestParentType(cls, toClass), toClass, typeVarAssigns);
    }

    private static Map<TypeVariable<?>, Type> getTypeArguments(
            ParameterizedType parameterizedType, Class<?> toClass,
            Map<TypeVariable<?>, Type> subtypeVarAssigns) {
        Class<?> cls = (Class<?>) parameterizedType.getRawType();
        if (!isAssignable(cls, toClass)) {
            return null;
        }
        Map<TypeVariable<?>, Type> typeVarAssigns;
        typeVarAssigns = subtypeVarAssigns == null ? new HashMap<>()
                : new HashMap<>(subtypeVarAssigns);
        Type[] typeArgs = parameterizedType.getActualTypeArguments();
        TypeVariable<?>[] typeParams = cls.getTypeParameters();
        for (int i = 0; i < typeParams.length; i++) {
            Type typeArg = typeArgs[i];
            typeVarAssigns.put(
                    typeParams[i],
                    typeArg
            );
        }
        if (toClass.equals(cls)) {
            return typeVarAssigns;
        }
        return getTypeArguments(getClosestParentType(cls, toClass), toClass, typeVarAssigns);
    }

    private static Map<TypeVariable<?>, Type> getTypeArguments(Type type, Class<?> toClass,
                                                               Map<TypeVariable<?>, Type> subtypeVarAssigns) {
        if (type instanceof Class<?>) {
            return getTypeArguments((Class<?>) type, toClass, subtypeVarAssigns);
        }
        if (type instanceof ParameterizedType) {
            return getTypeArguments((ParameterizedType) type, toClass, subtypeVarAssigns);
        }
        return null;
    }

    private static Type getClosestParentType(Class<?> cls, Class<?> superClass) {
        if (superClass.isInterface()) {
            Type[] interfaceTypes = cls.getGenericInterfaces();
            Type genericInterface = null;

            for (Type midType : interfaceTypes) {
                Class<?> midClass = null;

                if (midType instanceof ParameterizedType) {
                    midClass = (Class<?>) ((ParameterizedType) midType).getRawType();
                } else if (midType instanceof Class<?>) {
                    midClass = (Class<?>) midType;
                } else {
                    throw new IllegalStateException("Unexpected generic"
                            + " interface type found: " + midType);
                }
                boolean a1 = isAssignable(midClass, superClass);
                boolean a2 = isAssignable(genericInterface, (Type) midClass);
                if (isAssignable(midClass, superClass)
                        && isAssignable(genericInterface, (Type) midClass)) {
                    genericInterface = midType;
                }
            }
            if (genericInterface != null) {
                return genericInterface;
            }
        }
        return cls.getGenericSuperclass();
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
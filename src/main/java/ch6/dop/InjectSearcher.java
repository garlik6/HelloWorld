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
        // only a null type can be assigned to null type which
        // would have cause the previous to return true
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
            // only have to compare the raw type to the class
            return isAssignable(((ParameterizedType) type).getRawType(), toClass);
        }
        throw new IllegalStateException("found an unhandled type: " + type);
    }

    private static boolean isAssignable(Type type, ParameterizedType toParameterizedType,
                                        Map<TypeVariable<?>, Type> typeVarAssigns) {
        // all types are assignable to themselves
        if (toParameterizedType.equals(type)) {
            return true;
        }
        // get the target type's raw type
        Class<?> toClass = (Class<?>) toParameterizedType.getRawType();
        // get the subject type's type arguments
        // and supertype arguments up to and including the target class.
        Map<TypeVariable<?>, Type> fromTypeVarAssigns = getTypeArguments(type, toClass, null);
        // null means the two types are not compatible
        if (fromTypeVarAssigns == null) {
            return false;
        }
        // compatible types, but there's no type arguments. this is equivalent
        // to comparing Map< ?, ? > to Map, and raw types are always assignable
        // to parameterized types.
        if (fromTypeVarAssigns.isEmpty()) {
            return true;
        }
        // get the target type's type arguments
        Map<TypeVariable<?>, Type> toTypeVarAssigns = getTypeArguments(toParameterizedType,
                toClass, typeVarAssigns);

        // now to check each type argument
        assert toTypeVarAssigns != null;
        for (TypeVariable<?> var : toTypeVarAssigns.keySet()) {
            Type toTypeArg = toTypeVarAssigns.get(var);
            Type fromTypeArg = fromTypeVarAssigns.get(var);

            if (toTypeArg == null && fromTypeArg instanceof Class) {
                continue;
            }
            // parameters must either be absent from the subject type, within
            // the bounds of the wildcard type, or be an exact match to the
            // parameters of the target type.
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
        if (toType instanceof TypeVariable<?>) {
            return isAssignable(type, (TypeVariable<?>) toType, typeVarAssigns);
        }

        throw new IllegalStateException("found an unhandled type: " + toType);
    }

    private static boolean isAssignable(Type type, WildcardType toWildcardType,
                                        Map<TypeVariable<?>, Type> typeVarAssigns) {
        if (type == null) {
            return true;
        }

        // only a null type can be assigned to null type which
        // would have cause the previous to return true
        if (toWildcardType == null) {
            return false;
        }

        // all types are assignable to themselves
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
        // make sure they're assignable
        if (!isAssignable(cls, toClass)) {
            return null;
        }
        // create a copy of the incoming map, or an empty one if it's null
        HashMap<TypeVariable<?>, Type> typeVarAssigns = subtypeVarAssigns == null ? new HashMap<>()
                : new HashMap<>(subtypeVarAssigns);
        // has target class been reached?
        if (toClass.equals(cls)) {
            return typeVarAssigns;
        }
        // walk the inheritance hierarchy until the target class is reached
        return getTypeArguments(getClosestParentType(cls, toClass), toClass, typeVarAssigns);
    }

    private static Map<TypeVariable<?>, Type> getTypeArguments(
            ParameterizedType parameterizedType, Class<?> toClass,
            Map<TypeVariable<?>, Type> subtypeVarAssigns) {
        Class<?> cls = (Class<?>) parameterizedType.getRawType();
        // make sure they're assignable
        if (!isAssignable(cls, toClass)) {
            return null;
        }
        Map<TypeVariable<?>, Type> typeVarAssigns;
        typeVarAssigns = subtypeVarAssigns == null ? new HashMap<>()
                : new HashMap<>(subtypeVarAssigns);
        // get the subject parameterized type's arguments
        Type[] typeArgs = parameterizedType.getActualTypeArguments();
        // and get the corresponding type variables from the raw class
        TypeVariable<?>[] typeParams = cls.getTypeParameters();
        // map the arguments to their respective type variables
        for (int i = 0; i < typeParams.length; i++) {
            Type typeArg = typeArgs[i];
            typeVarAssigns.put(
                    typeParams[i],
                    typeArg
            );
        }
        if (toClass.equals(cls)) {
            // target class has been reached. Done.
            return typeVarAssigns;
        }
        // walk the inheritance hierarchy until the target class is reached
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
        // only look at the interfaces if the super class is also an interface
        if (superClass.isInterface()) {
            // get the generic interfaces of the subject class
            Type[] interfaceTypes = cls.getGenericInterfaces();
            // will hold the best generic interface match found
            Type genericInterface = null;

            // find the interface closest to the super class
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
                // check if this interface is further up the inheritance chain
                // than the previously found match
                boolean a1 = isAssignable(midClass, superClass);
                boolean a2 = isAssignable(genericInterface, (Type) midClass);
                if (isAssignable(midClass, superClass)
                        && isAssignable(genericInterface, (Type) midClass)) {
                    genericInterface = midType;
                }
            }
            // found a match?
            if (genericInterface != null) {
                return genericInterface;
            }
        }
        // none of the interfaces were descendants of the target class, so the
        // super class has to be one, instead
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

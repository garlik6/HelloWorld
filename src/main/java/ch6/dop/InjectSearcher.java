package ch6.dop;

import ch6.dop.container.Cont;
import ch6.dop.container.IntCont;
import ch6.dop.container.StringCont;
import org.apache.commons.lang3.reflect.TypeUtils;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InjectSearcher {
    private static final ClassLocator classLocator = new ClassLocator();

    public static List<Class<?>> listClassesToInject(Object o, String field) throws NoSuchFieldException, IllegalAccessException {
        List<Class<?>> list = new ArrayList<>();
        Field foundField = getField(o, field);
        Type fieldType = foundField.getGenericType();
        for (Class<?> foundClass : classLocator.findAllClasses()) {
            if (foundClass == IntCont.class || foundClass == Cont.class || foundClass == StringCont.class) {
                if (TypeUtils.isAssignable(foundClass, fieldType)) {
                    Type[] types = foundClass.getTypeParameters();
                    list.add(foundClass);
                }
            }
        }
        return list;
    }

    private static boolean isAssignable(final Type type, final Class<?> toClass) {
        if (toClass.equals(type)) {
            return true;
        }

        if (type instanceof Class<?>) {
            return ((Class<?>) type).isAssignableFrom(toClass);
        }

        if (type instanceof ParameterizedType) {
            // only have to compare the raw type to the class
            return isAssignable(((ParameterizedType) type).getRawType(), toClass);
        }
        if (type instanceof TypeVariable<?>) {
            // if any of the bounds are assignable to the class, then the
            // type is assignable to the class.
            for (final Type bound : ((TypeVariable<?>) type).getBounds()) {
                if (isAssignable(bound, toClass)) {
                    return true;
                }
            }
            return false;
        }
        // wildcard types are not assignable to a class (though one would think
        // "? super Object" would be assignable to Object)
        if (type instanceof WildcardType) {
            return false;
        }
        throw new IllegalStateException("found an unhandled type: " + type);
    }

    private static boolean isAssignable(final Type type, final ParameterizedType toParameterizedType,
                                        final Map<TypeVariable<?>, Type> typeVarAssigns) {
        // all types are assignable to themselves
        if (toParameterizedType.equals(type)) {
            return true;
        }
        // get the target type's raw type
        final Class<?> toClass = (Class<?>) toParameterizedType.getRawType();
        // get the subject type's type arguments including owner type arguments
        // and supertype arguments up to and including the target class.
        final Map<TypeVariable<?>, Type> fromTypeVarAssigns = getTypeArguments(type, toClass, null);
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

        // get the target type's type arguments including owner type arguments
        final Map<TypeVariable<?>, Type> toTypeVarAssigns = getTypeArguments(toParameterizedType,
                toClass, typeVarAssigns);

        // now to check each type argument
        for (final TypeVariable<?> var : toTypeVarAssigns.keySet()) {
            final Type toTypeArg = unrollVariableAssignments(var, toTypeVarAssigns);
            final Type fromTypeArg = unrollVariableAssignments(var, fromTypeVarAssigns);

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

    public static boolean isAssignable(final Type type, final Type toType) {
        return isAssignable(type, toType, null);
    }

    private static boolean isAssignable(final Type type, final Type toType,
                                        final Map<TypeVariable<?>, Type> typeVarAssigns) {
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


    private static boolean isAssignable(final Type type, final WildcardType toWildcardType,
                                        final Map<TypeVariable<?>, Type> typeVarAssigns) {
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

        final Type[] toUpperBounds = toWildcardType.getUpperBounds();
        final Type[] toLowerBounds = toWildcardType.getLowerBounds();

        if (type instanceof WildcardType) {
            final WildcardType wildcardType = (WildcardType) type;
            final Type[] upperBounds = wildcardType.getUpperBounds();
            final Type[] lowerBounds = wildcardType.getLowerBounds();

            for (Type toBound : toUpperBounds) {
                // if there are assignments for unresolved type variables,
                // now's the time to substitute them.

                // each upper bound of the subject type has to be assignable to
                // each
                // upper bound of the target type
                for (final Type bound : upperBounds) {
                    if (!isAssignable(bound, toBound, typeVarAssigns)) {
                        return false;
                    }
                }
            }

            for (Type toBound : toLowerBounds) {
                // if there are assignments for unresolved type variables,
                // now's the time to substitute them.

                // each lower bound of the target type has to be assignable to
                // each
                // lower bound of the subject type
                for (final Type bound : lowerBounds) {
                    if (!isAssignable(toBound, bound, typeVarAssigns)) {
                        return false;
                    }
                }
            }
            return true;
        }

        for (final Type toBound : toUpperBounds) {
            // if there are assignments for unresolved type variables,
            // now's the time to substitute them.
            if (!isAssignable(type, toBound, typeVarAssigns)) {
                return false;
            }
        }

        for (final Type toBound : toLowerBounds) {
            // if there are assignments for unresolved type variables,
            // now's the time to substitute them.
            if (!isAssignable(toBound, type, typeVarAssigns)) {
                return false;
            }
        }
        return true;
    }

//    private boolean isAssignable(Class<?> foundClass, Type toType) {
//        if (toType instanceof Class<?>) {
//            return isAssignable(foundClass, (Class<?>) toType);
//        }
//        if (toType instanceof ParameterizedType) {
//            return isAssignable(foundClass, (ParameterizedType) toType);
//        }
//        throw new IllegalStateException("found an unhandled type: " + toType);
//    }
//
//    private boolean isAssignable(Class<?> foundClass, ParameterizedType toType) {
//        Class<?> toClass = (Class<?>) toType.getRawType();
//        Map<TypeVariable<?>, Type> fromTypeVars = getTypeArguments(foundClass, toClass, null);
//        if (fromTypeVars == null) {
//            return false;
//        }
//        if (fromTypeVars.isEmpty()) {
//            return true;
//        }
//        Map<TypeVariable<?>, Type> toTypeVars = getTypeArguments(toType,toClass,null);
//
//        // now to check each type argument
//        for (final TypeVariable<?> var : toTypeVars.keySet()) {
//            final Type toTypeArg = unrollVariableAssignments(var, toTypeVars);
//            final Type fromTypeArg = unrollVariableAssignments(var, fromTypeVars);
//            if (toTypeArg == null && fromTypeArg instanceof Class) {
//                continue;
//            }
//            // parameters must either be absent from the subject type, within
//            // the bounds of the wildcard type, or be an exact match to the
//            // parameters of the target type.
//            if (fromTypeArg != null && toTypeArg != null
//                    && !toTypeArg.equals(fromTypeArg)
//                    && !(toTypeArg instanceof WildcardType && isAssignable(fromTypeArg,(WildcardType) toTypeArg
//                    ))) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private boolean isAssignable(Type type, WildcardType toWildcardType) {
//        if (type == null) {
//            return true;
//        }
//
//        // only a null type can be assigned to null type which
//        // would have cause the previous to return true
//        if (toWildcardType == null) {
//            return false;
//        }
//
//        // all types are assignable to themselves
//        if (toWildcardType.equals(type)) {
//            return true;
//        }
//
//        final Type[] toUpperBounds = toWildcardType.getUpperBounds();
//        final Type[] toLowerBounds = toWildcardType.getLowerBounds();
//
//        if (type instanceof WildcardType) {
//            final WildcardType wildcardType = (WildcardType) type;
//            final Type[] upperBounds = wildcardType.getUpperBounds();
//            final Type[] lowerBounds = wildcardType.getLowerBounds();
//
//            for (Type toBound : toUpperBounds) {
//                for (final Type bound : upperBounds) {
//                    if (!isAssignable(bound, toBound)) {
//                        return false;
//                    }
//                }
//            }
//            for (Type toBound : toLowerBounds) {
//                for (final Type bound : lowerBounds) {
//                    if (!isAssignable(toBound, bound)) {
//                        return false;
//                    }
//                }
//            }
//            return true;
//        }
//
//        for (final Type toBound : toUpperBounds) {
//            // if there are assignments for unresolved type variables,
//            // now's the time to substitute them.
//            if (!isAssignable(type, toBound)) {
//                return false;
//            }
//        }
//
//        for (final Type toBound : toLowerBounds) {
//            if (!isAssignable(toBound, type)) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private  boolean isAssignable(final Type type, final Type toType,
//                                        final Map<TypeVariable<?>, Type> typeVarAssigns) {
//
//        if (type instanceof Class<?>) {
//            return isAssignable((Class<?>)type,  toType);
//        }
//        if (toType == null || toType instanceof Class<?>) {
//            return isAssignable(type, (Class<?>) toType);
//        }
//        if (toType instanceof ParameterizedType) {
//            return isAssignable(type, (ParameterizedType) toType, typeVarAssigns);
//        }
//
//        if (toType instanceof GenericArrayType) {
//            return isAssignable(type, (GenericArrayType) toType, typeVarAssigns);
//        }
//
//        if (toType instanceof WildcardType) {
//            return isAssignable(type, (WildcardType) toType, typeVarAssigns);
//        }
//
//        if (toType instanceof TypeVariable<?>) {
//            return isAssignable(type, (TypeVariable<?>) toType, typeVarAssigns);
//        }
//
//        throw new IllegalStateException("found an unhandled type: " + toType);
//    }
//
//
//    private static Type unrollVariableAssignments(TypeVariable<?> typeVariable,
//                                                  final Map<TypeVariable<?>, Type> typeVarAssigns) {
//        Type result;
//        do {
//            result = typeVarAssigns.get(typeVariable);
//            if (result instanceof TypeVariable<?> && !result.equals(typeVariable)) {
//                typeVariable = (TypeVariable<?>) result;
//                continue;
//            }
//            break;
//        } while (true);
//        return result;
//    }
//
//
//    private Map<TypeVariable<?>, Type> getTypeArguments(Type type, Class<?> toClass, Map<TypeVariable<?>, Type> typeVariableTypeMap) {
//        if (type instanceof Class<?>) {
//            return getTypeArguments((Class<?>) type, toClass, typeVariableTypeMap);
//        }
//
//        if (type instanceof ParameterizedType) {
//            return getTypeArguments((ParameterizedType) type, toClass, typeVariableTypeMap);
//        }
//
//        return null;
//    }
//
//
//    private Map<TypeVariable<?>, Type> getTypeArguments(
//            ParameterizedType parameterizedType,
//            Class<?> toClass,
//            Map<TypeVariable<?>, Type> subtypeVarAssigns) {
//        Class<?> cls = (Class<?>) parameterizedType.getRawType();
//        if (!isAssignable(cls, toClass)) {
//            return null;
//        }
//        Map<TypeVariable<?>, Type> typeVarAssigns;
//        typeVarAssigns = subtypeVarAssigns == null ? new HashMap<>()
//                : new HashMap<>(subtypeVarAssigns);
//        Type[] typeArgs = parameterizedType.getActualTypeArguments();
//        TypeVariable<?>[] typeParams = cls.getTypeParameters();
//        for (int i = 0; i < typeParams.length; i++) {
//            final Type typeArg = typeArgs[i];
//            typeVarAssigns.put(
//                    typeParams[i],
//                    typeVarAssigns.getOrDefault(typeArg, typeArg)
//            );
//        }
//        if (toClass.equals(cls)) {
//            return typeVarAssigns;
//        }
//        return getTypeArguments(getClosestParentType(cls, toClass), toClass, typeVarAssigns);
//    }
//
//    private Map<TypeVariable<?>, Type> getTypeArguments(Class<?> cls, Class<?> toClass, Map<TypeVariable<?>, Type> typeVariableTypeMap) {
//        if (!isAssignable(cls, toClass)) {
//            return null;
//        }
//        HashMap<TypeVariable<?>, Type> typeVarType = new HashMap<>();
//        if (toClass.equals(cls)) {
//            return typeVarType;
//        }
//        return getTypeArguments(getClosestParentType(cls, toClass), toClass, typeVariableTypeMap);
//    }
//
//    private Type getClosestParentType(Class<?> cls, Class<?> toClass) {
//        return cls.getGenericSuperclass();
//    }
//
//
//    private boolean isAssignable(Class<?> foundClass, Class<?> toClass) {
//        return toClass.isAssignableFrom(foundClass);
//    }

    private static Map<TypeVariable<?>, Type> getTypeArguments(Class<?> cls, final Class<?> toClass,
                                                               final Map<TypeVariable<?>, Type> subtypeVarAssigns) {
        // make sure they're assignable
        if (!isAssignable(cls, toClass)) {
            return null;
        }
        // can't work with primitives
        if (cls.isPrimitive()) {
            // both classes are primitives?
            if (toClass.isPrimitive()) {
                // dealing with widening here. No type arguments to be
                // harvested with these two types.
                return new HashMap<>();
            }
        }
        // create a copy of the incoming map, or an empty one if it's null
        final HashMap<TypeVariable<?>, Type> typeVarAssigns = subtypeVarAssigns == null ? new HashMap<>()
                : new HashMap<>(subtypeVarAssigns);

        // has target class been reached?
        if (toClass.equals(cls)) {
            return typeVarAssigns;
        }

        // walk the inheritance hierarchy until the target class is reached
        return getTypeArguments(getClosestParentType(cls, toClass), toClass, typeVarAssigns);
    }
    private static Map<TypeVariable<?>, Type> getTypeArguments(
            final ParameterizedType parameterizedType, final Class<?> toClass,
            final Map<TypeVariable<?>, Type> subtypeVarAssigns) {
        final Class<?> cls = (Class<?>) parameterizedType.getRawType();

        // make sure they're assignable
        if (!isAssignable(cls, toClass)) {
            return null;
        }
        final Map<TypeVariable<?>, Type> typeVarAssigns;
        typeVarAssigns = new HashMap<>();
        // get the subject parameterized type's arguments
        final Type[] typeArgs = parameterizedType.getActualTypeArguments();
        // and get the corresponding type variables from the raw class
        final TypeVariable<?>[] typeParams = cls.getTypeParameters();

        // map the arguments to their respective type variables
        for (int i = 0; i < typeParams.length; i++) {
            final Type typeArg = typeArgs[i];
            typeVarAssigns.put(
                    typeParams[i],
                    typeVarAssigns.getOrDefault(typeArg, typeArg)
            );
        }
        if (toClass.equals(cls)) {
            // target class has been reached. Done.
            return typeVarAssigns;
        }
        // walk the inheritance hierarchy until the target class is reached
        return getTypeArguments(getClosestParentType(cls, toClass), toClass, typeVarAssigns);
    }


    private static Map<TypeVariable<?>, Type> getTypeArguments(final Type type, final Class<?> toClass,
                                                               final Map<TypeVariable<?>, Type> subtypeVarAssigns) {
        if (type instanceof Class<?>) {
            return getTypeArguments((Class<?>) type, toClass, subtypeVarAssigns);
        }

        if (type instanceof ParameterizedType) {
            return getTypeArguments((ParameterizedType) type, toClass, subtypeVarAssigns);
        }
        return null;
    }

    private static Type getClosestParentType(final Class<?> cls, final Class<?> superClass) {
        // only look at the interfaces if the super class is also an interface
        if (superClass.isInterface()) {
            // get the generic interfaces of the subject class
            final Type[] interfaceTypes = cls.getGenericInterfaces();
            // will hold the best generic interface match found
            Type genericInterface = null;

            // find the interface closest to the super class
            for (final Type midType : interfaceTypes) {
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

    public static Field getField(Object o, String field) throws NoSuchFieldException {
        Class<?> currentClass = o.getClass();
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

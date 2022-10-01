package ch04.n9;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class N9 {

    public static void main(String[] args) {
        B class2 = new B();
        A class1 = new A("3", class2);
        C class3 = new C();
        D class4 = new D("DDDD", class2);
        R[] arrayA = new R[]{class1, class1, class4};
        class3.setA(arrayA);
        class2.setC(class3);
        System.out.println(toString(class1));
    }

    private static final Set<Class<?>> SPECIAL_TYPES;

    static {
        SPECIAL_TYPES = new HashSet<>();
        SPECIAL_TYPES.add(Boolean.class);
        SPECIAL_TYPES.add(Character.class);
        SPECIAL_TYPES.add(Byte.class);
        SPECIAL_TYPES.add(Short.class);
        SPECIAL_TYPES.add(Integer.class);
        SPECIAL_TYPES.add(Long.class);
        SPECIAL_TYPES.add(Float.class);
        SPECIAL_TYPES.add(Double.class);
        SPECIAL_TYPES.add(Void.class);
        SPECIAL_TYPES.add(String.class);
    }

    public static boolean isSpecialType(Class<?> clazz) {
        return SPECIAL_TYPES.contains(clazz);
    }

    public static String toString(Object object) {
        return toStringg(object, new HashSet<>());
    }

    public static String toStringg(Object object, Set<Integer> IDs) {
        int id = System.identityHashCode(object);
        if (id == 0)
            return "access denied";
        if (IDs.contains(id))
            return "cyclic reference to @" + id;
        else
            IDs.add(id);
        if (object == null)
            return "";
        StringBuilder sb = new StringBuilder(object.getClass().getSimpleName()).append("={");
        String className = object.getClass().getSimpleName();

        if (object.getClass().isArray()) {
            for (Object o : (Object[]) object) {
                sb.append(toStringg(o, IDs)).append(",");
            }
        } else if (isSpecialType(object.getClass())) {
            sb.append(object);
        } else {
            try {
                for (Field f : object.getClass().getDeclaredFields()) {
                    if (sb.length() > className.length() + 3) {
                        sb.append(", ");
                    }

                    Object value = null;
                    if (f.trySetAccessible()) {
                        f.setAccessible(true);
                        value = f.get(object);
                    }
                    assert value != null;
                    sb.append(f.getType().getSimpleName()).append(' ').append(f.getName()).append('=').append(toStringg(value, IDs));
                }
            } catch (IllegalAccessException e) {
                System.out.println(e.getMessage());
            }
        }

        return sb.append('}').toString();
    }
}

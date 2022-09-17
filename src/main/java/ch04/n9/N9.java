package ch04.n9;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class N9 {

    public static void main(String[] args) {
        Class2 class2 = new Class2();
        Class1 class1 = new Class1("3", class2);
        class2.setA(class1);

        System.out.println(toString(class1));
    }
    private static final Set<Class<?>> BOX_TYPES;

    static {
        BOX_TYPES = new HashSet<>();
        BOX_TYPES.add(Boolean.class);
        BOX_TYPES.add(Character.class);
        BOX_TYPES.add(Byte.class);
        BOX_TYPES.add(Short.class);
        BOX_TYPES.add(Integer.class);
        BOX_TYPES.add(Long.class);
        BOX_TYPES.add(Float.class);
        BOX_TYPES.add(Double.class);
        BOX_TYPES.add(Void.class);
    }

    public static boolean isBoxType(Class<?> clazz) {
        return BOX_TYPES.contains(clazz);
    }


    public static String toString(Object object) {

        StringBuilder sb = new StringBuilder(object.getClass().getSimpleName()).append("={");
        String className = object.getClass().getSimpleName();

        if (object.getClass().isArray()) {
            sb.append(Arrays.toString((Object[]) object));
        } else if (isBoxType(object.getClass())) {
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
                    sb.append(f.getType().getSimpleName()).append(' ').append(f.getName()).append('=').append(toString(value));
                }
            } catch (IllegalAccessException e) {
                System.out.println(e.getMessage());
            }
        }

        return sb.append('}').toString();
    }
}

package ch6.dop;

import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ClassLocator {
    public Set<Class<?>> locatedClasses = new HashSet<>();

    public Set<Class<?>> findAllClasses() throws ClassLocationException {
        String dir = getSourseDir();
        if (dir.endsWith(".jar")) {
            throw new ClassLocationException("Unsupported application");
        }
        File file = new File(dir);
        if (!file.isDirectory()) {
            throw new ClassLocationException("Invalid directory");
        }
        try {
            for (File f : Objects.requireNonNull(file.listFiles())) {
                this.scanDir(f, "");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("some classes not found");
        }
        return locatedClasses;
    }

    private void scanDir(File file, String packageName) throws ClassNotFoundException {
        if (file.isDirectory()) {
            packageName += file.getName() + ".";
            for (File f : Objects.requireNonNull(file.listFiles())) {
                scanDir(f, packageName);
            }
        } else {
            if (!file.getName().endsWith(".class")) {
                return;
            }
            String className = packageName + file.getName().replace(".class", "");
            locatedClasses.add(Class.forName(className));
            System.out.println(className);
        }
    }

    private static String getSourseDir() {
        return ClassLocator.class.getProtectionDomain().getCodeSource().getLocation().getFile();
    }
}

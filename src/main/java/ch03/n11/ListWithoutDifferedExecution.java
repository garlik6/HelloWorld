package ch03.n11;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListWithoutDifferedExecution {
    enum mode {
        EXTENSION,
        AT_BEGINNING,
        AT_SOME_PLACE;
    }

    public static String[] list(File directory, String param, mode flag) {
        String[] names = directory.list();
        if ((names == null) || (param.equals(""))) {
            return names;
        }
        List<String> v = new ArrayList<>();
        for (String name : names) {
            if (accept(name, param, flag)) {
                v.add(name);
            }
        }
        return v.toArray(new String[0]);
    }

    private static boolean accept(String name, String param, mode flag) {
        switch (flag){
            case EXTENSION:
                return getExtension(name).equals(param);
            case AT_BEGINNING:
                return name.toLowerCase().startsWith(param.toLowerCase());
            case AT_SOME_PLACE:
                return name.toLowerCase().contains(param.toLowerCase());
            default:
                throw new IllegalStateException("Unexpected value: " + flag);
        }
    }

    private static String getExtension(String fileName){
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            return fileName.substring(i+1);
        }
        return "";
    }
}

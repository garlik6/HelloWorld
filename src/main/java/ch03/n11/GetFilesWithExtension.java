package ch03.n11;

import java.io.File;

public class GetFilesWithExtension {
    public static String[] getFileNamesWithExtension(File directory, String extension) {
        return directory.list((dir,x) -> getExtension(x).equals(extension));
    }

    private static String getExtension(String fileName){
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            return fileName.substring(i+1);
        }
        return "";
    }
}

package ch03.n11;

import java.io.File;

public class N11 {
    public static void main(String[] args) {
        var file = new File("C:\\Users\\grigo\\IdeaProjects\\com.bashev.mephi\\src\\main\\java\\ch02\\n9");
        String[] array = GetFilesWithExtension.getFileNamesWithExtension(file, "txt");
    }
}

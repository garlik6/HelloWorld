package ch03.n11;

import java.io.File;
import java.util.Arrays;

public class N11 {
    public static void main(String[] args) {
        var file = new File("C:\\Users\\grigo\\IdeaProjects\\com.bashev.mephi\\src\\main\\java\\ch02\\n9");
        String[] array = GetFiles.getFileNamesWithExtension(file, "java");
        System.out.println(Arrays.toString(array));
        array = GetFiles.getFileNamesWithBeginning(file, "Ga");
        System.out.println(Arrays.toString(array));
        array = GetFiles.getFileNamesWithSubString(file, "Ge");
        System.out.println(Arrays.toString(array));
    }
}

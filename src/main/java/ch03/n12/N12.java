package ch03.n12;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class N12 {
    public static void main(String[] args) {
        var file = new File("C:\\Users\\grigo\\IdeaProjects\\com.bashev.mephi\\src\\main\\java\\ch02\\n9");

        File[] files = file.listFiles();
        Comparator<File> comp = Comparator.comparing(File::isDirectory).reversed().thenComparing(File::getName);










        if (files != null) {
            System.out.println(Arrays.toString(files));
            Arrays.sort(files, comp);
            System.out.println(Arrays.toString(files));
            for (File f:
                 files) {
                System.out.println(f.getName());
            }
        }
    }
}

package ch03.n12;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class N12 {
    public static void main(String[] args) {
        var file = new File("C:\\Users\\grigo\\IdeaProjects\\com.bashev.mephi\\src\\main\\java\\ch02\\n9");
        File[] files = file.listFiles();
        Comparator<File> comp = Comparator.comparing( (File x) -> !x.isDirectory() ).thenComparing(File::getName);
        if (files != null) {
            Arrays.sort(files, comp);
            for (File f:
                 files) {
                System.out.println(f.getName());
            }
        }
    }
}

package ch03.n10;

import java.io.File;

public class N10 {
    public static void main(String[] args) {
        var directory  = new File("C:\\Users\\grigo\\IdeaProjects\\com.bashev.mephi\\src\\main\\java\\ch03");
        File[] array1 = DirectorySubDirectories.getSubDirectoriesLambda(directory);
        File[] array2 = DirectorySubDirectories.getSubDirectoriesMethodReference(directory);
        File[] array3 = DirectorySubDirectories.getSubDirectoriesAnonymous(directory);
    }
}

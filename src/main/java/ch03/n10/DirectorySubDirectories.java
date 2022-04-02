package ch03.n10;


import java.io.File;
import java.io.FileFilter;

public class DirectorySubDirectories {
    public static File[] getSubDirectoriesLambda(File directory){
            return directory.listFiles((x) -> x.isDirectory());
        }

    public static File[] getSubDirectoriesMethodReference(File directory){
        return directory.listFiles(File::isDirectory);
    }

    public static File[] getSubDirectoriesAnonymous(File directory){
        return directory.listFiles(new FileFilter (){
            @Override
            public boolean accept(File pathname) {
                return directory.isDirectory();
            }
        });
    }
    }


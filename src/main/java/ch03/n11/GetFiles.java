package ch03.n11;

import java.io.File;

public class GetFiles {
    public static String[] getFileNamesWithExtension(File directory, String extension) {
        return ListWithoutDifferedExecution.list(directory,extension, ListWithoutDifferedExecution.mode.EXTENSION);
    }

    public static String[] getFileNamesWithBeginning(File directory, String beginning) {
        return ListWithoutDifferedExecution.list(directory,beginning, ListWithoutDifferedExecution.mode.AT_BEGINNING);
    }
    public static String[] getFileNamesWithSubString(File directory, String substring) {
        return ListWithoutDifferedExecution.list(directory, substring, ListWithoutDifferedExecution.mode.AT_SOME_PLACE);
    }
}

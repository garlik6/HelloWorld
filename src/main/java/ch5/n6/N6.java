package ch5.n6;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class N6 {
    static String path = "file.txt";

//    public static void main(String[] args){
//        BufferedReader in = null;
//        try {
//            in = Files.newBufferedReader(Path.of(path), StandardCharsets.UTF_8);
////            Read from in
//        } catch (IOException ex) {
//            System.err.println("Caught IOException: ” + ex.getMessage()");
//        } finally {
//            if (in != null) {
//                try {
//                    in.close(); // Caution—might throw an exception
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
//    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader in = Files.newBufferedReader(Path.of(path), StandardCharsets.UTF_8)) {
            //            Read from in
        } catch (IOException ex) {
            System.err.println("Caught IOException: ” + ex.getMessage()");
        }
        // Caution—might throw an exception
    }

//    public static void main(String[] args) throws IOException {
//        BufferedReader in = null;
//        try {
//            try {
//                in = Files.newBufferedReader(Path.of(path), StandardCharsets.UTF_8);
//            } finally {
//                if (in != null) {
//                    in.close(); // Caution—might throw an exception
//                }
//            }
//        } catch (IOException ex) {
//            System.err.println("Caught IOException: ” + ex.getMessage()");
//        }
//    }
}


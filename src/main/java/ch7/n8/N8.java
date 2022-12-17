package ch7.n8;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class N8 {
    public static void main(String[] args) throws IOException {
        wordOccurrences(Path.of("src/main/java/ch7/n7/file.txt")).forEach((s, integers) -> System.out.println(s + "->" + integers));
    }

    public static Map<String, Set<Integer>> wordOccurrences(Path file) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            Map<String, Set<Integer>> map = new TreeMap<>();
            int lineNumber = 0;
            while (reader.ready()) {
                String line = reader.readLine();
                String[] strings = line.split("\\s+");
                for (String string : strings) {
                    Set<Integer> set = map.getOrDefault(string, new HashSet<>());
                    set.add(lineNumber);
                    map.put(string, set);
                }
                lineNumber++;
            }
            return map;
        }
    }
}

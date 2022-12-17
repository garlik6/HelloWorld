package ch7.n7_9;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;

public class N7 {
    public static void main(String[] args) throws IOException {
        wordOccurrences(Path.of("src/main/java/ch7/n7/file.txt")).forEach((a, b) -> System.out.println(a + " " + b));
    }

    public static Map<String, Integer> wordOccurrences(Path file) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            Map<String, Integer> map = new TreeMap<>();
            while (reader.ready()) {
                String line = reader.readLine();
                String[] strings = line.split("\\s+");
                for (String string : strings) {
                    map.merge(string, 1, Integer::sum);

                    if (map.containsKey(string)) {
                        map.put(string, map.get(string) + 1);
                    } else {
                        map.put(string, 1);
                    }

                    map.put(string, map.getOrDefault(string, 0) + 1);

                    map.putIfAbsent(string, 1);
                    map.put(string, map.get(string) + 1);

                    Integer i = map.get(string);
                    if (i == null) {
                        map.put(string,1);
                    } else {
                        map.put(string,i + 1);
                    }
                }
            }
            return map;
        }
    }
}

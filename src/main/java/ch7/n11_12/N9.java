package ch7.n11_12;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class N9 {
    public static  String sentence = "Addjflksfjlsdkfj ffffffffn fff jjjj kkkk llll sldkfjal;kdsjfl;aksjfd.";
    public static void main(String[] args) {
        shuffleWords(sentence);
        shuffleAllWords(sentence);
    }

    private static void shuffleWords(String sentence) {
        List<String> list = Arrays.stream(N9.sentence.split("\\s+")).collect(Collectors.toList());
        Collections.shuffle(list.subList(1,list.size() - 2));
        list.forEach(s -> System.out.print(s + " "));
    }

    private static void shuffleAllWords(String sentence) {
        List<String> list = Arrays.stream(sentence.split("\\s+")).collect(Collectors.toList());
        trim(list);
        Collections.shuffle(list);
        fix(list);
        list.forEach(s -> System.out.print(s + " "));
        System.out.println();
    }

    private static void fix(List<String> list) {
        String firstWord = list.get(0);
        String firstLetter = firstWord.substring(0,1).toUpperCase();
        firstWord = firstLetter + firstWord.substring(1);
        list.set(0,firstWord);
        String lastWord = list.get(list.size() - 1);
        list.set(list.size() - 1,lastWord + ".");
        System.out.println();
    }

    private static void trim(List<String> list) {
        list.replaceAll(String::toLowerCase);
        list.replaceAll(s -> s.replace(".",""));
    }
}

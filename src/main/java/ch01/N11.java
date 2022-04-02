package ch01;

public class N11 {
    public static boolean isASCII(int symbol) {
        return (symbol <= 127);
    }

    public static void main(String[] args) {

        String str = "☢⚒☢⚒☢1HeLLo1WoRLd1☢⚒☢⚒☢";
        int[] codePoints = str.codePoints().toArray();
        for (int codePoint : codePoints) {
            if (!isASCII(codePoint))
                System.out.printf("%c(U+%s)\n", (char) codePoint, Integer.toHexString(codePoint));
        }
    }
}

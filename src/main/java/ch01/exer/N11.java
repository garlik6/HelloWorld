package ch01.exer;

public class N11 {
    public static boolean isASCII (int symbol){
        boolean result = (symbol <= 127);
        return result;
    }
    
    public static void main(String[] args) {

        String str = "☢⚒☢⚒☢1HeLLo1WoRLd1☢⚒☢⚒☢";
        int[] codePoints = str.codePoints().toArray();
        for(int i = 0; i < codePoints.length; i++){
            if(!isASCII(codePoints[i]))
                System.out.printf("%c(U+%s)\n",(char)codePoints[i], Integer.toHexString(codePoints[i]));
        }
    }
}

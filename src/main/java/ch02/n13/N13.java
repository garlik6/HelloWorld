//package ch02.n13;
//import com.opencsv.CSVReader;
//import java.io.FileReader;
//
//public class N13 {
//    private static final String PATH = "src/main/resources/brothen.csv";
//
//    public static void main(String[] args) throws Exception {
//        CSVReader reader = new CSVReader(new FileReader(PATH));
//        String[] nextLine;
//        while ((nextLine = reader.readNext()) != null) {
//            for (String e : nextLine) {
//                System.out.format("%s ", e);
//            }
//        }
//    }

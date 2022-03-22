package ch01.exer;

public class N16 {

    public static double average(double first, double... values){
        double sum = first;
        for (double v : values) sum += v;
        return sum / (values.length+1);
    }
    public static void main(String[] args) {
        double[] scores = { 3, 4.5, 10, 0 };
        double avg = average(0.5, scores);
        System.out.println(avg);
    }
}

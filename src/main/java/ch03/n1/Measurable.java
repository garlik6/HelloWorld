package ch03.n1;

public interface Measurable {
    double getMeasure();
    static double average(Measurable[] objects){
        double sum = 0;
        int length = objects.length;
        for (Measurable item : objects) {
            sum += item.getMeasure();
        }
        return length == 0 ? 0 : sum/length;
    }

    static Measurable largest (Measurable[] objects){
        Measurable max = objects[0];
        for (Measurable item : objects) {
            if(max.getMeasure() < item.getMeasure())
                max = item;
        }
        return max;
    }
}
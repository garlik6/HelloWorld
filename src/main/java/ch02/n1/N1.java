package ch02.n1;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class N1 {
    public static void main(String[] args) {
        int month = 3;
        int year = 2022;
        LocalDate date = LocalDate.of(year, month, 1);


        System.out.println(" Sun Mon Tue Wed Thu Fri Sat ");
        DayOfWeek weekday = date.getDayOfWeek();
        int value = weekday.getValue(); // 1 = Sunday, ... 7 = Saturday
        if (value == 7){
            value = 1;
        }
        else {
            value++;
        }
        for (int i = 1; i < value; i++)
            System.out.print("    ");
        while (date.getMonthValue() == month) {
            System.out.printf("%4d", date.getDayOfMonth());
            date = date.plusDays(1);
            if (date.getDayOfWeek().getValue() == 7)
                System.out.println();
        }
        if (date.getDayOfWeek().getValue() != 7)
            System.out.println();
        System.out.println();
    }

}
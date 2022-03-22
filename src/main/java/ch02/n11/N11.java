package ch02.n11;

import java.time.DayOfWeek;
import static java.time.LocalDate.of;
import java.time.LocalDate;
import static java.lang.System.out;
public class N11 {

    public static void main(String[] args) {
        int month = 3;
        int year = 2022;
        LocalDate date = of(year, month, 1);


        out.println(" Sun Mon Tue Wed Thu Fri Sat ");
        DayOfWeek weekday = date.getDayOfWeek();
        int value = weekday.getValue(); // 1 = Sunday, ... 7 = Saturday
        if (value == 7){
            value = 1;
        }
        else {
            value++;
        }
        for (int i = 1; i < value; i++)
            out.print("    ");
        while (date.getMonthValue() == month) {
            out.printf("%4d", date.getDayOfMonth());
            date = date.plusDays(1);
            if (date.getDayOfWeek().getValue() == 7)
                out.println();
        }
        if (date.getDayOfWeek().getValue() != 7)
            out.println();
        out.println();
    }

}

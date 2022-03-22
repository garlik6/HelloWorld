package ch02.n9;

public class N9 {
    public static void main(String[] args) {
        Car car = new Car(10,1,20);
        car.DriveByNumberOfMiles(6);
        car.addFuel(10);
        car.DriveByNumberOfMiles(10);
    }
}

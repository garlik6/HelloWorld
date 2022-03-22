package ch02.n9;

public class N9 {
    public static void main(String[] args) {
        Car car = new Car(10,1,20, 400, 400);
        car.DriveByNumberOfMilesX(6);
        car.addFuel(10);
        car.DriveByNumberOfMilesX(10);
        car.DriveByNumberOfMilesY(-10);
    }
}

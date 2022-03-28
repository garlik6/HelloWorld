package ch02.n9;

public class Car {
    private double x;
    private double y;

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }

    private double gasInTank;
    private final double gallonsPerMile;//gallons per mile
    private final double TankCapacity;
    private final double xBoundary;
    private final double yBoundary;

    public double getGallonsPerMile() {
        return gallonsPerMile;
    }

    public double getGasInTank() {
        return gasInTank;
    }

    public double getTankCapacity() {
        return TankCapacity;
    }

    public Car(double gasInTank, double fuelEfficiency, double tankCapacity, double xBoundary, double yBoundary) {
        this.xBoundary = xBoundary;
        this.yBoundary = yBoundary;
        this.x = xBoundary/2;
        this.y = yBoundary/2;
        if (gasInTank < 0 || fuelEfficiency < 0 || tankCapacity < 0 )
            throw new IllegalArgumentException("cant take or process negative parameters");
        this.gasInTank = gasInTank;
        this.gallonsPerMile = fuelEfficiency;
        this.TankCapacity = tankCapacity;
    }
    private boolean checkEnoughFuel(double gallons){
        return gasInTank >= gallons;
    }

    public void DriveByNumberOfMilesX(double miles){
        if(x + miles > xBoundary || x +miles <0){
            return;
            }
        double EstimateOfFuelConsumed = Math.abs(miles*gallonsPerMile);
        if(checkEnoughFuel(EstimateOfFuelConsumed)) {
            gasInTank -= EstimateOfFuelConsumed;
            x += miles;
        }
        else {
            gasInTank = 0;
            x += (gasInTank/gallonsPerMile);
        }
    }

    public void DriveByNumberOfMilesY(double miles){
        if(y + miles > yBoundary || y + miles <0){
            return;
        }
        double EstimateOfFuelConsumed = Math.abs(miles*gallonsPerMile);
        if(checkEnoughFuel(EstimateOfFuelConsumed)) {
            gasInTank -= EstimateOfFuelConsumed;
            y += miles;
        }
        else {
            gasInTank = 0;
            y += (gasInTank/gallonsPerMile);
        }
    }

    public void addFuel(double gallons){
        if (gallons + gasInTank > TankCapacity){
            throw new RuntimeException("exceeding tank capacity");
        }
        if (gallons < 0){
            throw new IllegalArgumentException("can not take fuel out of the tank");
        }
        gasInTank += gallons;
    }
}

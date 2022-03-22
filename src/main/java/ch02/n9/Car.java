package ch02.n9;

public class Car {
    private double coordinate = 0;
    private double gasInTank;
    private final double gallonsPerMile;//gallons per mile
    private final double TankCapacity;

    public double getCoordinate() {
        return coordinate;
    }

    public double getGallonsPerMile() {
        return gallonsPerMile;
    }

    public double getGasInTank() {
        return gasInTank;
    }

    public double getTankCapacity() {
        return TankCapacity;
    }

    public Car(double gasInTank, double fuelEfficiency, double tankCapacity) {
        if (gasInTank < 0 || fuelEfficiency < 0 || tankCapacity < 0 )
            throw new IllegalArgumentException("cant take or process negative parameters");
        this.gasInTank = gasInTank;
        this.gallonsPerMile = fuelEfficiency;
        this.TankCapacity = tankCapacity;
    }
    private boolean checkEnoughFuel(double gallons){
        if(gasInTank >= gallons)
            return true;
        else
            return false;
    }
    public void DriveByNumberOfMiles(double miles){
        if(miles < 0)
        {
            throw new IllegalArgumentException("can not drive negative number of miles");
        }
        double EstimateOfFuelConsumed = miles*gallonsPerMile;
        if(checkEnoughFuel(EstimateOfFuelConsumed)) {
            gasInTank -= EstimateOfFuelConsumed;
            coordinate += miles;
        }
        else {
            gasInTank = 0;
            coordinate += (gasInTank/gallonsPerMile);
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

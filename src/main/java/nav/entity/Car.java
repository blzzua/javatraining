package nav.entity;

public class Car {

    //Заводские х-стики
    private final double acceleration;
    private final double deceleration;
    private final double maxVelocity;
    private final double volumeGasTankMax;
    private final double gasConsumptionPerCycle;

    //Изменяемые при работе
    private boolean powerOn;
    private double velocity;
    private double volumeGasTankCur;
    private double gasQuality ;
    private double distance;
    private Driver driver;


    public Car(int acceleration,
               int deceleration,
               int maxVelocity,
               int volumeGasTankMax,
               int gasConsumptionPerCycle) {
        this.acceleration = acceleration;
        this.deceleration = deceleration;
        this.maxVelocity = maxVelocity;
        this.volumeGasTankMax = volumeGasTankMax;
        this.gasConsumptionPerCycle = gasConsumptionPerCycle;
    }

    public void setDriver(Driver driver) {
        if (driver != null && this.velocity == 0) {
            this.driver = driver;
        }
    }

    public void unsetDriver() {
        if (this.driver != null && this.velocity == 0) {
            this.driver = null;
        }
    }

    public Driver getDriver() {
        return driver;
    }

    public void perIteration() {
        if (this.volumeGasTankCur == 0) {
            this.powerOn = false;
        }

        if (this.powerOn) {
            this.volumeGasTankCur--;
        } else {
            dec();
        }
        this.distance += this.getVelocity();
    }

    public void acc() {
        acc(1.0d);
    }
    public void acc(double coef) {
        if (this.powerOn && this.velocity != this.maxVelocity) {
            double factAccMax = this.maxVelocity - this.velocity;
            double curAcc = this.acceleration * this.gasQuality * preprocessCoef(coef);
            double factAcc = curAcc > factAccMax
                    ? factAccMax
                    : curAcc;
            this.velocity += factAcc;
        }
    }

    public void dec() {
        if (this.velocity >= 0) {
            this.velocity = this.deceleration > this.velocity
                    ? 0
                    : this.velocity - this.deceleration;
        }
    }

    public boolean powerSwitch() {
        this.powerOn = driver != null && volumeGasTankCur > 0 && !this.powerOn;
        return this.powerOn;
    }
    public double refuel() {
        return refuel(this.volumeGasTankMax, 1.0);
    }

    public double refuel(double volumeGas) {
        return refuel(volumeGas, 1.0);
    }
    public double refuel(double volumeGas, double gasQuality) {
        double gasFact = 0;
        double gasQualityFact = 0;

        if (volumeGas > 0 &&
                gasQuality > 0 &&
                this.velocity == 0 &&
                !this.powerOn &&
                this.volumeGasTankMax != this.volumeGasTankCur) {
            double volumeAvailableLeft = this.volumeGasTankMax - this.volumeGasTankCur;
            gasFact = volumeAvailableLeft > volumeGas ? volumeGas : volumeAvailableLeft;
            gasQualityFact = ((this.volumeGasTankCur * this.gasQuality)  + ( gasFact * gasQuality )) / (this.volumeGasTankCur + gasFact);
            this.volumeGasTankCur += gasFact;
            this.gasQuality = gasQualityFact;
        }

        return gasFact;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public double getDeceleration() {
        return deceleration;
    }

    public double getMaxVelocity() {
        return maxVelocity;
    }

    public double getVolumeGasTankMax() {
        return volumeGasTankMax;
    }

    public double getGasConsumptionPerCycle() {
        return gasConsumptionPerCycle;
    }

    public double getVelocity() {
        return velocity;
    }

    public double getVolumeGasTankCur() {
        return volumeGasTankCur;
    }

    public double getGasQuality() {
        return gasQuality;
    }

    private static double preprocessCoef(double coef){
        double result = 0;
        if (coef > 0) {
            result = coef > 1.0 ? 1.0 : coef;
        }
        return result;
    }
    public double getDistance() {
        return distance;
    }

}


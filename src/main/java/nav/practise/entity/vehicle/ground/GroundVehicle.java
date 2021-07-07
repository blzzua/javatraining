package nav.practise.entity.vehicle.ground;

import nav.practise.entity.Driver;
import nav.practise.entity.GasQuality;
import nav.practise.entity.scheduling.Iterating;
import nav.practise.entity.vehicle.Vehicle;

public class GroundVehicle implements Vehicle, Iterating {

    private final double acceleration;
    private final double deceleration;
    private final double maxVelocity;
    private final double volumeGasTankMax;
    private final double gasConsumptionPerCycle;

    private double distance;
    private boolean powerOn;
    private double velocity;
    private double volumeGasTankCur;
    private double gasQuality;
    private Driver driver;

    public GroundVehicle(int acceleration,
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

    @Override
    public void setDriver(Driver driver) {
        if (driver != null && this.velocity == 0) {
            this.driver = driver;
        }
    }

    @Override
    public void unsetDriver() {
        if (this.driver != null && this.velocity == 0) {
            this.driver = null;
        }
    }

    @Override
    public void perIteration() {
        if (this.powerOn) {
            if (this.volumeGasTankCur < this.gasConsumptionPerCycle) {
                this.distance += this.velocity * this.volumeGasTankCur / this.gasConsumptionPerCycle;
                this.volumeGasTankCur = 0;
                this.powerOn = false;
            } else {
                this.distance += this.velocity;
                this.volumeGasTankCur -= this.gasConsumptionPerCycle;
            }
        } else {
            dec();
        }
    }

    @Override
    public void acc() {
        acc(1.0d);
    }

    @Override
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

    @Override
    public void dec() {
        dec(1.0);
    }

    @Override
    public void dec(double coef) {
        if (this.velocity >= 0) {
            double factDec = this.deceleration * preprocessCoef(coef);
            this.velocity = factDec > this.velocity
                    ? 0
                    : this.velocity - factDec;
        }
    }

    @Override
    public boolean powerSwitch() {
        this.powerOn = driver != null && volumeGasTankCur > 0 && !this.powerOn;
        return this.powerOn;
    }
    @Override
    public double refuelIn(double volumeGas, double gasQuality) {
        double gasFact = 0;

        if (volumeGas > 0 &&
                this.velocity == 0 &&
                !this.powerOn &&
                this.volumeGasTankMax != this.volumeGasTankCur) {
            double volumeAvailableLeft = this.volumeGasTankMax - this.volumeGasTankCur;
            gasFact = volumeAvailableLeft > volumeGas ? volumeGas : volumeAvailableLeft;
            this.gasQuality = (this.volumeGasTankCur * this.gasQuality + gasFact * gasQuality) / (this.volumeGasTankCur + gasFact);
            this.volumeGasTankCur += gasFact;
        }

        return gasFact;
    }

    @Override
    public double refuelIn(double volumeGas, GasQuality gasQuality) {
        double gasFact = 0;

        if (volumeGas > 0 &&
                this.velocity == 0 &&
                !this.powerOn &&
                this.volumeGasTankMax != this.volumeGasTankCur) {
            double volumeAvailableLeft = this.volumeGasTankMax - this.volumeGasTankCur;
            gasFact = volumeAvailableLeft > volumeGas ? volumeGas : volumeAvailableLeft;
            this.gasQuality = (this.volumeGasTankCur * this.gasQuality + gasFact * gasQuality.getAccCoef()) / (this.volumeGasTankCur + gasFact);
            this.volumeGasTankCur += gasFact;
        }

        return gasFact;
    }

    @Override
    public double getDistance() {
        return distance;
    }

    @Override
    public double getAcceleration() {
        return acceleration;
    }

    @Override
    public double getDeceleration() {
        return deceleration;
    }

    @Override
    public double getMaxVelocity() {
        return maxVelocity;
    }

    @Override
    public double getVolumeGasTankMax() {
        return volumeGasTankMax;
    }

    @Override
    public double getGasConsumptionPerCycle() {
        return gasConsumptionPerCycle;
    }

    @Override
    public double getVelocity() {
        return velocity;
    }

    @Override
    public double getVolumeGasTankCur() {
        return volumeGasTankCur;
    }

    @Override
    public double getGasQuality() {
        return gasQuality;
    }

    @Override
    public boolean isPowerOn() {
        return powerOn;
    }

    @Override
    public Driver getDriver() {
        return driver;
    }

    @Override
    public String toString() {
        return "Velocity: " + this.velocity +
                ", gas left: " + this.volumeGasTankCur +
                ", gas quality " + this.gasQuality +
                ", with driver " + (this.driver != null) +
                ", power " + (this.powerOn ? "on" : "off") +
                ", total distance: " + this.distance;
    }

    private static double preprocessCoef(double coef) {
        double result = 0;

        if (coef > 0) {
            result = coef > 1.0 ? 1.0 : coef;
        }

        return result;
    }
}

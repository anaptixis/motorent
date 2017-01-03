package application;

import java.util.ArrayList;

/**
 * Created by panos on 3/1/2017.
 * General Abstract class for vehicles
 * Inherit only
 */
public abstract class Vehicle {
    String plate;                                       //vehicle plates
    String totalRentDays;                               //how many days it has been rented
    double pricePerDay;                                 //current price per day
    double buyCost;                                     //initial price
    ArrayList<Cost> costs = new ArrayList<Cost>();      //vehicle 's functional costs


    public Vehicle(String plate, String totalRentDays, double pricePerDay, double buyCost, ArrayList<Cost> costs) {
        this.plate = plate;
        this.totalRentDays = totalRentDays;
        this.pricePerDay = pricePerDay;
        this.buyCost = buyCost;
        this.costs = costs;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getTotalRentDays() {
        return totalRentDays;
    }

    public void setTotalRentDays(String totalRentDays) {
        this.totalRentDays = totalRentDays;
    }

    public ArrayList<Cost> getCosts() {
        return costs;
    }

    public void setCosts(ArrayList<Cost> costs) {
        this.costs = costs;
    }

    public double getBuyCost() {
        return buyCost;
    }

    public void setBuyCost(double buyCost) {
        this.buyCost = buyCost;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
}

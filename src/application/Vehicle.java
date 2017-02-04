package application;

import java.util.ArrayList;

/**
 * Created by panos on 3/1/2017.
 * General Abstract class for vehicles
 * Inherit only
 */
public abstract class Vehicle {
    String plate;                                       //vehicle plates
    int totalRentDays;                               //how many days it has been rented
    double pricePerDay;                                 //current price per day
    double buyCost;                                     //initial price
    ArrayList<Cost> costs = new ArrayList<Cost>();      //vehicle 's functional costs
    ArrayList<Rental> rentals=new ArrayList<Rental>();

    public Vehicle(String plate, int totalRentDays, double pricePerDay, double buyCost, ArrayList<Cost> costs) {
        this.plate = plate;
        this.totalRentDays = totalRentDays;
        this.pricePerDay = pricePerDay;
        this.buyCost = buyCost;
        this.costs = costs;
    }
    
    public Vehicle(String plate)
    {
    	this.plate=plate;
    	this.totalRentDays=0;
    	this.costs=new ArrayList<Cost>();
    	this.rentals=new ArrayList<Rental>();
    }
    
    public void addNewRental(Rental r)
    {
    	rentals.add(r);
    }
    
    public void addNewCost (Cost c)
    {
    	costs.add(c);
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public int getTotalRentDays() {
        return totalRentDays;
    }

    public void setTotalRentDays(int totalRentDays) {
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
    
    public abstract double calculateTotalCost();
    public abstract double calculateServiceCost();
    public abstract double calculateTotalGain();
    
    public double calculateTotalRevenue()
    {
    	return (calculateTotalCost()-calculateTotalGain());
    }
    
    //Checks if the total revenue is higher than zero
    // >0 the vehicle returns gain -> should be green
    // <0 the vehicle returns loss -> should be red
    // =0 the vehicle returns even -> should be orange
    public String doesItWorthIt()
    {
    	if(calculateTotalRevenue()>0)
    	{
    		return "gain";
    	}
    	else if(calculateTotalRevenue()<0)
    	{
    		return "loss";
    	}
    	else
    	{
    		return "even";
    	}
    }
}

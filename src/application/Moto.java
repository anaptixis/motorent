package application;

import java.util.ArrayList;

/**
 * Created by panos on 3/1/2017.
 * Represents a MotorBike
 */
public class Moto extends Vehicle {

    public Moto(String plate, int totalRentDays, double pricePerDay, double buyCost, ArrayList<Cost> costs) {
        super(plate, totalRentDays, pricePerDay, buyCost, costs);
    }
    
    public Moto(String plate)
    {
    	super(plate);
    }

	@Override
	//Calculate total cost using total service cost and buy cost
	public double calculateTotalCost() {
		// TODO Auto-generated method stub
		return (calculateServiceCost()+this.buyCost);
	}
		

	@Override
	//Calculate all service costs
	public double calculateServiceCost() {
		double totalCost=0;
		
		//Check if there are any costs
		if(this.costs!=null)
		{
			//Cost sum
			for (Cost c: costs)
			{
				totalCost+=c.price;
			}
		}
		return totalCost;
	}

	@Override
	//Calculate total gain from all rentals
	public double calculateTotalGain() {
		
		double totalGain=0;
		
		//Check if there are rentals
		if(this.rentals!=null)
		{
			//Sum the revenue
			for(Rental r: rentals)
			{
				totalGain+=r.getTotalRentGain();
			}
		}
		// TODO Auto-generated method stub
		return totalGain;
	}


}

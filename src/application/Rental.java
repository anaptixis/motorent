package application;

public class Rental {
	
	int rentDays;
	double pricePerDay;
	
	public Rental(int rentDays,double pricePerDay)
	{
		this.rentDays=rentDays;
		this.pricePerDay=pricePerDay;
	}
	
	public double getTotalRentGain()
	{
		return (rentDays*pricePerDay);
	}

	public int getRentDays() {
		return rentDays;
	}

	public void setRentDays(int rentDays) {
		this.rentDays = rentDays;
	}

	public double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}
	
	

}

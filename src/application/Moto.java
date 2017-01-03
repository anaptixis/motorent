package application;

import java.util.ArrayList;

/**
 * Created by panos on 3/1/2017.
 * Represents a MotorBike
 */
public class Moto extends Vehicle {

    public Moto(String plate, String totalRentDays, double pricePerDay, double buyCost, ArrayList<Cost> costs) {
        super(plate, totalRentDays, pricePerDay, buyCost, costs);
    }


}

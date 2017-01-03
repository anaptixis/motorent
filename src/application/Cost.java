package application;

/**
 * Created by panos on 3/1/2017.
 * Money spent on a vehicle
 */
public class Cost {

    String description;
    double price;

    public Cost(String description, double price) {
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

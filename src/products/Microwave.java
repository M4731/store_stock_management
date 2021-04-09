package products;

import categories.Category;
import distributors.Distributor;

public class Microwave extends Product
{
    private int weight;
    private int power;
    private int energy;
    private int warranty;
    private String colour;

    public Microwave(String denumire, double price, Category category, Distributor distributor, int weight, int power, int energy, int warranty, String colour) {
        super(denumire, price, category, distributor);
        this.weight = weight;
        this.power = power;
        this.energy = energy;
        this.warranty = warranty;
        this.colour = colour;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}

package products;

import categories.Category;
import distributors.Distributor;

public class Fridge extends Product
{
    private int weight;
    private int volume;
    private int energy;
    private int warranty;
    private String colour;

    static final String className = "fridge";

    public Fridge(String denumire, double price, Category category, Distributor distributor, int weight, int volume, int energy, int warranty, String colour) {
        super(denumire, price, category, distributor);
        this.weight = weight;
        this.volume = volume;
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

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
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

package products;

import categories.Category;
import distributors.Distributor;

public abstract class Product
{
    protected int ID;
    protected String denumire;
    protected double price;
    protected double discount;

    protected Category category;
    protected Distributor distributor;

    static int increment = 0;

    public Product(String denumire, double price, Category category, Distributor distributor) {
        this.denumire = denumire;
        this.price = price;
        this.discount = 0;
        this.category = category;
        this.distributor = distributor;

        category.addProduct(this);
        distributor.addProduct(this);

        increment ++;
        this.ID = increment;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Distributor getDistributor() {
        return distributor;
    }

    public void setDistributor(Distributor distributor) {
        this.distributor = distributor;
    }

    public int getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "ID=" + ID +
                ", denumire='" + denumire + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", category=" + category.getNume() +
                ", distributor=" + distributor.getNume() +", ";
    }
}

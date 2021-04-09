package products;

import categories.Category;
import distributors.Distributor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Dairy extends Product
{
    private int quantity;
    private String fromAnimal;
    private LocalDateTime expire;
    private double fat;

    public Dairy(String denumire, double price, Category category, Distributor distributor, int quantity, String fromAnimal, LocalDateTime expire, double fat) {
        super(denumire, price, category, distributor);
        this.quantity = quantity;
        this.fromAnimal = fromAnimal;
        this.expire = expire;
        this.fat = fat;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getFromAnimal() {
        return fromAnimal;
    }

    public void setFromAnimal(String fromAnimal) {
        this.fromAnimal = fromAnimal;
    }

    public LocalDateTime getExpire() {
        return expire;
    }

    public void setExpire(LocalDateTime expire) {
        this.expire = expire;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    @Override
    public String toString() {
        return super.toString()+
                "quantity=" + quantity +
                ", fromAnimal='" + fromAnimal + '\'' +
                ", expire=" + expire.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")) +
                ", fat=" + fat;
    }
}

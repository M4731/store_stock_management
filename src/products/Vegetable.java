package products;

import categories.Category;
import distributors.Distributor;

import java.time.LocalDateTime;

public class Vegetable extends Product
{
    private int quantity;
    private String type;
    private LocalDateTime expire;
    private String origin;

    static final String className = "vegetable";

    public Vegetable(String denumire, double price, Category category, Distributor distributor, int quantity, String type, LocalDateTime expire, String origin) {
        super(denumire, price, category, distributor);
        this.quantity = quantity;
        this.type = type;
        this.expire = expire;
        this.origin = origin;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getExpire() {
        return expire;
    }

    public void setExpire(LocalDateTime expire) {
        this.expire = expire;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        return super.toString()+
                "quantity=" + quantity +
                ", type='" + type + '\'' +
                ", expire=" + expire +
                ", origin='" + origin + '\'';
    }

}

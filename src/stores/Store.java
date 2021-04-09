package stores;

import products.Product;

import java.util.*;

public class Store
{
    private int ID;
    private String location;
    private String name;
    private Set<Product> products = new HashSet<>();

    static int increment = 0;

    public Store(String location, String name) {
        this.location = location;
        this.name = name;

        increment ++;
        this.ID = increment;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public int getID() {
        return ID;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (var i : products)
        {
            out.append(i.getDenumire()).append(" ");
        }
        return "ID=" + ID +
                ", location='" + location + '\'' +
                ", name='" + name + '\'' +
                ", products= { " + out+ " }";
    }

    public void addProduct(Product product)
    {
        products.add(product);
    }
}

package stores;

import products.Product;

import java.util.*;

public class Store implements Comparable<Store>
{
    private int ID;
    private String location;
    private String name;
    private Set<Product> products = new TreeSet<>();

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

    @Override
    public int compareTo(Store o) {
        return(this.name.compareTo(o.name));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return ID == store.ID && location.equals(store.location) && name.equals(store.name) && products.equals(store.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, location, name, products);
    }

    public void removeProduct(Product product)
    {
        this.products.remove(product);
    }
}


package distributors;

import products.Product;

import java.util.ArrayList;

public class Distributor
{
    private int ID;
    private String nume;
    private String location;
    private ArrayList<Product> productList = new ArrayList<>();

    static int increment = 0;

    public Distributor(String nume, String location) {
        this.nume = nume;
        this.location = location;

        increment ++;
        this.ID = increment;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public int getID() {
        return ID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (var i : productList)
        {
            out.append(i.getDenumire()).append(" ");
        }
        return  "ID=" + ID +
                ", nume='" + nume + '\'' +
                ", location='" + location + '\'' +
                ", productList= { " +out+"}";
    }

    public void addProduct(Product p)
    {
        this.productList.add(p);
    }
}



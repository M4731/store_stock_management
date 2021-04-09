package categories;

import products.Product;
import java.util.ArrayList;

public class Category
{
    private int ID;
    private String nume;
    private ArrayList<Product> productList = new ArrayList<>();

    static int increment = 0;

    public Category(String nume) {
        this.nume = nume;

        increment ++;
        this.ID = increment;
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

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (var i : productList)
        {
            out.append(i.getDenumire()).append(" ");
        }
        return "ID=" + ID +
                ", nume='" + nume + '\'' +
                ", productList= { " +out+"}";
    }

    public void addProduct(Product p)
    {
        this.productList.add(p);
    }
}

package main;

import categories.Category;
import distributors.Distributor;
import stores.Store;

import java.util.ArrayList;
import java.util.Scanner;

public class Service
{
    private ArrayList<Store> stores = new ArrayList<>();
    private ArrayList<Category> categories = new ArrayList<>();
    private ArrayList<Distributor> distributors = new ArrayList<>();

    private static Service instance = null;
    private Service(){}

    public static Service getInstance()
    {
        if(instance == null)
        {
            instance = new Service();
        }
        return instance;
    }

    public ArrayList<Store> getStores() {
        return stores;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public ArrayList<Distributor> getDistributors() {
        return distributors;
    }

    public void addCategory()
    {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.print("Category name : ");
        String categoryName = scanner.next();
        Category category = new Category(categoryName);
        categories.add(category);
    }

    public void showCategories()
    {
        for(Category i:categories)
        {
            System.out.println(i.toString());
        }
    }
}

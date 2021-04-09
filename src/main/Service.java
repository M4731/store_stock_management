package main;

import categories.Category;
import distributors.Distributor;
import products.Dairy;
import products.Fridge;
import products.Microwave;
import products.Vegetable;
import stores.Store;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;
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

    Category categoryChoice(String categoryString)
    {
        for(Category c:categories)
        {
            if(c.getNume().equals(categoryString))
            {
                return c;
            }
        }
        return new Category("");
    }

    Distributor distributorChoice(String distributorString)
    {
        for(Distributor d:distributors)
        {
            if(d.getNume().equals(distributorString))
            {
                return d;
            }
        }
        return new Distributor("", "");
    }

    public void addCategory()
    {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.print("Category name : ");
        String categoryName = scanner.next();
        Category category = new Category(categoryName);
        categories.add(category);
    }

    public void addCategoryFromCode(Category c)
    {
        categories.add(c);
    }

    public void showCategories()
    {
        for(Category i:categories)
        {
            System.out.println(i.toString());
        }
    }

    public void addDistributor()
    {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.print("Distributor's name : ");
        String distributorName = scanner.next();
        System.out.print("Where is the distributor located? ");
        String distributorLocation = scanner.next();
        Distributor distributor =  new Distributor(distributorName, distributorLocation);
        distributors.add(distributor);
    }
    public void addDistributorFromCode(Distributor d)
    {
        distributors.add(d);
    }

    public void showDistributors()
    {
        for(Distributor i:distributors)
        {
            System.out.println(i.toString());
        }
    }

    public void addStore()
    {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.print("Store's name : ");
        String storeName = scanner.next();
        System.out.print("Where is the store located? ");
        String storeLocation = scanner.next();
        Store store = new Store(storeLocation, storeName);
        stores.add(store);
    }

    public void addStoreFromCode(Store s)
    {
        stores.add(s);
    }

    public void showStores()
    {
        for(Store i:stores)
        {
            System.out.println(i.toString());
        }
    }

    public void addProductToStore()
    {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.print("Type of product : ");
        String productType = scanner.next();
        productType = productType.toLowerCase();
        System.out.print("Product's name : ");
        String productName = scanner.next();
        System.out.print("Product's price : ");
        int productPrice = scanner.nextInt();
        System.out.print("The category of the product : ");
        String productCategoryString = scanner.next();
        Category productCategory = categoryChoice(productCategoryString);
        System.out.print("The distributor of the product : ");
        String productDistributorString = scanner.next();
        Distributor productDistributor = distributorChoice(productDistributorString);
        System.out.print("In which store do you want to sell the product? (ID)");
        int productStoreID = scanner.nextInt();
        switch(productType)
        {
            case "vegetable" ->
                    {
                        System.out.print("Product quantity : ");
                        int productQuantity = scanner.nextInt();
                        System.out.print("Vegetable type : ");
                        String vegetableType = scanner.next();
                        System.out.print("Expiring date (dd-mm-yyyy) : ");
                        String data = scanner.next();
                        int day = Integer.parseInt(data.substring(0,2));
                        int month = Integer.parseInt(data.substring(3, 5));
                        int year = Integer.parseInt(data.substring(6, 10));
                        int hr = 0;
                        int min = 0;
                        LocalDateTime productExpiration = LocalDateTime.of(year, month, day, hr, min);
                        System.out.print("Origin of the product : ");
                        String productOrigin = scanner.next();

                        Vegetable vegetable = new Vegetable(productName, productPrice, productCategory, productDistributor, productQuantity, vegetableType, productExpiration, productOrigin);
                        stores.get(productStoreID).addProduct(vegetable);
                    }
            case "dairy" ->
                    {
                        System.out.print("Product quantity : ");
                        int productQuantity = scanner.nextInt();
                        System.out.print("What animal is it produced by? : ");
                        String productFromAnimal = scanner.next();
                        System.out.print("Expiring date (dd-mm-yyyy) : ");
                        String data = scanner.next();
                        int day = Integer.parseInt(data.substring(0,2));
                        int month = Integer.parseInt(data.substring(3, 5));
                        int year = Integer.parseInt(data.substring(6, 10));
                        int hr = 0;
                        int min = 0;
                        LocalDateTime productExpiration = LocalDateTime.of(year, month, day, hr, min);
                        System.out.print("Percent of animal fat : ");
                        double productFat = scanner.nextDouble();

                        Dairy d = new Dairy(productName, productPrice, productCategory, productDistributor, productQuantity, productFromAnimal, productExpiration, productFat);
                        stores.get(productStoreID).addProduct(d);
                    }
            case "fridge" ->
                    {
                        System.out.print("Product weight : ");
                        int productWeight = scanner.nextInt();
                        System.out.print("Product volume : ");
                        int productVolume = scanner.nextInt();
                        System.out.print("Product warranty (years) : ");
                        int productWarranty = scanner.nextInt();
                        System.out.print("Product energy usage : ");
                        int productEnergy = scanner.nextInt();
                        System.out.print("Product colour : ");
                        String productColour = scanner.next();

                        Fridge f = new Fridge(productName, productPrice, productCategory, productDistributor, productWeight, productVolume, productWarranty, productEnergy, productColour);
                        stores.get(productStoreID).addProduct(f);
                    }
            case "microwave" ->
                    {
                        System.out.print("Product weigh : ");
                        int productWeight = scanner.nextInt();
                        System.out.print("Product power : ");
                        int productPower = scanner.nextInt();
                        System.out.print("Product energy usage : ");
                        int productEnergy = scanner.nextInt();
                        System.out.print("Product warranty (years) : ");
                        int productWarranty = scanner.nextInt();
                        System.out.print("Product colour : ");
                        String productColour = scanner.next();

                        Microwave microwave = new Microwave(productName, productPrice, productCategory, productDistributor, productWeight, productPower, productEnergy, productWarranty, productColour);
                        stores.get(productStoreID).addProduct(microwave);
                    }

        }

    }

    //TODO BINARY SEARCH


}

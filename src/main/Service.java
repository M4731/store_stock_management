package main;

import categories.Category;
import database.Repositories;
import distributors.Distributor;
import products.*;
import stores.Store;
import validations.Validation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;

public class Service
{
    private ArrayList<Store> stores = new ArrayList<>();
    private ArrayList<Category> categories = new ArrayList<>();
    private ArrayList<Distributor> distributors = new ArrayList<>();
    private Repositories repo = new Repositories();

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
        AuditService AS = AuditService.getInstance();
        AS.auditWrite("addCategory");

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.print("Category name : ");
        String categoryName = scanner.next();
        if(!Validation.categoryNameValidation(categoryName))
        {
            return;
        }
        Category category = new Category(categoryName);
        categories.add(category);
        repo.insertCategory(category);
    }

    public void editCategory()
    {
        AuditService AS = AuditService.getInstance();
        AS.auditWrite("editCategory");

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.print("Category name : ");
        String categoryName = scanner.next();
        if(!Validation.categoryNameValidation(categoryName))
        {
            return;
        }
        System.out.print("New category name : ");
        String newCategoryName = scanner.next();
        if(!Validation.categoryNameValidation(newCategoryName))
        {
            return;
        }
        ArrayList<Category> categoriesDatabase = repo.findAll();
        for(var i:categoriesDatabase)
        {
            if(i.getNume().equals(categoryName))
            {
                i.setNume(newCategoryName);
                repo.updateCategory(i);

                break;
            }
        }
    }

    public void addCategoryFromCode(Category c)
    {
        categories.add(c);
    }

    public void showCategories()
    {
        AuditService AS = AuditService.getInstance();
        AS.auditWrite("showCategories");
        ArrayList<Category> categoriesDatabase = repo.findAll();

        for(Category i:repo.findAll())
        {
            System.out.println(i.toString());
        }

    }

    public void deleteCategory()
    {
        AuditService AS = AuditService.getInstance();
        AS.auditWrite("deleteCategory");

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.print("Category Name : ");
        String categoryName = scanner.next();
        if(!Validation.categoryNameCheck(categoryName, categories))
        {
            return;
        }
        repo.deleteCategory(categoryName);
        categories.removeIf(c -> c.getNume().equals(categoryName));
    }

    public void addDistributor()
    {
        AuditService AS = AuditService.getInstance();
        AS.auditWrite("addDistributor");

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.print("Distributor's name : ");
        String distributorName = scanner.next();
        System.out.print("Where is the distributor located? ");
        String distributorLocation = scanner.next();
        if(!Validation.categoryNameValidation(distributorName) || !Validation.categoryNameValidation(distributorLocation))
        {
            return;
        }
        Distributor distributor =  new Distributor(distributorName, distributorLocation);
        distributors.add(distributor);
        repo.insertDistributor(distributor);
    }

    public void addDistributorFromCode(Distributor d)
    {
        distributors.add(d);
    }

    public void showDistributors()
    {
        AuditService AS = AuditService.getInstance();
        AS.auditWrite("showDistributors");

        for(Distributor i: repo.findAllDistributors())
        {
            System.out.println(i.toString());
        }
    }

    public void deleteDistributor()
    {
        AuditService AS = AuditService.getInstance();
        AS.auditWrite("deleteDistributor");

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.print("Distributor Name : ");
        String distributorName = scanner.next();
//        if(!Validation.distributorNameCheck(distributorName, distributors))
//        {
//            return;
//        }

        repo.deleteDistributor(distributorName);
        distributors.removeIf(c -> c.getNume().equals(distributorName));
    }

    public void editDistributor()
    {
        AuditService AS = AuditService.getInstance();
        AS.auditWrite("editDistributor");

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.print("Distributor name : ");
        String distributorName = scanner.next();
        if(!Validation.categoryNameValidation(distributorName))
        {
            return;
        }
        System.out.print("New distributor name : ");
        String newDistributorName = scanner.next();
        System.out.print("New distributor location : ");
        String newDistributorLocation = scanner.next();
        if(!Validation.categoryNameValidation(newDistributorName))
        {
            return;
        }
        ArrayList<Distributor> distributorsDatabase = repo.findAllDistributors();
        for(var i:distributorsDatabase)
        {
            if(i.getNume().equals(distributorName))
            {
                i.setNume(newDistributorName);
                i.setLocation(newDistributorLocation);
                repo.updateDistributor(i);

                break;
            }
        }
    }

    public void addStore()
    {
        AuditService AS = AuditService.getInstance();
        AS.auditWrite("addStore");

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.print("Store's name : ");
        String storeName = scanner.next();
        System.out.print("Where is the store located? ");
        String storeLocation = scanner.next();
        if(!Validation.categoryNameValidation(storeLocation) || !Validation.categoryNameValidation(storeName))
        {
            return;
        }
        Store store = new Store(storeLocation, storeName);
        stores.add(store);
        repo.insertStore(store);
    }

    public void addStoreFromCode(Store s)
    {
        stores.add(s);
    }

    public void showStores()
    {
        AuditService AS = AuditService.getInstance();
        AS.auditWrite("showStores");

        for(Store i:repo.findAllStores())
        {
            System.out.println(i.toString());
        }
    }

    public void deleteStore()
    {
        AuditService AS = AuditService.getInstance();
        AS.auditWrite("deleteStore");

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.print("Store Name : ");
        String storeName = scanner.next();
//        if(!Validation.storeNameCheck(storeName, stores))
//        {
//            return;
//        }
        repo.deleteStore(storeName);
        stores.removeIf(c -> c.getName().equals(storeName));
    }

    public void editStore()
    {
        AuditService AS = AuditService.getInstance();
        AS.auditWrite("editStore");

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.print("Store name : ");
        String storeName = scanner.next();
        if(!Validation.categoryNameValidation(storeName))
        {
            return;
        }
        System.out.print("New store name : ");
        String newStoreName = scanner.next();
        System.out.print("New store location : ");
        String newStoreLocation = scanner.next();
        if(!Validation.categoryNameValidation(newStoreName))
        {
            return;
        }
        ArrayList<Store> storesDatabase = repo.findAllStores();
        for(var i:storesDatabase)
        {
            if(i.getName().equals(storeName))
            {
                i.setName(newStoreName);
                i.setLocation(newStoreLocation);
                repo.updateStore(i);

                break;
            }
        }
    }

    public void addProductToStore()
    {
        AuditService AS = AuditService.getInstance();
        AS.auditWrite("addProductToStore");

        ArrayList<String> productTypes = new  ArrayList<>();
        productTypes.add("vegetable");
        productTypes.add("diary");
        productTypes.add("fridge");
        productTypes.add("microwave");

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.print("Type of product : ");
        String productType = scanner.next();
        if(!Validation.checkProductType(productType.toLowerCase(), productTypes))
        {
            return;
        }
        productType = productType.toLowerCase();

        System.out.print("Product's name : ");
        String productName = scanner.next();
        int productPrice;

        System.out.print("Product's price : ");
        try{
            productPrice = scanner.nextInt();
        }
        catch(Exception e)
        {
            System.out.println("Invalid Number");
            return;
        }

        System.out.print("The category of the product : ");
        String productCategoryString = scanner.next();
        if(!Validation.categoryNameCheck(productCategoryString, categories))
        {
            return;
        }
        Category productCategory = categoryChoice(productCategoryString);

        System.out.print("The distributor of the product : ");
        String productDistributorString = scanner.next();
        if(!Validation.distributorNameCheck(productDistributorString, distributors))
        {
            return;
        }
        Distributor productDistributor = distributorChoice(productDistributorString);

        int productStoreID;
        System.out.print("In which store do you want to sell the product? (ID)");
        try{
            productStoreID = scanner.nextInt();
        }
        catch(Exception e)
        {
            System.out.println("Invalid Number");
            return;
        }
        if(!Validation.checkStoreID(productStoreID, stores))
        {
            return;
        }

        switch(productType)
        {
            case "vegetable" ->
                    {
                        System.out.print("Product quantity : ");
                        int productQuantity;
                        try{
                            productQuantity = scanner.nextInt();
                        }
                        catch(Exception e)
                        {
                            System.out.println("Invalid Number");
                            return;
                        }

                        System.out.print("Vegetable type : ");
                        String vegetableType = scanner.next();

                        System.out.print("Expiring date (dd-mm-yyyy) : ");
                        String data = scanner.next();
                        int day, month, year, hr, min;
                        try{
                            day = Integer.parseInt(data.substring(0,2));
                            month = Integer.parseInt(data.substring(3, 5));
                            year = Integer.parseInt(data.substring(6, 10));
                            hr = 0;
                            min = 0;
                        }
                        catch(Exception e)
                        {
                            System.out.println("Invalid Number");
                            return;
                        }
                        LocalDateTime productExpiration;
                        try
                        {
                            productExpiration = LocalDateTime.of(year, month, day, hr, min);
                        }
                        catch(Exception e)
                        {
                            System.out.println("Invalid Date");
                            return;
                        }

                        System.out.print("Origin of the product : ");
                        String productOrigin = scanner.next();

                        Vegetable vegetable = new Vegetable(productName, productPrice, productCategory, productDistributor, productQuantity, vegetableType, productExpiration, productOrigin);
                        stores.get(productStoreID-1).addProduct(vegetable);
                    }
            case "dairy" ->
                    {
                        System.out.print("Product quantity : ");
                        int productQuantity;
                        try{
                            productQuantity = scanner.nextInt();
                        }
                        catch(Exception e)
                        {
                            System.out.println("Invalid Number");
                            return;
                        }

                        System.out.print("What animal is it produced by? : ");
                        String productFromAnimal = scanner.next();

                        System.out.print("Expiring date (dd-mm-yyyy) : ");
                        String data = scanner.next();
                        int day, month, year, hr, min;
                        try{
                            day = Integer.parseInt(data.substring(0,2));
                            month = Integer.parseInt(data.substring(3, 5));
                            year = Integer.parseInt(data.substring(6, 10));
                            hr = 0;
                            min = 0;
                        }
                        catch(Exception e)
                        {
                            System.out.println("Invalid Number");
                            return;
                        }
                        LocalDateTime productExpiration;
                        try
                        {
                            productExpiration = LocalDateTime.of(year, month, day, hr, min);
                        }
                        catch(Exception e)
                        {
                            System.out.println("Invalid Date");
                            return;
                        }

                        System.out.print("Percent of animal fat : ");
                        double productFat;
                        try{
                            productFat = scanner.nextDouble();
                        }
                        catch(Exception e)
                        {
                            System.out.println("Invalid Number");
                            return;
                        }

                        Dairy d = new Dairy(productName, productPrice, productCategory, productDistributor, productQuantity, productFromAnimal, productExpiration, productFat);
                        stores.get(productStoreID-1).addProduct(d);
                    }
            case "fridge" ->
                    {
                        System.out.print("Product weight : ");
                        int productWeight;
                        try{
                            productWeight = scanner.nextInt();
                        }
                        catch(Exception e)
                        {
                            System.out.println("Invalid Number");
                            return;
                        }

                        System.out.print("Product volume : ");
                        int productVolume;
                        try{
                            productVolume = scanner.nextInt();
                        }
                        catch(Exception e)
                        {
                            System.out.println("Invalid Number");
                            return;
                        }

                        System.out.print("Product warranty (years) : ");
                        int productWarranty;
                        try{
                            productWarranty = scanner.nextInt();
                        }
                        catch(Exception e)
                        {
                            System.out.println("Invalid Number");
                            return;
                        }

                        System.out.print("Product energy usage : ");
                        int productEnergy;
                        try{
                            productEnergy = scanner.nextInt();
                        }
                        catch(Exception e)
                        {
                            System.out.println("Invalid Number");
                            return;
                        }

                        System.out.print("Product colour : ");
                        String productColour = scanner.next();

                        Fridge f = new Fridge(productName, productPrice, productCategory, productDistributor, productWeight, productVolume, productWarranty, productEnergy, productColour);
                        stores.get(productStoreID-1).addProduct(f);
                    }
            case "microwave" ->
                    {
                        System.out.print("Product weight : ");
                        int productWeight;
                        try{
                            productWeight = scanner.nextInt();
                        }
                        catch(Exception e)
                        {
                            System.out.println("Invalid Number");
                            return;
                        }

                        System.out.print("Product power : ");
                        int productPower;
                        try{
                            productPower = scanner.nextInt();
                        }
                        catch(Exception e)
                        {
                            System.out.println("Invalid Number");
                            return;
                        }

                        System.out.print("Product energy usage : ");
                        int productEnergy;
                        try{
                            productEnergy = scanner.nextInt();
                        }
                        catch(Exception e)
                        {
                            System.out.println("Invalid Number");
                            return;
                        }

                        System.out.print("Product warranty (years) : ");
                        int productWarranty;
                        try{
                            productWarranty = scanner.nextInt();
                        }
                        catch(Exception e)
                        {
                            System.out.println("Invalid Number");
                            return;
                        }

                        System.out.print("Product colour : ");
                        String productColour = scanner.next();

                        Microwave microwave = new Microwave(productName, productPrice, productCategory, productDistributor, productWeight, productPower, productEnergy, productWarranty, productColour);
                        stores.get(productStoreID-1).addProduct(microwave);
                    }

        }
    }

    public void addProductToStoreFromCode(Product p, int store)
    {
        stores.get(store).addProduct(p);
    }

    public void showAllProductsFromaStore()
    {
        AuditService AS = AuditService.getInstance();
        AS.auditWrite("showAllProductsFromaStore");

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.print("Name of the store : ");
        String storeName = scanner.next();
        if(!Validation.storeNameCheck(storeName,stores))
        {
            return;
        }

        Store st = new Store("","");
        for(var i : stores)
        {
            if(i.getName().equals(storeName))
            {
                st = i;
            }
        }
//        Store s = new Store("", "");
//        s.setName(storeName);
//        var index = Collections.binarySearch(stores, s);
//        Store store = stores.get(index);

        for(var p:st.getProducts())
            System.out.println(p);
    }

    public void deleteFromStore()
    {
        AuditService AS = AuditService.getInstance();
        AS.auditWrite("deleteFromStore");

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.print("Name of the store : ");
        String storeName = scanner.next();
        if(!Validation.storeNameCheck(storeName,stores))
        {
            return;
        }
        System.out.print("Name of the product : ");
        String productName = scanner.next();
        if(!Validation.checkProductOnStock(storeName, productName, stores))
        {
            return;
        }

        for (Store i:stores)
        {
            if(i.getName().equals(storeName))
            {
                for(Product p:i.getProducts())
                {
                    if(p.getDenumire().equals(productName))
                    {
                        i.removeProduct(p);
                        break;
                    }
                }
                break;
            }
        }
    }

    public void storeWithTheMostProductsOnStock()
    {
        AuditService AS = AuditService.getInstance();
        AS.auditWrite("storeWithTheMostProductsOnStock");

        int start = 0;
        String storeName = "";
        for (Store s:stores)
        {
            if(s.getProducts().size() > start)
            {
                start = s.getProducts().size();
                storeName = s.getName();
            }
        }

        Store st = new Store("","");
        for(var i : stores)
        {
            if(i.getName().equals(storeName))
            {
                st = i;
            }
        }

//        Store s = new Store("", "");
//        s.setName(storeName);
//        var index = Collections.binarySearch(stores, s);
//        Store store = stores.get(index);

        System.out.println(st);
    }

    public void checkItem()
    {
        AuditService AS = AuditService.getInstance();
        AS.auditWrite("checkItem");

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.print("Name of the store : ");
        String storeName = scanner.next();
        if(!Validation.storeNameCheck(storeName,stores))
        {
            return;
        }
        System.out.print("Name of the product : ");
        String productName = scanner.next();
        if(!Validation.checkProductOnStock(storeName, productName, stores))
        {
            return;
        }

//        Boolean semafor = false;
//
//        for(Store i:stores)
//        {
//            if(i.getName().equals(storeName))
//            {
//                for(Product p:i.getProducts())
//                {
//                    if(p.getDenumire().equals(productName))
//                    {
//                        System.out.println("The product is on stock!");
//                        semafor = true;
//                        break;
//                    }
//                }
//                break;
//            }
//        }
//        if(!semafor)
//        {
        System.out.println("The product on stock :( !");
//        }
    }


}

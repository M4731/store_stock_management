package validations;

import categories.Category;
import distributors.Distributor;
import products.Product;
import stores.Store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Validation
{
    public static boolean categoryNameValidation(String s)
    {
        if(s.equals(""))
        {
            System.out.println("Unavailable name.");
            return false;
        }
        return true;
    }

    public static boolean categoryNameCheck(String s, ArrayList<Category> categories)
    {
        for (Category c:categories)
        {
            if(c.getNume().equals(s))
            {
                return true;
            }
        }
        System.out.println("There isn't any category with that name");
        return false;
    }

    public static boolean distributorNameCheck(String s, ArrayList<Distributor> distributors)
    {
        for (Distributor d:distributors)
        {
            if(d.getNume().equals(s))
            {
                return true;
            }
        }
        System.out.println("There isn't any distributor with that name");
        return false;
    }

    public static boolean storeNameCheck(String s, ArrayList<Store> stores)
    {
        for (Store store:stores)
        {
            if(store.getName().equals(s))
            {
                return true;
            }
        }
        System.out.println("There isn't any store with that name");
        return false;
    }

    public static boolean checkProductType(String s, ArrayList<String> types)
    {
        for (String check:types)
        {
            if(check.equals(s))
            {
                return true;
            }
        }
        System.out.println("There isn't any product type with that name");
        return false;
    }

    public static boolean checkStoreID(int id, ArrayList<Store> stores)
    {
        for(Store store:stores)
        {
            if (Integer.compare(store.getID(), id)==0)
            {
                return true;
            }
        }
        System.out.println("ID unavailable.");
        return false;
    }

    public static boolean checkProductOnStock(String storeName, String productName, ArrayList<Store> stores)
    {
        Store s = new Store("", "");
        for(var i:stores)
        {
            if(i.getName().equals(storeName))
            {
                s = i;
            }
        }

        for(Product p:s.getProducts())
        {
            if(p.getDenumire().equals(productName))
            {
                return true;
            }
        }
        System.out.println("The product is not on stock!");
        return true;
    }
}

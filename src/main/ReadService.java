//https://stackabuse.com/reading-and-writing-csvs-in-java/

package main;

import categories.Category;
import distributors.Distributor;
import products.Dairy;
import products.Vegetable;
import stores.Store;
import validations.Validation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;

public class ReadService {
    private static ReadService instance = null;
    private ReadService(){}

    public static ReadService getInstance()
    {
        if(instance == null)
        {
            instance = new ReadService();
        }
        return instance;
    }

    public void readCategories()
    {
        Service service = Service.getInstance();
        try
        {
            String row;
            BufferedReader csvReader = new BufferedReader(new FileReader("./InOut/Input/categories.csv"));
            while ((row = csvReader.readLine()) != null) {
                Category c = new Category(row);
                service.addCategoryFromCode(c);
            }
            csvReader.close();
        }
        catch (IOException e) {
            System.out.println("Unable to access categories.csv");
        }
    }

    public void readDistributors()
    {
        Service service = Service.getInstance();
        try
        {
            String row;
            BufferedReader csvReader = new BufferedReader(new FileReader("./InOut/Input/distributors.csv"));
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                Distributor d = new Distributor(data[0],data[1]);
                service.addDistributorFromCode(d);
            }
            csvReader.close();
        }
        catch (IOException e) {
            System.out.println("Unable to access distributors.csv");
        }
    }

    public void readStores()
    {
        Service service = Service.getInstance();
        try
        {
            String row;
            BufferedReader csvReader = new BufferedReader(new FileReader("./InOut/Input/stores.csv"));
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                Store s = new Store(data[0],data[1]);
                service.addStoreFromCode(s);
            }
            csvReader.close();
        }
        catch (IOException e) {
            System.out.println("Unable to access stores.csv");
        }
    }

    public void readDairies()
    {
        Service service = Service.getInstance();
        try
        {
            String row;
            BufferedReader csvReader = new BufferedReader(new FileReader("./InOut/Input/dairies.csv"));
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");

                Category productCategory = service.categoryChoice(data[2]);
                Distributor productDistributor = service.distributorChoice(data[3]);
                int day, month, year, hr, min;
                day = Integer.parseInt(data[6].substring(0,2));
                month = Integer.parseInt(data[6].substring(3, 5));
                year = Integer.parseInt(data[6].substring(6, 10));
                hr = 0;
                min = 0;
                LocalDateTime productExpiration;
                productExpiration = LocalDateTime.of(year, month, day, hr, min);


                Dairy d = new Dairy(data[0],Double.parseDouble(data[1]),productCategory,productDistributor,
                        Integer.parseInt(data[4]),data[5],productExpiration,Double.parseDouble(data[7]));

                service.addProductToStoreFromCode(d, Integer.parseInt(data[8]));
//                productCategory.addProduct(d);
//                productDistributor.addProduct(d);
            }
            csvReader.close();
        }
        catch (IOException e) {
            System.out.println("Unable to access dairies.csv");
        }
    }

    public void readVegetables()
    {
        Service service = Service.getInstance();
        try
        {
            String row;
            BufferedReader csvReader = new BufferedReader(new FileReader("./InOut/Input/vegetables.csv"));
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");

                Category productCategory = service.categoryChoice(data[2]);
                Distributor productDistributor = service.distributorChoice(data[3]);
                int day, month, year, hr, min;
                day = Integer.parseInt(data[6].substring(0,2));
                month = Integer.parseInt(data[6].substring(3, 5));
                year = Integer.parseInt(data[6].substring(6, 10));
                hr = 0;
                min = 0;
                LocalDateTime productExpiration;
                productExpiration = LocalDateTime.of(year, month, day, hr, min);


                Vegetable v = new Vegetable(data[0],Double.parseDouble(data[1]),productCategory,productDistributor,
                        Integer.parseInt(data[4]),data[5],productExpiration,data[7]);

                service.addProductToStoreFromCode(v, Integer.parseInt(data[8]));
            }
            csvReader.close();
        }
        catch (IOException e) {
            System.out.println("Unable to access vegetables.csv");
        }
    }

}

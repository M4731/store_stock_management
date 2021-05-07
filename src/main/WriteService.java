package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class WriteService {
    private static WriteService instance = null;
    private WriteService(){}

    public static WriteService getInstance()
    {
        if(instance == null)
        {
            instance = new WriteService();
        }
        return instance;
    }

    public void writeCategories()
    {
        Service service = Service.getInstance();
        try {
            FileWriter csvWriter = new FileWriter("./InOut/Output/categories.csv");
            csvWriter.write("Name,Products\n");
            for(var s:service.getCategories())
            {
                csvWriter.write(s.getNume()+",");
                for(var i:s.getProductList())
                {
                    csvWriter.write(i.getDenumire()+" ");
                }
                csvWriter.write("\n");
            }

            csvWriter.close();
        }
        catch(IOException e)
        {
            System.out.println("Cannot print into categories.csv");
        }

    }

    public void writeDistributors()
    {
        Service service = Service.getInstance();
        try {
            FileWriter csvWriter = new FileWriter("./InOut/Output/distributors.csv");
            csvWriter.write("Name,Location,Products\n");
            for(var s:service.getDistributors())
            {
                csvWriter.write(s.getNume()+","+s.getLocation()+",");
                for(var i:s.getProductList())
                {
                    csvWriter.write(i.getDenumire()+" ");
                }
                csvWriter.write("\n");
            }

            csvWriter.close();
        }
        catch(IOException e)
        {
            System.out.println("Cannot print into distributors.csv");
        }
    }

    public void writeStores()
    {
        Service service = Service.getInstance();
        try {
            FileWriter csvWriter = new FileWriter("./InOut/Output/stores.csv");
            csvWriter.write("Name,Location,Products\n");
            for(var s:service.getStores())
            {
                csvWriter.write(s.getName()+","+s.getLocation()+",");
                for(var i:s.getProducts())
                {
                    csvWriter.write(i.getDenumire()+" ");
                }
                csvWriter.write("\n");
            }

            csvWriter.close();
        }
        catch(IOException e)
        {
            System.out.println("Cannot print into stores.csv");
        }
    }

    public void writeProducts()
    {
        Service service = Service.getInstance();
        try {
            FileWriter csvWriter = new FileWriter("./InOut/Output/products.csv");
            csvWriter.write("---\n");
            for(var s:service.getStores())
            {
                for(var prod:s.getProducts())
                {
                    csvWriter.write(prod.getDenumire().toUpperCase()+","+prod+"\n");
                }
            }

            csvWriter.close();
        }
        catch(IOException e)
        {
            System.out.println("Cannot print into products.csv");
        }
    }
}

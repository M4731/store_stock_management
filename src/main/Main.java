package main;


import categories.Category;
import database.Setup;
import distributors.Distributor;
import products.Dairy;
import products.Vegetable;
import stores.Store;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");

        Setup setup = new Setup();
        setup.setup();

//        Distributor dm = new Distributor("dm", "Romania");
//        System.out.println(dm);
//
//        Store s = new Store("Romania", "LIDL");
//
//        Vegetable tomato = new Vegetable("tomato", 5, food, dm, 1, "cherry", LocalDateTime.now(), "Romania");
//        Dairy d = new Dairy("dada", 5, food, dm, 1, "cow", LocalDateTime.now(), 2.25);

        Service service = Service.getInstance();

        ReadService RS = ReadService.getInstance();
        RS.readCategories();
        RS.readDistributors();
        RS.readStores();
        RS.readDairies();
        RS.readVegetables();

        String menu_choice;

//        service.addStoreFromCode(s);
//        service.addDistributorFromCode(dm);
//        service.addCategoryFromCode(food);
//
//        service.addProductToStoreFromCode(tomato, 0);
//        service.addProductToStoreFromCode(d, 0);

        WriteService WS = WriteService.getInstance();
        WS.writeCategories();
        WS.writeDistributors();
        WS.writeStores();
        WS.writeProducts();

        AuditService AS = AuditService.getInstance();

        do
        {
            System.out.println("Menu\n1 - add category");
            System.out.println("2 - show categories");
            System.out.println("3 - delete category");
            System.out.println("4 - add distributor");
            System.out.println("5 - show distributors");
            System.out.println("6 - delete distributor");
            System.out.println("7 - add store");
            System.out.println("8 - show stores");
            System.out.println("9 - delete store");
            System.out.println("10 - add product to store");
            System.out.println("11 - show all the products from a store");
            System.out.println("12 - remove a product from a store");
            System.out.println("13 - the store with the most products on stock");
            System.out.println("14 - check if a product is on stock at a store");
            System.out.println("15 - edit category");
            System.out.println("16 - edit distributor");
            System.out.println("17 - edit store");
            System.out.println("18 - show vegetables");
            System.out.println("exit - exit menu");
            menu_choice = scanner.next();
            switch (menu_choice)
            {
                case "1" -> service.addCategory();
                case "2" -> service.showCategories();
                case "3" -> service.deleteCategory();
                case "4" -> service.addDistributor();
                case "5" -> service.showDistributors();
                case "6" -> service.deleteDistributor();
                case "7" -> service.addStore();
                case "8" -> service.showStores();
                case "9" -> service.deleteStore();
                case "10" -> service.addProductToStore();
                case "11" -> service.showAllProductsFromaStore();
                case "12" -> service.deleteFromStore();
                case "13" -> service.storeWithTheMostProductsOnStock();
                case "14" -> service.checkItem();
                case "15" -> service.editCategory();
                case "16" -> service.editDistributor();
                case "17" -> service.editStore();
                case "18" -> service.showVegetables();
            }

        }while(!menu_choice.equals("exit"));

    }
}

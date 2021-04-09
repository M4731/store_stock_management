package main;


import categories.Category;
import distributors.Distributor;
import products.Vegetable;
import stores.Store;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");

        Category food = new Category("food");
        System.out.println(food);


        Distributor dm = new Distributor("dm", "Romania");
        System.out.println(dm);

        Store s = new Store("Romania", "LIDL");
//
//        Vegetable tomato = new Vegetable("tomato", 5, food, dm, 1, "cherry", LocalDateTime.now(), "Romania");
//        System.out.println(tomato.toString());
//        System.out.println(food.toString());
//        System.out.println(dm.toString());

        Service service = Service.getInstance();
        String menu_choice;

        service.addStoreFromCode(s);
        service.addDistributorFromCode(dm);
        service.addCategoryFromCode(food);

        do
        {
            System.out.println("Menu\n1 - add category");
            System.out.println("2 - show categories");
            System.out.println("3 - add distributor");
            System.out.println("4 - show distributors");
            System.out.println("5 - add store");
            System.out.println("6 - show stores");
            System.out.println("7 - add product to store");
            System.out.println("exit - exit menu");
            menu_choice = scanner.next();
            switch (menu_choice)
            {
                case "1" -> service.addCategory();
                case "2" -> service.showCategories();
                case "3" -> service.addDistributor();
                case "4" -> service.showDistributors();
                case "5" -> service.addStore();
                case "6" -> service.showStores();
                case "7" -> service.addProductToStore();
            }

        }while(!menu_choice.equals("exit"));

    }
}

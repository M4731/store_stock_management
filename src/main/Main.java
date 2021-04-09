package main;


import categories.Category;
import distributors.Distributor;
import products.Vegetable;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
//        Category food = new Category("food");
//        System.out.println(food.toString());
//
//        Distributor dm = new Distributor("dm", "Romania");
//        System.out.println(dm.toString());
//
//        Vegetable tomato = new Vegetable("tomato", 5, food, dm, 1, "cherry", LocalDateTime.now(), "Romania");
//        System.out.println(tomato.toString());
//        System.out.println(food.toString());
//        System.out.println(dm.toString());

        Service service = Service.getInstance();
        String menu_choice = "";

        do
        {
            System.out.println("Menu\n1 - add category");
            System.out.println("2 - show categories");
            System.out.println("exit - exit menu");
            menu_choice = scanner.next();
            if(menu_choice.equals("1"))
            {
                service.addCategory();
            }
            else if(menu_choice.equals("2"))
            {
                service.showCategories();
            }

        }while(!menu_choice.equals("exit"));


    }
}

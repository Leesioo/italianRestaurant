package italianrestaurant.staff;

import italianrestaurant.food.Food;
import italianrestaurant.supply.*;

import java.io.IOException;
import java.io.InputStreamReader;

public class Waiter {
    private Menu menu;

    public void handleCustomer() {
        int userSelection = 0;
        menu = new Menu();
        while (userSelection != 2) {
            scrollTheScreen();
            System.out.println("Hi, welcome in Prego Italian Restaurant! Would you like...");
            System.out.println("1. Take order");
            System.out.println("2. Pay the bill");
            userSelection = getUserSelection();
            switch (userSelection) {
                case 1:
                    takeOrder();
                    break;
                case 2:
                    payTheBill();
                    break;
            }
        }
        System.out.println("See you!");
    }

    private void takeOrder() {
        scrollTheScreen();
        System.out.println("I can recommend you delicious pizzas!");
        menu.showMenu();
        int userSelection = getUserSelection();
        Chef chef = new Chef();
        Food food = chef.prepareOrderedFoodById(userSelection);
        if (food == null) {
            System.out.println("Mamma mia! That's not even a pizza! Vaffanculo!");
        } else {
            System.out.println("Here is your " + food);
        }
    }

    private void payTheBill() {
        scrollTheScreen();
        System.out.println("That will be XXX total.");
    }

    private int getUserSelection() {
        InputStreamReader reader = new InputStreamReader(System.in);
        int readSelection = '0';
        try {
            readSelection = reader.read();
        } catch (IOException e) {
            System.out.println("Could not read input");
        }
        return readSelection - '0';
    }

    private void scrollTheScreen() {
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
    }
}

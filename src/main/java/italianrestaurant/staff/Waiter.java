package italianrestaurant.staff;

import italianrestaurant.food.Food;
import italianrestaurant.supply.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class Waiter implements Runnable, FoodObserver{
    private Menu menu;
    private Kitchen kitchen;
    private boolean thereIsNewFood;

    public Waiter() {
        menu = new Menu();
        kitchen = Kitchen.getInstance();
    }

    @Override
    public void update() {
        thereIsNewFood = true;
    }

    @Override
    public void run() {
        // zaczynamy pracę więc się rejestrujemy
        kitchen.registerObserver(this);
        while (!Thread.currentThread().isInterrupted()) {
            if (clientDemandsAttention()) {
                handleCustomer();
            }
            if (thereIsNewFood) {
                thereIsNewFood = false;
                Optional<Food> food = kitchen.takeFood();
                if (!food.isPresent()) {
                    System.out.println("Mamma mia! I don't do that!!!");
                } else {
                    System.out.println("Here is your " + food.get());
                }
            }
        }
        // kończymy harować więc się wyrejestrowujemy
        kitchen.unregisterObserver(this);
    }

    private boolean clientDemandsAttention() {
        try {
            int availableCharacters = System.in.available();
            if (availableCharacters > 0) {
                System.in.read(new byte[availableCharacters]);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }

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
        Order order = new Order();
        order.addElement(menu.getProductInfo(userSelection));
        kitchen.addOrder(order);
    }

//    to jest wersja co działała bez wątków
//    private void takeOrder() {
//        scrollTheScreen();
//        System.out.println("I can recommend you delicious pizzas!");
//        menu.showMenu();
//        int userSelection = getUserSelection();
//        Chef chef = new Chef();
//        Optional<Food> food = chef.prepareOrderedFoodById(userSelection);
//        if (!food.isPresent()) {
//            System.out.println("Mamma mia! I don't do that!!!");
//        } else {
//            System.out.println("Here is your " + food);
//        }
//    }

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

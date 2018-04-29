package italianrestaurant.staff;

import italianrestaurant.food.*;
import italianrestaurant.supply.Menu;
import italianrestaurant.supply.Order;
import italianrestaurant.supply.OrderElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Chef implements Runnable, OrderObserver{
    private Menu menu;
    private FoodFactory foodFactory;
    private Kitchen kitchen;
    private boolean thereIsNewOrder;

    @Override
    public void update() {
        thereIsNewOrder = true;
    }

    @Override
    public void run() {
        // zaczynamy pracę więc się rejestrujemy
        kitchen.registerObserver(this);
        while (!Thread.currentThread().isInterrupted()) {
            if (thereIsNewOrder) {
                thereIsNewOrder = false;
                Optional<Order> order = kitchen.takeOrder();
                if (order.isPresent()) {
                    List<Food> preparedFoods = prepareOrderedFoodWithLambda(order.get());
                    for (Food preparedFood : preparedFoods) {
                        kitchen.addFood(preparedFood);
                    }
                }
            }
        }
        // kończymy harować więc się wyrejestrowujemy
        kitchen.unregisterObserver(this);
    }

    public Chef() {
        menu = new Menu();
        kitchen = Kitchen.getInstance();
        foodFactory = new FoodFactory();
    }

    public Optional<Food> prepareOrderedFoodById(Integer productId) {
        OrderElement element = menu.getProductInfo(productId);
        return foodFactory.prepareFood(element);
    }

    public List<Food> prepareOrderedFood(Order order) {
        List<Food> preparedFood = new ArrayList<>();
        for (OrderElement element : order.getElements()) {
            foodFactory.prepareFood(element).ifPresent(preparedFood::add);
        }
        return preparedFood;
    }

    public List<Food> prepareOrderedFoodWithLambda(Order order) {
        return order.getElements().stream()
                .map(foodFactory::prepareFood)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}

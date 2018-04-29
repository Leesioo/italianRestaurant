package italianrestaurant.staff;

import italianrestaurant.food.Food;
import italianrestaurant.supply.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Kitchen {

    private static final Kitchen INSTANCE = new Kitchen();
    private List<OrderObserver> orderObservers;
    private List<FoodObserver> foodObservers;
    private Queue<Order> orders;
    private Queue<Food> foods;

    private Kitchen() {
        orderObservers = new ArrayList<>();
        foodObservers = new ArrayList<>();
        orders = new ConcurrentLinkedDeque<>();
        foods = new ConcurrentLinkedDeque<>();
    }

    public static Kitchen getInstance() {
        return INSTANCE;
    }

    public void registerObserver(OrderObserver observer) {
        orderObservers.add(observer);
    }

    public void registerObserver(FoodObserver observer) {
        foodObservers.add(observer);
    }

    public void unregisterObserver(OrderObserver observer) {
        orderObservers.remove(observer);
    }

    public void unregisterObserver(FoodObserver observer) {
        foodObservers.remove(observer);
    }

    public void addOrder(Order order) {
        orders.add(order);
        orderObservers.forEach(OrderObserver::update);
//        po staremu
//        for (OrderObserver observer: orderObservers) {
//            observer.update();
//        }
    }

    public void addFood(Food food) {
        foods.add(food);
        foodObservers.forEach(FoodObserver::update);
    }

    public Optional<Order> takeOrder() {
        return Optional.ofNullable(orders.poll());
    }

    public Optional<Food> takeFood() {
        return Optional.ofNullable(foods.poll());
    }
}

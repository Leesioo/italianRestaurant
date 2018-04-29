package italianrestaurant.staff;

import italianrestaurant.food.*;
import italianrestaurant.supply.Menu;
import italianrestaurant.supply.Order;
import italianrestaurant.supply.OrderElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Chef {
    private Menu menu;
    private FoodFactory foodFactory;

    public Chef() {
        menu = new Menu();
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

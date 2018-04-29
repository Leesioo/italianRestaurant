package italianrestaurant.staff;

import italianrestaurant.food.Dough;
import italianrestaurant.food.Food;
import italianrestaurant.food.Pizza;
import italianrestaurant.food.Size;
import italianrestaurant.supply.Order;
import italianrestaurant.supply.OrderElement;
import italianrestaurant.supply.OrderElementSpecificsType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Chef {
    public List<Food> prepareOrderedFood(Order order) {
        List<Food> preparedFood = new ArrayList<>();
        for (OrderElement element : order.getElements()) {
            String dough = element.getSpecifics().get(OrderElementSpecificsType.DOUGH).iterator().next();
            String sauce = element.getSpecifics().get(OrderElementSpecificsType.SAUCE).iterator().next();
            List<String> toppings = element.getSpecifics().get(OrderElementSpecificsType.TOPPING);

            Pizza.Builder pizzaBuilder = Pizza.builder(Dough.valueOf(dough.toUpperCase()), Size.MEDIUM);
            Pizza pizza = pizzaBuilder
                    .sauce(sauce)
                    .toppings(toppings)
                    .build();

            preparedFood.add(pizza);
        }
        return preparedFood;
    }
}

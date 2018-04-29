package italianrestaurant.staff;

import italianrestaurant.food.Dough;
import italianrestaurant.food.Food;
import italianrestaurant.food.Pizza;
import italianrestaurant.food.Size;
import italianrestaurant.supply.Order;
import italianrestaurant.supply.OrderElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Chef {
    public List<Food> prepareOrderedFood(Order order) {
        List<Food> preparedFood = new ArrayList<>();
        for (OrderElement element : order.getElements()) {
            String dough = element.getSpecifics().get("dough").iterator().next();
            String sauce = element.getSpecifics().get("sauce").iterator().next();
            Set<String> toppings = element.getSpecifics().get("topping");
            Pizza pizza = new Pizza();
            pizza.setDough(Dough.valueOf(dough.toUpperCase()));
            pizza.setSize(Size.MEDIUM);
            pizza.setSauce(sauce);
            for (String topping : toppings) {
                pizza.addToppings(topping);
            }
            preparedFood.add(pizza);
        }
        return preparedFood;
    }
}

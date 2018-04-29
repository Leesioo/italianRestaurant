package italianrestaurant.supply;

import java.util.HashMap;
import java.util.Map;

public class Menu {
    private Map<Integer, Product> menu;

    public Menu() {
        menu = new HashMap<>();
        prepareMenu();
    }

    private void prepareMenu() {
        OrderElement firstPizza = new OrderElement(OrderElementType.PIZZA);
        firstPizza.addSpecifics(OrderElementSpecificsType.DOUGH, "neapolitan");
        firstPizza.addSpecifics(OrderElementSpecificsType.SAUCE, "tomato");
        firstPizza.addSpecifics(OrderElementSpecificsType.TOPPING, "mozzarella");
        firstPizza.addSpecifics(OrderElementSpecificsType.TOPPING, "grana padano");
        Product firstProduct = new Product(1, "Neapolitan with mozzarella, grana padano and tomato sauce", firstPizza);
        menu.put(1, firstProduct);
        OrderElement secondPizza = new OrderElement(OrderElementType.PIZZA);
        secondPizza.addSpecifics(OrderElementSpecificsType.DOUGH, "new_york");
        secondPizza.addSpecifics(OrderElementSpecificsType.SAUCE, "tomato");
        secondPizza.addSpecifics(OrderElementSpecificsType.TOPPING, "mozzarella");
        secondPizza.addSpecifics(OrderElementSpecificsType.TOPPING, "prosciutto cotto");
        secondPizza.addSpecifics(OrderElementSpecificsType.TOPPING, "mushrooms");
        Product secondProduct = new Product(2, "New York with mozzarella, prosciutto cotto, mushrooms and tomato sauce", secondPizza);
        menu.put(2, secondProduct);
        OrderElement thirdPizza = new OrderElement(OrderElementType.PIZZA);
        thirdPizza.addSpecifics(OrderElementSpecificsType.DOUGH, "sicilian");
        thirdPizza.addSpecifics(OrderElementSpecificsType.SAUCE, "tomato");
        thirdPizza.addSpecifics(OrderElementSpecificsType.TOPPING, "mozzarella");
        thirdPizza.addSpecifics(OrderElementSpecificsType.TOPPING, "salami milano");
        thirdPizza.addSpecifics(OrderElementSpecificsType.TOPPING, "olives");
        Product thirdProduct = new Product(3, "Sicilian with mozzarella, salami milano, olives and tomato sauce", thirdPizza);
        menu.put(3, thirdProduct);
    }

    public void showMenu() {
        for (Integer i: menu.keySet()) {
            System.out.println(menu.get(i).getId() + ". " + menu.get(i).getDescription());
        }
    }

    public OrderElement getProductInfo(Integer i) {
        OrderElement element = menu.get(i).getElement();
        return element;
    }
}

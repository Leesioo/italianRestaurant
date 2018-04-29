package italianrestaurant.food;

import italianrestaurant.supply.OrderElement;
import italianrestaurant.supply.OrderElementSpecificsType;

import java.util.List;
import java.util.Optional;

public class FoodFactory {

    public Optional<Food> prepareFood(OrderElement element) {
        switch (element.getElementType()) {
            case PIZZA: {
                Pizza pizza = createPizza(element);
                return Optional.ofNullable(pizza);
            }
            case PASTA: {
                Pasta pasta = createPasta(element);
                return Optional.ofNullable(pasta);
            }
        }
        return Optional.empty();
    }

    private Pasta createPasta(OrderElement element) {
        String pastaKind = element.getSpecifics().get(OrderElementSpecificsType.PASTA_TYPE).iterator().next();
        String sauce = element.getSpecifics().get(OrderElementSpecificsType.SAUCE).iterator().next();
        List<String> additions = element.getSpecifics().get(OrderElementSpecificsType.TOPPING);
        boolean isParmezan = false;

        Pasta.Builder pastaBuilder = Pasta.builder();
        return pastaBuilder
//                .pastaKind(pastaKind)
                .sauce(sauce)
                .additions(additions)
                .build();
    }

    private Pizza createPizza(OrderElement element) {
        String dough = element.getSpecifics().get(OrderElementSpecificsType.DOUGH).iterator().next();
        String sauce = element.getSpecifics().get(OrderElementSpecificsType.SAUCE).iterator().next();
        List<String> toppings = element.getSpecifics().get(OrderElementSpecificsType.TOPPING);

        Pizza.Builder pizzaBuilder = Pizza.builder(Dough.valueOf(dough.toUpperCase()), Size.MEDIUM);
        return pizzaBuilder
                .sauce(sauce)
                .toppings(toppings)
                .build();
    }
}

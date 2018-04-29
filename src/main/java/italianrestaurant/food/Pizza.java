package italianrestaurant.food;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pizza implements Food, Bakeable {

    private final Dough dough;
    private final Size size;
    private final String sauce;
    private final List<String> toppings;

    public static class Builder {

        private Dough dough;
        private Size size;
        private String sauce;
        private List<String> toppings;

        public Builder(Dough dough, Size size) {
            this.dough = dough;
            this.size = size;
            this.toppings = new ArrayList<>();
        }

        public Pizza build() {
            return new Pizza(this);
        }

        public Builder sauce(String sauce) {
            this.sauce = sauce;
            return this;
        }

        public Builder toppings(List<String> toppings) {
            this.toppings.addAll(toppings);
            return this;
        }

        public Builder topping(String topping) {
            this.toppings.add(topping);
            return this;
        }
    }

    public static Builder builder(Dough dough, Size size) {
        Objects.requireNonNull(dough);
        Objects.requireNonNull(size);
        return new Builder(dough, size);
    }

    private Pizza(Builder builder) {
        this.dough = builder.dough;
        this.size = builder.size;
        this.sauce = builder.sauce;
        this.toppings = builder.toppings;
    }

    public Dough getDough() {
        return dough;
    }

    public Size getSize() {
        return size;
    }

    public String getSauce() {
        return sauce;
    }

    public List<String> getToppings() {
        // aby nikt nie zmienił składników przy pobieraniu tworzymy i zwracamy ich kopię
        List<String> tmpList = new ArrayList<>(toppings);
        return tmpList;
    }

    public void addToppings(String topping) {
        toppings.add("toppings");
    }

    public void bake(long timeInMillis) {
        try {
            Thread.sleep(timeInMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "dough=" + dough +
                ", size=" + size +
                ", sauce='" + sauce + '\'' +
                ", toppings=" + toppings +
                '}';
    }
}

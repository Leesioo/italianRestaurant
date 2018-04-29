package italianrestaurant.food;

import java.util.ArrayList;
import java.util.List;

public class Pasta implements Food{
    private final PastaKind pastaKind;
    private final String sauce;
    private final List<String> additions;
    private final boolean isParmezan;

    public static class Builder {

        private PastaKind pastaKind;
        private String sauce;
        private List<String> additions;
        private boolean isParmezan;

        public Builder() {
            this.additions = new ArrayList<>();
        }

        public Pasta build() {
            return new Pasta(this);
        }

        public Builder pastaKind(PastaKind pastaKind) {
            this.pastaKind = pastaKind;
            return this;
        }

        public Builder sauce(String sauce) {
            this.sauce = sauce;
            return this;
        }

        public Builder additions(List<String> additions) {
            this.additions.addAll(additions);
            return this;
        }

        public Builder addition(String addition) {
            this.additions.add(addition);
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private Pasta(Builder builder) {
        this.pastaKind = builder.pastaKind;
        this.isParmezan = builder.isParmezan;
        this.sauce = builder.sauce;
        this.additions = builder.additions;
    }

    public PastaKind getPastaKind() {
        return pastaKind;
    }

    public String getSauce() {
        return sauce;
    }

    public List<String> getAdditions() {
        return new ArrayList<>(additions);
    }

    public boolean isParmezan() {
        return isParmezan;
    }

    @Override
    public String toString() {
        return "Pasta{" +
                "pastaKind=" + pastaKind +
                ", sauce='" + sauce + '\'' +
                ", additions=" + additions +
                ", isParmezan=" + isParmezan +
                '}';
    }
}

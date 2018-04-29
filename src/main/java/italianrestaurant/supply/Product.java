package italianrestaurant.supply;

public class Product {
    private Integer id;
    private String description;
    private OrderElement element;

    public Product(Integer id, String description, OrderElement element) {
        this.id = id;
        this.description = description;
        this.element = element;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public OrderElement getElement() {
        return element;
    }
}

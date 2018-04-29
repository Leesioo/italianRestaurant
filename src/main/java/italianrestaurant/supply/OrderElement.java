package italianrestaurant.supply;

import java.util.*;

public class OrderElement {
    private final OrderElementType elementType;
    private final Map<OrderElementSpecificsType, List<String>> specifics;

    public OrderElement(OrderElementType elementType) {
        this.elementType = elementType;
        this.specifics = new HashMap<>();
    }

    public void addSpecifics(OrderElementSpecificsType type, String specific){
        List<String> collectionOfSpecificsForType = new ArrayList<>();
        collectionOfSpecificsForType.add(specific);
        specifics.merge(type, collectionOfSpecificsForType, (oldSpecifics, newSpecifics) -> {oldSpecifics.addAll(newSpecifics); return oldSpecifics;});
    }

    public OrderElementType getElementType() {
        return elementType;
    }

    public Map<OrderElementSpecificsType, List<String>> getSpecifics() {
        return specifics;
    }
}

package ecommerce_flutter.dto.request;
import ecommerce_flutter.model.Item;

import java.util.List;

public class OrderRequest {
    private String userId;
    private List<Item> items;

    // getters and setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}


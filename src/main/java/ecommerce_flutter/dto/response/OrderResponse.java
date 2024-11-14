package ecommerce_flutter.dto.response;

import ecommerce_flutter.model.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private String orderId;
    private String userId;
    private LocalDateTime orderDate;
    private String status;
    private List<ItemResponse> items;




    // Constructors, getters, setters
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ItemResponse {
        private String title;
        private String imageUrl;
        private int quantity;
        private double price;
        // Constructors, getters, setters
    }
}


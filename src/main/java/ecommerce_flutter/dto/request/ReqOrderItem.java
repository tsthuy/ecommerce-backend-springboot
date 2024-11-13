package ecommerce_flutter.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ecommerce_flutter.model.Order;
import ecommerce_flutter.model.Product;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReqOrderItem {
    int quantity;
    Order order;
    Product product;
}

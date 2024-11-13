package ecommerce_flutter.dto.response;

import ecommerce_flutter.model.OrderItem;
import ecommerce_flutter.model.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResOrderDTO {
    String id;
    Boolean status;
    User user;
    List<OrderItem> orderItems;
}

package ecommerce_flutter.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.File;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreationRequest {
    String title;
    String description;
    String imageUrl;
    String review;
    double price;
    String category;
    double rate;
}

package ecommerce_flutter.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "items")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String title;
    private String description;
    private String imageUrl;
    private String review;
    private String seller;
    private double price;
    private String category;
    private double rate;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}

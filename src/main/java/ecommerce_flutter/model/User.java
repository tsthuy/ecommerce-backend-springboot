package ecommerce_flutter.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder(toBuilder = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "username", unique = true)
    private String username;
    private String password;

    @Column(name = "role", columnDefinition = "varchar(255) default 'user'")
    private String role;
}

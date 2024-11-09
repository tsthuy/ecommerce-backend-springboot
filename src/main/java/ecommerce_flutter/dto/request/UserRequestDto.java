package ecommerce_flutter.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequestDto implements Serializable {
    @Size(min = 4, message = "USERNAME_INVALID")
    private String username;
    @Size(min = 6, message = "INVALID PASSWORD")
    private String password;
}

package ecommerce_flutter.controller;

import ecommerce_flutter.cloudinary.CloudinaryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/products")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    private final CloudinaryService cloudinaryService;
    @Autowired
    public ProductController(CloudinaryService cloudinaryService){
        this.cloudinaryService = cloudinaryService;
    }
    @PostMapping("/uploadImage")
    public String uploadImage(@RequestBody String base64Image) throws IOException{
        return cloudinaryService.uploadImage(base64Image); //return imageUrl
    }
}

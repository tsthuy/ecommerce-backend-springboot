package ecommerce_flutter.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;

@Service
public class CloudinaryService {
    private final Cloudinary cloudinary;

    public CloudinaryService(
            @Value("${cloudinary.cloud_name}") String cloudName,
            @Value("${cloudinary.api_key}") String apiKey,
            @Value("${cloudinary.api_secret}") String apiSecret) {
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret));
    }

    public String uploadImage(String base64Image) throws IOException {
        // Giải mã chuỗi Base64
        byte[] decodedBytes = Base64.getDecoder().decode(base64Image);
        // Tải ảnh lên Cloudinary và nhận lại URL
        Map uploadResult = cloudinary.uploader().upload(decodedBytes, ObjectUtils.emptyMap());
        return (String) uploadResult.get("secure_url");
    }
}

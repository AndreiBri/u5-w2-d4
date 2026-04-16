package andreibri.u5_w2_d3.services;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    public String upload(MultipartFile file) throws IOException {
        Map res = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        return res.get("url").toString();
    }
}
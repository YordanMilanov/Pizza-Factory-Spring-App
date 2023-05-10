package bg.softuni.pizzashop.service.impl;

import bg.softuni.pizzashop.service.ImageCloudService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.UUID;

@Service
public class ImageCloudServiceImpl implements ImageCloudService {

    private Cloudinary cloudinary;


    //setting up our details from cloudinary in the constructor
    public ImageCloudServiceImpl() {
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "duwdabwhr",
                "api_key", "748476418545365",
                "api_secret", "YIfc2Zi2bW93GHKzkTjW77kBH7Q",
                "secure", true));
    }

    public String saveImage(MultipartFile multipartFile) {
        String imageId = UUID.randomUUID().toString();
        Map params = ObjectUtils.asMap(
                "public_id", imageId,
                "overwrite", true,
                "resource_type", "image");

        File tmpFile = new File(imageId);
        try {
            Files.write(tmpFile.toPath(), multipartFile.getBytes());
            cloudinary.uploader().upload(tmpFile, params);
            Files.delete(tmpFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException();
        }

        String versionNumber = Long.toString(System.currentTimeMillis());
        return String.format("https://res.cloudinary.com/duwdabwhr/image/upload/v%s/%s.%s", versionNumber, imageId, getFileExtension(multipartFile.getOriginalFilename()));
    }

    private String getFileExtension(String fileName) { return fileName.substring(fileName.lastIndexOf(".") + 1);}
}

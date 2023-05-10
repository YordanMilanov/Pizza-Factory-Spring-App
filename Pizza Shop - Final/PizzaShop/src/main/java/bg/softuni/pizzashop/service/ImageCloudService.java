package bg.softuni.pizzashop.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageCloudService {

    String saveImage(MultipartFile multipartFile);
}

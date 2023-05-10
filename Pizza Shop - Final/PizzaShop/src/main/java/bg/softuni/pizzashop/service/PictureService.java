package bg.softuni.pizzashop.service;

import bg.softuni.pizzashop.model.entity.Picture;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PictureService {

    Picture uploadPicture(MultipartFile file, String name) throws IOException;
}

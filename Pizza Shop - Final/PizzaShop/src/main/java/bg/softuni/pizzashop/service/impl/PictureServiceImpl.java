package bg.softuni.pizzashop.service.impl;

import bg.softuni.pizzashop.model.entity.Picture;
import bg.softuni.pizzashop.repository.PictureRepository;
import bg.softuni.pizzashop.service.ImageCloudService;
import bg.softuni.pizzashop.service.PictureService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;

    private ImageCloudService imageCloudService;
    public PictureServiceImpl(PictureRepository pictureRepository, ImageCloudService imageCloudService) {
        this.pictureRepository = pictureRepository;
        this.imageCloudService = imageCloudService;
    }

    @Override
    public Picture uploadPicture(MultipartFile file, String name) throws IOException {
        Picture picture = new Picture();

//        no cloudinary - directly to the base
//        String encodedImage = Base64.getEncoder().encodeToString(file.getBytes());
//        picture.setData(encodedImage);

        picture.setTitle(name);
        String pictureURL = imageCloudService.saveImage(file);
        picture.setURL(pictureURL);
        pictureRepository.save(picture);

        return picture;
    }
}

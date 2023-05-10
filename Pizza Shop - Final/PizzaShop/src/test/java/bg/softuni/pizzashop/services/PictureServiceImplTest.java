package bg.softuni.pizzashop.services;

import bg.softuni.pizzashop.model.entity.Picture;
import bg.softuni.pizzashop.repository.PictureRepository;
import bg.softuni.pizzashop.service.ImageCloudService;
import bg.softuni.pizzashop.service.impl.PictureServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class PictureServiceImplTest {

    @Mock
    private PictureRepository pictureRepositoryMock;

    @Mock
    private ImageCloudService imageCloudServiceMock;

    @InjectMocks
    private PictureServiceImpl pictureService;

    @Test
    public void uploadPicture_ShouldReturnSavedPicture() throws IOException {
        MultipartFile file = new MockMultipartFile("testFile.jpg", new byte[]{});
        String name = "Test Picture";

        Picture expectedPicture = new Picture();
        expectedPicture.setTitle(name);
        expectedPicture.setURL("https://example.com/testFile.jpg");

        Mockito.when(imageCloudServiceMock.saveImage(file)).thenReturn(expectedPicture.getURL());
        Mockito.when(pictureRepositoryMock.save(Mockito.any(Picture.class))).thenReturn(expectedPicture);

        Picture actualPicture = pictureService.uploadPicture(file, name);

        Assertions.assertEquals(expectedPicture.getURL(), actualPicture.getURL());
        Assertions.assertEquals(expectedPicture.getId(), actualPicture.getId());
        Assertions.assertEquals(expectedPicture.getTitle(), actualPicture.getTitle());
        Mockito.verify(imageCloudServiceMock, Mockito.times(1)).saveImage(file);
        Mockito.verify(pictureRepositoryMock, Mockito.times(1)).save(Mockito.any(Picture.class));
    }

}

package bg.softuni.pizzashop.services;

import bg.softuni.pizzashop.model.entity.Product;
import bg.softuni.pizzashop.model.service.ProductServiceModel;
import bg.softuni.pizzashop.repository.ProductRepository;
import bg.softuni.pizzashop.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void testFindLastAddedProduct() {
        // Create a product object to be returned by the repository
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(10);

        ProductServiceModel productServiceModelMapped = new ProductServiceModel();
        productServiceModelMapped.setId(1l);
        productServiceModelMapped.setName("Test Product");
        productServiceModelMapped.setPrice(BigDecimal.TEN);

        when(modelMapper.map(product, ProductServiceModel.class)).thenReturn(productServiceModelMapped);

        // Mock the repository to return the product object when the findTopByOrderByIdDesc method is called
        when(productRepository.findTopByOrderByIdDesc()).thenReturn(Optional.of(product));

        // Call the findLastAddedProduct method and assert that it returns the correct product service model
        ProductServiceModel productServiceModel = productService.findLastAddedProduct();
        assertEquals(product.getId(), productServiceModel.getId());
        assertEquals(product.getName(), productServiceModel.getName());
        assertEquals(product.getPrice(), productServiceModel.getPrice().doubleValue());
    }

    @Test
    void testFindLastAddedProduct_2() {
        // Arrange
        Product product = new Product();
        product.setId(1L);
        ProductServiceModel expectedProductServiceModel = new ProductServiceModel();
        expectedProductServiceModel.setId(1L);

        when(productRepository.findTopByOrderByIdDesc()).thenReturn(Optional.of(product));
        when(modelMapper.map(product, ProductServiceModel.class)).thenReturn(expectedProductServiceModel);

        // Act
        ProductServiceModel actualProductServiceModel = productService.findLastAddedProduct();

        // Assert
        assertNotNull(actualProductServiceModel);
        assertEquals(expectedProductServiceModel.getId(), actualProductServiceModel.getId());
        verify(productRepository).findTopByOrderByIdDesc();
        verify(modelMapper).map(product, ProductServiceModel.class);
    }


    @Test
    public void testSaveProduct() {
        // Arrange
        ProductServiceModel productServiceModel = new ProductServiceModel();
        productServiceModel.setName("Test Product");
        productServiceModel.setPrice(BigDecimal.valueOf(10.99));

        Product productToSave = new Product();
        productToSave.setName("Test Product");
        productToSave.setPrice(10.99);
        productToSave.setGrams(0);
        productToSave.setCaloriesPer100(0);

        when(modelMapper.map(productServiceModel, Product.class)).thenReturn(productToSave);
        when(modelMapper.map(productToSave, ProductServiceModel.class)).thenReturn(productServiceModel);

        // Act
        ProductServiceModel result = productService.saveProduct(productServiceModel);

        // Assert

        //check if the productRepository save method is invoked
        verify(productRepository, times(1)).save(productToSave);

        //check if the saved product is returned as product service model
        assertEquals(productServiceModel, result);
    }
}
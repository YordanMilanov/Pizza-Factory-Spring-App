package bg.softuni.pizzashop.model.binding;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Size;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserRegisterBindingModelTests {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

    }

    @Test
    public void testGettersAndSetters() {
        UserRegisterBindingModel user = new UserRegisterBindingModel();

        user.setUsername("john123");
        user.setFullName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPhone("555-1234");
        user.setPassword("password");
        user.setConfirmPassword("password");
        user.setCity("New York");
        user.setNeighborhood("Brooklyn");
        user.setStreet("Main St");
        user.setStreetNumber(123);

        assertEquals("john123", user.getUsername());
        assertEquals("John Doe", user.getFullName());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("555-1234", user.getPhone());
        assertEquals("password", user.getPassword());
        assertEquals("password", user.getConfirmPassword());
        assertEquals("New York", user.getCity());
        assertEquals("Brooklyn", user.getNeighborhood());
        assertEquals("Main St", user.getStreet());
        assertEquals(123, user.getStreetNumber());
    }

    @Test
    public void testValidModel() {
        UserRegisterBindingModel model = new UserRegisterBindingModel();
        model.setUsername("testuser");
        model.setFullName("Test User");
        model.setEmail("test@example.com");
        model.setPhone("1234567890");
        model.setPassword("password");
        model.setConfirmPassword("password");
        model.setCity("Sofia");
        model.setNeighborhood("Mladost");
        model.setStreet("Alexander Malinov");
        model.setStreetNumber(42);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Assertions.assertTrue(validator.validate(model).isEmpty());
    }

    @Test
    public void testInvalidModel() {
        UserRegisterBindingModel invalidUserRegisterBindingModel = new UserRegisterBindingModel();
        invalidUserRegisterBindingModel.setUsername("test");
        invalidUserRegisterBindingModel.setFullName("Test User Test User Test User Test User Test User Test User Test User Test User Test User Test User Test User");
        invalidUserRegisterBindingModel.setEmail("test@example");
        invalidUserRegisterBindingModel.setPhone("12345");
        invalidUserRegisterBindingModel.setPassword("pass");
        invalidUserRegisterBindingModel.setConfirmPassword("pass");
        invalidUserRegisterBindingModel.setCity("S");
        invalidUserRegisterBindingModel.setNeighborhood("N");
        invalidUserRegisterBindingModel.setStreet("S");
        invalidUserRegisterBindingModel.setStreetNumber(-1);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Assertions.assertFalse(validator.validate(invalidUserRegisterBindingModel).isEmpty());
    }


    @Test
    public void testValidationSuccess() {
        UserRegisterBindingModel user = new UserRegisterBindingModel();
        user.setUsername("john123");
        user.setFullName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPhone("555-1234");
        user.setPassword("password");
        user.setConfirmPassword("password");
        user.setCity("New York");
        user.setNeighborhood("Brooklyn");
        user.setStreet("Main St");
        user.setStreetNumber(123);

        Set<ConstraintViolation<UserRegisterBindingModel>> violations = validator.validate(user);
        assertEquals(0, violations.size());
    }

    @Test
    public void testValidationFailure() {
        UserRegisterBindingModel invalidUserRegisterBindingModel = new UserRegisterBindingModel();
        invalidUserRegisterBindingModel.setUsername("test");
        invalidUserRegisterBindingModel.setFullName("Test User Test User Test User Test User Test User Test User Test User Test User Test User Test User Test User");
        invalidUserRegisterBindingModel.setEmail("test@example");
        invalidUserRegisterBindingModel.setPhone("12345");
        invalidUserRegisterBindingModel.setPassword("pass");
        invalidUserRegisterBindingModel.setConfirmPassword("pass");
        invalidUserRegisterBindingModel.setCity("S");
        invalidUserRegisterBindingModel.setNeighborhood("N");
        invalidUserRegisterBindingModel.setStreet("S");
        invalidUserRegisterBindingModel.setStreetNumber(-1);
        Set<ConstraintViolation<UserRegisterBindingModel>> violations = validator.validate(invalidUserRegisterBindingModel);
        assertEquals(8, violations.size());
    }

    @Test
    public void testStreetNumberValidation() {
        UserRegisterBindingModel validUser = new UserRegisterBindingModel();
        validUser.setUsername("manager");
        validUser.setFullName("manager");
        validUser.setEmail("test@example.com");
        validUser.setPhone("1234567890");
        validUser.setPassword("manager");
        validUser.setConfirmPassword("manager");
        validUser.setCity("city");
        validUser.setNeighborhood("street");
        validUser.setStreet("street");
        validUser.setStreetNumber(-1);


        Set<ConstraintViolation<UserRegisterBindingModel>> violations = validator.validate(validUser);
        assertEquals(1, violations.size());
        assertEquals("Street number must be positive", violations.iterator().next().getMessage());
    }
}

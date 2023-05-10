package bg.softuni.pizzashop.services;

import bg.softuni.pizzashop.model.binding.UserRegisterBindingModel;
import bg.softuni.pizzashop.model.entity.Address;
import bg.softuni.pizzashop.model.entity.Role;
import bg.softuni.pizzashop.model.entity.User;
import bg.softuni.pizzashop.model.entity.enums.RoleNameEnum;
import bg.softuni.pizzashop.model.service.UserServiceModel;
import bg.softuni.pizzashop.repository.AddressRepository;
import bg.softuni.pizzashop.repository.RoleRepository;
import bg.softuni.pizzashop.repository.UserRepository;
import bg.softuni.pizzashop.service.impl.AuthServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceImplTest {

    @Mock
    private PasswordEncoder mockPasswordEncoder;

    @Mock
    private UserRepository mockUserRepository;


    @Mock
    private ModelMapper mockModelMapper;

    @Mock
    private RoleRepository mockRoleRepository;

    @Mock
    private AddressRepository mockAddressRepository;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    private AuthServiceImpl toTest;

    //setup
    @BeforeEach
    void setUp() {
        mockModelMapper = mock(ModelMapper.class);
        mockPasswordEncoder = mock(PasswordEncoder.class);
        toTest = new AuthServiceImpl(mockUserRepository,
                mockPasswordEncoder,
                mockModelMapper,
                mockRoleRepository,
                mockAddressRepository);
    }

    @Test
    void registerUser() {

        //ARRANGE
        String encodedPassword = "encoded_password";

        UserRegisterBindingModel testUserRegisterBindingModel = new UserRegisterBindingModel();
        testUserRegisterBindingModel.setUsername("username");
        testUserRegisterBindingModel.setFullName("Test Name");
        testUserRegisterBindingModel.setEmail("test@example.com");
        testUserRegisterBindingModel.setPhone("phone");
        testUserRegisterBindingModel.setPassword("password");
        testUserRegisterBindingModel.setConfirmPassword("password");
        testUserRegisterBindingModel.setCity("city");
        testUserRegisterBindingModel.setNeighborhood("neighborhood");
        testUserRegisterBindingModel.setStreet("street");
        testUserRegisterBindingModel.setStreetNumber(1);

        Address expectedAddress = new Address();
        expectedAddress.setCity("city");
        expectedAddress.setNeighborhood("neighborhood");
        expectedAddress.setStreet("street");
        expectedAddress.setStreetNumber(1);
        expectedAddress.setId(1L);


        User expectedUser = new User();
        expectedUser.setUsername("username");
        expectedUser.setFullName("Test Name");
        expectedUser.setEmail("test@example.com");
        expectedUser.setPhone("phone");
        expectedUser.setPassword("password");
        expectedUser.setAddress(expectedAddress);

        Role expectedRole = new Role();
        expectedRole.setRole(RoleNameEnum.MANAGER);

        when(mockRoleRepository.findByRole("MANAGER")).thenReturn(Optional.of(expectedRole));
        when(mockModelMapper.map(testUserRegisterBindingModel, User.class)).thenReturn(expectedUser);
        when(mockAddressRepository.findByCityAndAndNeighborhoodAndStreetAndStreetNumber("city", "neighborhood", "street", 1)).thenReturn(Optional.of(expectedAddress));

        //ACT
        toTest.registerUser(testUserRegisterBindingModel);

        //ASSERT

        //assert that the userRepository.save() and address repo save is methods is called
        verify(mockUserRepository).save((any()));

        //return the saved argument as captor
        verify(mockUserRepository).save(userArgumentCaptor.capture());

        //get the saved user
        User actualSavedUser = userArgumentCaptor.getValue();

        //assert the information in the saved and passed users
        assertEquals(testUserRegisterBindingModel.getUsername(), actualSavedUser.getUsername());
        assertEquals(testUserRegisterBindingModel.getEmail(), actualSavedUser.getEmail());
        assertEquals(testUserRegisterBindingModel.getPhone(), actualSavedUser.getPhone());
        assertEquals(testUserRegisterBindingModel.getFullName(), actualSavedUser.getFullName());
        Assertions.assertNotEquals(testUserRegisterBindingModel.getPassword(), actualSavedUser.getPassword());

        if(actualSavedUser.getAddress().getId() != null) {
            assertEquals(testUserRegisterBindingModel.getCity(), actualSavedUser.getAddress().getCity());
            assertEquals(testUserRegisterBindingModel.getNeighborhood(), actualSavedUser.getAddress().getNeighborhood());
            assertEquals(testUserRegisterBindingModel.getStreet(), actualSavedUser.getAddress().getStreet());
            assertEquals(testUserRegisterBindingModel.getStreetNumber(), actualSavedUser.getAddress().getStreetNumber());
        } else {
        verify(mockAddressRepository).save(any());
        }
    }
}


package bg.softuni.pizzashop.services;

import bg.softuni.pizzashop.model.entity.User;
import bg.softuni.pizzashop.model.view.UserView;
import bg.softuni.pizzashop.repository.OrderRepository;
import bg.softuni.pizzashop.repository.RoleRepository;
import bg.softuni.pizzashop.repository.UserRepository;
import bg.softuni.pizzashop.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private ModelMapper mockModelMapper;

    @Mock
    private RoleRepository mockRoleRepository;

    @Mock
    private OrderRepository mockOrderRepository;

    private UserServiceImpl toTest;

    @BeforeEach
    void setUp() {
        mockModelMapper = mock(ModelMapper.class);
        toTest = new UserServiceImpl(mockUserRepository, mockModelMapper, mockRoleRepository, mockOrderRepository);
    }

    @Test
    void testGetAll() {

        // Mock the repository to return some test data
        User user1 = new User();
        User user2 = new User();
        user1.setUsername("user 1");
        user2.setUsername("user 2");
        when(mockUserRepository.findAll()).thenReturn(List.of(user1, user2));

        // Mock the mapper to return the expected UserView objects
        UserView userView1 = new UserView();
        userView1.setUsername("user 1");
        UserView userView2 = new UserView();
        userView2.setUsername("user 2");

        when(mockModelMapper.map(user1, UserView.class)).thenReturn(userView1);
        when(mockModelMapper.map(user2, UserView.class)).thenReturn(userView2);

        // Call the method being tested
        List<UserView> all = toTest.getAll();
        List<UserView> result = List.of(userView1, userView2);

        // Verify the expected results
        Assertions.assertEquals(2, all.size());
        Assertions.assertEquals(result.get(0).getUsername(), all.get(0).getUsername());
        Assertions.assertEquals(result.get(1).getUsername(), all.get(1).getUsername());
    }
}

package bg.softuni.pizzashop.service.impl;

import bg.softuni.pizzashop.model.entity.Address;
import bg.softuni.pizzashop.model.entity.Order;
import bg.softuni.pizzashop.model.entity.Role;
import bg.softuni.pizzashop.model.entity.User;
import bg.softuni.pizzashop.model.entity.enums.RoleNameEnum;
import bg.softuni.pizzashop.model.entity.enums.UserLevelEnum;
import bg.softuni.pizzashop.model.view.UserView;
import bg.softuni.pizzashop.repository.AddressRepository;
import bg.softuni.pizzashop.repository.OrderRepository;
import bg.softuni.pizzashop.repository.RoleRepository;
import bg.softuni.pizzashop.repository.UserRepository;
import bg.softuni.pizzashop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;

    private final OrderRepository orderRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleRepository roleRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<UserView> getAll() {
        List<User> allUsers = userRepository.findAll();

        List<UserView> collect = allUsers.stream().map(user -> modelMapper.map(user, UserView.class))
                .collect(Collectors.toList());
        return collect;
    }

    public UserView getUserViewModelById(Long id) {
        return modelMapper.map(userRepository.findById(id).get(), UserView.class);
    }

    @Override
    public void deleteRole(Long userId, Long roleId) throws Exception {
        User user = userRepository.findById(userId).get();
        Role role = roleRepository.findById(roleId).get();
        Set<Role> roles = user.getRoles();

        //if the user is left with no roles set his rank to the lowest
        if (roles.size() <= 1) {
            Role customerRole = roleRepository.findByRole("CUSTOMER").get();
            Set<Role> customerRoleSet = new HashSet<>(Collections.singleton(customerRole));
            user.setRoles(customerRoleSet);
            user.setLevel(UserLevelEnum.VIP);
            userRepository.save(user);
            throw new Exception("User must have at least one role! The rank of the account is set to the lowest possible - CUSTOMER, and the level is set to VIP");
        }

        //remove role
        for (Role currentRole : roles) {
            if (currentRole.getRole().toString().equals(role.getRole().toString())) {
                roles.remove(currentRole);
                //if the user has only CUSTOMER role, his level is set to VIP
                if (roles.size() == 1) {
                    for (Role lastRole : roles) {
                        if (lastRole.getRole() == RoleNameEnum.CUSTOMER) {
                            user.setLevel(UserLevelEnum.VIP);
                        }
                    }
                }
                break;
            }
        }

        //update user
        user.setRoles(roles);
        userRepository.save(user);

    }

    @Override
    public void addRoleToUser(Long userId, String selectedRole) throws Exception {
        User user = userRepository.findById(userId).get();

        if (!roleRepository.findByRole(selectedRole).isPresent()) {
            throw new Exception("Please select valid role!");
        }

        Role role = roleRepository.findByRole(selectedRole).get();

        for (Role userRole : user.getRoles()) {
            if (userRole.getRole().toString().equals(selectedRole)) {
                throw new Exception("The user already has this role!");
            }
        }
        user.getRoles().add(role);

        for (Role userRole : user.getRoles()) {
            if (userRole.getRole() == RoleNameEnum.STAFF || userRole.getRole() == RoleNameEnum.MANAGER) {
                user.setLevel(UserLevelEnum.EMPLOYEE);
            }
        }
        userRepository.save(user);
    }


    @Override
    public void deleteUser(Long id) {
        List<Order> orders = orderRepository.findByUser_Id(id).get();
        orderRepository.deleteAll(orders);
        userRepository.deleteById(id);
    }

    @Override
    public UserView getUserViewModel(String username) {
        return modelMapper.map(userRepository.findByUsername(username), UserView.class);
    }

    public boolean isUsernameUsed(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public void updateUsername(String oldName, String username) {
        User user = userRepository.findByUsername(oldName).get();
        user.setUsername(username);
        userRepository.save(user);

    }
}

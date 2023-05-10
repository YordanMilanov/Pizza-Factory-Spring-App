package bg.softuni.pizzashop.service.impl;

import bg.softuni.pizzashop.model.binding.UserRegisterBindingModel;
import bg.softuni.pizzashop.model.entity.Address;
import bg.softuni.pizzashop.model.entity.User;
import bg.softuni.pizzashop.model.entity.enums.RoleNameEnum;
import bg.softuni.pizzashop.model.entity.enums.UserLevelEnum;
import bg.softuni.pizzashop.model.service.UserServiceModel;
import bg.softuni.pizzashop.repository.AddressRepository;
import bg.softuni.pizzashop.repository.RoleRepository;
import bg.softuni.pizzashop.repository.UserRepository;
import bg.softuni.pizzashop.service.AuthService;
import org.aspectj.lang.annotation.After;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    private final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    private final RoleRepository roleRepository;

    private final AddressRepository addressRepository;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, RoleRepository roleRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public UserServiceModel registerUser(UserRegisterBindingModel userRegisterBindingModel) {
        userRegisterBindingModel.setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()));

        User user = modelMapper.map(userRegisterBindingModel, User.class);

        //make the first registered admin
        makeTheFirstRegisteredMANAGER(user);

        Address address = new Address();
        address.setCity(userRegisterBindingModel.getCity());
        address.setNeighborhood(userRegisterBindingModel.getNeighborhood());
        address.setStreet(userRegisterBindingModel.getStreet());
        address.setStreetNumber(userRegisterBindingModel.getStreetNumber());

        boolean addressAlreadyExists = addressRepository
                .findByCityAndAndNeighborhoodAndStreetAndStreetNumber(address.getCity(),
                        address.getNeighborhood(),
                        address.getStreet(),
                        address.getStreetNumber()).isPresent();


        if(addressAlreadyExists) {
            user.setAddress(addressRepository
                    .findByCityAndAndNeighborhoodAndStreetAndStreetNumber(address.getCity(),
                            address.getNeighborhood(),
                            address.getStreet(),
                            address.getStreetNumber()).get());
        } else {
        user.setAddress(addressRepository.save(address));
        }

        return modelMapper.map(userRepository.save(user), UserServiceModel.class);
    }


    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not Found!"));
    }

    //local methods
    private void makeTheFirstRegisteredMANAGER(User user) {
        if (userRepository.count() == 0) {
            user.setRoles(Set.of(roleRepository.findByRole("MANAGER").get()));
            user.setLevel(UserLevelEnum.EMPLOYEE);
        } else {
            user.setRoles(new HashSet<>());
            user.getRoles().add(roleRepository.findByRole(RoleNameEnum.CUSTOMER.toString()).get());
            user.setLevel(UserLevelEnum.NEW);
        }
    }

}

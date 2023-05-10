package bg.softuni.pizzashop.service;

import bg.softuni.pizzashop.model.binding.UserRegisterBindingModel;
import bg.softuni.pizzashop.model.entity.User;
import bg.softuni.pizzashop.model.service.UserServiceModel;

public interface AuthService {

    public UserServiceModel registerUser(UserRegisterBindingModel userRegisterBindingModel);

    public User getUserByUsername(String username);
}

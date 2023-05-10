package bg.softuni.pizzashop.service;

import bg.softuni.pizzashop.model.view.UserView;

import java.util.List;

public interface UserService {

    List<UserView> getAll();

    void deleteUser(Long id);

    UserView getUserViewModel(String username);

    public UserView getUserViewModelById(Long id);

    void deleteRole(Long userId, Long roleId) throws Exception;

    void addRoleToUser(Long userId, String selectedRole) throws Exception;

    boolean isUsernameUsed(String username);

    void updateUsername(String oldName, String username);
}

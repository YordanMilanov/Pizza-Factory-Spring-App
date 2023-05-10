package bg.softuni.pizzashop.aop;

import bg.softuni.pizzashop.model.binding.UserRegisterBindingModel;
import bg.softuni.pizzashop.model.service.UserServiceModel;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserServiceAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceAspect.class);

    @Pointcut("execution(public bg.softuni.pizzashop.model.service.UserServiceModel bg.softuni.pizzashop.service.impl.AuthServiceImpl.registerUser(bg.softuni.pizzashop.model.binding.UserRegisterBindingModel)) && args(userRegisterBindingModel)")
    public void registerUserPointcut(UserRegisterBindingModel userRegisterBindingModel) {}

    @AfterReturning(pointcut = "registerUserPointcut(userRegisterBindingModel)", returning = "userServiceModel")
    public void afterRegisterUser(UserRegisterBindingModel userRegisterBindingModel, UserServiceModel userServiceModel) {
        LOGGER.info("User with username {} has been successfully registered", userRegisterBindingModel.getUsername());
    }
}

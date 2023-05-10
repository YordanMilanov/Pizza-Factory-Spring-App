package bg.softuni.pizzashop.web;

import bg.softuni.pizzashop.errorHandling.ObjectNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.NoPermissionException;

//All exception messages are being passed to the custom exception page
@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @org.springframework.web.bind.annotation.ExceptionHandler(ObjectNotFoundException.class)
    protected String handleException(ObjectNotFoundException exception, Model model){
        model.addAttribute("message", "The requested resources are not found! the problem is being solved!");
        return "error.html";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(HttpClientErrorException.BadRequest.class)
    protected String handleException(Model model){
        model.addAttribute("message", "Please, check the request and resubmit!");
        return "error.html";
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    protected String handleException(Exception exception, Model model){
        model.addAttribute("message", "There was problem with the server, please try again!");
        return "error.html";
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @org.springframework.web.bind.annotation.ExceptionHandler(NoPermissionException.class)
    protected String handleException(NoPermissionException noPermissionException, Model model){
        model.addAttribute("message", "The link that you are trying to reach is not in your range of permissions!");
        return "error.html";
    }

}

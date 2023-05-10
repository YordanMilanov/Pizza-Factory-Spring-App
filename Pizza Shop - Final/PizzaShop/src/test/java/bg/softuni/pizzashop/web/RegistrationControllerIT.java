package bg.softuni.pizzashop.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testRegisterGetShown() throws Exception {
        mockMvc
                .perform(get("http://localhost:8080/users/register")).andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    @Test
    @WithMockUser(username = "manager2")
    void getAllComments() {

    }


    @Test
    void testRegistration_failure() throws Exception {
        mockMvc.perform(post("/users/register")
                        .param("username", "8")
                        .param("fullName", "7")
                        .param("email", "6@6.com")
                        .param("phone", "7")
                        .param("password", "6")
                        .param("passwordConfirm", "5")
                        .param("city", "8")
                        .param("center", "6")
                        .param("street", "5")
                        .param("streetNumber", "1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/register"));
    }

    @Test
    void testRegistration_success() throws Exception {
        mockMvc.perform(post("/users/register")
                        .param("username", "username")
                        .param("fullName", "username")
                        .param("email", "username6@email.com")
                        .param("phone", "username")
                        .param("password", "username")
                        .param("passwordConfirm", "username")
                        .param("city", "City")
                        .param("center", "Neighborhood")
                        .param("street", "Street")
                        .param("streetNumber", String.valueOf(1L))
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/register"));
    }

    @Test
    void testLogin_success() throws Exception {
        mockMvc.perform(post("/users/login")
                .param("username","manager2")
                .param("password","manager2")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void testLogin_failure() throws Exception {
        mockMvc.perform(post("/users/login")
                        .param("username","1")
                        .param("password","2")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/login?error=true"));
    }

}
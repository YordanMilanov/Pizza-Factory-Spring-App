package bg.softuni.pizzashop.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {



        httpSecurity.authorizeRequests().requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .antMatchers("/", "/menu/**", "/about").permitAll()
                .antMatchers("/users/register", "/users/login").anonymous()
                .antMatchers("/users/profile", "users/cart", "/api/**").authenticated()
                .antMatchers("/users/roles/**", "users/list/**").hasRole("MANAGER")
                .antMatchers("/product/**", "/ingredient/**").hasAnyRole("STAFF", "MANAGER")
                .antMatchers("/order/active", "/order/completed").hasAnyRole("STAFF", "MANAGER")
                .antMatchers("/api/**").permitAll()
                .antMatchers("/comments").permitAll()
                .and()
                //until here the above is one statement after and() we start doing second statement for the same httpSecurity object

                //now we change the default .formLogin() of spring security to our custom
                .formLogin()
                //path to our custom page
                .loginPage("/users/login")

                //this method requires the name of the <input name="username"> as a parameter.
                //UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY -> username of spring user
                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                //this method requires the name of the <input name="password"> as a parameter.
                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                //redirect if not successful and we add query parameter so we can render an error in the html for example error=true
                .failureUrl("/users/login?error=true")
                //successful redirect point
                .defaultSuccessUrl("/", true)
                .and()
                .logout()
                //custom url for logout, forcing to search exactly on it
                .logoutRequestMatcher(new AntPathRequestMatcher("/users/logout"))
                .deleteCookies()
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/");

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}
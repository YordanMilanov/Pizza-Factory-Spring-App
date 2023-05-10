package bg.softuni.pizzashop.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class I18NConfig {

    //by this class we understand which is the Locale(location/language) that the user want to use(his language request) and it comes from cookie with the request
    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setCookieName("lang");
        return cookieLocaleResolver;
    }

    //handler interceptor used to catch when some user want to change his Locale
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        //checks the http request and if there is param with name "lang" it knows that it has to change the language.
        // after that the localeResolver can change the language
        localeChangeInterceptor.setParamName("lang");

        return localeChangeInterceptor;
    }

    @Bean
    public MessageSource messageSource () {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();

        //the location of the messages.properties file which contains the default properties
        //located in static -> i18n -> messages
        resourceBundleMessageSource.setBasename("i18n/messages");
        resourceBundleMessageSource.setDefaultEncoding("UTF-8");
        return resourceBundleMessageSource;
    }

    //after that the interceptor must registered in configuration in the current situation -> webConfig
}

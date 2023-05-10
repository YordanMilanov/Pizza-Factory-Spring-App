package bg.softuni.pizzashop.config;

import bg.softuni.pizzashop.interceptor.IpBlackListInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {



    private final LocaleChangeInterceptor localeChangeInterceptor;
    private final IpBlackListInterceptor ipBlackListInterceptor;

    @Autowired
    public WebConfig(LocaleChangeInterceptor localeChangeInterceptor, IpBlackListInterceptor ipBlackListInterceptor) {
        this.localeChangeInterceptor = localeChangeInterceptor;
        this.ipBlackListInterceptor = ipBlackListInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //here we add our interceptors, and then we go to the html to set up the form that changes the locale parameter in the request
        registry.addInterceptor(localeChangeInterceptor);


        registry.addInterceptor(ipBlackListInterceptor);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}

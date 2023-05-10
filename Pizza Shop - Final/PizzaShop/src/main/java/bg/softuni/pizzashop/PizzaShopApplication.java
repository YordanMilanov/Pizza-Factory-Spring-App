package bg.softuni.pizzashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableScheduling
public class PizzaShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(PizzaShopApplication.class, args);
    }

}

package comicbook.store;

import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class StoreApplication {
    public static void main(String[] args) {SpringApplication.run(StoreApplication.class, args);}

    @Bean
    Hibernate5JakartaModule Hibernate5JakartaModule() {
        return new Hibernate5JakartaModule();
    }
}

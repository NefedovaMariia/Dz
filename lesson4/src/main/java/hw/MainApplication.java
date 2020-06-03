package hw;

import hw.domain.Person;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import hw.service.questionService;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/bundle");
        ms.setDefaultEncoding("windows-1251");
        return ms;
    }
    @Bean
    public CommandLineRunner commandLineRunner(AnnotationConfigApplicationContext ctx) {
        return args -> {
            questionService service = ctx.getBean(questionService.class);
            service.result(new Person());
        };
    }

}

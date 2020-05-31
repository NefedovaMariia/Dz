import config.ServicesConfig;
import domain.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import service.QuestionService;

@Configuration
@ComponentScan
public class Main {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ServicesConfig.class);
        context.refresh();
        QuestionService service =context.getBean(QuestionService.class);
        service.getPerson(new Person());
    }
}
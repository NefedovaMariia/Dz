import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.QuestionsService;
import domain.Person;


public class Main {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        QuestionsService s = context.getBean(QuestionsService.class);
        s.getPerson(new Person());
    }
}

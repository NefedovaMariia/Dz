import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.QuestionsService;
import domain.Person;


public class Main {

    private static final String CONFIG_LOCATION = "spring-context.xml";

    public static void main(String[] args) throws Exception {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(CONFIG_LOCATION);
        QuestionsService questionsService = context.getBean(QuestionsService.class);
        questionsService.getPerson(new Person());
    }
}

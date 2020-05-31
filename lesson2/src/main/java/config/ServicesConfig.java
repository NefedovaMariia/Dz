package config;

import dao.PersonDao;
import dao.PersonDaoSimple;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import service.QuestionService;
import service.QuestionServiceImpl;

@Configuration
public class ServicesConfig {
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/bundle");
        ms.setDefaultEncoding("windows-1251");
        return ms;
    }
    @Bean
    public PersonDao personDao() {
        return new PersonDaoSimple();
    }
    @Bean
    public QuestionService personService(PersonDao dao, MessageSource ms) {
        return new QuestionServiceImpl(dao, ms);
    }
}

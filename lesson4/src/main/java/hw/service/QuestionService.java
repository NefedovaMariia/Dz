package hw.service;

import hw.domain.Person;
import hw.domain.Question;

import java.util.List;

public interface QuestionService {

    List<Question> getQuestions() throws Exception;
    public void doPersonTest(Person person) throws Exception;
}

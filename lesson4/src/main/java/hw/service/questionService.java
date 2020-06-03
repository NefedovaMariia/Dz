package hw.service;

import hw.domain.Person;
import hw.domain.Question;

import java.util.List;

public interface questionService {
    List<Question> getQuestions() throws Exception;
    public void result(Person person) throws Exception;
}

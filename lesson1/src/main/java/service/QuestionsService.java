package service;
import domain.Person;
import domain.Questions;

import java.util.List;

public interface QuestionsService {
    List<Questions> getQuestions() throws Exception;
    void getPerson(Person person) throws Exception;
}

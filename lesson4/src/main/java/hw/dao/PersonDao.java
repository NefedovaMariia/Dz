package hw.dao;

import hw.domain.Question;

import java.util.List;

public interface PersonDao  {
    List<Question> readQuestionsFromSource() throws Exception;
}

package dao;
import domain.Questions;

import java.util.List;

public interface PersonDao {
    List<Questions> readQuestionsFromSource() throws Exception;
}

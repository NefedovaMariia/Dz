package service;

import dao.PersonDao;
import domain.Person;
import domain.Questions;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.lang.System.lineSeparator;

@Service
public class QuestionServiceImpl implements QuestionService {
    private MessageSource ms;
    private final PersonDao dao;

    public QuestionServiceImpl(PersonDao dao, MessageSource ms) {
        this.dao = dao;
        this.ms=ms;
    }

    protected int checkAnswers(List<Integer> studentsAnswers, List<Integer> rightAnswers) {
        int result = 0;
        Iterator studAnswersIter = studentsAnswers.iterator();
        Iterator rightAnswersIter = rightAnswers.iterator();
        for (; studAnswersIter.hasNext() && rightAnswersIter.hasNext(); ) {

            result += studAnswersIter.next().equals(rightAnswersIter.next()) ? 1 : 0;

        }
        return result;
    }
    public List<Questions> getQuestions ()throws Exception {
        return dao.readQuestionsFromSource();
    }
    private void printQuestion(Questions question) {
        System.out.println(ms.getMessage("question.number", null, Locale.getDefault()) + question.getNumber()
                + ":" + lineSeparator() + question.getQuestion() + lineSeparator()
                + ms.getMessage("question.answers", null, Locale.getDefault()) + lineSeparator() + "1. "
                + question.getAnswers().get(0) + lineSeparator()
                + "2. " + question.getAnswers().get(1) + lineSeparator()
                + "3. " + question.getAnswers().get(2) + lineSeparator()
                + ms.getMessage("person.enter", null, Locale.getDefault()));
    }
    private void printResult(Person person) {
        System.out.println(ms.getMessage("person.name", null, Locale.getDefault()) + person.getName() + " "
                + person.getSurname()
                + ", " + ms.getMessage("person.result1", null, Locale.getDefault()) + person.getResult() + " "
                + ms.getMessage("person.result2", null, Locale.getDefault()));
    }
    public void getPerson (Person person) throws Exception {
            Scanner in = new Scanner(System.in);
            System.out.println(ms.getMessage("enter.name", null, Locale.getDefault()));
            String name = in.nextLine();
            person.setName(name);
            System.out.println(ms.getMessage("enter.surname", null, Locale.getDefault()));
            String surname = in.nextLine();
            person.setSurname(surname);
            List<Questions> questions = this.getQuestions();
            List<Integer> rightAnswers = new ArrayList<>();
            List<Integer> studentsAnswers = new ArrayList<>();
            if (questions != null) {
                for (Questions question : questions) {
                    printQuestion(question);
                    try {
                        studentsAnswers.add(in.nextInt());
                    }
                    catch(Exception ex)
                    {
                        System.out.println(ms.getMessage("expc2.message", null, Locale.getDefault()));
                    }
                    rightAnswers.add(question.getRightNumber());
                }
                person.setResult(checkAnswers(studentsAnswers, rightAnswers));
                printResult(person);
            } else {
                System.out.println(ms.getMessage("expc1.message", null, Locale.getDefault()));
            }
    }
}



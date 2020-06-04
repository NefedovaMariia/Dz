package hw.service;

import hw.dao.PersonDao;
import hw.domain.Person;
import hw.domain.Question;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.lang.System.lineSeparator;


@Service
public class QuestionServiceImpl implements QuestionService {

    private MessageSource ms;
    private final PersonDao dao;

    private static final String ENTER_NAME_CODE = "enter.name";
    private static final String ENTER_SURNAME_CODE = "enter.surname";

    private static final String PERSON_ENTER_CODE = "person.enter";
    private static final String PERSON_NAME_CODE = "person.name";
    private static final String PERSON_RESULT1_CODE = "person.result1";
    private static final String PERSON_RESULT2_CODE = "person.result2";

    private static final String QUESTION_NUMBER_CODE = "question.number";
    private static final String QUESTION_ANSWERS_CODE = "question.answers";

    private static final String EXCEPTION_MESSAGE1_CODE = "expc1.message";
    private static final String EXCEPTION_MESSAGE2_CODE = "expc12.message";

    public QuestionServiceImpl(PersonDao dao, MessageSource ms) {
        this.ms = ms;
        this.dao = dao;
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

    public List<Question> getQuestions() throws Exception {
        return dao.readQuestionsFromSource();
    }

    private void printQuestion(Question question) {

        StringBuilder questionMessage =
                new StringBuilder(ms.getMessage(QUESTION_NUMBER_CODE, null, Locale.getDefault()))
                        .append(question.getNumber())
                        .append(":").append(lineSeparator())
                        .append(question.getQuestion())
                        .append(lineSeparator())
                        .append(ms.getMessage(QUESTION_ANSWERS_CODE, null, Locale.getDefault()))
                        .append(lineSeparator());


        int answerNumber = 1;

        for (String answer : question.getAnswers()) {
            questionMessage.append(answerNumber++).append(". ")
                    .append(answer)
                    .append(lineSeparator());
        }

        questionMessage.append(ms.getMessage(PERSON_ENTER_CODE, null, Locale.getDefault()));
        System.out.println(questionMessage.toString());
    }

    private void printResult(Person person) {
        System.out.println(
            new StringBuilder(ms.getMessage(PERSON_NAME_CODE, null, Locale.getDefault()))
                .append(person.getName()).append(" ")
                .append(person.getSurname()).append(", ")
                .append(ms.getMessage(PERSON_RESULT1_CODE, null, Locale.getDefault()))
                .append(person.getResult()).append(" ")
                .append(ms.getMessage(PERSON_RESULT2_CODE, null, Locale.getDefault()))
                .append(".")
            .toString());

/*
                ms.getMessage(PERSON_NAME_CODE, null, Locale.getDefault()) + person.getName() + " "
                + person.getSurname()
                + ", " + ms.getMessage("person.result1", null, Locale.getDefault()) + person.getResult() + " "
                + ms.getMessage("person.result2", null, Locale.getDefault()));*/
    }

    Person getPerson(Person person) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println(ms.getMessage(ENTER_NAME_CODE, null, Locale.getDefault()));
        String name = in.nextLine();
        person.setName(name);
        System.out.println(ms.getMessage(ENTER_SURNAME_CODE, null, Locale.getDefault()));
        String surname = in.nextLine();
        person.setSurname(surname);
        return person;
    }

    public void doPersonTest(Person person) throws Exception {

        getPerson(person);

        Scanner in = new Scanner(System.in);
        List<Question> questions = this.getQuestions();
        List<Integer> rightAnswers = new ArrayList<>();
        List<Integer> studentsAnswers = new ArrayList<>();

        if (questions != null) {
            for (Question question : questions) {
                printQuestion(question);
                try {
                    studentsAnswers.add(in.nextInt());
                } catch (Exception ex) {
                    System.out.println(
                            ms.getMessage(EXCEPTION_MESSAGE2_CODE, null, Locale.getDefault()));
                }
                rightAnswers.add(question.getRightNumber());
            }
            person.setResult(checkAnswers(studentsAnswers, rightAnswers));
            printResult(person);
        } else {
            System.out.println(
                    ms.getMessage(EXCEPTION_MESSAGE1_CODE, null, Locale.getDefault()));
        }
    }
}

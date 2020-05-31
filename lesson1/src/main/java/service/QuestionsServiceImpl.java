package service;

import dao.PersonDao;
import domain.Person;
import domain.Questions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class QuestionsServiceImpl implements QuestionsService {

    private PersonDao dao;

    public QuestionsServiceImpl(PersonDao dao) {
        this.dao = dao;
    }

    protected int checkAnswers(List<Integer> studentsAnswers, List<Integer> rightAnswers) {

        int result = 0;

        Iterator studAnswersIter = studentsAnswers.iterator();
        Iterator rightAnswersIter = rightAnswers.iterator();

        for (; studAnswersIter.hasNext() && rightAnswersIter.hasNext(); ) {

            result += studAnswersIter.next().equals(rightAnswersIter.next())  ? 1 : 0;
        }

        return result;
    }

    public List<Questions> getQuestions() throws Exception {
        return dao.readQuestionsFromSource();
    }

    public void getPerson(Person person) throws Exception {

        Scanner in = new Scanner(System.in);
        System.out.println("Name: ");

        String name = in.nextLine();
        person.setName(name);
        System.out.println("Surname: ");

        String surname = in.nextLine();
        person.setSurname(surname);

        List<Questions> questions = getQuestions();
        List<Integer> rightAnswers = new ArrayList<>();
        List<Integer> studentsAnswers = new ArrayList<>();

        if (questions != null) {
            for (Questions question : questions) {

                System.out.println(question);
                studentsAnswers.add(in.nextInt());
                rightAnswers.add(question.getRightNumber());
            }

            person.setResult(checkAnswers(studentsAnswers, rightAnswers));

            int result = checkAnswers(studentsAnswers, rightAnswers);

            System.out.println("Студент " + name + " " + surname + "," +
                    " Вы дали " + result + " верных ответов из " + questions.size());
        } else {

            System.out.println("Ошибка при чтении файла с вопросами");
        }
    }
}
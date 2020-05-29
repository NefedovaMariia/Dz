package service;
import dao.PersonDao;
import domain.Person;
import domain.Questions;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuestionsServiceImpl implements QuestionsService {
    private PersonDao dao;
    public QuestionsServiceImpl(PersonDao dao) {
      this.dao = dao;
    }
    private static int checkAnswers(List<Integer> studentsAnswers, List<Integer> rightAnswers) {
        int result = 0;
        for (int i = 0; i < studentsAnswers.size(); i++) {
            if (rightAnswers.get(i).equals(studentsAnswers.get(i)))
            {
                result++;
            }
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
        int answer;
        for (Questions question : questions) {
            System.out.println(question);
            answer = in.nextInt();
            studentsAnswers.add(answer);
            rightAnswers.add(question.getRightNumber());
            }
        person.setResult(checkAnswers(studentsAnswers, rightAnswers));
        int result=checkAnswers(studentsAnswers, rightAnswers);
        System.out.println( "Студент " + name + " " + surname + "," + " Вы дали " + result + " верных ответов из 6");
    }
}
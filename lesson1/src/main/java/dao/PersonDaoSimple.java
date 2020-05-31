package dao;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import domain.Questions;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.dozer.CsvDozerBeanReader;
import org.supercsv.io.dozer.ICsvDozerBeanReader;
import org.supercsv.prefs.CsvPreference;

public class PersonDaoSimple implements PersonDao {

    private static final String DEFAULT_INPUT_FILE_NAME = "test.csv";

    private static final String[] FIELD_MAPPING = new String[]{
            "number",
            "question",
            "answers[0]",
            "answers[1]",
            "answers[2]",
            "rightNumber"
    };

    private static final CellProcessor[] processors = new CellProcessor[]{
            new Optional(new ParseInt()),
            new Optional(),
            new Optional(),
            new Optional(),
            new Optional(),
            new ParseInt()
    };

    public List<Questions> readQuestionsFromSource() throws Exception {

        List<Questions> questions = new ArrayList<>();
        ClassLoader classLoader = getClass().getClassLoader();
        File source = new File(classLoader.getResource(DEFAULT_INPUT_FILE_NAME).getFile());

        ICsvDozerBeanReader pojoReader =
                new CsvDozerBeanReader(new FileReader(source), CsvPreference.STANDARD_PREFERENCE);

        pojoReader.getHeader(true);
        pojoReader.configureBeanMapping(Questions.class, FIELD_MAPPING);

        Questions question;
        while ((question = pojoReader.read(Questions.class, processors)) != null) {
            questions.add(question);
        }

        return questions;
    }
}

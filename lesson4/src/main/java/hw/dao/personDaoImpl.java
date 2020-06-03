package hw.dao;

import hw.domain.Question;
import org.springframework.stereotype.Repository;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.dozer.CsvDozerBeanReader;
import org.supercsv.io.dozer.ICsvDozerBeanReader;
import org.supercsv.prefs.CsvPreference;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


@Repository
public class personDaoImpl implements PersonDao{
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

    public List<Question> readQuestionsFromSource() throws Exception {
        List<Question> questions = new ArrayList<>();
        ClassLoader classLoader = getClass().getClassLoader();
        File source = new File(classLoader.getResource(DEFAULT_INPUT_FILE_NAME).getFile());
        ICsvDozerBeanReader pojoReader =new CsvDozerBeanReader(new FileReader(source), CsvPreference.STANDARD_PREFERENCE);
        pojoReader.getHeader(true);
        pojoReader.configureBeanMapping(Question.class, FIELD_MAPPING);
        Question question;
        while ((question = pojoReader.read(Question.class, processors)) != null) {
            questions.add(question);
        }
        return questions;
    }
}

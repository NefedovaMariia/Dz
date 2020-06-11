package hw.dao;

import hw.domain.Author;
import hw.domain.Book;
import hw.domain.Genre;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import java.util.ArrayList;
import java.util.List;


@DisplayName("Тесты классов программы для хранения книг в библиотеке")
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
class BooksDaoImplTest {
    private static final int BOOKS_COUNT = 8;
    private static final String NEW_BOOK_TITLE = "new book";
    private static final int NEW_BOOK_ID = 8;
    private static final int GET_BOOK_ID = 1;
    @Autowired
    private BooksDaoImpl bookDao;

    @DisplayName("возращать правильное количество книг")
    @Test
    void shouldReturnCorrectBookCount() {
        Assertions.assertThat(bookDao.getCount()).isEqualTo(BOOKS_COUNT);
    }

    @DisplayName("добавлять книгу в БД")
    @Test
    void shouldInsertBook() {
        Book book = new Book(NEW_BOOK_ID, NEW_BOOK_TITLE, new Author(1,"Виктор Гюго"), new Genre(2,"Роман"));
        bookDao.insert(book);
        Book actualBook = bookDao.getById(NEW_BOOK_ID);
        Assertions.assertThat(actualBook.getTitle()).isEqualTo(book.getTitle());
        Assertions.assertThat(actualBook.getAuthor().getName()).isEqualTo(book.getAuthor().getName());
    }

    @DisplayName("получать нужную книгу по Id")
    @Test
    void shouldReturnCorrectBookById() {
        Book book = new Book(GET_BOOK_ID, "Отверженные", new Author(1, "Виктор Гюго"), new Genre(1, "Роман"));
        Book actualBook = bookDao.getById(GET_BOOK_ID);
        Assertions.assertThat(actualBook.getTitle()).isEqualTo(book.getTitle());
        Assertions.assertThat(actualBook.getAuthor().getName()).isEqualTo(book.getAuthor().getName());
        Assertions.assertThat(actualBook.getAuthor().getName()).isEqualTo(book.getAuthor().getName());

    }

    @DisplayName("получить все книги")
    @Test
    void shouldReturnCorrectBookList() {
        Book book1 = new Book(1, "Отверженные", new Author(1, "Виктор Гюго"), new Genre(1, "Роман"));
        Book book2 = new Book(2, "Три мушкетера", new Author(2, "Александр Дюма"), new Genre(2, "Роман"));
        Book book3 = new Book(3, "Евгений Онегин", new Author(3, "Александр Пушкин"), new Genre(3, "Роман в стихах"));
        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        List<Book> actualBooks = bookDao.getAll();
       for(int i=1;i<3; i++){
         Assertions.assertThat(actualBooks.get(i).getTitle()).isEqualTo(books.get(i).getTitle());
        Assertions.assertThat(actualBooks.get(i).getAuthor().getName()).isEqualTo(books.get(i).getAuthor().getName());
        Assertions.assertThat(actualBooks.get(i).getAuthor().getName()).isEqualTo(books.get(i).getAuthor().getName());}
    }
}
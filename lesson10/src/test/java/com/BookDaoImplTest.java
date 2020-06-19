package com;

import com.dao.BookDao;
import com.dao.CommentDao;
import com.domain.Author;
import com.domain.Book;
import com.domain.Genre;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Dao для работы с книгами")
@DataJpaTest
class BookDaoImplTest {

    private static final int EXPECTED_BOOK_COUNT = 7;
    private static final int EXPECTED_NUMBER_OF_BOOKS = 7;
    private static final long FIRST_BOOK_ID = 1;
    private static final String NEW_BOOK_TITLE = "new book";
    private static final String FIRST_BOOK_NAME = "Отверженные";
    @Autowired
    private BookDao bookDao;

    @Autowired
    private CommentDao commentDao;
    @Autowired
    private TestEntityManager em;

    @DisplayName("должен сохранять книгу в бд")
    @Test
    void shouldSaveBook() {
        val author = new Author("Достоевский");
        val genre = new Genre( "Истории");
        var book = new Book(NEW_BOOK_TITLE, author, genre);
        book = bookDao.save(book);

        assertThat(book.getId()).isGreaterThan(0);

        val actualBook = em.find(Book.class, book.getId());
        assertThat(actualBook).isNotNull().matches(b -> !b.getTitle().equals(""))
                .matches(b -> b.getAuthor() != null)
                .matches(b -> b.getGenre() != null);
    }

    @DisplayName("должен загружать информацию о нужной книге по её Id")
    @Test
    void shouldFindBookById() {
        val optionalActualBook = bookDao.findById(FIRST_BOOK_ID);
        val expectedBook = em.find(Book.class, FIRST_BOOK_ID);
        assertThat(optionalActualBook).isPresent().get().isEqualTo(expectedBook);
    }

    @DisplayName("должен загружать список всех книг с информацией об авторе и жанре")
    @Test
    void shouldReturnCorrectBookListWithGenreAndAuthor() {
        val books = bookDao.findAll();
        assertThat(books).isNotNull().hasSize(EXPECTED_NUMBER_OF_BOOKS)
                .allMatch(book -> !book.getTitle().equals(""))
                .allMatch(book -> book.getGenre() != null)
                .allMatch(book -> book.getAuthor() != null);
    }

    @DisplayName(" должен загружать информацию о нужной книге по ее имени")
    @Test
    void shouldFindBookByName() {
        val firstBook = em.find(Book.class, FIRST_BOOK_ID);
        List<Book> books = bookDao.findBooksByTitle(FIRST_BOOK_NAME);
        assertThat(books).containsOnlyOnce(firstBook);
    }


    @DisplayName(" должен выводить правильное количество книг")
    @Test
    void shouldReturnCorrectBookCount() {
        long count = bookDao.count();
        assertThat(count).isEqualTo(EXPECTED_BOOK_COUNT);
    }

}

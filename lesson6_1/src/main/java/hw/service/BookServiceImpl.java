package hw.service;

import hw.dao.BookDao;
import hw.domain.Author;
import hw.domain.Book;
import hw.domain.Genre;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class BookServiceImpl implements BookService{
    final private BookDao bookDao;
    final private GenreService genreService;
    final private AuthorService authorService;

    public BookServiceImpl(BookDao bookDao, GenreService genreService, AuthorService authorService){// GenreService genreService, AuthorService authorService) {
        this.bookDao = bookDao;
        this.genreService = genreService;
        this.authorService = authorService;
    }

    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Override
    public int getCount() {
        return bookDao.getCount();
    }

    @Override
    public void insert(Book book) {
        bookDao.insert(book);
    }

    @Override
    public Book getById(int id) {
        Book book = bookDao.getById(id);
        return book;
    }

    @Override
    public Book getNewBook() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите наименование книги: ");
        String title = sc.nextLine();
        System.out.print("Введите жанр: ");
        String genreName = sc.nextLine();
        System.out.print("Введите автора: ");
        String authorName = sc.nextLine();
        Genre genre = genreService.getGenre(genreName);
        Author author = authorService.getAuthor(authorName);
        System.out.print("Книга добавлена\n");
        return new Book(title, genre, author);
    }
}

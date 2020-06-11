package hw.shell;

import hw.domain.Book;
import hw.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;
import java.util.Scanner;

@ShellComponent
public class run {
    private BookService bookService;

    @Autowired
    public void run(BookService bookService) {
        this.bookService = bookService;
    }

    @ShellMethod(key = {"all"}, value = "show all books")
    public void allBooks() {
        List<Book> allBooks = bookService.getAll();
        allBooks.forEach(book -> System.out.print(book.getAuthor().getName()+ " "+book.getGenre().getName()+ " "+book.getTitle()+ " "+"\n"));
    }

    @ShellMethod(key = {"add"}, value = "add book to library")
    public void addBook() {
        Book book = bookService.getNewBook();
        bookService.insert(book);
    }

    @ShellMethod(key = {"getById"}, value = "get book by Id")
    public void getBookById() {
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        System.out.print(bookService.getById(id).getAuthor().getName()+" "+bookService.getById(id).getTitle()+"\n");
    }

    @ShellMethod(key = "count", value = "count of all books")
    public void bookCount() {
        System.out.print("Количество книг в библиотеке: "+bookService.getCount()+"\n");
    }

}

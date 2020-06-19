package com.shell;

import com.domain.Author;
import com.domain.Book;
import com.domain.Comment;
import com.service.AuthorService;
import com.service.BookService;
import com.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;
import java.util.Scanner;

@ShellComponent
public class ShellClass {
    private final BookService bookService;
    private final CommentService commentService;
    private final AuthorService authorService;

    @Autowired
    public ShellClass(BookService bookService, CommentService commentService, AuthorService authorService) {
        this.bookService = bookService;
        this.commentService = commentService;
        this.authorService = authorService;
    }

    @ShellMethod(key = {"all"}, value = "show all books")
    public void allBooks() {
        List<Book> allBooks = bookService.findAll();
        allBooks.forEach(book -> System.out.print(book.toString()+"\n"));
    }

    @ShellMethod(key = {"Add"}, value = "add book to library")
    public void addBook() {
        bookService.addNewBook();
    }

    @ShellMethod(key = {"GetBook"}, value = "get book by Id")
    public void getBookById() {
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        System.out.print(bookService.findById(id).toString());
    }

    @ShellMethod(key = {"DeleteBook"}, value = "delete book by Id")
    public void deleteBookById() {
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        bookService.deleteById(id);
    }

    @ShellMethod(key = {"booksCount"}, value = "count of all books")
    public void bookCount() {
        System.out.print(bookService.getCount()+"\n");
    }

    @ShellMethod(key = {"FindBookByName"}, value = "find book by name")
    public void findBookByName() {
        System.out.print("Введите имя книги, которую необходимо найти: ");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        List<Book> allBooks = bookService.findBooksByTitle(name);
        allBooks.forEach(book -> System.out.print(book.toString()));
    }

    @ShellMethod(key = {"Addcomment"}, value = "add comment to book by Id")
    public void addCommentToBookById() {
        commentService.addNewComment();
    }

    @ShellMethod(key = {"ShowAllComments"}, value = "show all comments to book by Id")
    public void showAllCommentsToBookById() {
        System.out.print("Введите Id книги, по которой отобразить комментарии: ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        List<Comment> allComments = commentService.findByBookId(id);
        System.out.print("Комментарии к книге " + bookService.findById(id).getTitle()+"\n");
        allComments.forEach(comment -> System.out.print(comment.toString()+"\n"));
    }

    @ShellMethod(key = {"DeleteComment"}, value = "delete comment by Id")
    public void deleteCommentById() {
        System.out.print("Введите Id комментария, который надо удалить");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        commentService.deleteById(id);
    }



    @ShellMethod(key = {"AllAuthors"}, value = "show all authors")
    public void showAllAuthors() {
        List<Author> authors = authorService.findAll();
        authors.forEach(author -> System.out.print(author.toString()));
    }

    @ShellMethod(key = {"AuthorsBook"}, value = "show all books by author id")
    public void showAllBooksByAuthorId() {
        System.out.print("Введите Id автора для отображения списка его книг: ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        List<Book> books = bookService.findAllBooksByAuthorId(id);
        System.out.print("Книги автора: " + authorService.findById(id).getName()+" - ");
        books.forEach(book -> System.out.print(book.getTitle()+","));
    }

}

package hw.domain;

public class Book {
    private int id;
    private String title;
    private Author author;
    private Genre genre;

    public Book(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Book(String title, Genre genre, Author author) {

        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public Book(int id, String title, Author author, Genre genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}

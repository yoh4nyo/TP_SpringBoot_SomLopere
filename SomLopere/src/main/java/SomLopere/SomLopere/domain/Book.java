package SomLopere.SomLopere.domain;

import jakarta.persistence.*;

/**
 * Entité représentant un livre
 */
@Entity
@Table(
        name = "books",
        uniqueConstraints = @UniqueConstraint(columnNames = "isbn") // ISBN unique
)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // le titre doit être renseigné
    private String title;

    @Column(nullable = false, unique = true) // doit être unique dans la base
    private String isbn;

    @Column(nullable = true)
    private Integer year;

    @Enumerated(EnumType.STRING) // Stocké en texte dans la base
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY) // plusieurs livres peuvent avoir le même auteur
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    public Book() {}

    public Book(String title, String isbn, Integer year, Category category, Author author) {
        this.title = title;
        this.isbn = isbn;
        this.year = year;
        this.category = category;
        this.author = author;
    }

    // --- GETTERS / SETTERS ---

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}

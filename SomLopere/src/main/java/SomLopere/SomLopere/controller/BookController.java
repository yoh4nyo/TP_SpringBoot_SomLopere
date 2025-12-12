package SomLopere.SomLopere.controller;

import SomLopere.SomLopere.domain.Book;
import SomLopere.SomLopere.domain.Category;
import SomLopere.SomLopere.dto.BookDTO;
import SomLopere.SomLopere.service.BookService;
import SomLopere.SomLopere.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST pour gérer les livres
 */
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    // GET /books → liste de tous les livres
    @GetMapping
    public List<Book> getBooks() {
        return bookService.findAll();
    }

    // GET /books/{id} → récupérer un livre par ID
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PostMapping
    public Book createBook(@RequestBody BookDTO dto) {
        var author = authorService.findById(dto.getAuthorId());
        if (author == null) throw new RuntimeException("Auteur non trouvé");

        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setIsbn(dto.getIsbn());
        book.setYear(dto.getYear());
        book.setCategory(dto.getCategory());
        book.setAuthor(author);

        return bookService.save(book);
    }

    // PUT /books/{id} → modifier un livre
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updated) {

        Book existing = bookService.findById(id);
        if (existing == null) return null; // simple

        existing.setTitle(updated.getTitle());
        existing.setIsbn(updated.getIsbn());
        existing.setYear(updated.getYear());
        existing.setCategory(updated.getCategory());

        // Mettre à jour l'auteur
        if (updated.getAuthor() != null) {
            var author = authorService.findById(updated.getAuthor().getId());
            existing.setAuthor(author);
        }

        return bookService.save(existing);
    }

    // DELETE /books/{id}
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.delete(id);
    }
}

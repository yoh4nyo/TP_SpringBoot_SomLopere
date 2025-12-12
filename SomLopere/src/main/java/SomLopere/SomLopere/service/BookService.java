package SomLopere.SomLopere.service;

import SomLopere.SomLopere.domain.Book;
import SomLopere.SomLopere.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service gérant la logique métier des livres
 */
@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Récupérer tous les livres
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    // Trouver un livre par ID
    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElse(null);
    }

    // Sauvegarder un livre (ajouter ou modifier)
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    // Supprimer un livre
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}

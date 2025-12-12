package SomLopere.SomLopere.repository;

import SomLopere.SomLopere.domain.Book;
import SomLopere.SomLopere.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // Recherche par titre
    List<Book> findByTitleContainingIgnoreCase(String title);

    // Recherche par catégorie
    List<Book> findByCategory(Category category);

    // Recherche par année
    List<Book> findByYearBetween(int start, int end);

    // Recherche par auteur
    List<Book> findByAuthorId(Long authorId);

    // --- Méthodes pour les statistiques ---

    // Nombre de livres par catégorie
    long countByCategory(Category category);

    // Nombre de livres par auteur
    long countByAuthorId(Long authorId);
}

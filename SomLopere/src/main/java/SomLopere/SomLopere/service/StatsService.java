package SomLopere.SomLopere.service;

import SomLopere.SomLopere.domain.Category;
import SomLopere.SomLopere.repository.BookRepository;
import SomLopere.SomLopere.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service qui calcule les statistiques
 */
@Service
public class StatsService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public StatsService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    /**
     * Stat : nombre de livres par catégorie
     */
    public Map<Category, Long> booksPerCategory() {
        Map<Category, Long> result = new HashMap<>();

        for (Category c : Category.values()) {
            long count = bookRepository.countByCategory(c);
            result.put(c, count);
        }
        return result;
    }

    /**
     * Stat : top auteurs (ceux qui ont le plus de livres)
     */
    public List<Map<String, Object>> topAuthors(int limit) {

        return authorRepository.findAll().stream()
                .map(author -> {
                    long count = bookRepository.countByAuthorId(author.getId());

                    Map<String, Object> entry = new HashMap<>();
                    entry.put("authorId", author.getId());
                    entry.put("name", author.getFirstName() + " " + author.getLastName());
                    entry.put("bookCount", count);
                    return entry;
                })
                .sorted((a, b) -> Long.compare(
                        (Long) b.get("bookCount"),
                        (Long) a.get("bookCount")
                ))
                .limit(limit)
                .collect(Collectors.toList());
    }
}

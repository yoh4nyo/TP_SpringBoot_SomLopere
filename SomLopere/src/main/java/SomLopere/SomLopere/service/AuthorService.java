package SomLopere.SomLopere.service;

import SomLopere.SomLopere.domain.Author;
import SomLopere.SomLopere.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service gérant la logique métier des auteurs
 */
@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    // Récupérer tous les auteurs
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    // Récupérer un auteur par son ID
    public Author findById(Long id) {
        return authorRepository.findById(id)
                .orElse(null); // simple niveau étudiant
    }

    // Enregistrer un auteur (création ou modification)
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    // Supprimer un auteur
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}

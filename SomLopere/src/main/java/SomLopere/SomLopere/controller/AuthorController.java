package SomLopere.SomLopere.controller;

import SomLopere.SomLopere.domain.Author;
import SomLopere.SomLopere.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST pour gérer les auteurs
 */
@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    // GET /authors → liste des auteurs
    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.findAll();
    }

    // GET /authors/{id} → auteur par ID
    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable Long id) {
        return authorService.findById(id);
    }

    // POST /authors → créer un auteur
    @PostMapping
    public Author createAuthor(@RequestBody Author author) {
        return authorService.save(author);
    }

    // PUT /authors/{id} → modifier un auteur
    @PutMapping("/{id}")
    public Author updateAuthor(@PathVariable Long id, @RequestBody Author updated) {

        Author existing = authorService.findById(id);
        if (existing == null) return null;

        existing.setFirstName(updated.getFirstName());
        existing.setLastName(updated.getLastName());
        existing.setBirthYear(updated.getBirthYear());

        return authorService.save(existing);
    }

    // DELETE /authors/{id}
    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.delete(id);
    }
}

package SomLopere.SomLopere.controller;

import SomLopere.SomLopere.service.StatsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller REST pour les statistiques
 */
@RestController
@RequestMapping("/stats")
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    // GET /stats/books-per-category
    @GetMapping("/books-per-category")
    public Map<?, ?> getBooksPerCategory() {
        return statsService.booksPerCategory();
    }

    // GET /stats/top-authors?limit=3
    @GetMapping("/top-authors")
    public List<?> getTopAuthors(@RequestParam(defaultValue = "3") int limit) {
        return statsService.topAuthors(limit);
    }
}

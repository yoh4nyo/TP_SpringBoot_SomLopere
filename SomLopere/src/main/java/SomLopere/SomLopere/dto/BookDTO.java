package SomLopere.SomLopere.dto;

import SomLopere.SomLopere.domain.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

/**
 * DTO pour la création / modification d'un livre
 */
public class BookDTO {

    @NotBlank(message = "Le titre ne peut pas être vide")
    private String title;

    @NotBlank(message = "L'ISBN est obligatoire")
    @Pattern(regexp = "\\d{3}-\\d{10}", message = "ISBN doit être au format XXX-XXXXXXXXXX")
    private String isbn;

    @NotNull(message = "L'année est obligatoire")
    @Min(value = 1450, message = "Année invalide")
    @Max(value = 2025, message = "Année invalide")
    private Integer year;

    @NotNull(message = "La catégorie est obligatoire")
    private Category category;

    @NotNull(message = "L'auteur est obligatoire")
    private Long authorId;

    // --- GETTERS / SETTERS ---

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

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}

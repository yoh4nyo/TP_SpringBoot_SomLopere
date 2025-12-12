package SomLopere.SomLopere.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

/**
 * DTO pour la création / modification d'un auteur
 */
public class AuthorDTO {

    @NotBlank(message = "Le prénom ne peut pas être vide")
    private String firstName;

    @NotBlank(message = "Le nom ne peut pas être vide")
    private String lastName;

    @NotNull(message = "L'année de naissance est obligatoire")
    @Min(value = 1000, message = "Année de naissance invalide")
    @Max(value = 2025, message = "Année de naissance invalide")
    private Integer birthYear;

    // --- GETTERS / SETTERS ---

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }
}

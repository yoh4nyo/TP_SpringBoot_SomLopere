# 📚 API REST - Gestion de Bibliothèque (Yohan SOM & Alexandre LOPERE)

## Description du projet

API REST développée avec Spring Boot permettant de gérer une bibliothèque (livres et auteurs) dans le cadre du TP API - Modèle MVC
Le projet implémente une architecture MVC complète avec gestion des entités **Author** et **Book**, incluant des fonctionnalités CRUD et des endpoints de statistiques

---

## Architecture du projet

Le projet suit l'architecture MVC suivante :

```
SomLopere/
├── controller/         # Contrôleurs REST (API endpoints)
│   ├── AuthorController.java
│   ├── BookController.java
│   └── StatsController.java
├── service/           # Logique métier
│   ├── AuthorService.java
│   ├── BookService.java
│   └── StatsService.java
├── repository/        # Accès aux données (Spring Data JPA)
│   ├── AuthorRepository.java
│   └── BookRepository.java
├── domain/            # Entités JPA
│   ├── Author.java
│   ├── Book.java
│   └── Category.java (enum)
├── dto/               # Data Transfer Objects
│   ├── AuthorDTO.java
│   └── BookDTO.java
└── exception/         # Gestion des erreurs
    ├── GlobalExceptionHandler.java
    ├── ResourceNotFoundException.java
    ├── DuplicateResourceException.java
    └── ApiError.java
```

---

## Entités

### Author
- `id` : Long (auto-généré)
- `firstName` : String
- `lastName` : String
- `birthYear` : Integer

### Book
- `id` : Long (auto-généré)
- `title` : String (obligatoire)
- `isbn` : String (unique, obligatoire)
- `year` : Integer
- `category` : Enum (NOVEL, ESSAY, POETRY, OTHER)
- `author` : Author (relation ManyToOne)

---

## Installation et lancement

### Prérequis
- **Java 17** ou supérieur
- **Maven 3.6+**
- **MySQL** (ou MariaDB)

### 1. Cloner le repository
```bash
git clone https://github.com/yoh4nyo/TP_SpringBoot_SomLopere.git
cd TP_SpringBoot_SomLopere/SomLopere
```

### 2. Configurer la base de données

Créer une base de données MySQL :
```sql
CREATE DATABASE tp_apimvc;
```

Modifier le fichier `src/main/resources/application.properties` si nécessaire :
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/tp_apimvc
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
server.port=8080
```

### 3. Compiler et lancer l'application

**Avec Maven :**
```bash
mvn clean install
mvn spring-boot:run
```

**Ou directement :**
```bash
./mvnw spring-boot:run
```

L'API sera accessible à l'adresse : **http://localhost:8080**

---

## Endpoints disponibles

### Auteurs (`/authors`)

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| `GET` | `/authors` | Liste tous les auteurs |
| `GET` | `/authors/{id}` | Récupère un auteur par ID |
| `POST` | `/authors` | Créer un nouvel auteur |
| `PUT` | `/authors/{id}` | Modifier un auteur |
| `DELETE` | `/authors/{id}` | Supprimer un auteur |

#### Exemples de requêtes

**Créer un auteur :**
```bash
POST http://localhost:8080/authors
Content-Type: application/json

{
  "firstName": "Victor",
  "lastName": "Hugo",
  "birthYear": 1802
}
```

**Récupérer tous les auteurs :**
```bash
GET http://localhost:8080/authors
```

---

### Livres (`/books`)

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| `GET` | `/books` | Liste tous les livres |
| `GET` | `/books/{id}` | Récupère un livre par ID |
| `POST` | `/books` | Créer un nouveau livre |
| `PUT` | `/books/{id}` | Modifier un livre |
| `DELETE` | `/books/{id}` | Supprimer un livre |

#### Exemples de requêtes

**Créer un livre :**
```bash
POST http://localhost:8080/books
Content-Type: application/json

{
  "title": "Les Misérables",
  "isbn": "978-2-07-036012-8",
  "year": 1862,
  "category": "NOVEL",
  "authorId": 1
}
```

**Modifier un livre :**
```bash
PUT http://localhost:8080/books/1
Content-Type: application/json

{
  "title": "Les Misérables (édition révisée)",
  "isbn": "978-2-07-036012-8",
  "year": 1862,
  "category": "NOVEL",
  "author": {
    "id": 1
  }
}
```

**Supprimer un livre :**
```bash
DELETE http://localhost:8080/books/1
```

---

### 📊 Statistiques (`/stats`)

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| `GET` | `/stats/books-per-category` | Nombre de livres par catégorie |
| `GET` | `/stats/top-authors?limit=3` | Top N auteurs avec le plus de livres |

#### Exemples de requêtes

**Livres par catégorie :**
```bash
GET http://localhost:8080/stats/books-per-category
```

Réponse :
```json
{
  "NOVEL": 15,
  "ESSAY": 8,
  "POETRY": 5,
  "OTHER": 2
}
```

**Top 3 auteurs :**
```bash
GET http://localhost:8080/stats/top-authors?limit=3
```

Réponse :
```json
[
  {
    "authorId": 1,
    "name": "Victor Hugo",
    "bookCount": 12
  },
  {
    "authorId": 2,
    "name": "Émile Zola",
    "bookCount": 8
  },
  {
    "authorId": 3,
    "name": "Albert Camus",
    "bookCount": 5
  }
]
```

## Catégories de livres

L'enum `Category` supporte les valeurs suivantes :
- `NOVEL` : Roman
- `ESSAY` : Essai
- `POETRY` : Poésie
- `OTHER` : Autre

---

## Gestion des erreurs

L'API gère les erreurs suivantes :
- **404 Not Found** : Ressource (auteur/livre) introuvable
- **409 Conflict** : ISBN déjà existant (duplication)
- **400 Bad Request** : Données invalides

Les erreurs sont retournées au format JSON structuré via `GlobalExceptionHandler`.

---

## Tester l'API

### Avec Postman

Vous pouvez importer la collection suivante dans Postman pour tester tous les endpoints :

1. Créer une nouvelle collection "SomLopere API"
2. Ajouter les requêtes ci-dessus
3. Configurer la variable d'environnement `baseUrl = http://localhost:8080`

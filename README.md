# 🎬 Gestion de Films

API REST Spring Boot multi-modules pour gérer un catalogue de films et leurs réalisateurs.

![CI](https://github.com/Veudzveulay/maeven_project/actions/workflows/ci.yml/badge.svg)

## 🏗️ Architecture

Projet Maven multi-modules :

```
gestion-films/
├── films-domain/    ← entités JPA + DTOs
├── films-service/   ← logique métier + repositories
└── films-web/       ← API REST + main Spring Boot
```

### Modèle métier

| Champ Film       | Type             | Contraintes                  |
|------------------|------------------|------------------------------|
| `id`             | Long             | auto-généré                  |
| `titre`          | String           | non vide, max 300, unique    |
| `annee`          | Integer          | entre 1888 et 2100           |
| `dureeMinutes`   | Integer          | > 0                          |
| `realisateur`    | `@ManyToOne`     | obligatoire                  |

| Champ Realisateur | Type   |
|-------------------|--------|
| `id`              | Long   |
| `nom`             | String, unique, max 200 |

## 🚀 Build & lancement

### Build complet (les 3 modules)
```bash
mvn clean install
```

### Lancer l'API en dev (H2 en mémoire)
```bash
mvn -pl films-web spring-boot:run
```

L'application démarre sur `http://localhost:8080`.
Console H2 : `http://localhost:8080/h2-console` (JDBC URL : `jdbc:h2:mem:films`, user : `sa`).

### Lancer en prod (PostgreSQL)
```bash
java -jar films-web/target/films-web.jar --spring.profiles.active=prod
```

Variables d'environnement attendues : `DB_URL`, `DB_USER`, `DB_PASSWORD`.

## 🌐 Endpoints

| Verbe  | Path              | Description           | Statut succès |
|--------|-------------------|-----------------------|---------------|
| POST   | `/api/films`      | Créer un film         | 201 + Location|
| GET    | `/api/films`      | Lister (paginé)       | 200           |
| GET    | `/api/films/{id}` | Récupérer un film     | 200           |
| PUT    | `/api/films/{id}` | Modifier              | 200           |
| DELETE | `/api/films/{id}` | Supprimer             | 204           |

### Codes d'erreur
- `400` — validation `@Valid` (champs invalides)
- `404` — `FilmNotFoundException`
- `409` — `TitreDejaExistantException`

## 🧪 Exemples curl

```bash
# Créer
curl -X POST http://localhost:8080/api/films \
  -H "Content-Type: application/json" \
  -d '{"titre":"Memento","annee":2000,"dureeMinutes":113,"realisateurNom":"Christopher Nolan"}'

# Lister
curl http://localhost:8080/api/films

# Récupérer par id
curl http://localhost:8080/api/films/1

# Modifier
curl -X PUT http://localhost:8080/api/films/1 \
  -H "Content-Type: application/json" \
  -d '{"titre":"Memento (rev.)","annee":2000,"dureeMinutes":113,"realisateurNom":"Christopher Nolan"}'

# Supprimer
curl -X DELETE http://localhost:8080/api/films/1 -i
```

## 🧪 Tests

```bash
mvn verify
```

- Tests unitaires (`films-domain`, `films-service`) → Surefire
- Tests `@DataJpaTest` (Spring Data + H2) → Surefire
- Tests d'intégration `@SpringBootTest` + MockMvc (suffixe `IT`) → Failsafe
- Couverture JaCoCo générée à `films-*/target/site/jacoco/`

## ⚙️ Profils Maven vs Profils Spring

| Aspect       | Profil **Maven**                          | Profil **Spring**                              |
|--------------|-------------------------------------------|------------------------------------------------|
| Activation   | `mvn -P prod`                             | `--spring.profiles.active=prod`                |
| Portée       | **Build-time** (compilation, dépendances) | **Run-time** (configuration de l'application) |
| Effet        | Choix de plugins, dépendances optionnelles, `finalName`, etc. | Choix d'un `application-{profil}.yml` (datasource, logs, etc.) |
| Exemple      | Activer un plugin de packaging spécifique | Basculer entre H2 (dev) et PostgreSQL (prod)  |

Les deux sont **indépendants** et peuvent se combiner : un build Maven en profil `release` peut produire un JAR exécuté avec `--spring.profiles.active=prod`.

## 🤖 CI

GitHub Actions exécute `mvn clean verify` à chaque push (`main`, `develop`) et pull request sur `main`.
Le workflow upload :
- les rapports surefire en cas d'échec,
- le JAR final (`films-web.jar`) en cas de succès sur `main`,
- un commentaire JaCoCo sur les PRs.

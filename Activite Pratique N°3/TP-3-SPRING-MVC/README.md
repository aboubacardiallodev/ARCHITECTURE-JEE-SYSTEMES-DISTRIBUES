# TP-3-SPRING-MVC

Projet Spring Boot MVC pour la gestion des produits avec Thymeleaf, Spring Data JPA et Spring Security.

## 1. Objectif du TP

Ce TP montre comment :

1. construire une application web MVC avec Spring Boot et Thymeleaf,
2. realiser les operations CRUD sur les produits,
3. securiser l'application avec authentification et autorisations par role.

## 2. Stack technique

- Java 17
- Spring Boot 4.1.0
- Spring Web MVC
- Thymeleaf + thymeleaf-extras-springsecurity6
- Spring Data JPA
- Spring Security
- H2 Database (in-memory)
- Maven

## 3. Fonctionnalites

- Authentification via page de login personnalisee (`/login`).
- Deconnexion via endpoint `/logout` et bouton "Se deconnecter" sur la vue liste.
- Gestion des produits :
  - liste paginee et recherche par mot-cle,
  - ajout, modification, suppression (reservees au role `ADMIN`).
- Gestion des acces :
  - role `USER` : consultation de la liste,
  - role `ADMIN` : CRUD complet.

## 4. Comptes de test

Definis dans `SecurityConfig` :

- `utilisateur1` / `@2026` -> role `USER`
- `administrateur` / `@enset` -> roles `USER`, `ADMIN`

## 5. Configuration principale

Le fichier `src/main/resources/application.properties` configure :

- H2 en memoire : `jdbc:h2:mem:springmvc`
- console H2 activee : `/h2-console`
- schema JPA : `create-drop`
- port serveur : `8080`

Une configuration MySQL est deja presente en commentaires.

## 6. Lancer le projet

Depuis le dossier `TP-3-SPRING-MVC` :

```bash
mvn spring-boot:run
```

Ou avec le wrapper Maven :

```bash
./mvnw spring-boot:run
```

Sous Windows :

```bat
mvnw.cmd spring-boot:run
```

## 7. URLs utiles

- Application : `http://localhost:8080/products`
- Login : `http://localhost:8080/login`
- Console H2 : `http://localhost:8080/h2-console`
- Page acces refuse : `http://localhost:8080/403`

Parametres H2 :

- JDBC URL : `jdbc:h2:mem:springmvc`
- User : `sa`
- Password : *(vide)*

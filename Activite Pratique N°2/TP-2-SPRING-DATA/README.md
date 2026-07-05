# TP-2-SPRING-DATA

Projet Spring Boot de demonstration de Spring Data JPA avec base H2 en memoire.

## 1. Objectif du TP

Ce TP montre comment :

1. manipuler des entites avec Spring Data JPA (CRUD),
2. gerer des relations entre entites (OneToMany, ManyToOne, OneToOne, ManyToMany),
3. initialiser des donnees de test au demarrage avec `CommandLineRunner`.

## 2. Stack technique

- Java 17
- Spring Boot 4.1.0
- Spring Data JPA
- H2 Database (in-memory)
- Lombok
- Maven

## 3. Configuration principale

Le fichier `src/main/resources/application.properties` configure :

- une base H2 en memoire (`jdbc:h2:mem:springdata`),
- la console H2 activee sur `/h2-console`,
- la creation/suppression automatique du schema (`spring.jpa.hibernate.ddl-auto=create-drop`),
- l'affichage SQL dans la console (`spring.jpa.show-sql=true`).

## 4. Structure du code

### 4.1 Classe de lancement

- `net.aboubacar.Application`
  - point d'entree Spring Boot (`main`),
  - contient 3 beans `CommandLineRunner` pour executer les demonstrations metier.

### 4.2 Entites

- `Product` : produit (id, name, price, quantity)
- `Patient` : patient (nom, dateNaissance, malade, score)
- `Medecin` : medecin (nom, email, specialite)
- `Appointment` : rendez-vous (date, status, patient, medecin)
- `Consultation` : consultation (dateConsultation, rapport, appointment)
- `AppUser` : utilisateur applicatif (username, password, email, roles)
- `AppRole` : role applicatif (roleName, description, users)

### 4.3 Enumeration

- `AppointmentStatus` : `PENDING`, `CANCELLED`, `DONE`

### 4.4 Repositories Spring Data

- `ProductRepository`
  - `findByName(String name)`
  - `findByNameContains(String keyword)`
  - `findByPriceLessThan(double price)`
  - `searchProducts(String keyword)` (requete JPQL)
- `PatientRepository`
  - `findByMalade(boolean malade)`
  - `findByNomContains(String keyword)`
  - `findByScoreGreaterThan(int score)`
- `MedecinRepository`
  - `findByNom(String nom)`
- `AppointmentRepository`
- `ConsultationRepository`
- `AppUserRepository`
  - `findByUsername(String username)`
- `AppRoleRepository`
  - `findByRoleName(String roleName)`

## 5. Relations entre entites

- `Patient` **1..n** `Appointment`
- `Medecin` **1..n** `Appointment`
- `Appointment` **1..1** `Consultation`
- `AppUser` **n..n** `AppRole` (table de jointure `user_roles`)

## 6. Scenarios executes au demarrage

Les 3 `CommandLineRunner` de `Application` executent automatiquement :

1. **Gestion des produits**
   - insertion de produits,
   - lecture globale,
   - lecture par id,
   - recherche par mot-cle,
   - mise a jour,
   - suppression.

2. **Gestion hospitaliere**
   - creation de patients et medecins,
   - filtrage des patients malades,
   - creation d'un rendez-vous,
   - creation d'une consultation liee au rendez-vous.

3. **Gestion users/roles**
   - creation des roles `ADMIN` et `USER`,
   - creation d'utilisateurs avec attribution de roles,
   - affichage des utilisateurs et de leurs roles.

## 7. Execution du projet

Depuis le dossier `TP-2-SPRING-DATA` :

```bash
mvn spring-boot:run
```

Ou via IntelliJ : lancer la classe `net.aboubacar.Application`.

## 8. Acces a la console H2

Quand l'application est demarree :

- URL console : `http://localhost:8080/h2-console`
- JDBC URL : `jdbc:h2:mem:springdata`
- User : `sa`
- Password : *(vide)*

## 9. Points importants a retenir

- Spring Data JPA reduit fortement le code DAO grace aux interfaces `JpaRepository`.
- Les methodes derivees (`findBy...`) couvrent beaucoup de besoins sans requete SQL explicite.
- Les relations JPA doivent etre pensees selon le besoin metier et la strategie de chargement (`LAZY`/`EAGER`).
- Les donnees H2 en memoire sont volatiles : elles disparaissent a l'arret de l'application.

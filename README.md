# API REST Spring Boot : Gestion des animaux

API REST développée avec Spring Boot pour permettre à un refuge de gérer ses animaux : création, liste et mise à jour du statut.

TP de rattrapage, ISI Dakar.

## Technologies

- Java 17
- Spring Boot 4.1.1 (Web MVC, Data JPA, Validation)
- PostgreSQL
- Maven
- Swagger UI (springdoc-openapi)

## Modèle de données

Entité `Animal` :

| Attribut    | Type   | Règle                                                   |
|-------------|--------|---------------------------------------------------------|
| `id`        | Long   | Identifiant unique, généré automatiquement              |
| `nom`       | String | Obligatoire                                             |
| `categorie` | String | Obligatoire (ex. : chien, chat, oiseau)                 |
| `status`    | Enum   | `DISPONIBLE`, `ADOPTE` ou `EN_SOIN` (défaut : `DISPONIBLE`) |

## Prérequis

- JDK 17 ou plus
- PostgreSQL installé et démarré
- Maven (ou le wrapper `./mvnw` fourni dans le projet)

## Installation et lancement

### 1. Cloner le projet

```bash
git clone https://github.com/saliou-crypto/Api_rest.git
cd Api_rest
```

### 2. Créer la base de données

```bash
createdb refuge
```

### 3. Configurer la connexion

Dans `src/main/resources/application.properties`, adapte l'utilisateur et le mot de passe à ta machine :

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/refuge
spring.datasource.username=TON_USER
spring.datasource.password=TON_MOT_DE_PASSE

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

La table `animal` est créée automatiquement par Hibernate au premier démarrage.

### 4. Lancer l'application

```bash
./mvnw spring-boot:run
```

L'API est disponible sur `http://localhost:8080`.

## Endpoints

| Méthode | URL                          | Description                          |
|---------|------------------------------|--------------------------------------|
| `POST`  | `/api/animaux`               | Créer un animal                      |
| `GET`   | `/api/animaux`               | Lister les animaux                   |
| `PATCH` | `/api/animaux/{id}/status`   | Modifier le statut d'un animal       |

### Exemples

Créer un animal :

```bash
curl -X POST http://localhost:8080/api/animaux \
  -H "Content-Type: application/json" \
  -d '{"nom": "Rex", "categorie": "chien"}'
```

Réponse :

```json
{"categorie":"chien","id":1,"nom":"Rex","status":"DISPONIBLE"}
```

Lister les animaux :

```bash
curl http://localhost:8080/api/animaux
```

Changer le statut de l'animal d'id 1 :

```bash
curl -X PATCH http://localhost:8080/api/animaux/1/status \
  -H "Content-Type: application/json" \
  -d '{"status": "ADOPTE"}'
```

### Codes de réponse

| Code | Cas                                                        |
|------|------------------------------------------------------------|
| 200  | Succès (liste, modification du statut)                     |
| 201  | Animal créé                                                |
| 400  | `nom` ou `categorie` manquant, ou statut invalide          |
| 404  | Aucun animal avec l'id demandé                             |

## Documentation interactive (Swagger)

Une fois l'application lancée, ouvre :

http://localhost:8080/swagger-ui/index.html

Tu peux y voir les 3 endpoints et les tester directement depuis le navigateur.

## Structure du projet

```
src/main/java/sn/isi/refuge
├── RefugeApplication.java
├── controller
│   └── AnimalController.java
├── model
│   ├── Animal.java
│   └── Status.java
├── repository
│   └── AnimalRepository.java
└── service
    └── AnimalService.java
```

## Auteur

Cheikh Saliou Dieng, ISI Dakar

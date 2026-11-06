# Kafka Spring Project

Application **Spring Boot** qui illustre le traitement de flux d'événements avec **Apache Kafka**, en combinant **Spring Cloud Stream** et **Kafka Streams**.

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.2-brightgreen)
![Kafka](https://img.shields.io/badge/Apache%20Kafka-Confluent%207.9-black)
![Maven](https://img.shields.io/badge/Build-Maven-blue)

## Sommaire

- [Aperçu](#aperçu)
- [Technologies](#technologies)
- [Architecture](#architecture)
- [Prérequis](#prérequis)
- [Installation et lancement](#installation-et-lancement)
- [Utilisation](#utilisation)
- [Structure du projet](#structure-du-projet)
- [Tests](#tests)
- [Captures d'écran](#captures-décran)
- [Auteur](#auteur)

## Aperçu

Ce projet met en pratique les concepts clés de Kafka dans un écosystème Spring :

- production et consommation de messages (producer / consumer) ;
- exposition de l'application via une API REST (Spring Web MVC) ;
- traitement de flux en temps réel avec **Kafka Streams** ;
- programmation par fonctions (`Supplier`, `Function`, `Consumer`) grâce à **Spring Cloud Stream**.

> 📝 *Complétez cette section avec le cas d'usage précis de votre application (ex. : analyse de clics, comptage de pages vues, etc.).*

## Technologies

| Technologie | Version / détail |
|---|---|
| Java | 21 |
| Spring Boot | 4.1.2 |
| Spring Cloud | 2025.1.4 |
| Spring Cloud Stream | binders Kafka et Kafka Streams |
| Apache Kafka / Kafka Streams | Confluent Platform 7.9.10 (via Docker) |
| Apache Zookeeper | Confluent 7.9.10 (via Docker) |
| Lombok | réduction du code répétitif |
| Maven | via le wrapper `mvnw` |
| Docker Compose | démarrage de l'infrastructure Kafka |

## Architecture

```
┌──────────────┐   HTTP    ┌──────────────────────┐        ┌─────────────┐
│   Client     │ ────────► │  Application Spring  │ ─────► │ Kafka Broker│
│ (navigateur, │           │  Boot (REST + Cloud  │ ◄───── │  :9092      │
│  Postman…)   │ ◄──────── │  Stream + Streams)   │        └──────┬──────┘
└──────────────┘           └──────────────────────┘               │
                                                          ┌───────▼───────┐
                                                          │   Zookeeper   │
                                                          │    :2181      │
                                                          └───────────────┘
```

L'infrastructure (Zookeeper + broker Kafka) est définie dans `compose.yml` :

| Service | Image | Port |
|---|---|---|
| `zookeeper` | `confluentinc/cp-zookeeper:7.9.10` | 2181 |
| `broker` | `confluentinc/cp-kafka:7.9.10` | 9092 (hôte) / 29092 (interne Docker) |

## Prérequis

- [JDK 21](https://adoptium.net/) ou supérieur
- [Docker](https://www.docker.com/) et Docker Compose
- Git
- (Optionnel) Maven : le wrapper `mvnw` est fourni

## Installation et lancement

### 1. Cloner le dépôt

```bash
git clone https://github.com/omarKhabou46/kafka-spring-project.git
cd kafka-spring-project
```

### 2. Démarrer Kafka

```bash
docker compose -f compose.yml up -d
```

Vérifier que les conteneurs tournent :

```bash
docker ps
```

### 3. Lancer l'application

Linux / macOS :

```bash
./mvnw spring-boot:run
```

Windows :

```bash
mvnw.cmd spring-boot:run
```

L'application se connecte au broker sur `localhost:9092`.

### 4. Arrêter l'environnement

```bash
docker compose -f compose.yml down
```

## Utilisation

> 📝 *Complétez avec vos endpoints et topics réels.*

Exemple de vérification des topics depuis le conteneur Kafka :

```bash
# Lister les topics
docker exec -it broker kafka-topics --bootstrap-server localhost:9092 --list

# Consommer les messages d'un topic
docker exec -it broker kafka-console-consumer \
  --bootstrap-server localhost:9092 \
  --topic <NOM_DU_TOPIC> \
  --from-beginning
```

Exemple d'appel à l'API REST :

```bash
curl http://localhost:8080/<VOTRE_ENDPOINT>
```

## Structure du projet

```
kafka-spring-project/
├── .mvn/wrapper/      # Maven Wrapper
├── src/
│   ├── main/
│   │   ├── java/      # Code source (package com.enset)
│   │   └── resources/ # Configuration (application.properties / yml)
│   └── test/          # Tests
├── compose.yml        # Zookeeper + Kafka
├── pom.xml            # Dépendances Maven
├── mvnw / mvnw.cmd    # Maven Wrapper
└── README.md
```

## Tests

```bash
./mvnw test
```

Le projet utilise `spring-boot-starter-kafka-test`, `spring-boot-starter-webmvc-test` et `spring-cloud-stream-test-binder`.

## Captures d'écran

> 📝 *Collez ici les 3 captures déjà présentes dans l'ancien README. Pour qu'elles restent permanentes, placez-les dans un dossier `docs/images/` du dépôt et référencez-les ainsi :*

<img width="1758" height="768" alt="image" src="https://github.com/user-attachments/assets/b53118b4-6290-4f05-90eb-9493e299f4db" />
<img width="1549" height="700" alt="image" src="https://github.com/user-attachments/assets/3f597c73-d751-4a69-9e14-39eca835bd5b" />
<img width="1549" height="700" alt="image" src="https://github.com/user-attachments/assets/40881a36-ce51-47e0-a371-cefbcc848874" />


## Auteur

**Omar Khabou** — [@omarKhabou46](https://github.com/omarKhabou46)








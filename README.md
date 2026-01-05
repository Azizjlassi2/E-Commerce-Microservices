# Spring Boot + Spring Cloud + Keycloak — Microservices Project

Un projet backend **microservices Java** basé sur **Spring Boot** et **Spring Cloud**, sécurisé avec **Keycloak** et orchestré avec Docker.

Ce projet implémente une architecture microservices complète, couvrant les briques essentielles d’un backend moderne :

- 🌐 **Configuration Server** (Spring Cloud Config)
- 🔍 **Service Discovery** (Eureka)
- 🚪 **API Gateway** (Spring Cloud Gateway)
- 🔁 **Communication Inter-services**
  - Synchrone via OpenFeign
  - Asynchrone avec Kafka
- 🔒 **Sécurité & Authentification** (A Développer)
  - OAuth2 / OpenID Connect via **Keycloak**
- 🧠 **Tracing & Monitoring** (A Développer)
  - Zipkin + Spring Actuator
- 🐳 **Conteneurs & Infrastructure**
  - Docker & Docker Compose pour dev local

---

## Stack Technique

| Catégorie            | Technologie               |
| -------------------- | ------------------------- |
| Langage              | Java                      |
| Framework            | Spring Boot, Spring Cloud |
| Auth & IAM           | Keycloak (OIDC/OAuth2)    |
| Messaging            | Kafka                     |
| Docker               | Docker, Docker-Compose    |
| Monitoring & Tracing | Zipkin, Spring Actuator   |

---

## ⚙️ Fonctionnalités clés

- Multi-services indépendants orchestrés via Eureka.
- Gateway API centralisée pour routing + sécurité.
- Flux d’authentification sécurisé par Keycloak (JWT).
- Échange de messages inter-services (Kafka).
- Tracing distribué avec Zipkin.

---

## 📂 Structure du projet

```bash
.
├── config-service/
├── discovery-service/
├── gateway-service/
├── customer-service/
├── product-service/
├── order-service/
├── payment-service/
├── notification-service/
├── mysql-init/
├── docker-compose.yml
└── README.md

```

Chaque microservice contient :

- un `Dockerfile`
- un projet Spring Boot indépendant
- une configuration externalisée via Config Server

---

## ▶️ Exécuter l’application Microservices

L’ensemble des services est lancé **localement via Docker Compose**, sans dépendances externes.

### ✅ Prérequis

- Docker ≥ 24.x
- Docker Compose ≥ v2
- Ports disponibles :
  - `3306`, `8080–8085`, `8761`, `8888`, `9092`, `1080`

Vérification :

```bash
docker --version
docker compose version
```

Démarrage
Depuis la racine du projet :

```bash
docker compose up --build
```

Mode détaché :

```bash
docker compose up -d --build
```

Le démarrage est séquentiel via healthcheck

(MySQL → Config → Eureka → Gateway → Services) 🧠 Ordre logique des services

MySQL
Config Service
Discovery Service (Eureka)
API Gateway
Zookeeper & Kafka
Services métier

### 🌐 Accès aux composants

| Composant            | URL                   |
| -------------------- | --------------------- |
| Eureka Dashboard     | http://localhost:8761 |
| Config Server        | http://localhost:8888 |
| API Gateway          | http://localhost:8080 |
| Customer Service     | http://localhost:8081 |
| Product Service      | http://localhost:8082 |
| Order Service        | http://localhost:8083 |
| Payment Service      | http://localhost:8084 |
| Notification Service | http://localhost:8085 |
| MailDev              | http://localhost:1080 |

### 🧪 Monitoring & Logs

Lister les conteneurs :

```bash
docker compose ps
```

Logs d’un service :

```bash
docker compose logs -f product-service
```

### 🛑 Arrêt

```bash
docker compose down
```

Arrêt + suppression des volumes :

```bash
docker compose down -v
```

### 🧠 Notes techniques

- Configuration externalisée via Spring Cloud Config
- Discovery dynamique avec Eureka
- Routage centralisé via API Gateway
- Communication asynchrone avec Kafka
- Initialisation MySQL via mysql-init
- Réseau Docker dédié : micro-network

### 🚧 Améliorations prévues

- Sécurité OAuth2 / JWT (Keycloak)
- Tracing distribué (Zipkin)
- Centralisation des logs
- Tests d’intégration

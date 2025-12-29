# Spring Boot + Spring Cloud + Keycloak — Microservices Project

Un projet backend **microservices Java** basé sur **Spring Boot** et **Spring Cloud**, sécurisé avec **Keycloak** et orchestré avec Docker.

Ce projet implémente une architecture microservices complète, couvrant les briques essentielles d’un backend moderne :

- 🌐 **Configuration Server** (Spring Cloud Config)
- 🔍 **Service Discovery** (Eureka)
- 🚪 **API Gateway** (Spring Cloud Gateway)
- 🔁 **Communication Inter-services**
  - Synchrone via OpenFeign
  - Asynchrone avec Kafka
- 🔒 **Sécurité & Authentification**
  - OAuth2 / OpenID Connect via **Keycloak**
- 🧠 **Tracing & Monitoring**
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

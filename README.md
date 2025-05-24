# 🎵 Playlist API

Este proyecto es una API REST desarrollada con **Spring Boot 3.3.12**. Permite gestionar listas de reproducción (`playlists`) y canciones asociadas, utilizando una base de datos **H2 en memoria** y autenticación básica.

## 🚀 Tecnologías

- Java 17
- Spring Boot Web
- Spring Data JPA
- Spring Security
- H2 Database
- Maven
- Lombok

## 📦 Requisitos

- Java 17
- Maven 3.9+

## ⚙️ Instalación y ejecución local

Clona el repositorio y compila la aplicación:

```bash
git clone https://github.com/tu-usuario/playlist-api.git
cd playlist-api
mvn clean install
mvn spring-boot:run
```

## 🔐 Autenticación
La API está protegida con autenticación básica. Y están configuradas dentro del código por agilidad

## 📚 Endpoints principales

| Método | Endpoint         | Descripción                     |
| ------ |------------------|---------------------------------|
| GET    | `/lists`         | Obtener todas las playlists     |
| GET    | `/lists/{name}`  | Obtener una playlist por nombre |
| POST   | `/lists`         | Crear una nueva playlist        |
| DELETE | `/lists/{name}`  | Eliminar una playlist           |


También incluye endpoints anidados para la gestión de canciones dentro de una playlist.

## 📂 Estructura del proyecto

    src/
    └── main/
    ├── java/com/quipux/api/
    │    ├── configuration/
    │    ├── controller/
    │    ├── model/
    │    ├── repository/
    └── resources/
    ├── application.properties


# Songlify

## A simple Spring Boot project to manage songs, singers, and albums.
```
Project Structure
java/com/songlify
├── advices
├── constants
├── controllers
├── dto
├── exceptions
├── models
├── repositories
├── response
└── services
```


## API Documentation

This project includes Swagger UI for interactive API exploration. With it, you can:

- Explore all available endpoints
- View request/response models
- Test API endpoints directly from the browser

### Accessing API Docs

- **Swagger UI:**  
  http://localhost:8080/swagger-ui/index.html

- **Redoc:**  
  http://localhost:8080/redoc.html

## Endpoints

### Song Controller

- **POST** `/api/v1/songs`  
  Add a new song.

- **GET** `/api/v1/songs/singer/{singerId}`  
  Get all songs by a specific singer.

---

### Singer Controller

- **GET** `/api/v1/singers`  
  Get all singers.

- **POST** `/api/v1/singers`  
  Add a new singer.

- **PATCH** `/api/v1/singers`  
  Update an existing singer.

- **GET** `/api/v1/singers/{singerId}`  
  Get details of a specific singer.

- **DELETE** `/api/v1/singers/{singerId}`  
  Delete a specific singer.

---

### Album Controller

- **GET** `/api/v1/albums`  
  Get all albums.

- **POST** `/api/v1/albums`  
  Add a new album.

- **PATCH** `/api/v1/albums`  
  Update an existing album.

- **GET** `/api/v1/albums/singer/{singerId}`  
  Get all albums of a specific singer.

- **DELETE** `/api/v1/albums/singer/{singerId}/album/{albumId}`  
  Delete a specific album of a singer.

---

## Notes

- This project is meant for ****learning purposes.**** 
- Built with Spring Boot.
- Simple REST API for managing songs, singers, and albums.

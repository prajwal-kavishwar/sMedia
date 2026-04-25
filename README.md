# sMedia Backend Assignment

##  Project Overview
This project is a social media backend built using Spring Boot, PostgreSQL, and Redis. It supports creating posts, adding comments, and handling bot interactions with strict control mechanisms to prevent misuse and ensure system stability.

---

##  Key Features
- REST APIs for creating posts, comments, and likes
- Redis-based virality scoring system
- Concurrency-safe bot interaction limits (max 100 bot replies per post)
- Cooldown mechanism to restrict repeated bot-user interactions
- Notification batching system to prevent spam

---

## Thread Safety (Atomic Locks)

To handle concurrent bot requests safely, Redis is used as a gatekeeper before writing to the database.

Each post maintains a counter:post:{id}:bot_count

Redis provides atomic operations (`INCR`), ensuring thread-safe updates even under heavy load.


---

##  APIs
- POST /api/posts
- POST /api/posts/{postId}/comments
- POST /api/posts/{postId}/like

---

##  Testing
- Concurrency tested using ApacheBench (200 parallel requests)
- Verified maximum 100 bot replies constraint
- Verified notification batching system

---


## 📁 Project Structure

```text
sMedia/
├── src/
│   ├── main/
│   │   ├── java/com/project/sMedia/
│   │   │   ├── controller/        # REST APIs
│   │   │   │   ├── PostController.java
│   │   │   │   ├── CommentController.java
│   │   │   │   └── LikeController.java
│   │   │   │
│   │   │   ├── service/           # Business logic + Redis logic
│   │   │   │   ├── PostService.java
│   │   │   │   ├── CommentService.java
│   │   │   │   ├── RedisService.java
│   │   │   │   ├── ViralityService.java
│   │   │   │   └── NotificationService.java
│   │   │   │
│   │   │   ├── scheduler/         # Background jobs
│   │   │   │   └── NotificationScheduler.java
│   │   │   │
│   │   │   ├── repository/        # Database access (JPA)
│   │   │   │   ├── PostRepository.java
│   │   │   │   ├── CommentRepository.java
│   │   │   │   ├── UserRepository.java
│   │   │   │   └── BotRepository.java
│   │   │   │
│   │   │   ├── entity/            # Database models
│   │   │   │   ├── Post.java
│   │   │   │   ├── Comment.java
│   │   │   │   ├── User.java
│   │   │   │   └── Bot.java
│   │   │   │
│   │   │   ├── dto/               # Request DTOs
│   │   │   │   └── request/
│   │   │   │       ├── PostRequest.java
│   │   │   │       └── CommentRequest.java
│   │   │   │
│   │   │   └── SMediaApplication.java
│   │   │
│   │   └── resources/
│   │       └── application.yaml
│   │
│   └── test/
│
├── docker-compose.yml            # PostgreSQL + Redis setup
├── pom.xml                       # Maven dependencies
├── README.md                     # Project documentation
└── sMedia.postman_collection.json  # API testing collection


---

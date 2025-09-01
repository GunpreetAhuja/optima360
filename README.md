# Optima360 Workflow Platform 🚀

![Build](https://img.shields.io/badge/build-passing-brightgreen)
![Docker](https://img.shields.io/badge/docker-ready-blue)
![Java](https://img.shields.io/badge/java-17-orange)
![Spring Boot](https://img.shields.io/badge/spring--boot-3.3-brightgreen)
![License](https://img.shields.io/badge/license-MIT-lightgrey)


A **Java Spring Boot + ActiveMQ** based workflow automation demo for management companies.  
This project demonstrates **asynchronous processing** using **Queues** and **Topics**, built with industry-standard practices.

---

## ✨ Features
- ✅ REST API to request and complete reports
- ✅ **ActiveMQ Queue** for task distribution (point-to-point)
- ✅ **ActiveMQ Topic** for broadcasting events (pub-sub)
- ✅ Spring Boot 3.3 + Java 17
- ✅ Gradle build + Jenkins CI/CD pipeline
- ✅ Docker + Docker Compose support
- ✅ Integration tests with embedded broker
- ✅ PostgreSQL-ready persistence layer (future extension)

---

## 🛠️ Tech Stack

| Layer            | Technology |
|------------------|------------|
| Language         | Java 17 |
| Framework        | Spring Boot 3.3 |
| Messaging        | ActiveMQ (JMS) |
| Build Tool       | Gradle |
| CI/CD            | Jenkins |
| Containerization | Docker, Docker Compose |
| Database (ext.)  | PostgreSQL/MySQL |
| Testing          | JUnit 5, Spring Boot Test |

---

## ⚡ Quick Start

### Option A: Run with Docker Compose
```bash
docker compose up --build
# App: http://localhost:8080
# ActiveMQ Console: http://localhost:8161 (admin/admin)
```

### Option B: Run Locally
1. Start ActiveMQ locally:
   ```bash
   docker run -it --rm -p 61616:61616 -p 8161:8161 \
     -e ACTIVEMQ_ADMIN_LOGIN=admin -e ACTIVEMQ_ADMIN_PASSWORD=admin \
     apache/activemq-classic:5.18.3
   ```

2. Run the app:
   ```bash
   ./gradlew bootRun
   ```

---

## 📡 API Endpoints

### 1. Submit a report request (Queue)
```bash
curl -X POST http://localhost:8080/api/reports/123
```

### 2. Broadcast report completed event (Topic)
```bash
curl -X POST http://localhost:8080/api/reports/123/complete
```

---

## ✅ Tests
Run unit + integration tests:
```bash
./gradlew test
```

---

## 🔄 CI/CD (Jenkins)
Pipeline stages:
1. **Build** (Gradle)
2. **Test** (JUnit 5)
3. **Docker Build**
4. **Deploy via Docker Compose**

---

## 📜 License
MIT License — free to use for learning, interviewing, or internal projects.

---

### 👨‍💻 Author
Maintained by **Gunpreet Ahuja**  
Feel free to fork and adapt for your own projects.


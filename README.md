# AI User Story Generator (USGenerator)

An AI-powered full-stack web application that generates structured **User Stories with Acceptance Criteria** based on selected domains and feature combinations.

This system uses:

* **Spring Boot (Backend)**
* **Vanilla JavaScript (Frontend)**
* **Google Gemini API (AI Content Generator)**
* **Apache POI (Excel Export)**

---

## 🚀 Project Overview

This application allows users to:

* Select domain (Banking, Healthcare, E-commerce, etc.)
* Generate AI-based user stories
* Navigate multiple combinations
* Track used combinations (avoid duplicates)
* Download generated results in **Excel (.xlsx)**

Key highlights:

* Clean UI and modular frontend
* Professional layered backend
* AI-driven structured output
* Scalable and production-ready architecture

---

## 🏛️ System Architecture

Frontend → Spring Boot REST API → Gemini AI → Excel Generator

---

## 📁 Project Structure

### Frontend


```
frontend/
│
├── index.html 
│
├── css/
│ └── style.css 
│
├── js/
│ ├── config.js 
│ ├── validation.js
│ ├── api.js 
│ ├── generator.js 
│ ├── table.js 
│ ├── navigation.js 
│ └── download.js 
│
└── assets/
└── loader.gif

```

---
### Backend (Spring Boot)


```
backend/
│
├── controller/
│ └── UserStoryController.java
│
├── service/
│ ├── UserStoryService.java
│ ├── GeminiService.java
│ ├── CombinationTrackerService.java
│ └── ExcelService.java
│
├── model/
│ ├── UserStory.java
│ ├── GenerationRequest.java
│ ├── CombinationCriteria.java
│ └── GenerationResponse.java
│
├── repository/
│ └── CombinationStatusRepository.java
│
├── util/
│ ├── PromptBuilder.java
│ └── ExcelGenerator.java
│
├── config/
│ ├── GeminiConfig.java
│ └── CorsConfig.java
│
└── UsgeneratorApplication .java

```

---

## 🖥️ Frontend Flow

* Domain selection in index.html
* Validation for inputs
* API requests via api.js
* Story generation via generator.js
* Table rendering and navigation controls
* Excel download implementation

---

## 🧠 Backend Layer Explanation

### Controller Layer

Handles incoming REST API requests for generation and file download.

### Service Layer

* Business logic
* Gemini API communication
* Excel generation
* Combination tracking

### Model Layer

Defines data structures for requests and responses.

### Repository Layer

Stores used combination history.

### Utility Layer

* PromptBuilder for AI prompts
* ExcelGenerator for .xlsx file creation

### Config Layer

API keys, CORS setup, and RestTemplate configuration.

---

## 🔄 Complete Flow

1. User selects domain
2. Clicks Generate
3. Frontend sends request
4. Backend builds prompt
5. Gemini AI returns stories
6. Backend formats response
7. Frontend displays table
8. User downloads Excel

---

## ⭐ Key Features

* AI-generated user stories
* Combination tracking
* Modular architecture
* Excel export
* Validation + clean UI
* Structured prompt engineering

---

## 🛠 Technologies Used

### Frontend

HTML, CSS, Vanilla JS

### Backend

Java, Spring Boot, Apache POI

### AI

Google Gemini API

---

## ▶️ How to Run

### Backend

* Add Gemini API key in application.properties
* Run Spring Boot application
* Access at [http://localhost:8080](http://localhost:8080)

### Frontend

* Open index.html
* Run with Live Server

---

## 🧱 Design Patterns

* Layered Architecture
* REST API Principles
* Service-Oriented Design
* Separation of Concerns

---

## 🏆 Professional Aspects

* Scalable folder structure
* Reusable service utilities
* Production-grade architecture
* AI integrated with validated prompts
* Excel export for documentation teams

---

## 👨‍💻 Author

**Sonu Kumar**
Final Year B.Tech (CSE) Student
AI & Full Stack Developer

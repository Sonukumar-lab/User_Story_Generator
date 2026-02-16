# AI User Story Generator (USGenerator)

An AI-powered full-stack web application that generates structured User Stories with Acceptance Criteria based on selected domains and combinations.

The system uses **Spring Boot (Backend)** and **Vanilla JavaScript (Frontend)** and integrates with **Google Gemini API** for intelligent content generation.

---

## Project Overview

This project allows users to:

- Select a domain (e.g., Banking, Healthcare, E-commerce, etc.)
- Generate AI-based User Stories
- Navigate through multiple combinations
- Track used combinations to avoid repetition
- Download generated user stories in Excel format

The system ensures:

- Clean UI  
- Controlled combinations  
- API-based AI generation  
- Excel export functionality  
- Professional layered architecture  

---

## Project Architecture

Frontend (HTML + CSS + JS)
↓
REST API (Spring Boot Backend)
↓
Google Gemini API
↓
Excel Generation

---

## 📂 Project Structure

### Frontend Structure

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

---

## Frontend Flow Explanation

### 1️ index.html
- Provides UI for domain selection  
- Contains Generate button  
- Displays dynamic table for user stories  
- Shows loader while API is processing  

### 2️ config.js
Stores:
- Available domains  
- Minimum/Maximum combinations  
- Configuration constants  

Ensures structured generation rules.

### 3️ validation.js
Validates:
- Domain selection  
- Empty inputs  
- Invalid combinations  

Prevents invalid API calls.

### 4️ api.js
Handles:
- REST API calls to backend  
- Sending `GenerationRequest`  
- Receiving `GenerationResponse`  
- Error handling  

### 5️ generator.js
- Triggered when user clicks Generate  
- Calls validation  
- Sends request to backend  
- Updates UI after response  

### 6️ table.js
Dynamically renders:
- User Story  
- Acceptance Criteria  

Updates table without page refresh.

### 7️ navigation.js
Handles:
- Previous combination  
- Next combination  
- Avoids repeated combinations  

### 8️ download.js
- Calls backend Excel API  
- Downloads `.xlsx` file  
- Handles file response properly  

---

## Backend Structure (Spring Boot)
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

---

## Backend Detailed Explanation

### 1. Controller Layer

#### UserStoryController.java
Handles:
- `/generate` → Generate user stories  
- `/download` → Excel file download  

Acts as REST API entry point.

---

### 2. Service Layer

#### UserStoryService.java
Main business logic:
- Accepts request  
- Calls `GeminiService`  
- Tracks combinations  
- Returns structured response  

#### GeminiService.java
Responsible for:
- Connecting to Google Gemini API  
- Sending structured prompt  
- Receiving AI-generated response  
- Parsing output  

#### CombinationTrackerService.java
- Tracks used combinations  
- Prevents repetition  
- Maintains combination history  

#### ExcelService.java
- Accepts generated user stories  
- Calls `ExcelGenerator`  
- Returns Excel file as response  

---

### 3. Model Layer

#### UserStory.java
Represents:
- Title  
- Description  
- Acceptance Criteria  

#### GenerationRequest.java
Incoming request:
- Domain  
- Combination criteria  

#### CombinationCriteria.java
Defines:
- Selected features  
- Filtering logic  

#### GenerationResponse.java
Outgoing response:
- List of user stories  
- Metadata  

---

### 4. Repository Layer

#### CombinationStatusRepository.java
- Stores used combinations  
- Prevents duplication  

---

### 5. Utility Layer

#### PromptBuilder.java
- Builds structured prompt  
- Formats input for Gemini AI  
- Ensures clean AI output  

#### ExcelGenerator.java
- Creates `.xlsx` file  
- Formats:
  - Columns  
  - Headers  
  - Rows  
- Uses Apache POI internally  

---

### 6. Config Layer

#### GeminiConfig.java

Reads from `application.properties`:
spring.application.name=usgenerator
gemini.api.key=YOUR_API_KEY
gemini.api.url=YOUR_GEMINI_ENDPOINT


Configures:
- API Key  
- Base URL  
- RestTemplate  

#### CorsConfig.java
- Enables frontend-backend communication  
- Prevents CORS errors  

---

## Complete Flow (Step-by-Step)

### Step 1: User Selects Domain
Frontend validates input.

### Step 2: Generate Button Click
`generator.js` triggers API call.

### Step 3: Backend Receives Request
`UserStoryController` receives `GenerationRequest`.

### Step 4: Business Logic Execution
`UserStoryService`:
- Validates criteria  
- Calls `GeminiService`  
- Builds prompt via `PromptBuilder`  

### Step 5: Gemini API Call
AI generates structured user stories.

### Step 6: Response Processing
Backend:
- Converts AI output into `UserStory` objects  
- Returns `GenerationResponse`  

### Step 7: Frontend Table Rendering
`table.js` dynamically displays data.

### Step 8: Excel Download
User clicks Download:
- Frontend calls `/download`  
- `ExcelService` generates file  
- File downloaded as `.xlsx`  

---

## Key Features

- AI-based User Story Generation  
- Clean Modular Architecture  
- Combination Tracking  
- CORS Configuration  
- Structured Prompt Engineering  
- Excel Download Functionality  
- Dynamic UI Rendering  
- Professional Layered Backend  

---

## Technologies Used

### Frontend
- HTML5  
- CSS3  
- Vanilla JavaScript  

### Backend
- Java  
- Spring Boot  
- REST API  
- Apache POI  

### AI Integration
- Google Gemini API  

---

## How to Run Project

### Backend Setup

1. Open project in IDE  
2. Add your Gemini API Key in `application.properties`  
3. Run `Application.java`  

Backend runs on:
http://localhost:8080

---

### Frontend Setup

1. Open `index.html`  
2. Run using Live Server  
3. Ensure backend is running  

---

## Design Pattern Used

- Layered Architecture  
- Separation of Concerns  
- RESTful API Design  
- Service-Oriented Structure  

---

## Why This Project is Professional

- Clean folder structure  
- Proper separation of layers  
- API-based architecture  
- Scalable design  
- Secure configuration  
- Reusable services  
- Excel export functionality  
- Production-ready architecture style  

---

## 👨‍💻 Author

**Sonu Kumar**  
4th Year B.Tech (CSE) Student









## Overview
This repository contains an **API Automation Live Project** built using **REST Assured** with **Cucumber (BDD)** in **Java**.  
The goal of this framework is to validate the end-to-end behavior of Google Maps APIs by automating the core CRUD operations:

- **POST** – Add Place
- **GET** – Get Place
- **PUT** – Update Place
- **DELETE** – Delete Place

The framework supports:
- Clean separation between **payloads**, **test data**, **API client layer**, and **step definitions**
- **Data-driven testing** using **Cucumber Scenario Outline + Examples**
- Randomized test data generation per execution to avoid conflicts and improve test reliability

---

## Tech Stack / Tools Used
- **Language:** Java
- **API Automation:** REST Assured
- **BDD:** Cucumber
- **Test Runner:** TestNG
- **Build Tool:** Maven

---

## Java Version Compatibility
This project is maintained in a way that it can be worked on locally with a higher Java version, while still being compatible with older Java used in remote environments.

- **Java Version in Eclipse (Local):** Java 11  
- **Java Version in GitHub (Repo/CI compatibility):** Java 8  

> Note: Ensure your Maven compiler plugin is configured to compile with Java 8 when building from GitHub.

---

## Configuration
Project configuration is externalized in a properties file:

**Path:** `src/main/resources/config/GoogleMapsConfig.properties`

This file contains:
- **Base URL**
- **API Key** (passed as query parameter for POST, GET, PUT requests)

Example keys typically used:
- `baseUrl`
- `apiKey`

---

## How to Run the Tests

### Steps
1. **Import** the project into your **Eclipse Workspace**.
2. Ensure your local environment can run with **Java 8 compatible settings** (even if Eclipse uses Java 11).
3. Add / verify required dependencies in `pom.xml`:
   - Cucumber
   - TestNG
   - REST Assured
   - Maven Surefire Plugin
   - Maven Compiler Plugin
4. Run the test runner:
   - `src/test/java/testrunner/TestRunner.java`

---

## Framework Design Highlights

### 1) API Endpoints Management
All endpoints are stored centrally using an enum:
- `GoogleMapsEndpoint.java` stores endpoints for **POST / GET / PUT / DELETE**.

### 2) Request Payloads using POJOs
Request payloads are modeled using Java POJOs to match JSON structure:
- `GOOGLEPostRequestPayload.java`
- `GOOGLEPutRequestPayload.java`
- `GOOGLEDeleteRequestPayload.java`

### 3) Randomized Test Data
Test data is generated dynamically for each run to ensure uniqueness:
- `RandomUtilities.java` generates random values
- `GoogleMapsTestData.java` consumes `RandomUtilities` and provides structured test data methods

### 4) API Client Wrapper Layer
To keep step definitions clean and reusable, an API client layer is used:
- `APIMethods.java` contains HTTP utility methods and returns response interface
- Also includes a helper to extract values by JSON key from responses

### 5) Hooks and Logging Utilities
- `Hooks.java` manages test setup and cleanup
- `Helper.java` appends date/time to logs and deletes folders after each execution

---

## Final Folder Structure (As Implemented)

```text
APIAutomationProject
│
├─ src
│  ├─ main
│  │  ├─ java
│  │  │  ├─ constants
│  │  │  │  └─ GoogleMapsEndpoint.java
│  │  │  │
│  │  │  ├─ googlerequestpayloads
│  │  │  │  ├─ GOOGLEPostRequestPayload.java
│  │  │  │  ├─ GOOGLEPutRequestPayload.java
│  │  │  │  └─ GOOGLEDeleteRequestPayload.java
│  │  │  │
│  │  │  ├─ propertyutilities
│  │  │  │  └─ GoogleConfig.java
│  │  │  │
│  │  │  ├─ utilities
│  │  │  │  └─ RandomUtilities.java
│  │  │  │
│  │  │  └─ postparameters
│  │  │     └─ POSTLocation.java
│  │  │
│  │  └─ resources
│  │     └─ config
│  │        └─ GoogleMapsConfig.properties
│  │
│  └─ test
│     ├─ java
│     │  ├─ datetimeutilities
│     │  │  └─ Helper.java
│     │  │
│     │  ├─ hooks
│     │  │  └─ Hooks.java
│     │  │
│     │  ├─ stepdefinition
│     │  │  └─ APIStepDefinition.java
│     │  │
│     │  ├─ testdata
│     │  │  ├─ GoogleMapsTestData.java
│     │  │  └─ TestDataBuild.java
│     │  │
│     │  ├─ testrunner
│     │  │  └─ TestRunner.java
│     │  │
│     │  └─ wrapper
│     │     └─ APIMethods.java
│     │
│     └─ resources
│        └─ features
│           └─ GoogleMapsAPI.feature
│
└─ README.md
```

---

## Author / Maintainer Notes
This framework is designed to mimic a **real-time API automation project** structure:
- Scalable for adding more endpoints
- Easy maintenance using centralized endpoints + payload POJOs
- Supports clean BDD automation practices with reusable components


# API Automation Project

This project is a Java-based API automation framework for testing Google Maps Places API endpoints using RestAssured, TestNG, and Maven. It focuses on validating create, retrieve, update, and delete operations for place records and includes both positive and negative scenarios.

## Overview

The framework is designed to automate API validation in a structured and reusable way. It contains:

- Request builders for API calls
- Response parsers and validators
- Static and dynamic JSON validation utilities
- Data-driven test support
- Extent Reports for both positive and negative test execution

This project is useful for learning and implementing API test automation patterns using real-world REST API interactions.

## Tech Stack

- Java 17
- Maven
- TestNG
- RestAssured
- Jackson
- AssertJ
- ExtentReports
- SLF4J

## Project Structure

```text
APIAutomationProject
├── pom.xml
├── testng.xml
├── src
│   ├── main
│   │   └── java
│   │       ├── constants
│   │       │   └── GoogleMapsAPIConfiguration.java
│   │       ├── enums
│   │       │   ├── PlaceEndpoints.java
│   │       │   └── PlaceHttpMethod.java
│   │       ├── utilities
│   │       │   ├── ExtentLogger.java
│   │       │   ├── ExtentManager.java
│   │       │   ├── ExtentTestManager.java
│   │       │   ├── LoggingUtilities.java
│   │       │   └── RandomUtilities.java
│   │       └── wrapper
│   │           ├── PlaceRequestUtilities.java
│   │           ├── PlaceResponseUtilities.java
│   │           └── PlaceValidationUtilities.java
│   └── test
│       └── java
│           ├── responses
│           │   ├── delete_response.json
│           │   ├── get_after_delete_response.json
│           │   ├── post_response.json
│           │   ├── put_after_delete_response.json
│           │   └── put_response.json
│           ├── testdata
│           │   └── TestDataBuild.java
│           ├── tests
│           │   ├── BaseTest.java
│           │   ├── DataDrivenPlaceTest.java
│           │   ├── GoogleMapsNegativeTests.java
│           │   ├── GoogleMapsPositiveTests.java
│           │   └── InvalidPlaceID.java
│           └── utilities
│               └── JSONDataProvider.java
└── reports
    ├── positive
    └── negative

# Log Detector Tool

A Java Spring Boot application that exposes a REST API to upload application log files, parse and classify log entries, detect repeated failures, and return a structured plain-text summary report for debugging and monitoring.

## Problem Statement

Modern software systems generate large volumes of logs during execution. These logs contain useful information about application flow, warnings, failures, exceptions, and system health. Manually scanning log files to identify critical issues is time consuming and error prone.

This project solves that problem by building a Spring Boot REST service that:
- accepts log file uploads via a REST API endpoint
- parses each log entry into structured fields
- counts log levels such as INFO, WARN, and ERROR
- detects repeated log messages across distributed components
- returns a readable summary report as an HTTP response

## Project Structure

```text
log-detector-tool/
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/heena/logdetector/
│   │   │       ├── Main.java                    
│   │   │       ├── LogReader.java
│   │   │       ├── LogParser.java            
│   │   │       ├── LogAnalyzer.java             
│   │   │       ├── LogReportGenerator.java      
│   │   │       ├── controller/
│   │   │       │   └── LogController.java       
│   │   │       ├── service/
│   │   │       │   └── LogAnalysisService.java  
│   │   │       └── model/
│   │   │           └── LogEntry.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── module/
│   │           └── app.log
│   └── test/
│       └── java/
│           └── com/heena/logdetector/
│               ├── LogAnalyzerTest.java
│               └── LogParserTest.java
```

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/logs/analyze` | Upload a `.log` file and receive a plain-text summary report |
| GET | `/api/logs/health` | Check if the service is running |

### Example Request

```bash
curl -X POST http://localhost:8080/api/logs/analyze \
  -F "file=@src/main/resources/module/app.log"
```

### Example Response

```
Log Analysis Report
===================
Total entries: 120
INFO  : 85
WARN  : 22
ERROR : 13

Repeated Messages:
- "Connection timeout" — 5 occurrences
- "NullPointerException in PaymentService" — 3 occurrences
```

## Features

- REST API for log file upload and analysis (Spring MVC)
- Parses raw log lines into structured log entries
- Counts total INFO, WARN, and ERROR logs
- Detects repeated messages across log entries
- Returns a plain-text summary report via HTTP response
- Unit tests using JUnit 5

## Tech Stack

- Java 17
- Spring Boot 3.2.5
- Spring MVC (REST)
- Maven
- JUnit 5

## Running the Application

```bash
# Build
mvn clean install

# Run
mvn spring-boot:run

# The API will be available at http://localhost:8080
```

## Running Tests

```bash
mvn test
```
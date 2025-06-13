# Internship Application Program

A Java CLI program developed to fulfill the BACS2063 Data Structures and Algorithms course assignment at TAR UMT. This program simulates basic interactions between students (applicants) and companies (employers), using self-implemented Abstract Data Types (ADTs) instead of Java’s built-in Collections Framework. The goal is to demonstrate understanding of data structures and apply the ECB (Entity-Control-Boundary) design pattern as required by the assignment.

## 📁 Project Structure

```
com.tarumt
├── adt              # Custom ADT interfaces and implementations
│   ├── function     # Functional interfaces (lambda callbacks)
│   ├── list         # List ADT implementations (doubly linked lists)
│   ├── map          # Map ADT implementations (simple hash map)
│   └── set          # Set ADT implementations (simple hash set)
├── boundary         # UI classes for interacting with users (console-based)
├── control          # Business logic and controllers for each module (core)
├── dao              # Data access classes (hard coded mock data)
├── entity           # Entity classes (e.g., Applicant, JobPosting, Company)
└── utility
    ├── common       # Shared helper classes (e.g., input handling, logging)
    ├── matching     # Job-applicant matching algorithms and logic
    ├── pretty       # Display formatting utilities (e.g., tables, output styling)
    ├── search       # Search and filter logic (e.g., keyword, fuzzy search)
    └── validation   # Input validation and basic data checks
```

---

## 🧠 Design Patterns and Programming Techniques

### 🏭 Factory Pattern – `ConditionFactory`

**Factory Design Pattern** in `ConditionFactory` to create the right type of validation condition (`IntegerCondition`, `DecimalCondition`, `StringCondition`) based on the input or annotation.

#### ✅ Example:

```java
IntegerCondition condition = ConditionFactory.integer()
    .min(0)
    .max(100);
```

Or dynamically from field annotations:

```java
Field field = ValidationFieldReflection.getField(SomeClass.class, "age");
Condition condition = ConditionFactory.fromField(field);
```

> This centralizes and abstracts condition creation, promoting maintainability and extensibility.

---

### 🔗 Method Chaining

Many utility and validation classes (e.g., `IntegerCondition`, `StringCondition`) use method chaining to make condition-building expressive and readable.

#### ✅ Example:

```java
IntegerCondition condition = ConditionFactory.integer()
    .min(18, "Age must be at least 18")
    .max(60, "Age must not exceed 60")
    .enumeration(18, 25, 30, 35);
```

> This pattern makes complex validation rules more fluent and declarative.

---

### 🏷️ Custom Annotation-Oriented Programming

The validation system supports **custom annotations** like `@Min`, `@Max`, `@Regex`, and `@Enumeration`, which are processed at runtime using reflection in `ConditionFactory`.

#### ✅ Example:

```java
public class JobPosting {
    @Min(value = 1500, message = "Salary must be at least RM1500")
    @Max(value = 10000)
    private int salary;

    @Regex(pattern = "^[A-Za-z ]+$", message = "Job title must contain only letters and spaces")
    private String title;
}
```

The annotations are parsed via:

```java
ConditionFactory.fromField(field);
```

---

## 📦 Core Modules

- **Job Management**: CRUD + filters for job postings
- **Applicant Management**: CRUD + filters for applicants
- **Matching Engine**: Skill/location-based ranking with custom weightings
- **Interview Scheduler**: Time slot allocation and candidate ranking

---

## 📑 Reports

- Job & applicant listings
- Matching scores
- Interview schedules and outcomes

---

## 🔍 Advanced Search Support

- Keyword-based search (e.g., "developer" matches job title/description).
- Fuzzy search: implemented using both Damerau-Levenshtein Similarity and Jaro-Winkler Similarity to handle typos and approximate matches

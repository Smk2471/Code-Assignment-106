# Code Assignment 106 – Employee Analyzer

## 📌 Problem Statement
BIG COMPANY wants to analyze its organizational structure and identify improvements.

The system must check:

1. **Manager Salary Rules**
   - A manager must earn **at least 20% more** than the average salary of their direct subordinates.
   - A manager must earn **no more than 50% more** than the average salary of their direct subordinates.
   - Report managers who earn **less** or **more**, and by how much.

2. **Reporting Line Length**
   - An employee must not have **more than 4 managers** between them and the CEO.
   - Report employees with a reporting line that is **too long**, and by how much.

3. **Input**
   - A CSV file with the format:

        Id,firstName,lastName,salary,managerId  
        123,Joe,Doe,60000,  
        124,Martin,Chekov,45000,123  
        125,Bob,Ronstad,47000,123  
        300,Alice,Hasacat,50000,124  
        305,Brett,Hardleaf,34000,300  

---

## 📂 Project Structure

    code-assignment-106/
     ├── employees.csv
     ├── pom.xml
     ├── src/
     │   ├── main/java/com/bigcompany/
     │   │   ├── App.java
     │   │   ├── model/Employee.java
     │   │   └── service/EmployeeAnalyzer.java
     │   └── test/java/com/bigcompany/service/
     │       └── EmployeeAnalyzerTest.java
     └── README.md

---

## ⚙️ Assumptions
- The CSV file is **well-formed** and always has a header line.
- The `managerId` column is empty (`null`) for the **CEO**.
- Salaries are positive numbers.
- An employee has only **one direct manager**.
- The CSV size can be up to 1000 rows, which is manageable in memory for this solution.
- If no subordinates exist for a manager, salary validation is skipped.
- Reporting line depth is counted as the **number of managers above an employee** (CEO has depth 0).

---

## 🛠️ Approach
1. **Parsing Input**
   - Read CSV file into `Employee` objects.
   - Store employees in a `Map<Integer, Employee>` for quick lookup.

2. **Building Relationships**
   - Create a map of `managerId → list of employees` to quickly find subordinates.

3. **Salary Validation**
   - For each manager, calculate average salary of direct subordinates.
   - Compute the **20%–50% allowed salary range**.
   - Compare manager’s salary to the range and report if it is **too low** or **too high**.

4. **Reporting Line Validation**
   - For each employee, calculate the depth of their management chain up to the CEO.
   - If depth > 4, report the violation and by how much.

5. **Output**
   - Results are printed to the console for easy review.
   - JUnit tests are included for salary checks and reporting line checks.

---

## ⚙️ How to Run

### Prerequisites
- Java 21+
- Maven 3.9+
- IntelliJ IDEA (or any Java IDE)

### Steps
1. Clone or download this repository:

       git clone <repo-url>
       cd code-assignment-106

2. Open the project in **IntelliJ IDEA** (`File → Open → select pom.xml`).

3. Place `employees.csv` in:
   - Either the **project root** (next to `pom.xml`),  
   - Or in `src/main/resources` (recommended).

4. Run the main class:

       com.bigcompany.App

---

## 🖥️ Example Output
With the sample CSV:

    === Checking Managers' Salaries ===
    Manager 124 - Martin Chekov earns LESS than allowed by 15000.0

    === Checking Reporting Lines ===

- **Martin Chekov** is underpaid compared to his subordinate.
- No reporting line issues in this dataset.

---

## 🧪 Running Tests
To run unit tests:

    mvn test

---

## 🔍 Extended Testing (Optional)
If you want to test all scenarios:

### Extended employees.csv

    Id,firstName,lastName,salary,managerId
    123,Joe,Doe,60000,
    124,Martin,Chekov,45000,123
    125,Bob,Ronstad,47000,123
    300,Alice,Hasacat,90000,124
    305,Brett,Hardleaf,34000,300
    400,Sam,Worker,30000,305
    401,Tom,Intern,25000,400
    402,Mike,Trainee,20000,401
    403,Jim,Junior,18000,402
    404,Liam,Deep,15000,403

### Expected Output

    === Checking Managers' Salaries ===
    Manager 124 - Martin Chekov earns LESS than allowed by 15000.0
    Manager 300 - Alice Hasacat earns MORE than allowed by 39000.0

    === Checking Reporting Lines ===
    Employee 404 - Liam Deep has too long reporting line by 1

---

## 👨‍💻 Author
- Developed for **Code Exercise 106**
- Built using **Java 21, Maven, JUnit**

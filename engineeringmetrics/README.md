
# 💻 Engineering Metrics Dashboard

A Spring Boot + HTML/JS based dashboard that helps you track and visualize DevOps engineering KPIs like:

- **Change Failure Rate (CFR)**
- **Mean Time To Recovery (MTTR)**
- **Total Deployments**
- **Total Incidents**

It provides a visual representation using **Chart.js** for easy consumption by teams, managers, or stakeholders.

---

## 📦 Tech Stack

- Java 17
- Spring Boot
- Maven
- PostgreSQL (optional for production use)
- Chart.js + HTML (Frontend)
- Gitpod (optional, for browser-based execution)

---

## 🚀 How to Run This Project

You can run the application either **locally** or on **Gitpod** (cloud IDE in your browser). Both setups are explained below.

---

## ✅ 1. GitHub Setup

### Step 1: Clone the Repository

```bash
git clone https://github.com/jignesh-khandelwal/jenkins-on-gitpod.git
cd engineeringmetrics
````

---

## 💡 2. Run in Gitpod (Browser-based, No Setup Required)

> Skip to **Section 3** if you prefer running locally.

### Step 1: Open Gitpod

Open the GitHub repo in Gitpod using the following URL:

```
https://gitpod.io/#https://github.com/jignesh-khandelwal/jenkins-on-gitpod
```

### Step 2: Wait for Environment to Load

Gitpod will:

* Clone your repo
* Set up the development environment
* Open a terminal and code editor

### Step 3: Build & Run the App

```bash
chmod +x mvnw
./mvnw spring-boot:run
```

> If Maven is globally installed, you can also run:
> `mvn spring-boot:run`

### Step 4: Open the Application

Click the **PORTS** tab in Gitpod and open `http://localhost:8080` (Gitpod will forward it to a public URL).

---

## 🖥️ 3. Run Locally (on Your Laptop)

### Step 1: Prerequisites

Ensure you have the following installed:

* Java 17+
* Maven
* Git

### Step 2: Clone and Enter Directory

```bash
git clone https://github.com/jignesh-khandelwal/jenkins-on-gitpod.git
cd engineeringmetrics
```

### Step 3: Run the Spring Boot App

```bash
mvn spring-boot:run
```

> This will start the app on `http://localhost:8080`

---

## 🧪 4. Insert Sample Data (Optional)

If you're starting fresh and want to see some sample metrics:

Open your browser or Postman and hit:

```
http://localhost:8080/api/insert-sample-data
```

You should receive a success message like:

```json
{"message": "Sample data inserted successfully"}
```

---

## 📊 5. View Metrics Dashboard (UI)

Go to:

```
http://localhost:8080
```

You’ll see:

* A **Pie Chart** showing failed vs successful deployments
* A **Bar Chart** showing totals and MTTR

These charts are rendered using **Chart.js** and dynamically fetch data from `/api/metrics`.

---

## 📡 6. API Endpoints

| Method | Endpoint                  | Description                       |
| ------ | ------------------------- | --------------------------------- |
| GET    | `/api/metrics`            | Fetches calculated metrics        |
| POST   | `/api/insert-sample-data` | Adds sample incidents/deployments |
| GET    | `/api/incidents`          | List of all incidents             |
| GET    | `/api/deployments`        | List of all deployments           |

---

## 🗂️ 7. Project Structure

```
engineeringmetrics/
├── src/
│   ├── main/
│   │   ├── java/com/mttr/engineeringmetrics/
│   │   │   ├── controller/
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   │   └── service/
│   │   └── resources/
│   │       ├── static/
│   │       │   └── index.html
│   │       └── application.properties
├── pom.xml
└── README.md
```

---

## ⚙️ 8. Configuration

If you want to switch from in-memory DB (H2) to PostgreSQL, edit the `application.properties` file:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/yourdb
spring.datasource.username=youruser
spring.datasource.password=yourpass
spring.jpa.hibernate.ddl-auto=update
```

---
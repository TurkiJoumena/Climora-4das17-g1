# 🌦️ Climora

**Weather Monitoring and Forecasting Platform Based on Microservices Architecture**

Climora is a weather monitoring platform designed to provide weather information, forecasts, historical data, alerts, and personalized activity planning through a microservices-based architecture.

## 📁 Project Structure

```text
Climora/
│
├── Backend/
│   ├── MS1-Localisation/
│   ├── MS2-Stations-Sources/
│   ├── MS3-Meteo-Actuelle/
│   ├── MS4-Alertes-Notifications/
│   ├── MS5-Previsions/
│   ├── MS6-Planificateur-Activites/
│   └── MS7-Signalements/
│
├── Frontend/
│
├── Documentation/
│   ├── Presentation/
│   └── Diagrams/
│
└── README.md
```

## ⚙️ Microservices Architecture

| Microservice | Responsibility | Database |
|---|---|---|
| MS1 – Location Service | City and location management | H2 |
| MS2 – Stations & Sources Service | Weather stations and data source management | H2 |
| MS3 – Current Weather Service | Real-time weather measurements | MongoDB |
| MS4 – Alerts & Notifications Service | Weather alerts and notification management | MySQL |
| MS5 – Forecast Service | Weather forecasts for the next 7 days | MongoDB |
| MS6 – Activity Planner Service | Activity recommendations and evaluations | PostgreSQL |
| MS7 – Reports Service | User weather reports and voting | PostgreSQL |

## 🗄️ Database Architecture

Each microservice has its own dedicated database:

- **MS1:** `localisation_db` – H2
- **MS2:** `sources_db` – H2
- **MS3:** `meteo_db` – MongoDB
- **MS4:** `alertes_db` – MySQL
- **MS5:** `previsions_db` – MongoDB
- **MS6:** `activites_db` – PostgreSQL
- **MS7:** `signalements_db` – PostgreSQL

## 🛠️ Technologies

Technologies and development tools will be documented as the project progresses.

## 📚 Documentation

- **Presentation:** Project presentation and overview.
- **Diagrams:** Architecture diagrams and UML models.


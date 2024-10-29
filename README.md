# MyWeather

## 1. Project Description

The MyWeather application is designed for searching and retrieving weather data. It fulfills the MVP (Minimum Viable Product) requirements for a weather application of this kind.
It uses a free API provided by AccuWeather as its data source.

| ![Current Weather](docs/screenshot1.jpg) | ![Search History](docs/screenshot2.jpg) |
| --- | --- |

## 2. Implemented Required Features:

- city search
- presentation of a list of searched cities with selection option
- retrieval of current weather data for a selected city
- presentation of city data and its current weather conditions according to specifications (current temp, cloudiness, precipitation probability, humidity)
- validation of search field according to functional requirements: city name cannot contain numbers or special characters, support for Polish characters, use of regular expressions
- presentation of the current temperature with color representing hot, moderate, and low temperatures according to requirements

## 3. Implemented Additional Features

- retrieval of a 5-day weather forecast for the selected city, presented in a day-by-day table
- presentation of additional detailed data for current weather, such as wind speed and direction, feels-like temperature, and visibility
- saving selected cities to a local database
- display of the saved cities list
- retrieval of weather and forecast for a city selected from the saved cities list
- support for color themes in line with the mobile device’s day or night mode
- multilingual support (i18n), with files created for both Polish and English languages (default)

## 4. Application Architecture

### 4.1 Clean Architecture

The application applies **Clean Architecture** principles, dividing it into layers: domain, data, and presentation.

The goals of this approach are:
- a maintainable and clear project structure
- modular division, allowing independent development and faster code compilation
- encapsulation of business logic
- separation of dependencies between layers: outer layers depend on inner layers, specific implementations depend on abstractions
- scalability
- easier testing

**Dependency Injection** is used for configuration and object instantiation, as well as to provide dependencies to higher layers.

### 4.2 Project Modularity

The application is divided into the following modules (from lowest/innermost layer):

- **domain** - defines entities of the domain model and business logic: use cases and repository interface
- **data** - manages data access, containing DTO (data transfer objects) implementation and repository, data fetching from the API, and data save/read in a local database using DAO (data access objects). This layer also handles translating database and API objects (JSON) to domain model objects.
- **common** - auxiliary module containing, for example, data validation functions
- **presentation** - user interface (UI) module, containing color theme, screen appearance, controls, and view model
- **app** - entry point to the application, invoking the main activity (MainActivity)

### 4.3 Presentation Layer Architecture

In the presentation layer, the **MVI** (Model - View - Intent) architectural pattern is used, which allows:
- state management
- component independence
- state and process reproducibility
- unidirectional communication
- easier debugging and testing

The ViewModel maintains and exposes the state to the views. Intents define user interactions.
The View is updated based on the state in the ViewModel, which serves as the "single source of truth."

## 5. Frameworks and Libraries Used

The programming language used is **Kotlin**, version **2.0.20**.

- **Hilt 2.51** - library for dependency injection
- **Retrofit 2.11** - framework for REST API communication
- **Room 2.6.1** - ORM framework for local database operations
- **Coroutines 1.9** - Kotlin’s built-in library for concurrent operation processing
- **Compose 2024.09.02** - library for declarative UI building
- **Moshi 1.15.1** - library assisting with JSON data parsing

Details are in the **lib.versions.toml** file.

## 6. AccuWeather API

AccuWeather API was chosen for the project due to its simplicity and clear documentation. The endpoints directly fulfill the project requirements.

Resources used:

**Locations API**
- `GET locations/v1/cities/search` - for city search

**Forecast API**
- `GET forecasts/v1/daily/5day/{cityKey}` - for retrieving a 5-day forecast for a given city

**Current Conditions API**
- `GET currentconditions/v1/{cityKey}` - for retrieving current weather conditions for a given city

Detailed documentation: https://developer.accuweather.com/apis

## 7. Project Configuration and Build

- Configured for compilation with Android SDK API 34.
- Minimum device API: 26 (Android 8)
- Android Studio: 2024.1.2 Patch 1
- OpenJDK 17.0.11
- Gradle 8.7

An AccuWeather API key is required to compile the project:
https://developer.accuweather.com/user/me/apps

The key should be placed in the **local.properties** file, which is not stored in the repository.
Add the following line to this file:

`ACCUWEATHER_API_KEY=xxxxxxxxxxxxxxxxxxxxxxxxxxxxx`

**NOTE:** In the free access package, it is possible to make up to 50 API requests per day.

## 8. Unit Testing ###

- Unit tests have been implemented in the **data** and **common** modules to verify the correct functioning of business logic and data layers. Basic use cases have been tested, covering the domain layer as well.
- Unit and UI tests for the **presentation** layer have not been implemented due to time constraints.

## 9. Future Development ###

The application is ready for further development. Proposed additional features outside the requirements include:
- loading the last-selected city’s data from the local DB upon app startup
- data caching: saving current weather and forecast data for a city on a given day to the local DB, to minimize API calls
- settings screen: options for units (metric/imperial), forecast language (currently hard-coded in service definition), etc.
- implementation of additional features provided by the AccuWeather API, such as detailed hourly forecasts or longer-term forecasts (available in paid package)
- management of the saved cities list (e.g., deletion option), which is already supported in business logic

The project should be prepared for production and deployment. Consider implementing CI/CD for app building (e.g., GitLab pipelines).

NewsAppCompose

A modern News App built with Jetpack Compose, MVVM, Hilt, and Clean Architecture that fetches real-time headlines from NewsAPI.org.
It can also serve as a template for starting new Android projects — simply replace the current screens and update your API setup.

📱 Features
	•	Fetch top headlines by country & category
	•	Built entirely with Jetpack Compose
	•	Clean Architecture (Separation of concerns)
	•	MVVM Pattern with reactive StateFlow
	•	Hilt Dependency Injection
	•	DataStore Preferences for local storage
	•	Reusable Base Repository with ApiProcessor for handling API states (Loading, Success, Error, NetworkError)
	•	Easy to replace UI screens for new projects
	•	Status bar color customization


 🛠️ Tech Stack
	•	Kotlin
	•	Jetpack Compose
	•	Hilt for DI
	•	Clean Architecture (Domain, Data, Presentation layers)
	•	Coroutines & Flow for async operations
	•	Retrofit for network calls
	•	Coil for image loading
	•	DataStore Preferences for local storage


📖 How to Use as a Template
	1.	Delete or replace the current screens in ui/screens/
	2.	Update API service in data/remote/ApiService.kt
	3.	Keep the architecture, DI, and repository setup for faster project setup
	4.	Customize Theme and Navigation

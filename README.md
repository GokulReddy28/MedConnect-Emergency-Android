🚨 MedConnect Emergency Android App

A mobile emergency alert system that instantly contacts police, notifies backend, and sends real-time GPS location.

📌 Overview

This Android app allows users to trigger an emergency with one big button.
When pressed:

1️⃣ A direct call is placed (police / emergency number)
2️⃣ GPS location is captured
3️⃣ A backend API request sends emergency details
4️⃣ User gets confirmation UI
5️⃣ Doctor/Hospital get alerts via backend
🛠 Tech Stack
Component	Technology
Language	Java
API Client	Retrofit
Permissions	Runtime Permissions
Location	FusedLocationProviderClient
UI	XML + ConstraintLayout
🚀 Features
✔ Large Emergency Button

Easy to access, fast response.

✔ Automatic Phone Call

Calls police instantly with permission checks.

✔ GPS Location Tracking

Uses Google Play Services for accurate location.

✔ Backend Integration

Sends emergency details using Retrofit.

✔ Login System

Role-based redirect:

Patient Dashboard

Doctor Dashboard

Hospital Dashboard

📡 API Used

Base URL (Ngrok or Localhost):

https://your-ngrok-url/api/

1. Login
POST /api/user/login

2. Trigger Emergency
POST /api/emergency/trigger

🧩 Main Components
File	Purpose
LoginActivity.java	Handles login through API
mainActivity.java	Contains emergency button + call + location
ApiClient.java	Retrofit base setup
ApiInterface.java	API endpoints
Emergency layout	UI for emergency button screen
🔧 How to Run
1️⃣ Open in Android Studio
2️⃣ Update ApiClient.java with Ngrok URL
3️⃣ Enable location + call permissions on device
4️⃣ Run on phone or emulator
📱 Screenshots (Add later)

Login screen

Emergency button

Alert success popup

👤 Author

Gokul Reddy
Android Developer | Java | Mobile UI/UX

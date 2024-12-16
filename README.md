# Firebase WebView Analytics Integration

This project demonstrates how to integrate a WebView-based application with Firebase Analytics. It includes event tracking for both WebView and native components, ensuring seamless communication between JavaScript and Android native code.

---

## 🚀 Features
1. **Event Logging**: Supports custom events, screen views, e-commerce events, promotions, and error tracking.
2. **WebView Integration**: Communicates between JavaScript and Android using `JavascriptInterface`.
3. **Firebase Analytics Integration**: Logs events to Firebase, including enriched user and session data.
4. **Session & User Management**: Validates and manages `session_id` and `user_id` to ensure data consistency.

---

## 📂 Project Structure
### **Key Files**
- **MainActivity.kt**:
  Initializes the WebView, Firebase Analytics, and the JavaScript bridge.
  
- **WebBridgeManager.kt**:
  Handles communication between JavaScript and native Android code.

- **FirebaseProvider.kt**:
  Logs enriched events to Firebase Analytics.

- **SessionManager.kt** & **UserManager.kt**:
  Manages session and user identifiers.

- **index.html**:
  A sample HTML file demonstrating event tracking from JavaScript.

---

## 🛠️ Setup Instructions

### **1. Prerequisites**
- Android Studio installed.
- Firebase Project created and configured.
- Git installed on your system.

### **2. Firebase Setup**
1. Create a new Firebase project in the Firebase Console.
2. Add your Android app to the project.
3. Download the `google-services.json` file and place it in the `app/` directory.
4. Enable DebugView in Firebase for testing event tracking.


# 🖼️ PHOTO GALLERY APP (ANDROID)

A simple and efficient **Photo Gallery Android Application** built using **Kotlin** that allows users to view, manage, and browse images stored on their device. The app focuses on performance, clean UI, and proper handling of Android permissions.

---

## 🚀 FEATURES

* 📂 **View Device Images**

  * Displays photos stored on the device
  * Fetches images using MediaStore
* 🧭 **Smooth Navigation**

  * Grid-based image layout
  * Fast scrolling with optimized loading
* 🔐 **Runtime Permissions**

  * Handles storage/media permissions properly
* ♻️ **RecyclerView**

  * Efficient image loading and recycling
* 🧩 **Modular Code Structure**

  * Easy to understand and maintain

---

## 🛠️ TECH STACK

* **Language:** Kotlin
* **UI:** XML Layouts
* **Components:** Activities, RecyclerView
* **Image Source:** Android MediaStore API
* **Architecture:** Basic MVVM / Clean structure
* **Build Tool:** Gradle

---

## 📂 PROJECT STRUCTURE

```
com.example.photogallery
│
├── activities
│   └── MainActivity.kt
│
├── adapters
│   └── PhotoAdapter.kt
│
├── models
│   └── Photo.kt
│
├── utils
│   └── PermissionUtils.kt
│
├── res
│   ├── layout
│   ├── drawable
│   └── values
```

---

## 📸 SCREENS INCLUDED

* Photo Grid Screen
  
  <img width="377" height="828" alt="Screenshot 2026-01-05 172420" src="https://github.com/user-attachments/assets/6b609cc5-2f25-4480-8ae0-b3f395159cf6" />

* Permission Request Screen
  
   <img width="379" height="835" alt="Screenshot 2026-01-05 172434" src="https://github.com/user-attachments/assets/e627f2cc-0eea-4d5c-80b2-e147ec59a045" />

---

## ✅ HOW TO RUN THE PROJECT

1. Clone the repository

   ```bash
   git clone <your-repository-url>
   ```
2. Open the project in **Android Studio**
3. Sync Gradle files
4. Grant media/storage permission when prompted
5. Run the app on an emulator or physical device ▶️

> 📌 Note: For Android 13+, media permissions are handled using `READ_MEDIA_IMAGES`.

---

## 🎯 LEARNING OUTCOMES

* Working with **MediaStore API**
* Handling **runtime permissions**
* Implementing **RecyclerView with GridLayoutManager**
* Managing device storage data efficiently

---

## 👨‍💻 AUTHOR

**Kartike Saini**
Android Developer | Kotlin
📧 Email: [kartikesaini2304@gmail.com](mailto:kartikesaini2304@gmail.com)

---

## ⭐ SUPPORT

If you find this project helpful, give it a ⭐ on GitHub and feel free to fork or contribute!

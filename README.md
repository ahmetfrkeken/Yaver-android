# Yaver Android

Android client for **Yaver**, an AI-assisted task manager. Built with Kotlin, Jetpack Compose and Material 3.
The backend lives in a separate repo: [`yaver-backend`](https://github.com/ahmetfrkeken/Yaver-backend).

## Tech stack
- Kotlin, Jetpack Compose, Material 3
- Dagger Hilt (dependency injection)
- MVVM with unidirectional data flow (ViewModel, StateFlow)

## Planned
- [ ] Retrofit + OkHttp + kotlinx.serialization networking
- [ ] Login / register and token storage (DataStore)
- [ ] Task list with Room (offline-first) and Navigation 3
- [ ] AI assistant chat with streamed responses

## Run locally
1. Start the backend (see the `yaver-backend` README).
2. Open the project in Android Studio and run it on an emulator.
   The emulator reaches the backend at `http://10.0.2.2:8080`.

## Status
Work in progress.
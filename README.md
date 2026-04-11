# 🔯 72 Names — Kabbalistic Meditation App

Android app for Kabbalistic meditation on the 72 Names of God.
Built with Kotlin, Jetpack Compose, Clean Architecture, MVVM.

---

## 📱 Features (roadmap)

- Navigate all 72 Names with meditation texts (8 per page, swipe navigation)
- Search by meaning or transliteration
- Daily name suggestion based on the Kabbalistic calendar
- Birth date profile with personal insights
- Share meditations with others
- Multi-language support (PT, EN, ES, FR, IT, DE) via pre-translated JSON assets

---

## 🏗️ Architecture

Clean Architecture with 3 layers:
- **Presentation** — Jetpack Compose screens + ViewModels (MVVM)
- **Domain** — Entities, UseCases, Repository interfaces (pure Kotlin)
- **Data** — Room DB, DataStore, JSON assets, Repository implementations

Dependency injection via Hilt.

---

## 🛠️ Tech stack

| Layer | Technology |
|---|---|
| Language | Kotlin 2.0 |
| UI | Jetpack Compose |
| Architecture | Clean Architecture + MVVM |
| DI | Hilt |
| Local DB | Room |
| Preferences | DataStore |
| Navigation | Navigation Compose |
| Min SDK | API 26 (Android 8.0) |
| Compile SDK | API 35 |

---

## 📁 Project structure

```
app/src/main/
├── assets/
│   └── sacred_names_pt.json
├── java/com/nomes72/app/
│   ├── data/
│   │   ├── local/
│   │   │   ├── dao/SacredNameDao.kt
│   │   │   ├── entity/SacredNameEntity.kt
│   │   │   └── NamesDatabase.kt
│   │   └── repository/
│   │       ├── SacredNameRepositoryImpl.kt
│   │       └── UserProfileRepositoryImpl.kt
│   ├── di/
│   │   ├── DatabaseModule.kt
│   │   ├── DataStoreModule.kt
│   │   └── RepositoryModule.kt
│   ├── domain/
│   │   ├── model/
│   │   │   ├── DailyInsight.kt
│   │   │   ├── SacredName.kt
│   │   │   └── UserProfile.kt
│   │   ├── repository/
│   │   │   ├── SacredNameRepository.kt
│   │   │   └── UserProfileRepository.kt
│   │   └── usecase/
│   │       ├── GetAllNamesUseCase.kt
│   │       ├── GetDailyNameUseCase.kt
│   │       └── GetNameByNumberUseCase.kt
│   ├── ui/
│   │   ├── screen/
│   │   │   ├── HomeScreen.kt
│   │   │   └── HomeViewModel.kt
│   │   └── theme/
│   │       ├── Color.kt
│   │       ├── Theme.kt
│   │       └── Type.kt
│   ├── MainActivity.kt
│   └── NomesApp.kt
└── res/
```

---

## 📓 Production diary

### ✅ Day 1 — 2026-04-10
- Defined app concept: 72 Kabbalistic Names meditation app
- Chose tech stack: Kotlin native, Jetpack Compose, Clean Architecture
- Decided on pre-translated assets strategy for 6 languages (PT, EN, ES, FR, IT, DE)
- Created private GitHub repository
- Cloned repo locally on Ubuntu
- Created Android Studio project (Empty Activity, Kotlin DSL)
- Connected project to GitHub via SSH
- First commit: initial project setup

### ✅ Day 2 — 2026-04-11
- Updated `libs.versions.toml` with all project dependencies
  (Hilt, Room, Navigation Compose, DataStore, Coroutines, Serialization)
- Updated `build.gradle.kts` (root and app module)
- Fixed dependency versions for compatibility with AGP 8.8.0
- compileSdk = 35, targetSdk = 35
- Created Clean Architecture package structure (domain, data, ui, di)
- Created domain models: `SacredName`, `DailyInsight`, `UserProfile`
- Created repository interfaces: `SacredNameRepository`, `UserProfileRepository`
- Created UseCases: `GetDailyNameUseCase`, `GetAllNamesUseCase`, `GetNameByNumberUseCase`
- Established rule: always create/edit files via Android Studio, not terminal

### ✅ Day 3 — 2026-04-11
- Created `NomesApp` class with `@HiltAndroidApp`
- Added `@AndroidEntryPoint` to `MainActivity`
- Created `SacredNameEntity` (Room entity with `||` separator for attributes)
- Created `SacredNameDao` (getAllNames, getByNumber, insertAll, count)
- Created `SacredNameRepositoryImpl` (loads JSON on first run, Room as cache)
- Created `sacred_names_pt.json` asset with all 72 Names in Portuguese
- Revised JSON: refined meanings, meditations and attributes for consistency

### ✅ Day 4 — 2026-04-11
- Created `NamesDatabase` (Room database class)
- Created `UserProfileRepositoryImpl` with DataStore
- Created Hilt modules: `DatabaseModule`, `DataStoreModule`, `RepositoryModule`
- Fixed `getAllNames()` to call `ensureDataLoaded()` before emitting

### ✅ Day 5 — 2026-04-11
- Created `HomeViewModel` with StateFlow, search filtering, pagination (8 per page)
- Created `HomeScreen` with HorizontalPager (swipe), search bar, name cards
- Updated `MainActivity` to display `HomeScreen`
- Fixed JSON asset location: moved from `data/assets/` to `app/src/main/assets/`
- First successful run on physical device

### 🔜 Day 6
- Create `DetailScreen` — meditation view for a single Name
- Share functionality (share meditation text)
- Navigation between Home and Detail

### 🔜 Day 7
- Create `ProfileScreen` — birth date input
- Personal insight calculation based on birth date
- Create `ProfileViewModel`

### 🔜 Day 8
- Kabbalistic calendar engine — daily Name suggestion logic
- `CalendarScreen` — browse Names by date
- Daily notification setup

### 🔜 Day 9
- Multi-language support — load JSON by device locale
- Add English, Spanish, French, Italian, German translations
- Locale detection and fallback logic

### 🔜 Day 10
- UI polish — typography, colors, dark mode
- App icon and splash screen
- Accessibility improvements

### 🔜 Day 11
- Testing — unit tests for UseCases and ViewModels
- Integration tests for Room
- Bug fixes

### 🔜 Day 12
- Play Store preparation — signing, ProGuard, release build
- Store listing — screenshots, description, icon
- First internal release 🚀

---

*Built with intention. One day at a time.* 🌟
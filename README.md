# 🔯 72 Names — Kabbalistic Meditation App

Android app for Kabbalistic meditation on the 72 Names of God.
Built with Kotlin, Jetpack Compose, Clean Architecture, MVVM.

---

## 📱 Features

- Navigate all 72 Names with meditation texts (8 per page, swipe navigation)
- Detail view with full meditation, attributes, Torah verse and angel name
- Navigate between names (previous/next) directly from detail view
- Search by meaning or transliteration
- Share meditation texts with others
- Daily name card based on the Kabbalistic calendar (5-day angel periods)
- Birth date profile with personal sacred name (traditional angel calendar)
- Multi-language support (PT, EN, ES, FR, IT, DE) — content and UI fully translated
- Automatic locale detection with English fallback
- Traditional Hebrew font (Noto Serif Hebrew) for authentic letter display
- Custom teal color palette with light and dark mode support
- Custom app icon (Kabbalah Shin symbol)
- Splash screen with app branding
- Onboarding flow (3 pages, first launch only)
- Animated screen transitions (slide + fade)
- Privacy policy included
- 100% offline — no internet required, no ads, no tracking
- Release-ready AAB (signed, ProGuard enabled)

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
| Language | Kotlin 2.1.20 |
| UI | Jetpack Compose (BOM 2025.05.00) |
| Architecture | Clean Architecture + MVVM |
| DI | Hilt 2.58 |
| Local DB | Room 2.7.2 |
| Preferences | DataStore 1.1.7 |
| Navigation | Navigation Compose 2.9.0 |
| Splash | AndroidX SplashScreen 1.2.0 |
| Build | AGP 8.13.2 / Gradle 9.4.1 / KSP 2.1.20 |
| Min SDK | API 26 (Android 8.0) |
| Compile SDK | API 35 |

---

## 📁 Project structure

```
app/src/main/
├── assets/
│   ├── sacred_names_pt.json
│   ├── sacred_names_en.json
│   ├── sacred_names_es.json
│   ├── sacred_names_fr.json
│   ├── sacred_names_it.json
│   ├── sacred_names_de.json
│   └── privacy_policy.html
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
│   │   ├── usecase/
│   │   │   ├── GetAllNamesUseCase.kt
│   │   │   ├── GetDailyNameUseCase.kt
│   │   │   └── GetNameByNumberUseCase.kt
│   │   └── util/
│   │       └── AngelCalendar.kt
│   ├── ui/
│   │   ├── navigation/
│   │   │   └── AppNavigation.kt
│   │   ├── screen/
│   │   │   ├── DetailScreen.kt
│   │   │   ├── DetailViewModel.kt
│   │   │   ├── HomeScreen.kt
│   │   │   ├── HomeViewModel.kt
│   │   │   ├── OnboardingScreen.kt
│   │   │   ├── ProfileScreen.kt
│   │   │   └── ProfileViewModel.kt
│   │   └── theme/
│   │       ├── Color.kt
│   │       ├── Theme.kt
│   │       └── Type.kt
│   ├── MainActivity.kt
│   └── NomesApp.kt
├── res/
│   ├── drawable/
│   │   └── ic_foreground_kabbalah_shin.xml
│   ├── font/
│   │   ├── noto_serif_hebrew_regular.ttf
│   │   └── noto_serif_hebrew_bold.ttf
│   ├── mipmap-anydpi-v26/
│   │   ├── ic_launcher.xml
│   │   └── ic_launcher_round.xml
│   ├── values/
│   │   ├── strings.xml (PT — default)
│   │   └── splash.xml
│   ├── values-en/strings.xml
│   ├── values-es/strings.xml
│   ├── values-fr/strings.xml
│   ├── values-it/strings.xml
│   └── values-de/strings.xml
└── test/
    └── java/com/nomes72/app/
        ├── AngelCalendarTest.kt
        ├── GetAllNamesUseCaseTest.kt
        ├── GetNameByNumberUseCaseTest.kt
        └── SacredNameEntityTest.kt
```

---

## 🧪 Tests

25 unit tests covering:
- `AngelCalendar` — period boundaries, full year coverage, leap year, no gaps (14 tests)
- `GetNameByNumberUseCase` — valid/invalid numbers, edge cases (4 tests)
- `GetAllNamesUseCase` — full list, ordering, empty repository (3 tests)
- `SacredNameEntity` — domain conversion, roundtrip, separator handling (4 tests)

Run tests: `./gradlew test`

---

## 🚀 Build

Debug: `./gradlew assembleDebug`
Release: `./gradlew bundleRelease`

Release AAB: `app/build/outputs/bundle/release/app-release.aab` (3.8MB)

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

### ✅ Day 6 — 2026-04-11
- Created `DetailScreen` with full meditation view (hebrew letters, transliteration, meaning, angel, meditation text, Torah verse, attributes as chips)
- Created `DetailViewModel` with previous/next navigation between names
- Created `AppNavigation` with Navigation Compose (Home → Detail)
- Added share functionality (formatted meditation text via Android share sheet)
- Added Noto Serif Hebrew font for traditional hebrew letter styling
- Increased hebrew letter size to 96sp in detail view
- Fixed bottom navigation bar overlap with system navigation (windowInsetsPadding)

### ✅ Day 7 — 2026-04-12
- Created `ProfileScreen` with DatePicker for birth date input
- Created `ProfileViewModel` with personal sacred name calculation
- Implemented traditional Kabbalistic angel calendar (72 periods of ~5 days, starting March 21)
- Corrected calculation method: from digit sum to traditional calendar periods (aligned with Ian Mecler's "A Força")
- Added profile icon to HomeScreen header
- Navigation: Home → Profile → Detail (tap on personal name card)

### ✅ Day 8 — 2026-04-12
- Extracted `AngelCalendar` utility class to `domain/util` (shared between Profile and Daily name)
- Added daily name card to HomeScreen (shows current period's angel)
- Refactored `GetDailyNameUseCase` to use traditional Kabbalistic calendar
- Simplified `ProfileViewModel` to use shared `AngelCalendar`
- Refactored `SacredNameRepository.getNameOfDay()` to receive pre-calculated number

### ✅ Day 9 — 2026-04-12
- Added multi-language support: EN, ES, FR, IT, DE (5 new JSON files, 72 names each)
- Updated `SacredNameRepositoryImpl` with automatic device locale detection
- Fallback to English for unsupported languages
- All translations maintain consistent structure with PT original

### ✅ Day 10 — 2026-04-12
- Created `NomesTheme` with custom teal color palette (#147A8C) matching app icon
- Implemented light and dark mode (follows system setting)
- Custom app icon: Kabbalah Shin symbol on teal background
- Internationalized all UI strings: 6 string resource files (PT, EN, ES, FR, IT, DE)
- Replaced all hardcoded Portuguese text with `stringResource()` references
- Updated `DetailViewModel.getShareText()` to use localized strings

### ✅ Day 11 — 2026-04-12
- Created 25 unit tests across 4 test classes
- `AngelCalendarTest`: 14 tests (period boundaries, full year coverage, leap year, no gaps)
- `GetNameByNumberUseCaseTest`: 4 tests (valid/invalid numbers, exceptions)
- `GetAllNamesUseCaseTest`: 3 tests (full list, ordering, empty repository)
- `SacredNameEntityTest`: 4 tests (domain conversion, roundtrip, separator handling)
- All tests passing

### ✅ Day 12 — 2026-04-13
- Added splash screen with Shin icon on teal background (AndroidX SplashScreen)
- Created onboarding flow (3 pages with swipe, translated in 6 languages, shown only on first launch)
- Added animated screen transitions (slide + fade) on all navigation routes
- Created privacy policy (HTML, included in assets)
- Generated release signing key (RSA 2048-bit, 10000 days validity)
- Configured release build with signing and ProGuard
- Generated first release AAB (3.8MB)
- Prepared Play Store listing texts (EN + PT)
- Play Store publication: on standby

### ✅ Day 13 — 2026-05-26
- Updated all dependencies to latest compatible versions:
  Kotlin 2.1.20, KSP 2.1.20-1.0.31, Hilt 2.58, AGP 8.13.2, Gradle 9.4.1,
  Room 2.7.2, Compose BOM 2025.05.00, Navigation 2.9.0, Lifecycle 2.9.0,
  DataStore 1.1.7, Coroutines 1.10.1, SplashScreen 1.2.0
- Resolved Hilt/Kotlin metadata version incompatibility
- All 25 tests passing on updated stack
- New release AAB generated successfully
- Created Google Play Developer account (Maurizio Prizzi) — verification pending

---

*Built with intention. One day at a time.* 🌟
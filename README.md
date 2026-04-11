cat > README.md << 'EOF'
# 🔯 72 Names — Kabbalistic Meditation App

Android app for Kabbalistic meditation on the 72 Names of God.
Built with Kotlin, Jetpack Compose, Clean Architecture, MVVM.

---

## 📱 Features (roadmap)

- Navigate all 72 Names with meditation texts
- Daily name suggestion based on the Kabbalistic calendar
- Birth date profile with personal insights
- Share meditations with others
- Multi-language support (PT, EN, ES, FR, IT, DE)

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
| Language | Kotlin 2.1 |
| UI | Jetpack Compose |
| Architecture | Clean Architecture + MVVM |
| DI | Hilt |
| Local DB | Room |
| Preferences | DataStore |
| Navigation | Navigation Compose |
| Min SDK | API 26 (Android 8.0) |

---

## 📓 Production diary

### Day 1 — 2025-04-10
- Defined app concept: 72 Kabbalistic Names meditation app
- Chose tech stack: Kotlin native, Jetpack Compose, Clean Architecture
- Decided on pre-translated assets strategy for 6 languages (PT, EN, ES, FR, IT, DE)
- Created private GitHub repository
- Cloned repo locally on Ubuntu
- Created Android Studio project (Empty Activity, Kotlin DSL)
- Connected project to GitHub via SSH
- First commit: initial project setup

---

*Built with intention. One day at a time.* 🌟
EOF
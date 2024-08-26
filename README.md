# NoteApp
This cross-platform Note Taking CRUP app leverages Kotlin Multiplatform and [Jetpack Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/) to deliver a consistent user experience across Android and iOS devices. 
## Prerequisite

- [Android Studio](https://developer.android.com/studio) - For Android development and project management.
- [Xcode](https://apps.apple.com/us/app/xcode/id497799835)(Optional) - For iOS development and project management.
- [JDK](https://www.oracle.com/java/technologies/downloads/?er=221886) - Required for Android development.
- [Kotlin Multiplatform Plugin](https://kotlinlang.org/docs/multiplatform-plugin-releases.html?_gl=1*130bj1*_gcl_au*MTk1MDYwOTc4MS4xNzIxNjMzNjAy*_ga*MTM4NzQwMTA3NS4xNjk3NDg5MzQ5*_ga_9J976DJZ68*MTcyMzExNTUwNy43Ni4xLjE3MjMxMTU1OTYuNDQuMC4w#release-details)- In Android Studio, select Settings/Preferences | Plugins, search Marketplace for Kotlin Multiplatform, and then install it.

## Installation

### Android

- Open the project in Android Studio.
- Connect your physical device or open an emulator.
- Run the gradle command below from Android Studio terminal to build and install the android application on the connected android device/emulator.

```bash
./gradlew installDebug
```

### iOS

- Navigate to __Run -> Edit__ Configurations in the Android Studio main menu.
- Click the plus sign and select __iOS Application__.
- Give the configuration a descriptive name then locate and select the __app-ios.xworkspace__ file in the ___app-ios___ module.
- Select the desired iOS simulator from the execution target list.
### Screenshots

### Android

<p align="center">
  <img src="https://github.com/user-attachments/assets/c9703fac-363f-4ab3-8567-3b6a0a379f62" alt="screenshot-1" height="700" width="320">
  <img src="https://github.com/user-attachments/assets/7858503a-7f4f-43d3-b917-bb3509cb44bd" alt="screenshot-2" height="700" width="320">
</p>


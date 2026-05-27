# Keyboard Xbox Controller Mapper

An Android application that maps keyboard key presses to Xbox controller button inputs. Fully remappable buttons optimized for NBA 2K26 MyTeam.

## Features

- **Keyboard to Controller Mapping**: Simulate Xbox controller button presses using keyboard input
- **Fully Remappable Buttons**: Customize all 16 controller buttons to any keyboard key
  - Face Buttons: A, B, X, Y
  - Shoulder Buttons: LB, RB, LT, RT
  - D-Pad: Up, Down, Left, Right
  - Analog Sticks: Left Stick, Right Stick
  - Menu Buttons: Start, Back
- **NBA 2K26 MyTeam Optimized**: Pre-configured with optimal default key mappings for MyTeam gameplay
- **Easy Configuration**: User-friendly interface to remap buttons
- **Persistent Settings**: Your custom mappings are saved and persist across app restarts
- **Default Presets**: One-click reset to default mappings

## Default Key Mappings (NBA 2K26 MyTeam)

| Controller Button | Default Keyboard Key |
|-------------------|----------------------|
| A (Pass/Shoot) | SPACE |
| B (Shoot) | X |
| X (Pass) | Q |
| Y (Steal/Defend) | E |
| LB (Block) | W |
| RB (Sprint) | R |
| LT | C |
| RT | V |
| D-Pad Up | DPAD_UP |
| D-Pad Down | DPAD_DOWN |
| D-Pad Left | DPAD_LEFT |
| D-Pad Right | DPAD_RIGHT |
| Left Stick | BUTTON_THUMBL |
| Right Stick | BUTTON_THUMBR |
| Start | MENU |
| Back | BACK |

## Installation

1. Download the latest APK from the [Releases](../../releases) tab
2. Enable "Install from Unknown Sources" in your device settings
3. Install the APK file
4. Launch the app and toggle "Enable Controller Service" to start

## Usage

### Basic Usage
1. Open the app
2. Toggle the "Enable Controller Service" switch to ON
3. The service will start intercepting keyboard presses and mapping them to controller buttons
4. Launch your game (NBA 2K26 MyTeam) and use your keyboard

### Remapping Keys
1. Tap "Key Mappings" from the main screen
2. Click "Edit Mappings"
3. Click on any controller button to remap it
4. Press the keyboard key you want to map to that button
5. Click "Save" to apply changes

### Reset to Defaults
1. Go to "Key Mappings"
2. Click "Reset to Default"
3. This will restore all buttons to their original mappings

## System Requirements

- Android 8.0+ (API 26)
- Device with keyboard input capability

## Permissions

The app requires the following permissions:
- `INJECT_EVENTS` - To simulate controller button presses
- `BIND_ACCESSIBILITY_SERVICE` - To monitor and intercept keyboard input
- `BLUETOOTH` (optional) - For future Xbox controller compatibility

## Technical Details

- **Language**: Kotlin
- **Architecture**: Service-based with Accessibility Service integration
- **Storage**: SharedPreferences for persistent key mapping storage
- **Build System**: Gradle with Android Plugin

## Building from Source

### Requirements
- Android Studio 4.2+
- Android SDK 34
- Kotlin 1.9.0
- Gradle 7.4.2

### Build Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/Ismarkmyname/KeyboardXboxController.git
   cd KeyboardXboxController
   ```

2. Open in Android Studio or build via command line:
   ```bash
   ./gradlew build
   ```

3. Generate APK:
   ```bash
   ./gradlew assembleRelease
   ```

4. Find the APK at: `app/build/outputs/apk/release/app-release.apk`

## Troubleshooting

### Service won't start
- Ensure the app has the necessary permissions
- Check Android accessibility settings and enable the service
- Restart the device

### Keys not being detected
- Some keys may not be supported by your device
- Try remapping to a different key
- Check device keyboard settings

### App crashes on startup
- Clear app data in Settings → Apps → Keyboard Xbox Controller → Storage → Clear Data
- Reinstall the app

## Known Limitations

- Requires device keyboard input (external keyboard or soft keyboard with key event support)
- Some devices may have restricted key injection capabilities
- Accessibility service permission must be manually granted

## Contributing

Feel free to submit issues and enhancement requests!

## License

This project is provided as-is for educational and personal use.

## Disclaimer

This app is designed for personal use. Always check the game's terms of service before using controller mappers. NBA 2K26 is a trademark of 2K Games. This app is not affiliated with or endorsed by 2K Games.

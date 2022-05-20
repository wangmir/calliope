# Calliope

## Getting Started

### Directory structure

- di : dependency injection root
- feature : multiple features (business logic) as each directory.
  - bloc : bloc, events, state
  - screens: view which is related to this feature
- l10n: string resource with localization
- routes.dart: page routing table for this app
### String resource management

- check https://pub.dev/packages/intl
- check https://marketplace.visualstudio.com/items?itemName=localizely.flutter-intl
- u can easily extract string resource to .arb file using vscode action.
  - https://twitter.com/localizely/status/1255175275454881793

### Renaming project

#### Bundle renaming
- https://pub.dev/packages/rename
#### App name renaming
- https://pub.dev/packages/rename_app
- NOTE: still some of html, android manifest will not be changed, u need to manually change it.

### [bloc pattern](https://bloclibrary.dev/#/coreconcepts)

## Testing

### Integration_test

### Firebase setup


#### Firebase Emulation

- Firebase auth emulation test sequence.
  - firebase emulator를 시작한다.
  `firebase emulators:start --import="./integration_test/resources/emulator_dump"`
  - test를 진행한다.
- 새로운 emulation setting을 만들고 싶을 때
  - firebase emulator를 export option으로 시작한다.
  `firebase emulators:start --export-on-exit="./integration_test/resources/emulator_dump"`

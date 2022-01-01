# Development Overview

## Naming

- Calliope: Project name of this application. In Greek mythology, Calliope (/kəˈlaɪəpiː/ kə-LY-ə-pee; Ancient Greek: Καλλιόπη, romanized: Kalliópē, lit. 'beautiful-voiced') is the Muse who presides over eloquence and epic poetry; so called from the ecstatic harmony of her voice.([wikipedia](https://en.wikipedia.org/wiki/Calliope))
- DayLog: daily log contains VoiceLog, STTLog, TextLog and FeelingLog
- VoiceLog: Voice recorded data which is logged by user through DayLog
- STTLog: Text-transformed voice data which is tightly linked with VoiceLog.
- TextLog: Text data of daily log, it can be same text as STTLog but not must be, user can fix or, just newly create text log too.
- FeelingLog: Daily Feeling in 7 categories of feeling. It will be described using color or emoji, etc.

## Rough feature lists

- User can create daily diary using voice record.
- Voice will be tranformed into text using STT (Speech-to-Text).
- User can add representative feeling of daily log.
  - Happy, Sad, Joyful, Desirous, Hateful, Fear, Loving (seven feelings of confucianism)
- User can see calender view for voice log
- User can see list view of voice log
- User can add text to each voice log
- User can search voice log using keyword of STT transformed text or it's additional text
- User can see statistics of daily log
- User can navigate voice log using feeling categories
- User can set alarm setting to encourage their daily logging


## View

### Main Views

4 views will be changed using navigation bar or horizontal sliding.

#### DayLog List view

- Time-serised daylog list view
  - Month level divider
- Year selection button
- Preview of text
  - text log present ? TextLog : STTLog
- Play button for VoiceLog
- Auto-forward toggle (playing of voice log will be auto forwarded to next log)
- Direction toggle (auto-forward direction)
- Search button

#### DayLog Calendar view

- Calendar will be changed with vertical sliding.
- Calendar will show each DayLog and its feeling icon (color or emoji,,,) at corresponding day box.
- clicking empty day will create new daylog.

#### Setting View

- Alert setting
  - `< <  09:00 PM  > >`
    - when click time text
      - alarm on/off
      - alarm message
- App info.
- Google Drive Setting
- Data backup
- Password setting
  - fingerprint verification on/off

#### Statistics View

TBD

### Subviews

#### Tutorial View

TBD

#### DayLog View

- Emotion
- voice Recording / Play view
- STT button
  - how can we show text?
- Text view

#### Player Panel

- Play button / stop button
- DayLog preview
- PlayList button
  - expand to show play list

#### Floating DayLog create button

- Show daylog view for today

## Non-functional requirements

- No backend server
- Locale support
- Android compose UI
- Google Drive sync support
- Multiple diary notebook support
- On-time premium payment
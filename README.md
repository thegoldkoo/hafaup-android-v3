# HafaUp Android App (TWA)

괌장터 Android 앱 — Trusted Web Activity 로 https://hafaup.com 감쌈

## 🚀 빌드

GitHub Actions 가 자동으로 빌드합니다. Secrets 에 아래 값이 설정되어 있어야 함:

- `KEYSTORE_BASE64`: Base64 인코딩된 .keystore 파일
- `KEYSTORE_PASSWORD`: 키스토어 비밀번호
- `KEY_ALIAS`: 키 별칭 (`hafaup`)
- `KEY_PASSWORD`: 키 비밀번호

main 브랜치에 push 하면 자동 빌드. Actions 탭 → Artifacts 에서 AAB 다운로드.

## 📦 Version 2.0.0 변경사항

- versionCode 1 → 2
- App Shortcuts 추가 (글쓰기/채팅/장터/택배)
- Share Target 지원 (다른 앱 → 괌장터 공유)
- Deep Link (hafaup.com/app.html 링크 → 앱에서 열림)
- Themed Icon 지원 (Android 13+)
- 다크모드 대응
- Network Security 설정 추가

## 🛠 수동 빌드

```bash
./gradlew bundleRelease \
  -PKEYSTORE_FILE=hafaup-release.keystore \
  -PKEYSTORE_PASSWORD=YOUR_PASSWORD \
  -PKEY_ALIAS=hafaup \
  -PKEY_PASSWORD=YOUR_PASSWORD
```

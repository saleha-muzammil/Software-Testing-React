# Software Testing for React Application with Firebase Backend

This repository contains testing artifacts for a React-based project with a Firebase backend, which can be found at [Software Engineering Project](https://github.com/Kajamohan-Krishnadasan/Software-Engineering-Project). All the relevant testing files are included in the project's directory.

## Contents

- **Control Flow Graphs:** Visual representations of the control flow in the application are available in the `Control Flow Graphs` folder.
- **Black Box Testing - EC:** Documentation on Dry Black Box Testing and Equivalence Classes are located in the `Black Box testing- EC` folder.
- **Automated Testing:** For automated testing assets, refer to the `src`, `target`, and `pom.xml` files within the repository. These are also bundled in the `testing.zip` archive for convenience.

## Setup for Automated Testing

Automated testing was performed using Selenium WebDriver and ChromeDriver. To install and run these tests, follow the steps outlined below:

1. **Selenium Setup:**
   - Ensure Java and Maven are installed on your system.
   - Install Selenium WebDriver from [Selenium HQ](https://www.selenium.dev/downloads/).
   - Download the correct version of ChromeDriver based on your Chrome version from [ChromeDriver - WebDriver for Chrome](https://sites.google.com/chromium.org/driver/).

2. **Running Tests:**
   - Navigate to the root directory of the project.
   - Execute `mvn test` to run the automated tests.

## Firebase Configuration

To connect the project to your Firebase instance, you need to set up Firebase and configure the project with your credentials.

1. **Create a Firebase Web App:**
   - Visit the [Firebase Console](https://console.firebase.google.com/).
   - Add a new project and navigate to the 'Web app' configuration.
   - Set up Firestore as the database and Firebase Storage.

2. **Configure Firebase in the Project:**
   - Navigate to `Project/src/firebase/firebase.js`.
   - Replace the placeholders in the `firebaseConfig` object with your Firebase configuration keys.

```javascript
import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";
import { getStorage } from "firebase/storage";
import { getFirestore } from "firebase/firestore";

// Your web app's Firebase configuration
const firebaseConfig = {
  apiKey: "YOUR_API_KEY",
  authDomain: "YOUR_AUTH_DOMAIN",
  projectId: "YOUR_PROJECT_ID",
  storageBucket: "YOUR_STORAGE_BUCKET",
  messagingSenderId: "YOUR_MESSAGING_SENDER_ID",
  appId: "YOUR_APP_ID",
  measurementId: "YOUR_MEASUREMENT_ID"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const auth = getAuth(app);
const storage = getStorage(app);
const db = getFirestore(app);

export { db, auth, app, storage };
```
Manually populate the Firestore database with initial data entries since the system does not include a sign-up function.
Set up the document paths and other required configurations as per your project's data schema.
Make sure to keep your Firebase credentials confidential and do not expose them in public repositories.
    Adjust the Firebase rules according to the needs of your testing scenarios.

Happy testing!

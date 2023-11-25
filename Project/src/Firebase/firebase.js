// import * as firebase from "firebase/app"
import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";
import { getStorage } from "firebase/storage";
import { getFirestore } from "firebase/firestore";

// Your web app's Firebase configuration
const firebaseConfig = {

  apiKey: "AIzaSyB3W1QSoUA4czhze15zM3QJrWbfZnuxOEU",

  authDomain: "stproject-161c6.firebaseapp.com",

  projectId: "stproject-161c6",

  storageBucket: "stproject-161c6.appspot.com",

  messagingSenderId: "1054039637485",

  appId: "1:1054039637485:web:d1ca1fb8cffcc78b3a3be4",

  measurementId: "G-6FJRP200Q7"



};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const auth = getAuth(app);
const storage = getStorage(app);
const db = getFirestore(app);

// const uid = auth.currentUser?.uid;

export { db, auth, app, storage };

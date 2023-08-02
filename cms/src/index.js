// Import react (essential) and reactdom (for components on browsers) libraries
import React from "react";
import ReactDOM from "react-dom/client"
import App from "./App";
// Get a reference to the diw with ID root
const el = document.getElementById("root");
// Tell React to take control of that element
const root = ReactDOM.createRoot(el);
// Create a component in the file App.js, import in here

// Show the component on the screen
root.render(<App />);
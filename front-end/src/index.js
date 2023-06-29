import React from 'react';
import ReactDOM from 'react-dom/client';
import './style.css';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import './script.js';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);


reportWebVitals();

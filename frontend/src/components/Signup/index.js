import React, { useState } from 'react';
import axios from 'axios';
import { NavLink } from 'react-router-dom';
const Signup = () => {
  const apiUrl = process.env.REACT_APP_API_URL;

  const [formData, setFormData] = useState({
    userName: '',
    userFirstName: '',
    userLastName: '',
    userEmail:' ',
    userPassword: '',
  });

  const { userName, userFirstName, userLastName,userEmail, userPassword } = formData;

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // Make a POST request using Axios
    axios.post(`${apiUrl}/registerNewUser`, formData)
      .then((response) => {
        console.log('User registration successful:', response.data);
        // Reset form fields after successful registration
        setFormData({
          userName: '',
          userFirstName: '',
          userLastName: '',
          userEmail:' ',
          userPassword: '',
        });
      })
      .catch((error) => {
        console.error('Error during user registration:', error);
        // Handle error here if needed
      });
  };


  return (
    <div>
      <h2>Signup</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Username:</label>
          <input
            type="text"
            name="userName"
            value={userName}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>First Name:</label>
          <input
            type="text"
            name="userFirstName"
            value={userFirstName}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Last Name:</label>
          <input
            type="text"
            name="userLastName"
            value={userLastName}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Email:</label>
          <input
            type="text"
            name="userEmail"
            value={userEmail}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Password:</label>
          <input
            type="password"
            name="userPassword"
            value={userPassword}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit">Signup</button>
        <p>Already have an account? <NavLink to="/signin">Sign in</NavLink></p>
      </form>
    </div>
  );
};

export default Signup;

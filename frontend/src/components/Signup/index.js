import React, { useState } from 'react';
import axios from 'axios';
import { NavLink } from 'react-router-dom';
import './style.css'; // Đảm bảo tệp này chứa CSS cần thiết

const Signup = () => {
  const apiUrl = process.env.REACT_APP_API_URL;

  const [formData, setFormData] = useState({
    userName: '',
    userFirstName: '',
    userLastName: '',
    userEmail: '',
    userPassword: '',
  });

  const { userName, userFirstName, userLastName, userEmail, userPassword } = formData;

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
        setFormData({
          userName: '',
          userFirstName: '',
          userLastName: '',
          userEmail: '',
          userPassword: '',
        });
      })
      .catch((error) => {
        console.error('Error during user registration:', error);
      });
  };

  return (
    <div className="container">
      <div className="signup-form">
        <h2>Đăng ký</h2>
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label>Tên đăng nhập:</label>
            <input
              type="text"
              name="userName"
              value={userName}
              onChange={handleChange}
              required
            />
          </div>
          <div className="form-group">
            <label>Họ:</label>
            <input
              type="text"
              name="userFirstName"
              value={userFirstName}
              onChange={handleChange}
              required
            />
          </div>
          <div className="form-group">
            <label>Tên:</label>
            <input
              type="text"
              name="userLastName"
              value={userLastName}
              onChange={handleChange}
              required
            />
          </div>
          <div className="form-group">
            <label>Email:</label>
            <input
              type="email"
              name="userEmail"
              value={userEmail}
              onChange={handleChange}
              required
            />
          </div>
          <div className="form-group">
            <label>Mật khẩu:</label>
            <input
              type="password"
              name="userPassword"
              value={userPassword}
              onChange={handleChange}
              required
            />
          </div>
          <button type="submit">Đăng ký</button>
          <p>Đã có tài khoản? <NavLink to="/signin">Đăng nhập</NavLink></p>
        </form>
      </div>
    </div>
  );
};

export default Signup;

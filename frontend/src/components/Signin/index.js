import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate, NavLink } from 'react-router-dom';
import './style.css'; // Đảm bảo tệp này chứa CSS cần thiết

const Signin = () => {
  const apiUrl = process.env.REACT_APP_API_URL;
  const navigate = useNavigate();
  
  const [formData, setFormData] = useState({
    userName: '',
    userPassword: '',
  });

  const [loginStatus, setLoginStatus] = useState('');
  
  const { userName, userPassword } = formData;

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post(`${apiUrl}/authenticate`, formData);
      const { user, jwtToken } = response.data;
      handleLoginSuccess(jwtToken);

      if (user.role.some((role) => role.roleName === 'Admin')) {
        navigate('/admin-dashboard');
      } else {
        navigate('/');
      }

      setFormData({
        userName: '',
        userPassword: '',
      });
    } catch (error) {
      handleLoginFailure('Đăng nhập KHÔNG thành công. Bạn vui lòng thử lại hoặc đăng nhập bằng cách khác nhé!');
    }
  };

  const handleLoginSuccess = (jwtToken) => {
    localStorage.setItem('jwtToken', jwtToken);
    navigate('/');
  };

  const handleLoginFailure = (errorMessage) => {
    setLoginStatus(errorMessage);
  };

  return (
    <div className="container">
      <div className="login-form">
        <h2>Đăng nhập</h2>
        <form onSubmit={handleSubmit}>
          <input
            type="text"
            name="userName"
            value={userName}
            onChange={handleChange}
            placeholder="Tên đăng nhập"
            required
          />
          <input
            type="password"
            name="userPassword"
            value={userPassword}
            onChange={handleChange}
            placeholder="Mật khẩu"
            required
          />
          <button type="submit">Đăng nhập</button>
          <NavLink to="/forgotpassword">Quên mật khẩu?</NavLink>
          {loginStatus && <p className="error-message">{loginStatus}</p>}
        </form>
      </div>
    </div>
  );
};

export default Signin;

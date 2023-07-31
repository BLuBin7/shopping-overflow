import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate,NavLink } from 'react-router-dom';
import { toast, ToastContainer } from 'react-toastify';

const Signin = () => {
  const apiUrl = process.env.REACT_APP_API_URL;

    // qua trang home
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    userName: '',
    userPassword: '',
  });

  const { userName, userPassword } = formData;

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

//   const handleSubmit = async (e) => {
//     e.preventDefault();

//     try {
//       const response = await axios.post('http://localhost:8080/authenticate', formData, {
//         headers: {
//           'Authorization': 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJCTHVCaW4iLCJleHAiOjE2OTA1Nzc0NjcsImlhdCI6MTY5MDU1OTQ2N30.3N091d43m5PVZTCJxI00LhRp6j3aBZcKLtgy3GrwXobJ5YgJPQR6Ix3B0wzJUABcdn-TW-rCjX_lOeLWOEGohA', // Replace YOUR_JWT_TOKEN_HERE with the actual JWT token
//         },
//       });
//       console.log('Logged in:', response.data);
//       // Reset form fields after successful login
//       setFormData({
//         username: '',
//         password: '',
//       });
//     } catch (error) {
//       console.error('Error during login:', error);
//       // Handle error here if needed
//     }
//   };

// const handleSubmit = async (e) => {
//     e.preventDefault();

//     try {
//       const response = await axios.post('http://localhost:8080/authenticate', formData);
//       const { jwtToken } = response.data; // Extract the jwtToken from the response
//       console.log('Logged in:', jwtToken);

//       // Store the JWT token in the browser's local storage or session storage for future use
//       // Here, we use local storage, but you can use session storage if needed
//       localStorage.setItem('jwtToken', jwtToken);

//       // Reset form fields after successful login
//       setFormData({
//         username: '',
//         password: '',
//       });
      
//       // qua home
//        navigate('/');
//     } catch (error) {
//       console.error('Error during login:', error);
//       // Handle error here if needed
//     }
//   };

// chưa role
// const handleLoginSuccess = (jwtToken) => {
//     // Store the JWT token in the browser's local storage or session storage for future use
//     localStorage.setItem('jwtToken', jwtToken);

//     // Reset form fields after successful login
//     setFormData({
//       username: '',
//       password: '',
//     });

//     // Redirect to the / page
//     navigate('/');
//   };
//   // trang thai dang nhap
//   const [loginStatus, setLoginStatus] = useState('');
//   const handleLoginFailure = (errorMessage) => {
//     // Show notification for login failure
//     toast.error(errorMessage, {
//       position: toast.POSITION.TOP_CENTER,
//     });

//     // Update login status state with the error message to display it in the UI
//     setLoginStatus(errorMessage);
//   };

//   const handleSubmit = async (e) => {
//     e.preventDefault();

//     try {
//       const response = await axios.post('http://localhost:8080/authenticate', formData);
//       const { jwtToken } = response.data; // Extract the jwtToken from the response
//       console.log('Logged in:', jwtToken);
      
//       // Handle successful login
//       handleLoginSuccess(jwtToken);
//     } catch (error) {
//       console.error('Error during login:', error);

//       // Handle login failure and show notification
//       handleLoginFailure('Invalid credentials. Please try again.');
//     }
//   };


// đã role 
 const handleLoginSuccess = (jwtToken) => {
      // Store the JWT token in the browser's local storage or session storage for future use
      localStorage.setItem('jwtToken', jwtToken);
  
      // Reset form fields after successful login
      setFormData({
        username: '',
        password: '',
      });
  
      // Redirect to the / page
      navigate('/');
    };
    // trang thai dang nhap
    const [loginStatus, setLoginStatus] = useState('');
    const handleLoginFailure = (errorMessage) => {
      // Show notification for login failure
      toast.error(errorMessage, {
        position: toast.POSITION.TOP_CENTER,
      });
  
      // Update login status state with the error message to display it in the UI
      setLoginStatus(errorMessage);
    };
  
    const handleSubmit = async (e) => {
      e.preventDefault();
  
      try {
        const response = await axios.post(`${apiUrl}/authenticate`, formData);
        const { user,jwtToken } = response.data; // Extract the jwtToken from the response
        console.log('Logged in:', jwtToken);
        // Handle successful login
        handleLoginSuccess(jwtToken);
        if (user.role.some((role) => role.roleName === 'Admin')) {
          // Redirect to the admin dashboard or any other admin-specific page
          window.location.href = '/admin-dashboard';
        } else {
          // Redirect to the user dashboard or any other user-specific page
          window.location.href = '/';
        }
        // Reset form fields after successful login
        setFormData({
          userName: '',
          userPassword: '',
        });
      } catch (error) {
        console.error('Error during login:', error);
  
        // Handle login failure and show notification
        handleLoginFailure('Invalid credentials. Please try again.');
      }
    };

  return (
    <div>
      <h2>Sign In</h2>
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
          <label>Password:</label>
          <input
            type="userPassword"
            name="userPassword"
            value={userPassword}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit" >Sign In</button>
        <NavLink to="/forgotpassword">Forgot Password ?</NavLink>        
        {loginStatus && <p style={{ color: 'red' }}>{loginStatus}</p>}
      </form>
    </div>
  );
};

export default Signin;

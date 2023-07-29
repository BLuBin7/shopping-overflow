import React from 'react';
import PropTypes from 'prop-types';
import { useNavigate,NavLink } from 'react-router-dom';
import { useState, useEffect } from 'react';


const Dashboard = () => {
    const navigate = useNavigate();
    const [userName, setUserName] = useState('');
  
    useEffect(() => {
      // Check if the user is authenticated (e.g., if a JWT token is present)
      const jwtToken = localStorage.getItem('jwtToken');
      if (!jwtToken) {
        // Redirect to the /signin page if not authenticated
        navigate('/signin');
      } else {
        // Extract userName from JWT token (assuming userName is stored in the payload)
        try {
          const payload = jwtToken.split('.')[1];
          const decodedPayload = atob(payload);
          const { sub: userName } = JSON.parse(decodedPayload);
          setUserName(userName);
        } catch (error) {
          console.error('Error decoding JWT token:', error);
          // Handle error here if needed
        }
      }
    }, [navigate]);
  
    // Other content and UI elements for the Home component
    return (
      <div>
        <h2>Welcome, {userName}!</h2>
        <NavLink to ="/addproduct">Addproduct</NavLink>
        <NavLink to ="/allproducts">Allproducts</NavLink>
      </div>
    );
  };

export default Dashboard;
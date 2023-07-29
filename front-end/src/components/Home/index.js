import React from 'react';
import { useState, useEffect } from 'react';
import {useNavigate,Routes,Route,NavLink} from 'react-router-dom';
import AllProducts from '../admin/allProducts';
import ProductDetails from '../admin/ProductDetails';
import PropTypes from 'prop-types';
const Home = () => {
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
      {/* Other content */}
      <h1>My E-commerce Store</h1>
      <nav>
        {/* Use NavLink for active styling */}
        <NavLink to="/allproducts" activeClassName="active">
          All Products
        </NavLink>
      </nav>
      <Routes>
        <Route path="/allproducts" element={<AllProducts />} />
        <Route path="/products/:productId" element={<ProductDetails />} />
      </Routes>
      </div>
    );
  };
export default Home;
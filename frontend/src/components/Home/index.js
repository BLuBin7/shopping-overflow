import React from 'react';
import { useState, useEffect } from 'react';
import {useNavigate,Routes,Route,NavLink} from 'react-router-dom';
import AllProducts from '../admin/allProducts';
import Slider from '../Slider';
import Category from '../Category';
const Home = () => {
    const navigate = useNavigate();
    const [userName, setUserName] = useState('');
    const [searchKey, setSearchKey] = useState('');

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
      <h1 className = "display-4">My E-commerce Store</h1>
      {/* Other content */}
      <p className = "lead text-start">Welcome, {userName}!</p>
      <h1>My E-commerce Store</h1>
      <Slider />
      <br />
      <Category/>
      <AllProducts />
      {/* <nav>
        <NavLink to="/allproducts" >
          All Products
        </NavLink>
      </nav>  */}
      </div>
    );
  };
export default Home;
import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import AllProducts from "../admin/allProducts";
import Slider from "../Slider";
import Category from "../Category";
import "./style.css"; // Import custom CSS for Home

const Home = () => {
  const navigate = useNavigate();
  const [userName, setUserName] = useState("");

  useEffect(() => {
    const jwtToken = localStorage.getItem("jwtToken");
    if (!jwtToken) {
      navigate("/signin");
    } else {
      try {
        const payload = jwtToken.split(".")[1];
        const decodedPayload = atob(payload);
        const { sub: userName } = JSON.parse(decodedPayload);
        setUserName(userName);
      } catch (error) {
        console.error("Error decoding JWT token:", error);
      }
    }
  }, [navigate]);

  return (
    <div className="home-container">
      <header >
        <h1 className="display-4">My E-commerce Store</h1>
        <p className="lead">Welcome, {userName}!</p>
      </header>
      <main>
        <section className="slider-section">
          <Slider />
        </section>
        <section className="category-section">
          <Category />
        </section>
        <section className="products-section">
          <AllProducts />
        </section>
      </main>
    </div>
  );
};

export default Home;

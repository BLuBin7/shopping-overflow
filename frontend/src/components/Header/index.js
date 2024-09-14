import React, { useState } from "react";
import "./style.scss";

import { Link, NavLink, Router, Route, useNavigate } from "react-router-dom";


import Login from "../Signup";
import AllProducts from "../admin/allProducts";
const Header = () => {
  const [searchKey, setSearchKey] = useState("");
  const navigate = useNavigate();
  const [products, setProducts] = useState([]);

  const handleSearchSubmit = (e) => {
    e.preventDefault();
    // navigate(`/allproducts?searchKey=${searchKey}`);
    navigate(`/productresponse?searchKey=${searchKey}`);
  };

  return (
    <div className="header">
  {/* <div className="logo">
    <img src="/path/to/logo.png" alt="Logo" />
  </div> */}
  <nav className="nav-left">
    <ul>
      <li><NavLink className="nav-link" to="/">Home</NavLink></li>
      <li><NavLink className="nav-link" to="/about">About</NavLink></li>
      <li><NavLink className="nav-link" to="/contact">Contact</NavLink></li>
    </ul>
  </nav>
  
  <div className="search-bar">
    <form onSubmit={handleSearchSubmit}>
      <input
        type="text"
        placeholder="Search products..."
        value={searchKey}
        onChange={(e) => setSearchKey(e.target.value)}
      />
      <button className="btn-search" type="submit">Search</button>
    </form>
  </div>
  <nav className="nav-right">
    <ul>
      <li><NavLink className="btn" to="/signup">Sign up</NavLink></li>
      <li><NavLink className="btn" to="/signin">Sign in</NavLink></li>
      {/* <li><NavLink className="btn" to="/cart">Cart</NavLink></li> */}
    </ul>
  </nav>
</div>
  );
};

export default Header;

import React, { useState } from "react";
import "./style.scss";
import { Link, NavLink, Router, Route, useNavigate  } from "react-router-dom";
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
    <header className="header">
      <h1>My Website</h1>
      <nav className="nav">
        <ul>
          <li>
            <a href="/">Home</a>
          </li>
          <li>
            <a href="/about">About</a>
          </li>
          <li>
            <a href="/contact">Contact</a>
          </li>
          <li>
            <a href="/contact">Product</a>
          </li>
          <li>
            <NavLink to="/signup">Sign up</NavLink>
          </li>
          <li>
            <a href="/cart">Cart</a>
          </li>
          <li>
            <form onSubmit={handleSearchSubmit}>
              <input
                type="text"
                name="searchKey"
                value={searchKey}
                onChange={(e) => setSearchKey(e.target.value)}
                placeholder="Search products..."
              />
              <button type="submit">Search</button>
            </form>
          </li>
        </ul>
      </nav>
    </header>
  );
};

export default Header;

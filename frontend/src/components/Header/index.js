import React from 'react';
import './style.scss';
import { Link, NavLink, Router,Route } from 'react-router-dom';
import Login from '../Signup';

const Header = () => {
  return (
    <header className="header">
      <h1>My Website</h1>
      <nav className="nav">
        <ul>
          <li><a href="/">Home</a></li>
          <li><a href="/about">About</a></li>
          <li><a href="/contact">Contact</a></li>
          <li><a href="/contact">Product</a></li>
          <li><NavLink to="/signup">Sign up</NavLink></li>
          <li ><a href="/cart">Cart</a></li>
        </ul>

  
      </nav>
    </header>
  );
};

export default Header;

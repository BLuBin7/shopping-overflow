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
    <div style={{ backgroundColor: "orange" }}>
      <h1>My Website</h1>
      <div className="row">
        <div className="col-md-6">
          <nav className="nav">
            <ul className="nav ">
              <li className="nav-item">
                <a className="nav-link active" aria-current="page" href="/">
                  Home
                </a>
              </li>
              <li className="nav-item">
                <a
                  className="nav-link active"
                  aria-current="page"
                  href="/about"
                >
                  About
                </a>
              </li>
              <li className="nav-item">
                <a
                  className="nav-link active"
                  aria-current="page"
                  href="/contact"
                >
                  Contact
                </a>
              </li>
            </ul>
          </nav>
        </div>

        <div className="col-md-6">
          <nav className="nav justify-content-end">
            <ul className="nav ">
              <li className="nav-item">
                <NavLink to="/signup" className="ms-auto">
                  Sign up
                </NavLink>
              </li>
              <li className="nav-item">
                <a href="/cart" className="ms-2">
                  Cart
                </a>
              </li>
            </ul>
          </nav>
        </div>
      </div>

      <nav className="nav justify-content-center">
        <ul>
          <form className="d-flex" onSubmit={handleSearchSubmit}>
            <input
              className="form-control me-2"
              type="text"
              aria-label="Search"
              name="searchKey"
              value={searchKey}
              onChange={(e) => setSearchKey(e.target.value)}
              placeholder="Search products..."
              style={{ width: "700px" }}
            />
            <button className="btn btn-outline-success" type="submit">
              Search
            </button>
          </form>
        </ul>

        {/* <form onSubmit={handleSearchSubmit}>
              <input
                type="text"
                name="searchKey"
                value={searchKey}
                onChange={(e) => setSearchKey(e.target.value)}
                placeholder="Search products..."
              />
              <button type="submit">Search</button>
            </form> */}
      </nav>
    </div>
  );
};

export default Header;

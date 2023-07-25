import React from "react";
import PropTypes from "prop-types";
import"./style.css";
import { useState, useContext } from "react";
import { useParams, useNavigate } from "react-router-dom";

const Header = () => {
  const param = window.location;
  console.log("Param: ", param);
  const [item, SetItemA] = useState();
  const [stateLogin, setStateLogin] = useState(
    localStorage.getItem["app"] ? true : false
  );

  return (
    <header>
      <div className="container">
        <nav className="navbar navbar-expand-lg">
          <a className="navbar-brand" href="http://localhost:3000/">
            <span className="web">ECO</span>BOOK
          </a>
          <button
            className="navbar-toggler"
            type="button"
            data-toggle="collapse"
            data-tgitarget="#navbarSupportedContent"
            aria-controls="navbarSupportedContent"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>

          <div className="collapse navbar-collapse" id="navbarSupportedContent">
            <ul className="navbar-nav ml-auto">
              <li
                className={`nav-item ${param.pathname == "/" ? "active" : ""}`}
              >
                <a className="nav-link" href="http://localhost:3000/">
                  Home{" "}
                </a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="\#shop">
                Shop{" "}
                </a>
              </li>
              <li
                className={`nav-item ${
                  param.pathname == "/Women" ? "active" : " "
                }`}
              >
                <a className="nav-link" href="http://localhost:3000/Women#">
                  Women
                </a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="\Jewelry#">
                Jewelry{" "}
                </a>
              </li>

              <li className="nav-item">
                <img
                  src="https://cdn-icons-png.flaticon.com/512/149/149071.png"
                  className="logo"
                />
              </li>
              <li>
                <div class="container_a">
                  <div class="progress2 progress-moved">
                    <div class="progress-bar2"></div>
                  </div>
                </div>
              </li>
            </ul>
          </div>
        </nav>
      </div>
    </header>
  );
};
export default Header;

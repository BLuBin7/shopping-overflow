'use client'

import React, {useState} from 'react';
import Link from 'next/link';

import 'remixicon/fonts/remixicon.css';
import HeaderNav from "@/component/header/HeaderNav";
import HeaderMain from "@/component/header/HeaderMain";


const Header: React.FC = () => {
  const [currencyOpen, setCurrencyOpen] = useState(false);
  const [languageOpen, setLanguageOpen] = useState(false);

  const currencies = ['USD', 'EURO', 'GBP', 'IDR'];
  const languages = ['English', 'German', 'Spanish', 'Bahasa'];

  return (
      <header>
        {/* Top header */}
        <div className="header-top mobile-hide">
          <div className="container">
            <div className="wrapper flexitem">
              <div className="left"></div>
              <ul className="flexitem main-links">
                <li><Link href="/blog">Blog</Link></li>
                <li><Link href="/featured-products">Featured Products</Link></li>
                <li><Link href="/wishlist">Wishlist</Link></li>
              </ul>
              <div className="right">
                <ul className="flexitem main-links">
                  <li><Link href="/sign-up">Sign Up</Link></li>
                  <li><Link href="/sign-in">Sign In</Link></li>
                  <li><Link href="/account">My Account</Link></li>
                  <li><Link href="/order-tracking">Order Tracking</Link></li>

                  {/* Currency Dropdown */}
                  <li
                      className="dropdown"
                      onMouseEnter={() => setCurrencyOpen(true)}
                      onMouseLeave={() => setCurrencyOpen(false)}
                  >
                    <Link href="#">
                      USD <span className="icon-small"><i className="ri-arrow-down-s-line"></i></span>
                    </Link>
                    {currencyOpen && (
                        <ul>
                          {currencies.map((currency, index) => (
                              <li
                                  key={currency}
                                  className={index === 0 ? 'current' : ''}
                              >
                                <Link href="#">{currency}</Link>
                              </li>
                          ))}
                        </ul>
                    )}
                  </li>

                  {/* Language Dropdown */}
                  <li
                      className="dropdown"
                      onMouseEnter={() => setLanguageOpen(true)}
                      onMouseLeave={() => setLanguageOpen(false)}
                  >
                    <Link href="#">
                      English <span className="icon-small"><i className="ri-arrow-down-s-line"></i></span>
                    </Link>
                    {languageOpen && (
                        <ul>
                          {languages.map((language, index) => (
                              <li
                                  key={language}
                                  className={index === 0 ? 'current' : ''}
                              >
                                <Link href="#">{language}</Link>
                              </li>
                          ))}
                        </ul>
                    )}
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>

        {/* Main header */}
        <div className="header">
          <div className="logo">
            <Link href="/public">SAFORU</Link>
          </div>
          <div className="header-nav mobile-hide">
            <div className="container">
              <div className="wrapper flexitem">
                <div className="search-box">
                  <form className="search">
                    <span className="icon-large"><i className="ri-search-line"></i></span>
                    <input
                        type="search"
                        placeholder="Search for products."
                    />
                    <button type="submit">Search</button>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>

        {/* HEADER NAP */}
        <HeaderNav />

        {/* HEADER MAIN */}
        <HeaderMain/>
      </header>
  );
};

export default Header;
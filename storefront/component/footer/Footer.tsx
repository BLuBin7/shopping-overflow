'use client'

import React from 'react';

import 'remixicon/fonts/remixicon.css';

const Footer: React.FC = () => {
    return (
        <footer>

            <div className="newsletter">
                <div className="container">
                    <div className="wrapper">
                        <div className="box">
                            <div className="content">
                                <h3>Join Our Newsletter</h3>
                                <p>Get E-mail updates about our latest shop and <strong>special offers</strong></p>
                            </div>
                            <form action="" className="search-newsletter">
                                <span className="icon-large"><i className="ri-mail-line"></i></span>
                                <input type="mail" placeholder="Your email address" required/>
                                <button type="submit">Sign Up</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div className="widgets">
                <div className="container">
                    <div className="wrapper">
                        <div className="flexwrap">
                            <div className="row">
                                <div className="item mini-links">
                                    <h4>Help & Contact</h4>
                                    <ul className="flexcol">
                                        <li><a href="#">Your Account</a></li>
                                        <li><a href="#">Your Orders</a></li>
                                        <li><a href="#">Shipping Rate</a></li>
                                        <li><a href="#">Returns</a></li>
                                        <li><a href="#">Assistant</a></li>
                                        <li><a href="#">Help</a></li>
                                        <li><a href="#">Contact us</a></li>
                                    </ul>
                                </div>
                            </div>
                            <div className="row">
                                <div className="item mini-links">
                                    <h4>Product Categories</h4>
                                    <ul className="flexcol">
                                        <li><a href="#">Beauty</a></li>
                                        <li><a href="#">Electronic</a></li>
                                        <li><a href="page-category.html">Women's Fashion</a></li>
                                        <li><a href="men-category.html">Men's Fashion</a></li>
                                        <li><a href="#">Girl's Fashion</a></li>
                                        <li><a href="#">Boy's Fashion</a></li>
                                        <li><a href="#">Health & Household</a></li>
                                        <li><a href="#">Home & Kitchen</a></li>
                                        <li><a href="#">Pet Supplies</a></li>
                                        <li><a href="page-shoes.html">Sports</a></li>
                                    </ul>
                                </div>
                            </div>
                            <div className="row">
                                <div className="item mini-links">
                                    <h4>Payment Info</h4>
                                    <ul className="flexcol">
                                        <li><a href="#">Bussiness Card</a></li>
                                        <li><a href="#">Shop with Points</a></li>
                                        <li><a href="#">Reload Your Balance</a></li>
                                        <li><a href="#">Paypal</a></li>
                                    </ul>
                                </div>
                            </div>
                            <div className="row">
                                <div className="item mini-links">
                                    <h4>Abous us</h4>
                                    <ul className="flexcol">
                                        <li><a href="#">Company Info</a></li>
                                        <li><a href="#">News</a></li>
                                        <li><a href="#">Investors</a></li>
                                        <li><a href="#">Careers</a></li>
                                        <li><a href="#">Policies</a></li>
                                        <li><a href="#">Customer reviews</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            {/*// <!-- widgets -->*/}

            <div className="footer-info">
                <div className="container">
                    <div className="wrapper">
                        <div className="flexcol">
                            <div className="logo">
                                <a href=""><span className="span circle"></span>.Store</a>
                            </div>
                            <div className="socials">
                                <ul className="flexitem">
                                    <li><a href="#"><i className="ri-twitter-line"></i></a></li>
                                    <li><a href="#"><i className="ri-facebook-line"></i></a></li>
                                    <li><a href="#"><i className="ri-instagram-line"></i></a></li>
                                    <li><a href="#"><i className="ri-linkedin-line"></i></a></li>
                                    <li><a href="#"><i className="ri-youtube-line"></i></a></li>
                                </ul>
                            </div>
                        </div>
                        <p className="mini-text">Copyright 2023 © .Store. All right reserved.</p>
                    </div>
                </div>
            </div>
            {/*// <!-- footer info -->*/}

            <div className="menu-bottom desktop-hide">
                <div className="container">
                    <div className="wrapper">
                        <nav>
                            <ul className="flexitem">
                                <li>
                                    <a href="#">
                                        <i className="ri-bar-chart-line"></i>
                                        <span>Trending</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <i className="ri-user-6-line"></i>
                                        <span>Account</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <i className="ri-heart-line"></i>
                                        <span>Wishlish</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="#0" className="t-search">
                                        <i className="ri-search-line"></i>
                                        <span>Search</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="#0">
                                        <i className="ri-shopping-cart-line"></i>
                                        <span>Cart</span>
                                        <div className="fly-item">
                                            <span className="item-number">0</span>
                                        </div>
                                    </a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
            {/*// <!-- menu bottom -->*/}

            <div className="search-bottom desktop-hide">
                <div className="container">
                    <div className="wrapper">
                        <form action="" className="search">
                            <a href="#" className="t-close search-close flexcenter"><i
                                className="ri-close-line"></i></a>
                            <span className="icon-large"><i className="ri-search-line"></i></span>
                            <input type="search" placeholder="Search to products" required/>
                            <button type="submit">Search</button>
                        </form>
                    </div>
                </div>
            </div>

            <div className="overlay"></div>

        </footer>
    )
}

export default Footer;
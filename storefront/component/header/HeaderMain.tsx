'use client'

import React, { useState } from 'react';
import Link from 'next/link';

const HeaderMain: React.FC = () => {
    const [isMenuOpen, setIsMenuOpen] = useState(false);

    const toggleMenu = () => {
        setIsMenuOpen(!isMenuOpen);
    };

    return (
        <div className="header-main desktop-hide-mobile-hide">
            <div className="container">
                <div className="wrapper flexitem">
                    <div className="left">
                        <div className="dpt-cat">

                            <div className="dpt-head">
                                <div className="main-text">All Departments</div>
                                <div className="mini-text mobile-hide">Total 1059 Products</div>
                                <button
                                    onClick={toggleMenu}
                                    className="dpt-trigger mobile-hide"
                                >
                                    <i className={`ri-menu-3-line ri-xl ${isMenuOpen ? 'hidden' : ''}`}></i>
                                    <i className={`ri-close-line ri-xl ${!isMenuOpen ? 'hidden' : ''}`}></i>
                                </button>
                            </div>

                            {/* MENU */}
                            {isMenuOpen && (
                                <div className="dpt-menu">
                                    <ul className="second-links">
                                        <li className="has-child beauty">
                                            <a href="#">
                                                <div className="icon-large"><i className="ri-bear-smile-line"></i></div>
                                                Beauty
                                                <div className="icon-small"><i className="ri-arrow-right-s-line"></i>
                                                </div>
                                            </a>
                                            <ul>
                                                <li><a href="#">Makeup</a></li>
                                                <li><a href="#">Skin Care</a></li>
                                                <li><a href="#">Hair Care</a></li>
                                                <li><a href="#">Fragrance</a></li>
                                                <li><a href="#">Foot & Hand Care</a></li>
                                                <li><a href="#">Tool & Accessories</a></li>
                                                <li><a href="#">Shave & Hair Removal</a></li>
                                                <li><a href="#">Personal Care</a></li>
                                            </ul>
                                        </li>
                                        <li className="has-child electronic">
                                            <a href="#">
                                                <div className="icon-large"><i
                                                    className="ri-bluetooth-connect-line"></i></div>
                                                Electronic
                                                <div className="icon-small"><i className="ri-arrow-right-s-line"></i>
                                                </div>
                                            </a>
                                            <ul>
                                                <li><a href="#">Camera</a></li>
                                                <li><a href="#">Cell Phone</a></li>
                                                <li><a href="#">Computers</a></li>
                                                <li><a href="#">GPS & Navigation</a></li>
                                                <li><a href="#">Headphone</a></li>
                                                <li><a href="#">Home Audio</a></li>
                                                <li><a href="#">Televison</a></li>
                                                <li><a href="#">Video Projectors</a></li>
                                                <li><a href="#">Wearable Technology</a></li>
                                            </ul>
                                        </li>

                                        <li className="has-child fashion">
                                            <a href="#">
                                                <div className="icon-large"><i className="ri-t-shirt-air-line"></i>
                                                </div>
                                                Women's fashion
                                                <div className="icon-small"><i className="ri-arrow-right-s-line"></i>
                                                </div>
                                            </a>
                                            <ul>
                                                <li><a href="#">Clothing</a></li>
                                                <li><a href="#">Shoes</a></li>
                                                <li><a href="#">Jewelry</a></li>
                                                <li><a href="#">Watches</a></li>
                                                <li><a href="#">Handbags</a></li>
                                                <li><a href="#">Accessories</a></li>
                                            </ul>
                                        </li>

                                        <li>
                                            <a href="#">
                                                <div className="icon-large"><i className="ri-shirt-line"></i></div>
                                                Men's fashion
                                            </a>
                                        </li>

                                        <li>
                                            <a href="#">
                                                <div className="icon-large"><i className="ri-user-5-line"></i></div>
                                                Girl's fashion
                                            </a>
                                        </li>

                                        <li>
                                            <a href="#">
                                                <div className="icon-large"><i className="ri-user-6-line"></i></div>
                                                Boy's fashion
                                            </a>
                                        </li>

                                        <li>
                                            <a href="#">
                                                <div className="icon-large"><i className="ri-heart-pulse-line"></i>
                                                </div>
                                                Health & Household
                                            </a>
                                        </li>

                                        <li className="has-child homekit">
                                            <a href="#">
                                                <div className="icon-large"><i className="ri-home-8-line"></i></div>
                                                Home & Kitchen
                                                <div className="icon-small"><i className="ri-arrow-right-s-line"></i>
                                                </div>
                                            </a>
                                            <div className="mega">
                                                <div className="flexcol">
                                                    <div className="row">
                                                        <h4><a href="#">Kitchen & Dining</a></h4>
                                                        <ul>
                                                            <li><a href="#">Kitchen</a></li>
                                                            <li><a href="#">Dining Room</a></li>
                                                            <li><a href="#">Pantry</a></li>
                                                            <li><a href="#">Great Room</a></li>
                                                            <li><a href="#">Breakfast Nook</a></li>
                                                        </ul>
                                                    </div>
                                                    <div className="row">
                                                        <h4><a href="#">Living</a></h4>
                                                        <ul>
                                                            <li><a href="#">Living Room</a></li>
                                                            <li><a href="#">Family Room</a></li>
                                                            <li><a href="#">Sunroom</a></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <div className="flexcol">
                                                    <div className="row">
                                                        <h4><a href="#">Bed & Bath</a></h4>
                                                        <ul>
                                                            <li><a href="#">Bathroom</a></li>
                                                            <li><a href="#">Powder Room</a></li>
                                                            <li><a href="#">Bedroom</a></li>
                                                            <li><a href="#">Storage & Closet</a></li>
                                                            <li><a href="#">Baby & Kid</a></li>
                                                        </ul>
                                                    </div>
                                                    <div className="row">
                                                        <h4><a href="#">Utility</a></h4>
                                                        <ul>
                                                            <li><a href="#">Landry</a></li>
                                                            <li><a href="#">Garage</a></li>
                                                            <li><a href="#">Mudroom</a></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <div className="flexcol">
                                                    <div className="row">
                                                        <h4><a href="#">Outdoor</a></h4>
                                                        <ul>
                                                            <li><a href="#">Landscape</a></li>
                                                            <li><a href="#">Patio</a></li>
                                                            <li><a href="#">Deck</a></li>
                                                            <li><a href="#">Pool</a></li>
                                                            <li><a href="#">Backyard</a></li>
                                                            <li><a href="#">Porch</a></li>
                                                            <li><a href="#">Exterior</a></li>
                                                            <li><a href="#">Outdoor Kitchen</a></li>
                                                            <li><a href="#">Front Yard</a></li>
                                                            <li><a href="#">Driveway</a></li>
                                                            <li><a href="#">Poolhouse</a></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </li>

                                        <li>
                                            <a href="#">
                                                <div className="icon-large"><i className="ri-android-line"></i></div>
                                                Pet Supplíe
                                            </a>
                                        </li>

                                        <li>
                                            <a href="#">
                                                <div className="icon-large"><i className="ri-basketball-line"></i></div>
                                                Sports
                                            </a>
                                        </li>

                                        <li>
                                            <a href="#">
                                                <div className="icon-large"><i className="ri-shield-star-line"></i>
                                                </div>
                                                Best Seller
                                            </a>
                                        </li>


                                    </ul>
                                </div>
                            )}
                        </div>
                    </div>

                    <div className="right">
                        <div className="search-box">
                            <form action="" className="search">
                                <span className="icon-large"><i className="ri-search-line"></i></span>
                                <input type="search" placeholder="Search for products."/>
                                <button type="submit">Search</button>
                            </form>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    );
};

export default HeaderMain;

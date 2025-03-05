'use client'

import React, { useState } from 'react';
import Link from 'next/link';

const HeaderNav: React.FC = () => {
    const [isMenuOpen, setIsMenuOpen] = useState(false);

    const toggleMenu = () => {
        setIsMenuOpen(!isMenuOpen);
    };

    return (
        <div className="header-nav">
            <div className="container">
                <div className="wrapper flexitem">
                    <a href="#" className="trigger desktop-hide">
                        <span className="i ri-menu-2-line"></span>
                    </a>
                    <div className="left flexitem">
                        <nav className="mobile-hide">
                            <ul className="flexitem second-links">
                                <li className="has-child">
                                    <Link href="/category/women">
                                        Women
                                        <div className="icon-small">
                                            <i className="ri-arrow-down-s-line"></i>
                                        </div>
                                    </Link>
                                    {/* Mega Menu for Women's Category */}
                                    <div className="mega">
                                        <div className="container">
                                            <div className="wrapper">
                                                {/* Women's Clothing Submenu */}
                                                <div className="flexcol">
                                                    <div className="row">
                                                        <h4>Women Clothing</h4>
                                                        <ul>
                                                            {[
                                                                'Dresses', 'Tops & Tees', 'Jackets & Coats',
                                                                'Pants & Capris', 'Sweaters', 'Costumes',
                                                                'Hoodies & Sweatshirts', 'Pajams & Robes',
                                                                'Shorts', 'Swimwear'
                                                            ].map(item => (
                                                                <li key={item}>
                                                                    <Link href={`/app/category/women/${item.toLowerCase().replace(/\s+/g, '-')}`}>
                                                                        {item}
                                                                    </Link>
                                                                </li>
                                                            ))}
                                                        </ul>
                                                    </div>
                                                </div>

                                                <div className="flexcol">
                                                    <div className="row">
                                                        <h4>Jewelry</h4>
                                                        <ul>
                                                            <li><a href="#">Accessories</a></li>
                                                            <li><a href="#">Bags & Purses</a></li>
                                                            <li><a href="#">Nexklaces</a></li>
                                                            <li><a href="#">Rings</a></li>
                                                            <li><a href="#">Earrings</a></li>
                                                            <li><a href="#">Bracelets</a></li>
                                                            <li><a href="#">Body Jewelry</a></li>
                                                        </ul>
                                                    </div>
                                                </div>


                                                <div className="flexcol">
                                                    <div className="row">
                                                        <h4>Beauty</h4>
                                                        <ul>
                                                            <li><a href="#">Bath Accessories</a></li>
                                                            <li><a href="#">Makeup & Cosmetics</a></li>
                                                            <li><a href="#">Skin Care</a></li>
                                                            <li><a href="#">Hair Care</a></li>
                                                            <li><a href="#">Essential 0ils</a></li>
                                                            <li><a href="#">Fragrances</a></li>
                                                            <li><a href="#">Soaps & Bath Bombs</a></li>
                                                            <li><a href="#">Face Masks & Coverings</a></li>
                                                            <li><a href="#">Spa Kits & Gifts</a></li>
                                                        </ul>
                                                    </div>
                                                </div>

                                                <div className="flexcol">
                                                    <div className="row">
                                                        <h4>Top Brands</h4>
                                                        <ul className="women-brands">
                                                            <li><a href="#">Nike</a></li>
                                                            <li><a href="#">Louis Vuitton</a></li>
                                                            <li><a href="#">Hermes</a></li>
                                                            <li><a href="#">Gucci</a></li>
                                                            <li><a href="#">Zalando</a></li>
                                                            <li><a href="#">Tiffany & Co.</a></li>
                                                            <li><a href="#">Zara</a></li>
                                                            <li><a href="#">H&M</a></li>
                                                            <li><a href="#">Cartier</a></li>
                                                            <li><a href="#">Chanel</a></li>
                                                            <li><a href="#">Hurley</a></li>
                                                        </ul>
                                                        <a href="#" className="view-all">View all brands <i
                                                            className="ri-arrow-right-line"></i></a>
                                                    </div>
                                                </div>

                                                <div className="flexcol products">
                                                    <div className="row">
                                                        <div className="media">
                                                            <div className="thumbnail object-cover">
                                                                <a href="#">
                                                                    <img src="assets/products/apparel4.jpg" alt=""/>
                                                                </a>
                                                            </div>
                                                        </div>
                                                        <div className="text-content">
                                                            <h4>Most wanted!</h4>
                                                            <a href="#" className="primary-button">Order Now</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>

                                <li><a href="men-category.html">Men</a></li>
                                <li>
                                    <a href="page-shoes.html">Sports
                                        <div className="fly-item"><span>New!</span></div>
                                    </a>
                                </li>
                                <li><a href="page-electronic.html">Electronic</a></li>
                                <li><a href="page-health-and-household.html">Health & Household</a></li>
                                <li><a href="page-home-and-kitchen.html">Home & Kitchen</a></li>

                            </ul>
                        </nav>
                    </div>
                    <div className="right">
                        <ul className="flexitem second-links">
                            <li className="mobile-hide"><a href="#">
                                <div className="icon-large"><i className="ri-heart-line"></i></div>
                                <div className="fly-item"><span className="item-number">0</span></div>
                            </a></li>
                            <li><a href="#" className="iscart">
                                <div className="icon-large">
                                    <i className="ri-shopping-cart-line"></i>
                                    <div className="fly-item"><span className="item-number">0</span></div>
                                </div>
                                <div className="icon-text">
                                    <div className="mini-text">Total</div>
                                    <div className="cart-total">$0.00</div>
                                </div>
                            </a></li>
                        </ul>
                    </div>


                </div>
            </div>
        </div>
    );
};

export default HeaderNav;

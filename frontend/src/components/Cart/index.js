import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import './style.css'; // Make sure this file contains the necessary CSS

const Cart = () => {
  const apiUrl = process.env.REACT_APP_API_URL;
  const { cartId } = useParams();
  const [cart, setCart] = useState([]);

  useEffect(() => {
    fetchProducts();
  }, [cartId]);

  const fetchProducts = async () => {
    try {
      const jwtToken = localStorage.getItem("jwtToken");
      const response = await axios.get(`${apiUrl}/getCartDetails`, {
        headers: {
          Authorization: `Bearer ${jwtToken}`,
          "Content-Type": "application/json",
        },
      });
      setCart(response.data);
    } catch (error) {
      console.error("Error fetching products:", error);
    }
  };

  return (
    <div className="cart-container">
      <h2 className="cart-title">Shopping Cart</h2>
      {cart.length > 0 ? (
        <ul className="cart-items">
          {cart.map((item) => (
            <li key={item.cartId} className="cart-item">
              <img
                src={`/image/${item.product.productImages[0].name}`} // Assuming first image is the thumbnail
                alt={item.product.productName}
                className="cart-item-image"
              />
              <div className="cart-item-details">
                <h3 className="cart-item-name">{item.product.productName}</h3>
                <p className="cart-item-description">{item.product.productDescription}</p>
                <p className="cart-item-price">
                  <span className="cart-item-discounted-price">
                    {item.product.productDiscountedPrice} VND
                  </span>
                  <span className="cart-item-actual-price">
                    {item.product.productActualPrice} VND
                  </span>
                </p>
              </div>
            </li>
          ))}
        </ul>
      ) : (
        <p className="empty-cart-message">Your cart is empty.</p>
      )}
    </div>
  );
};

export default Cart;

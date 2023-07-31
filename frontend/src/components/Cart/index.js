import React, { useState, useEffect } from "react";
import { NavLink, useParams } from "react-router-dom";
import axios from "axios";
const Cart = () => {
  const apiUrl = process.env.REACT_APP_API_URL;

  const { cartId } = useParams();
  const [cart, setCart] = useState([]);
  const [products, setProducts] = useState([]);
  useEffect(() => {
    // Fetch products from the backend API when the component mounts
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
    <div>
      <p>Hello</p>
      {cart.map((cart) => (
        <li key={cart.cartId}>
          <p>Product Name: {cart.product.productName}</p>
          <p>Product Description: {cart.product.productDescription}</p>
          <p>Actual Price: {cart.product.productActualPrice}</p>
          <p>Discounted Price: {cart.product.productDiscountedPrice}</p>
          {cart.product.productImages.map((image) => (
            <img
              key={image.id}
              src={`/image/${image.name}`} // Set the correct path to the image
              alt={image.name}
              style={{
                maxWidth: "200px",
                maxHeight: "200px",
                marginBottom: "10px",
              }}
            />
          ))}
        </li>
      ))}
    </div>
  );
};

export default Cart;

// ProductDetails.js
import React, { useState, useEffect } from 'react';
import { NavLink, useParams  } from 'react-router-dom';
import axios from 'axios';

const ProductDetails = () => {
  const { productId } = useParams(); 
  const [product, setProduct] = useState(null);

  useEffect(() => {
    fetchProductDetails();
  }, [productId]);

  const fetchProductDetails = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/getProductDetailsById/${productId}`);
      setProduct(response.data);
    } catch (error) {
      console.error('Error fetching product details:', error);
    }
  };

  if (!product) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <p>Hello</p>
      <h1>{product.productName}</h1>
      <p>Actual Price: {product.productActualPrice}</p>
      <p>Discounted Price: {product.productDiscountedPrice}</p>
      <p>Description: {product.productDescription}</p>
      {/* Render product images here */}
      <NavLink to="/allproducts">Back to All Products</NavLink>
      <hr />
    </div>
  );
};

export default ProductDetails;

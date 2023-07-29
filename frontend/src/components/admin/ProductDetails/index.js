// ProductDetails.js
import React, { useState, useEffect } from "react";
import { NavLink, useParams } from "react-router-dom";
import axios from "axios";

const ProductDetails = () => {
  const { productId } = useParams();
  const [product, setProduct] = useState(null);

  useEffect(() => {
    fetchProductDetails();
    // fetchAddtoCart();
  }, [productId]);

  const fetchProductDetails = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/getProductDetailsById/${productId}`
    );
      setProduct(response.data);
    } catch (error) {
      console.error("Error fetching product details:", error);
    }
  };

  if (!product) {
    return <div>Loading...</div>;
  }

  const fetchAddtoCart = async() => {
      try {
      const jwtToken = localStorage.getItem('jwtToken');

          const response = await axios.get(
            `http://localhost:8080/addToCart/${productId}`,{
          
              headers: {
                 Authorization: `Bearer ${jwtToken}`,
                'Content-Type': 'application/json',
              }
          }
          );
          // setProduct(response.data);
        } catch (error) {
          console.error("Error fetching product details:", error);
        }
  }

  return (
    <div>
      <p>Hello</p>
      <h1>{product.productName}</h1>
      <p>Actual Price: {product.productActualPrice}</p>
      <p>Discounted Price: {product.productDiscountedPrice}</p>
      <p>Description: {product.productDescription}</p>
      {product.productImages.map((image) => (
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
      <NavLink to="/allproducts">Back to All Products</NavLink>
      {/* <NavLink to={`/addtocart/${product.productId}`}
      >Add to cart</NavLink> */}
      <hr />
      <button 
      onClick={fetchAddtoCart}>Add to cart</button>
    </div>
  );
};

export default ProductDetails;

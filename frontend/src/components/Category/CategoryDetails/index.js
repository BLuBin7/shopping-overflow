import React, { useState, useEffect } from "react";
import { NavLink, useParams } from "react-router-dom";
import axios from "axios";
import Category from "../index";

const CategoryDetails = () => {
  const apiUrl = process.env.REACT_APP_API_URL;

  const { categoryId } = useParams();
  const [product, setProduct] = useState([]);

  const [category, setCategory] = useState(null);

  useEffect(() => {
    fetchProductDetails();
  }, [categoryId]);

  const fetchProductDetails = async () => {
    const jwtToken = localStorage.getItem("jwtToken");
    try {
      const response = await axios.get(
        `${apiUrl}/getAllProductbasedonCategory/${categoryId}`
      );
      setProduct(response.data);
    } catch (error) {
      console.error("Error fetching product details:", error);
    }
  };
  return (
    <>
      <p>sad</p>
      {product.map((product) => (
        <div key={product.productId} className="one-product">
          <NavLink
            to={`/products/${product.productId}`}
            style={{ textDecoration: "none" }}
          >
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
            <h3>{product.productName}</h3>
            <hr />
          </NavLink>
        </div>
      ))}
    </>
  );
};

export default CategoryDetails;

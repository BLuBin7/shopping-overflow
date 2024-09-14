// ProductDetails.js
import React, { useState, useEffect } from "react";
import { NavLink, useParams } from "react-router-dom";
import axios from "axios";

const ProductDetails = () => {
  const apiUrl = process.env.REACT_APP_API_URL;

  const { productId } = useParams();
  const [product, setProduct] = useState(null);

  const { user_name } = useParams();

  const [comment, setComment] = useState({
    // user_comment: "",
    // user_name:"",
    text: "",
  });

  useEffect(() => {
    fetchProductDetails();
    // fetchAddtoCart();
    // fetchAllComments();
  }, [productId]);

  // document.addEventListener("DOMContentLoaded", function() {
  //   fetchAllComments();
  // });

  const fetchProductDetails = async () => {
    const jwtToken = localStorage.getItem("jwtToken");
    try {
      const response = await axios.get(
        `${apiUrl}/getProductDetailsById/${productId}`
      );
      console.log(response.data);
      setProduct(response.data);
    } catch (error) {
      console.error("Error fetching product details:", error);
    }
  };

  if (!product) {
    return <div>Loading...</div>;
  }

  const fetchAddtoCart = async () => {
    try {
      const jwtToken = localStorage.getItem("jwtToken");

      const response = await axios.get(`${apiUrl}/addToCart/${productId}`, {
        headers: {
          Authorization: `Bearer ${jwtToken}`,
          "Content-Type": "application/json",
        },
      });
    } catch (error) {
      console.error("Error fetching product details:", error);
    }
  };

  const fetchAddComment = async () => {
    try {
      const jwtToken = localStorage.getItem("jwtToken");

      const formData = new FormData();

      formData.append("comment", JSON.stringify(comment));
      const response = await axios.post(`${apiUrl}/addComment/${productId}`, formData, {
        headers: {
          Authorization: `Bearer ${jwtToken}`,
          "Content-Type": "multipart/form-data",
        },
      });
      console.log("Comment added successfully:", response.data);
      setComment({
        // user_comment: "",
        text: "",
      });
    } catch (error) {
      console.error("Error fetching product details:", error);
    }
  };

  // const fetchAllComments = async () =>{
  //   try {
  //     const jwtToken = localStorage.getItem("jwtToken");

  //     const formData = new FormData();

  //     const response = await axios.get(`${apiUrl}/allcomment/${productId}`,{
  //     headers: {
  //       Authorization: `Bearer ${jwtToken}`,
  //       "Content-Type": "application/json",
  //     }});
  //     console.log("Comment added successfully:", response.data);

  //   }catch (error) {
  //     console.error("Error fetching product details:", error);
  //   };
  // }

  const handleChange = (e) => {
    setComment({
      ...comment,
      [e.target.name]: e.target.value,
    });
  };

  return (
    <div>
      <p>Hello</p>
      <p>Home/{product.category_name.categoryName}</p>
      <p>Add by: {product.addedBy.userName}</p>
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
      <br />
      <div>
      <label>Input Comment :</label>

      <input
        type="text"
        name="text"
        value={comment.text}
        onChange={handleChange}
        required
      />
      </div>
      <button onClick={fetchAddComment}>Submit</button>
      {product.userComment.map((comment) =>
      (<>
        <div>
        Name : 
        {comment.user_comment.userName}
        </div>
        <div>
        {comment.text}
        </div>
      </>))}
      <hr />
      <button onClick={fetchAddtoCart}>Add to cart</button>
      <NavLink to="/allproducts">Back to All Products</NavLink>

    </div>
  );
};

export default ProductDetails;

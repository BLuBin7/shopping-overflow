import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { NavLink } from 'react-router-dom';

// tên hoa camelcase bị lỗi??
const AllProducts = () => {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    // Fetch products from the backend API when the component mounts
    fetchProducts();
  }, []);

  const fetchProducts = async () => {
    try {
      const response = await axios.get('http://localhost:8080/getAllProducts');
      setProducts(response.data);
    } catch (error) {
      console.error('Error fetching products:', error);
    }
  };

  return (
    <div>
      <h1>Product List</h1>
      {products.map((product) => (
        <div key={product.productId}>
          {/* Use NavLink to link to individual product details page */}
          <NavLink to={`/products/${product.productId}`} style={{ textDecoration: 'none' }}>
            <h3>{product.productName}</h3>
            <p>Actual Price: {product.productActualPrice}</p>
            <p>Discounted Price: {product.productDiscountedPrice}</p>
            <p>Description: {product.productDescription}</p>
            {product.productImages.map((image) => (
              <img
                key={image.id}
                src={`/image/${image.name}`} // Set the correct path to the image
                alt={image.name}
                style={{ maxWidth: '200px', maxHeight: '200px', marginBottom: '10px' }}
              />
            ))}
            <hr />
          </NavLink>
        </div>
      ))}
    </div>
  );
};

export default AllProducts;

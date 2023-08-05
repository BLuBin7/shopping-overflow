import React from 'react';
import './style.scss';
const ProductPopup = ({ products, onClose }) => {
  return (
    <div className="product-popup">
      <button onClick={onClose}>Close</button>
      <ul className="products">
        {products.map((product) => (
          <li key={product.id}>{product.name}</li>
        ))}
      </ul>
    </div>
  );
};

export default ProductPopup;

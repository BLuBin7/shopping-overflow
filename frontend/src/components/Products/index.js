import React from 'react';
import './style.scss';
const Product = (props) => {
  const { productId, productName, productDescription, productDiscountedPrice, productActualPrice, productImages } = props;

  return (
    <div className="product" >
      <h2>{productName}</h2>
      <p>Description: {productDescription}</p>
      <p>Discounted Price: ${productDiscountedPrice}</p>
      <p>Actual Price: ${productActualPrice}</p>
      <p>Product ID: {productId}</p>
      <div>
        {productImages && productImages.map((image) => (
          <img key={image.id} src={image.url} alt={`Image ${image.id}`} />
        ))}
      </div>
    </div>
  );
};

export default Product;

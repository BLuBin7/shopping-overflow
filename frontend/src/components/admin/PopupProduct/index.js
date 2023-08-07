import React,{useEffect,useState} from 'react';
import './style.scss';

const ProductPopup = ({ products, onClose ,searchKey,fetchProducts}) => {

  const [popupSearchKey, setPopupSearchKey] = useState('');

  const handlePopupSearch = () => {
    fetchProducts(0, popupSearchKey); // Assuming the first page for popup search
  };

  return (
    <div className="product-popup">
       <input
        type="text"
        value={popupSearchKey}
        onChange={(e) => setPopupSearchKey(e.target.value)}
        placeholder="Search products in popup"
      />
      <button onClick={handlePopupSearch}>Search</button>
      <p>Search Key: {searchKey}</p>
      {products.length === 0 ? (
        <p>No products to display</p>
      ) :  (
      <ul className="products">
        <p>sak</p>
        {products.map((product) => (
          <div key={product.productId}>
          <h3>{product.productName}</h3>
          <hr />
        </div>
         ))}
        <p>nmom</p>
      </ul>
      )}
    </div>
  );
};

export default ProductPopup;

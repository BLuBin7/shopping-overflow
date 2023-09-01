import React, { useState, useEffect } from "react";
import axios from "axios";
import { NavLink,useLocation } from "react-router-dom";
import ProductPopup from "./../PopupProduct/index";
import "./style.scss";

// tên hoa camelcase bị lỗi??
const AllProducts = () => {
  // Lấy thông tin từ URL hiện tại
  const location = useLocation();
  const searchKeyFromURL = new URLSearchParams(location.search).get("searchKey");

  const apiUrl = process.env.REACT_APP_API_URL;
  const [products, setProducts] = useState([]);

  // amount product in one page
  const [currentPage, setCurrentPage] = useState(1);
  const productsPerPage = 30;
  const [pageNumber, setPageNumber] = useState(0);

  //Search
  const [isPopupOpen, setPopupOpen] = useState(false);
  const [popupProducts, setPopupProducts] = useState([]);
  const [searchKey, setSearchKey] = useState("");

  // useEffect(() => {
  //   // Fetch products from the backend API when the component mounts
  //   fetchProducts();
  // }, []);

  // const fetchProducts = async () => {
  //   try {
  //     const response = await axios.get(`${apiUrl}/getAllProducts`);
  //     setProducts(response.data);
  //   } catch (error) {
  //     console.error("Error fetching products:", error);
  //   }
  // };

  useEffect(() => {
    // Fetch products from the backend API when the searchKey changes
    fetchProducts(pageNumber, searchKeyFromURL);
  }, [pageNumber, searchKeyFromURL]);
  const fetchProducts = async (pageNumber, searchKey) => {
    try {
      const response = await axios.get(`${apiUrl}/getAllProducts`, {
        params: {
          pageNumber: pageNumber,
          searchKey: searchKeyFromURL,
        },
      });

      setProducts(response.data);
    } catch (error) {
      console.error("Error fetching products:", error);
    }
  };

  // amount of products in one page
  const getCurrentProducts = () => {
    const indexOfLastProduct = currentPage * productsPerPage;
    const indexOfFirstProduct = indexOfLastProduct - productsPerPage;
    return products.slice(indexOfFirstProduct, indexOfLastProduct);
  };

  const nextPage = () => {
    setPageNumber(pageNumber + 1);
    setCurrentPage((prevPage) => prevPage + 1);
  };

  const prevPage = () => {
    if (pageNumber > 0) {
      setPageNumber(pageNumber - 1);
      setCurrentPage((prevPage) => prevPage - 1);
    }
  };

  const handleOpenPopup = () => {
    setPopupOpen(true);
  };

  const handleClosePopup = () => {
    setPopupOpen(false);
  };

  const handleSearchChanged = (e) => {
    setPageNumber(0);
    setSearchKey(e);
    // setPopupOpen(true);
    // setPopupProducts([]);
    // handleOpenPopup();
  };
  return (
    <div className="product">

      {/* <h1>Product List</h1> */}

      {/* search */}
      <input
        type="text"
        value={searchKey}
        // onChange={(e) => setSearchKey(e.target.value) }
        onChange={(e)=>handleSearchChanged(e.target.value)} 
        placeholder="Search products"
      />
      {/* <NavLink onClick={handleSearch}  >Search</NavLink> */}

      {products.map((product) => (
        <div key={product.productId} className="one-product">
          {/* Use NavLink to link to individual product details page */}
          <NavLink
            to={`/products/${product.productId}`}
            style={{ textDecoration: "none" }}
          >
            {/* <p>Actual Price: {product.productActualPrice}</p>
            <p>Discounted Price: {product.productDiscountedPrice}</p>
          <p>Description: {product.productDescription}</p> */}
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

      {/* amount of products in one page */}
      <div className="pagination-buttons">
        <div>
          <button
            onClick={prevPage}
            disabled={getCurrentProducts().length === 1}
          >
            Previous Page
          </button>
          <button
            onClick={nextPage}
            disabled={getCurrentProducts().length === 0}
          >
            Next Page
          </button>
        </div>
      </div>

      {/* Display the popup when isPopupOpen is true */}
      {isPopupOpen && (
        <ProductPopup
          products={popupProducts}
          onClose={handleClosePopup}
          searchKey={searchKey}
          fetchProducts={fetchProducts}
        />
      )}
    </div>
  );
};

export default AllProducts;

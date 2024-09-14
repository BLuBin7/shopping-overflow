import React, { useState } from "react";
import axios from "axios";
import './style.css'; // Đảm bảo tệp này chứa CSS cần thiết

const ProductForm = () => {
  const categoryOptions = [
    { id: 1, name: "Nữ" },
    { id: 2, name: "Áo Tops" },
    { id: 3, name: "Đầm" },
    { id: 4, name: "Quần & Váy" },
    { id: 5, name: "Đồ lót & Đồ mặc nhà" },
    { id: 6, name: "Trang phục đi biển" },
    { id: 7, name: "Thời Trang Nam" },
    { id: 8, name: "Phụ Kiện" },
    { id: 9, name: "Trẻ em" },
    { id: 10, name: "Đồ Trang sức và Đồng hồ" },
    { id: 11, name: "Kích thước lớn" },
    { id: 12, name: "Nhà cửa & đời sống" },
    { id: 13, name: "Làm đẹp & Sức khỏe" },
    { id: 14, name: "Thiết bị điện tử" },
  ];

  const apiUrl = process.env.REACT_APP_API_URL;
  const [product, setProduct] = useState({
    productName: "",
    productDescription: "",
    productDiscountedPrice: 0,
    productActualPrice: 0,
    productImages: [],
    categoryId: 0,
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setProduct((prevProduct) => ({
      ...prevProduct,
      [name]: value,
    }));
  };

  const handleFileChange = (e) => {
    const { files } = e.target;
    setProduct((prevProduct) => ({
      ...prevProduct,
      productImages: [...prevProduct.productImages, ...files],
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const jwtToken = localStorage.getItem("jwtToken");

      const formData = new FormData();

      formData.append("categoryId", product.categoryId);
      formData.append("product", JSON.stringify(product));
      
      for (let i = 0; i < product.productImages.length; i++) {
        formData.append("imageFile", product.productImages[i]);
      }

      const response = await axios.post(`${apiUrl}/addNewProduct`, formData, {
        headers: {
          Authorization: `Bearer ${jwtToken}`,
          "Content-Type": "multipart/form-data",
        },
      });

      console.log("Product added successfully:", response.data);

      setProduct({
        productName: "",
        productDescription: "",
        productDiscountedPrice: 0,
        productActualPrice: 0,
        productImages: [],
        categoryId: 0,
      });
    } catch (error) {
      console.error("Error adding product:", error);
    }
  };

  return (
    <div className="container">
      <div className="product-form">
        <h2>Add Product</h2>
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label>Product Name:</label>
            <input
              type="text"
              name="productName"
              value={product.productName}
              onChange={handleChange}
              required
            />
          </div>
          <div className="form-group">
            <label>Product Description:</label>
            <input
              name="productDescription"
              value={product.productDescription}
              onChange={handleChange}
              required
            />
          </div>
          <div className="form-group">
            <label>Product Discounted Price:</label>
            <input
              type="number"
              name="productDiscountedPrice"
              value={product.productDiscountedPrice}
              onChange={handleChange}
              required
            />
          </div>
          <div className="form-group">
            <label>Product Actual Price:</label>
            <input
              type="number"
              name="productActualPrice"
              value={product.productActualPrice}
              onChange={handleChange}
              required
            />
          </div>
          <div className="form-group">
            <label>Product Images:</label>
            <input
              type="file"
              name="imageFile"
              onChange={handleFileChange}
              multiple
            />
          </div>
          <div className="form-group">
            <label>Chọn Danh Mục:</label>
            <select
              name="categoryId"
              value={product.categoryId}
              onChange={handleChange}
            >
              <option value="">Chọn Danh Mục</option>
              {categoryOptions.map((category) => (
                <option key={category.id} value={category.id}>
                  {category.name}
                </option>
              ))}
            </select>
          </div>
          <button type="submit">Add Product</button>
        </form>
      </div>
    </div>
  );
};

export default ProductForm;

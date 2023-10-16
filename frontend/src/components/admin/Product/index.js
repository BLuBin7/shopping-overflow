// import React, { useState } from 'react';
// import axios from 'axios';

// const Product = () => {
//   const [product, setProduct] = useState({
//     productName: '',
//     productDescription: '',
//     productDiscountedPrice: 0,
//     productActualPrice: 0,
//     productImages: [],
//   });

//   // const handleChange = (e) => {
//   //   const { name, value, type, files } = e.target;

//   //   // If the field is of type "file", handle file upload
//   //   if (type === "file") {
//   //     if (files) {
//   //       const filesArray = Array.from(files);
//   //       const fileHandlesArray = filesArray.map((file) => ({
//   //         file: file,
//   //         url: URL.createObjectURL(file),
//   //       }));
//   //       setProduct((prevProduct) => ({
//   //         ...prevProduct,
//   //         productImages: [...prevProduct.productImages, ...fileHandlesArray],
//   //       }));
//   //     }
//   //   } else {
//   //     setProduct((prevProduct) => ({
//   //       ...prevProduct,
//   //       [name]: value,
//   //     }));
//   //   }
//   // };

//   const handleSubmit = async (e) => {

//     e.preventDefault();

//     try {
//     // lấy token từ local storage
//       const jwtToken = localStorage.getItem('jwtToken');

//       // const formData = prepareFormDataForProduct(product);
//       const formData = new FormData();
//       formData.append("product", JSON.stringify(product));
//       product.productImages.forEach((imageFile, index) => {
//         formData.append(`imageFile${index}`, imageFile.file);
//       });

//       // Make the POST request to the backend API
//       const response = await axios.post('http://localhost:8080/addNewProduct', formData, {
//         headers: {
//             // phải có token
//            Authorization: `Bearer ${jwtToken}`,
//           // 'Content-Type': 'multipart/form-data', // Set the content type as multipart/form-data
//         },
//       });

//       console.log('Product added successfully:', response.data);

//       // Reset form fields after successful submission
//       setProduct({
//         productName: '',
//         productDescription: '',
//         productDiscountedPrice: 0,
//         productActualPrice: 0,
//         productImages: [],
//         // imageFile: [],
//       });
//     } catch (error) {
//       console.error('Error adding product:', error);
//       console.log("non");
//       // Handle error here if needed
//     }
//   };

//   const convertImageToBytes = (imageFile) => {
//     return new Promise((resolve, reject) => {
//       const reader = new FileReader();
//       reader.onloadend = () => {
//         // 'reader.result' contains the image data in base64 format
//         resolve(reader.result.split(',')[1]); // Extract the base64 data from the result
//       };
//       reader.onerror = (error) => {
//         reject(error);
//       };
//       reader.readAsDataURL(imageFile); // Read the image file as data URL (base64)
//     });
//   };

//   // const prepareFormDataForProduct = (product) => {
//   //   const formData = new FormData();
//   //   formData.append("product", JSON.stringify(product));

//   //   for (let i = 0; i < product.productImages.length; i++) {
//   //     const imageFile = product.productImages[i].file;
//   //     formData.append(`imageFile${i}`, imageFile);
//   //   }

//   //   return formData;
//   // };

//   // final
//   const prepareFormDataForProduct = () => {
//     const formData = new FormData();
//     formData.append("productName", product.productName);
//     formData.append("productDescription", product.productDescription);
//     formData.append("productDiscountedPrice", product.productDiscountedPrice);
//     formData.append("productActualPrice", product.productActualPrice);

//     for (let i = 0; i < product.productImages.length; i++) {
//       formData.append("imageFile", product.productImages[i]);
//     }

//     return formData;
//   };

//   const [imagePreview, setImagePreview] = useState(null);
//   const [imageData, setImageData] = useState(null);

//   const handleUploadClick = event => {
//     let file = event.target.files[0];
//     const imageData = new FormData();
//     imageData.append('imageFile', file);
//     setImageData(imageData);
//     setImagePreview(URL.createObjectURL(file));
// };
// // const onFileSelected = (event) => {
// //   if (event.target.files) {
// //     const filesArray = Array.from(event.target.files);
// //     const fileHandlesArray = filesArray.map((file) => ({
// //       file: file,
// //       url: URL.createObjectURL(file),
// //     }));
// //     setProduct((prevProduct) => ({
// //       ...prevProduct,
// //       productImages: [...prevProduct.productImages, ...fileHandlesArray],
// //     }));
// //   }
// // };

// // last
// const handleChange = (e) => {
//   const { name, value } = e.target;
//   setProduct((prevProduct) => ({
//     ...prevProduct,
//     [name]: value,
//   }));
// };

// // const onFileSelected = (event) => {
// //   if (event.target.files) {
// //     const filesArray = Array.from(event.target.files);
// //     const fileHandlesArray = filesArray.map((file) => ({
// //       name: file.name,
// //       type: file.type,
// //       pic_byte: file,
// //     }));
// //     setProduct((prevProduct) => ({
// //       ...prevProduct,
// //       productImages: [...prevProduct.productImages, ...fileHandlesArray],
// //     }));
// //   }
// // };

// const   onFileSelected = (event) => {
//   if (event.target.files) {
//     const filesArray = Array.from(event.target.files);
//     const fileHandlesArray = filesArray.map((file) => ({
//       file: file,
//       url: URL.createObjectURL(file),
//     }));
//     setProduct((prevProduct) => ({
//       ...prevProduct,
//       productImages: [...prevProduct.productImages, ...fileHandlesArray],
//     }));
//   }
// };

//   const removeImages = (index) => {
//     setProduct((prevProduct) => {
//       const updatedImages = [...prevProduct.productImages];
//       updatedImages.splice(index, 1);
//       return {
//         ...prevProduct,
//         productImages: updatedImages,
//       };
//     });
//   };

//   return (
//     <div>
//        <h2>Add Product</h2>
//        <form onSubmit={handleSubmit}>
//          <div>
//            <label>Product Name:</label>
//            <input type="text" name="productName" value={product.productName} onChange={handleChange} required />
//          </div>
//          <div>
//            <label>Product Description:</label>
//           <textarea
//             name="productDescription"
//             value={product.productDescription}
//             onChange={handleChange}
//             required
//           />
//         </div>
//         <div>
//           <label>Product Discounted Price:</label>
//           <input
//             type="number"
//             name="productDiscountedPrice"
//             value={product.productDiscountedPrice}
//             onChange={handleChange}
//             required
//           />
//         </div>
//         <div>
//           <label>Product Actual Price:</label>
//           <input
//             type="number"
//             name="productActualPrice"
//             value={product.productActualPrice}
//             onChange={handleChange}
//             required
//           />
//         </div>
//         <div>
//           <label>Product Images:</label>
//           {/* <input type="file" name="productImages" onChange={handleChange} multiple /> */}
//           <input
//           type="file"
//           // accept="image/*"
//           name="productImages"
//           onChange={onFileSelected}
//           // onChange={handleUploadClick}
//           // multiple
//           />
//         </div>
//         {/* <button type="submit">Add Product</button> */}
//         <button type="submit" >Add Product</button>
//       </form>
//       <div classNameName="col-6">
//             <div>
//               <div classNameName="mt-5">
//                 <div classNameName="row">
//                   {product.productImages.map((image, index) => (
//                     <div classNameName="col-3" key={index}>
//                       <div style={{ position: "relative" }}>
//                         <span
//                           classNameName="btn-remove-image"
//                           onClick={() => removeImages(index)}
//                         >
//                           x
//                         </span>
//                         <img
//                           // src={URL.createObjectURL(image.pic_byte)}
//                           alt="Product"
//                           width="100px"
//                           height="100px"
//                         />
//                       </div>
//                     </div>
//                   ))}
//                 </div>
//               </div>
//             </div>
//           </div>
//     </div>
//   );
// };

// export default Product;

import React, { useState } from "react";
import axios from "axios";

import ButtonGroup from "react-bootstrap/ButtonGroup";
import Dropdown from "react-bootstrap/Dropdown";
import DropdownButton from "react-bootstrap/DropdownButton";
import SplitButton from "react-bootstrap/SplitButton";

const ProductForm = () => {
  const apiUrl = process.env.REACT_APP_API_URL;
  const [product, setProduct] = useState({
    productName: "",
    productDescription: "",
    productDiscountedPrice: 0,
    productActualPrice: 0,
    productImages: [],
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

      // Reset form fields after successful submission
      setProduct({
        productName: "",
        productDescription: "",
        productDiscountedPrice: 0,
        productActualPrice: 0,
        productImages: [],
      });
    } catch (error) {
      console.error("Error adding product:", error);
    }
  };

  const [selectedCategory, setSelectedCategory] = useState(""); // State để lưu trữ danh mục được chọn

  const handleSelect = (category) => {
    setSelectedCategory(category);
  };

  return (
    <div>
      <h2>Add Product</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Product Name:</label>
          <input
            type="text"
            name="productName"
            value={product.productName}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Product Description:</label>
          <textarea
            name="productDescription"
            value={product.productDescription}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Product Discounted Price:</label>
          <input
            type="number"
            name="productDiscountedPrice"
            value={product.productDiscountedPrice}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Product Actual Price:</label>
          <input
            type="number"
            name="productActualPrice"
            value={product.productActualPrice}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Product Images:</label>
          <input
            type="file"
            name="imageFile"
            onChange={handleFileChange}
            multiple
          />
        </div>
              Danh Mục
          <Dropdown className="d-inline mx-2">
            <Dropdown.Toggle id="dropdown-autoclose-true">
              {selectedCategory}
            </Dropdown.Toggle>

            <Dropdown.Menu>
              <Dropdown.Item
                href="#"
                onClick={() => handleSelect("Thời Trang Nữ")}
              >
                Thời Trang Nữ
              </Dropdown.Item>
              <Dropdown.Item
                href="#"
                onClick={() => handleSelect("Thời Trang Nam")}
              >
                Thời Trang Nam
              </Dropdown.Item>
              <Dropdown.Item href="#" onClick={() => handleSelect("Sắc Đẹp")}>
                Sắc Đẹp
              </Dropdown.Item>
            </Dropdown.Menu>
          </Dropdown>
        
        <br/>
        <button type="submit">Add Product</button>
      </form>
    </div>
  );
};

export default ProductForm;

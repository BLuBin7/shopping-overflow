import React from "react";
import { NavLink } from "react-router-dom";
import "./index.css"; // Import custom CSS

const Category = () => {
  return (
    <div className="category-container">
      <div className="row category-row">
        <div className="col-md-4 col-lg-3 category-item">
          <NavLink to={`/getAllProductbasedonCategory/1`} className="category-link">
            <img
              className="img-fluid category-image"
              src="image/category/Thoi-Trang-Nam.png"
              alt="Thời Trang Nam"
            />
            <p className="category-title">Thời Trang Nam</p>
          </NavLink>
        </div>

        <div className="col-md-4 col-lg-3 category-item">
          <img
            className="img-fluid category-image"
            src="image/category/31234a27876fb89cd522d7e3db1ba5ca_tn.png"
            alt="Thời Trang Nam"
          />
          <p className="category-title">Điện Thoại </p>
        </div>

        <div className="col-md-4 col-lg-3 category-item">
          <img
            className="img-fluid category-image"
            src="image/category/978b9e4cb61c611aaaf58664fae133c5_tn.png"
            alt="Thiết Bị Điện tử"
          />
          <p className="category-title">Thiết Bị Điện tử</p>
        </div>

        <div className="col-md-4 col-lg-3 category-item">
          <img
            className="img-fluid category-image"
            src="image/category/c3f3edfaa9f6dafc4825b77d8449999d_tn.png"
            alt="Máy Tính & Laptop"
          />
          <p className="category-title">Máy Tính</p>
        </div>

        <div className="col-md-4 col-lg-3 category-item">
          <img
            className="img-fluid category-image"
            src="image/category/ec14dd4fc238e676e43be2a911414d4d_tn.png"
            alt="Máy Ảnh & Máy Quay Phim"
          />
          <p className="category-title">Máy Ảnh</p>
        </div>

        <div className="col-md-4 col-lg-3 category-item">
          <img
            className="img-fluid category-image"
            src="image/category/86c294aae72ca1db5f541790f7796260_tn.png"
            alt="Đồng Hồ"
          />
          <p className="category-title">Đồng Hồ</p>
        </div>

        <div className="col-md-4 col-lg-3 category-item">
          <img
            className="img-fluid category-image"
            src="image/category/74ca517e1fa74dc4d974e5d03c3139de_tn.png"
            alt="Giày Dép Nam"
          />
          <p className="category-title">Giày Nam</p>
        </div>
      </div>

      <div className="row category-row">
        <div className="col-md-4 col-lg-3 category-item">
          <NavLink to={`/getAllProductbasedonCategory/2`} className="category-link">
            <img
              className="img-fluid category-image"
              src="image/category/75ea42f9eca124e9cb3cde744c060e4d_tn.png"
              alt="Thời Trang Nữ"
            />
            <p className="category-title">Thời Trang Nữ</p>
          </NavLink>
        </div>

        <div className="col-md-4 col-lg-3 category-item">
          <img
            className="img-fluid category-image"
            src="image/category/099edde1ab31df35bc255912bab54a5e_tn.png"
            alt="Mẹ & Bé"
          />
          <p className="category-title">Mẹ & Bé</p>
        </div>

        <div className="col-md-4 col-lg-3 category-item">
          <img
            className="img-fluid category-image"
            src="image/category/24b194a695ea59d384768b7b471d563f_tn.png"
            alt="Nhà Cửa & Đời Sống"
          />
          <p className="category-title">Nhà Cửa </p>
        </div>

        <div className="col-md-4 col-lg-3 category-item">
          <img
            className="img-fluid category-image"
            src="image/category/ef1f336ecc6f97b790d5aae9916dcb72_tn.png"
            alt="Sắc Đẹp"
          />
          <p className="category-title">Sắc Đẹp</p>
        </div>

        <div className="col-md-4 col-lg-3 category-item">
          <img
            className="img-fluid category-image"
            src="image/category/49119e891a44fa135f5f6f5fd4cfc747_tn.png"
            alt="Sức Khỏe"
          />
          <p className="category-title">Sức Khỏe</p>
        </div>

        <div className="col-md-4 col-lg-3 category-item">
          <img
            className="img-fluid category-image"
            src="image/category/8e71245b9659ea72c1b4e737be5cf42e_tn.png"
            alt="Phụ Kiện & Trang Sức Nữ"
          />
          <p className="category-title">Phụ Kiện </p>
        </div>

        <div className="col-md-4 col-lg-3 category-item">
          <img
            className="img-fluid category-image"
            src="/image/category/36013311815c55d303b0e6c62d6a8139_tn.png"
            alt="Nhà Sách Online"
          />
          <p className="category-title">Sách</p>
        </div>
      </div>
    </div>
  );
};

export default Category;

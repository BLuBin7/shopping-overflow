import React from "react";
import { NavLink } from "react-router-dom";

const Category = () => {
  return (
    <>
      <div class="container text-center">
        <div class="row">
          <div className="col">
            <div className="border rounded">
              <NavLink 
              to={`/products/Thời-Trang-Nam-cat`}
              style={{ textDecoration: "none" }}>
                <img
                  className="img-fluid rounded"
                  src="image/category/Thoi-Trang-Nam.png"
                  alt="Thời Trang Nam"
                />
              </NavLink>
              <p>Thời Trang Nam</p>
            </div>
          </div>

          <div className="col">
            <img
              className="img-fluid rounded object-fit-contain border rounded"
              src="image/category/31234a27876fb89cd522d7e3db1ba5ca_tn.png"
              alt="Thời Trang Nam"
            />
            Điện Thoại & Phụ Kiện
          </div>

          <div className="col">
            <img
              className="img-fluid rounded"
              src="image/category/978b9e4cb61c611aaaf58664fae133c5_tn.png"
              alt="Thời Trang Nam"
            />
            Thiết Bị Điện tử
          </div>

          <div className="col">
            <img
              className="img-fluid rounded"
              src="image/category/c3f3edfaa9f6dafc4825b77d8449999d_tn.png"
              alt="Thời Trang Nam"
            />
            Máy Tính & Laptop
          </div>

          <div className="col">
            <img
              className="img-fluid rounded"
              src="image/category/ec14dd4fc238e676e43be2a911414d4d_tn.png"
              alt="Thời Trang Nam"
            />
            Máy Ảnh & Máy Quay Phim
          </div>

          <div className="col">
            <img
              className="img-fluid rounded"
              src="image/category/86c294aae72ca1db5f541790f7796260_tn.png"
              alt="Thời Trang Nam"
            />
            Đồng Hồ
          </div>

          <div className="col">
            <img
              className="img-fluid rounded"
              src="image/category/74ca517e1fa74dc4d974e5d03c3139de_tn.png"
              alt="Thời Trang Nam"
            />
            Giày Dép Nam
          </div>
        </div>

        <div class="row">
          <div className="col">
            <img
              className="img-fluid rounded"
              src="image/category/75ea42f9eca124e9cb3cde744c060e4d_tn.png"
              alt="Thời Trang Nam"
            />
            Thời Trang Nữ
          </div>

          <div className="col">
            <img
              className="img-fluid rounded"
              src="image/category/099edde1ab31df35bc255912bab54a5e_tn.png"
              alt="Thời Trang Nam"
            />
            Mẹ & Bé
          </div>

          <div className="col">
            <img
              className="img-fluid rounded"
              src="image/category/24b194a695ea59d384768b7b471d563f_tn.png"
              alt="Thời Trang Nam"
            />
            Nhà Cửa & Đời Sống
          </div>

          <div className="col">
            <img
              className="img-fluid rounded"
              src="image/category/ef1f336ecc6f97b790d5aae9916dcb72_tn.png"
              alt="Thời Trang Nam"
            />
            Sắc Đẹp
          </div>

          <div className="col">
            <img
              className="img-fluid rounded"
              src="image/category/49119e891a44fa135f5f6f5fd4cfc747_tn.png"
              alt="Thời Trang Nam"
            />
            Sức Khỏe
          </div>

          <div className="col">
            <img
              className="img-fluid rounded"
              src="image/category/8e71245b9659ea72c1b4e737be5cf42e_tn.png"
              alt="Thời Trang Nam"
            />
            Phụ Kiện & Trang Sức Nữ
          </div>

          <div className="col">
            <img
              className="img-fluid rounded"
              src="/image/category/36013311815c55d303b0e6c62d6a8139_tn.png"
              alt="Thời Trang Nam"
            />
            Nhà Sách Online
          </div>
        </div>
      </div>
    </>
  );
};

export default Category;

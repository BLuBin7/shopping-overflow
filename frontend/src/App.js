import React, { useState } from 'react';
import { BrowserRouter as Router,Outlet,Route,Routes,useNavigate } from 'react-router-dom';

import Signup from "./components/Signup";
import Header from "./components/Header";
import Home from "./components/Home";
import Signin from "./components/Signin";
import Dashboard from "./components/admin/dashboard";
import AdminDashboard from "./components/admin/dashboard";
import PrivateRoute from "./components/PrivateRoute";
import Product from "./components/admin/Product";
import AllProducts from "./components/admin/allProducts";
import ProductDetails from "./components/admin/ProductDetails";
import Cart from "./components/Cart";
import ForgotPassword from "./components/Signin/ForgotPassword";
import ResetPassword from "./components/Signin/ResetPassword";
import Profile from "./components/User/Profile";
import ProductPopup from "./components/admin/PopupProduct";
import ProductResponse from "./components/ProductResponse";


function App() {
  const searchKeyFromURL = new URLSearchParams(window.location.search).get(
    "searchKey"
  );
  return (
    <Router>
      {/* <Header setSearchKey={setSearchKey} /> */}
      <Header  />
      <Routes>
        {/* component trước sẽ là cha(sẽ đc vẫn giữ lại khi bị thay đổi) */}
        <Route path="/" element={<Home />}>
          {/* <Outlet /> */}
        </Route>
          
        <Route path="/productresponse" element={<ProductResponse searchKey={searchKeyFromURL}  />} />
        
        <Route path="/allproducts" element={<AllProducts  searchKey={searchKeyFromURL}  />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/signin" element={<Signin />} />
        <Route path="/admin-dashboard" element={<AdminDashboard />} />
        <Route path="/addproduct" element={<Product />} />
        <Route path="/products/:productId" element={<ProductDetails/>} />
        {/* <Route path="/addtocart/:productId" element = {<AddToCart/>}></Route> */}
        <Route path="/cart" element={<Cart />} />
        <Route path="/forgotpassword" element={<ForgotPassword />} />
        <Route path="/resetpassword" element={<ResetPassword />} />
        <Route path="/profile/:userName" element={< Profile/>} />
        <Route path="/allproducts/searchproduct" element={<ProductPopup/>} />

      </Routes>
    </Router>
  );
}

export default App;

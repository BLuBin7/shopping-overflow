import React, { useState } from "react";
import axios from "axios";

const ResetPasswordForm = () => {
  const [email, setEmail] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [newPasswordAgain, setNewPasswordAgain] = useState("");

  const handleSubmit = (e) => {
    const jwtToken = localStorage.getItem("jwtToken");

    e.preventDefault();
    axios
      .put(
        "http://localhost:8080/resetPass",
        {
          email: email,
          newPassword: newPassword,
          newPasswordAgain: newPasswordAgain,
        },
        {
          headers: {
          Authorization: `Bearer ${jwtToken}`,
            "Content-Type": "application/json",
          },
        }
      )
      .then((response) => {
        console.log(response.data); // Handle the response, show success message, etc.
      })
      .catch((error) => {
        console.error(error.response.data); // Handle the error, show error message, etc.
      });
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Email:</label>
        <input
          type="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
      </div>
      <div>
        <label>New Password:</label>
        <input
          type="password"
          value={newPassword}
          onChange={(e) => setNewPassword(e.target.value)}
          required
        />
      </div>
      <div>
        <label>Confirm New Password:</label>
        <input
          type="password"
          value={newPasswordAgain}
          onChange={(e) => setNewPasswordAgain(e.target.value)}
          required
        />
      </div>
      <button type="submit">Reset Password</button>
    </form>
  );
};

export default ResetPasswordForm;

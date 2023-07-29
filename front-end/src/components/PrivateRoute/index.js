import React from 'react';
import { Route, useNavigate } from 'react-router-dom';

function useAuth() {
  // Get the JWT token from local storage
  const jwtToken = localStorage.getItem('jwtToken');

  // Decode the JWT token to access the user information
  const decodedToken = jwtToken ? JSON.parse(atob(jwtToken.split('.')[1])) : null;
  const userRoles = decodedToken ? decodedToken.roles : [];

  return userRoles;
}
function PrivateRoute({ roles, ...props }) {
  const userRoles = useAuth();
  const navigate = useNavigate();

  if (!userRoles.some((role) => roles.includes(role))) {
    // Redirect to the signin page if the user does not have the required role(s)
    navigate('/signin');
    return null;
  }

  return <Route {...props} />;
}

export default PrivateRoute;
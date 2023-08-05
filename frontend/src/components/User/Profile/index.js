import React,{useState,useEffect } from 'react';
import { NavLink, useParams } from "react-router-dom";
import axios from 'axios';

function Profile(props) {
  const apiUrl = process.env.REACT_APP_API_URL;

    const { userName } = useParams();
    const [user, setUser] = useState(null);
    useEffect(() => {
        fetchProfileUser();
      }, [userName]);
    const fetchProfileUser = async () => {
        const jwtToken = localStorage.getItem('jwtToken');
        try {
          const response = await axios.get(`${apiUrl}/getProfile/${userName}`, {
            // headers: {
            //   Authorization: `Bearer ${jwtToken}`,
            //   'Content-Type': 'Application/json',
            // },
          });
          setUser(response.data);
        } catch (error) {
          console.error("Error fetching product details:", error);
        }
      };
      if (!user) {
        return <div>Loading...</div>;
      }
    return (
        <div>
            <p>Hi {user.userName}</p>
        </div>
    );
}

export default Profile;
// import React from 'react';
// import axios from 'axios';

// const fetchsendEmail = async (email) => {
//   const apiUrl = process.env.REACT_APP_API_URL;
//   try {
//     await axios.get(`${apiUrl}/sendemail`, {
//       params: { email }, // Pass the email as a query parameter
//     });

//     console.log("Email sent successfully.");
//   } catch (error) {
//     console.error("Error sending email:", error);
//   }
// };

// const ForgotPassword = () => {
//   const [email, setEmail] = React.useState('');

//   const handleEmailChange = (event) => {
//     setEmail(event.target.value);
//   };

//   const handleSendEmail = () => {
//     if (email) {
//       fetchsendEmail(email);
//     } else {
//       console.error("Email is required.");
//     }
//   };

//   return (
//     <div>
//       <p>Input email:</p>
//       <input type='text' value={email} onChange={handleEmailChange} />
//       <button onClick={handleSendEmail}>Send Email</button>
//     </div>
//   );
// };

// export default ForgotPassword;
// import React, { useState } from 'react';
// import axios from 'axios';

// const ForgotPassword = () => {
//   const [email, setEmail] = useState('');
//   const [subject, setSubject] = useState('');
//   const [body, setBody] = useState('');
//   const apiUrl = process.env.REACT_APP_API_URL; // Replace with your Spring Boot API URL

//   const handleEmailChange = (event) => {
//     setEmail(event.target.value);
//   };

//   const handleSubjectChange = (event) => {
//     setSubject(event.target.value);
//   };

//   const handleBodyChange = (event) => {
//     setBody(event.target.value);
//   };

//   const handleSendEmail = async () => {
//     const jwtToken = localStorage.getItem("jwtToken");

//     if (email && subject && body) {
//       try {
//         await axios.get(`${apiUrl}/sendemail`, {
//           params: {
//             toEmail: email,
//             subject: subject,
//             body: body,
//           },
//           headers: {
//             // Authorization: `Bearer ${jwtToken}`,
//             // "Content-Type": "application/json",
//           }
//         });

//         console.log("Email sent successfully.");
//       } catch (error) {
//         console.error("Error sending email:", error);
//       }
//     } else {
//       console.error("Please fill in all email details.");
//     }
//   };

//   return (
//     <div>
//       <p>Input email:</p>
//       <input type='text' value={email} onChange={handleEmailChange} />

//       <p>Input subject:</p>
//       <input type='text' value={subject} onChange={handleSubjectChange} />

//       <p>Input body:</p>
//       <textarea value={body} onChange={handleBodyChange} />

//       <button onClick={handleSendEmail}>Send Email</button>
//     </div>
//   );
// };

// export default ForgotPassword;


import React, { useState } from 'react';
import axios from 'axios';

const ForgotPassword = () => {

  const [email, setEmail] = useState('');
  const apiUrl = process.env.REACT_APP_API_URL; // Replace with your Spring Boot API URL

  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };

  const handleSendEmail = async () => {
    if (email) {
      try {
        await axios.post(
            `${apiUrl}/forgo`,
            { email: email },
            {
              headers: {
                "Content-Type": "application/json",
              },
            }
          );

        console.log("Password reset email sent successfully.");
      } catch (error) {
        console.error("Error sending password reset email:", error);
      }
    } else {
      console.error("Please enter your email address.");
    }
  };

  return (
    <div>
      <p>Input email:</p>
      <input type='text' value={email} onChange={handleEmailChange} />

      <button onClick={handleSendEmail}>Send Password Reset Email</button>
    </div>
  );
};

export default ForgotPassword;

import logo from './logo.svg';
// import './App.css';
import Header from './components/Header';
import Login from './components/Signup';
import Home from './components/Home';
import { Outlet,Route,Routes } from 'react-router-dom';
function App() {
  return (
    <>
      <Header />
      <Home/>
      <Outlet/>

      
    </>
  );
}

export default App;

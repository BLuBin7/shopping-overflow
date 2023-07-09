import "./App.css";
import logo from "./logo.svg";
// add modules bootstrap
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import Navbar from "./layout/Navbar";
import Home from "./pages/Home";
// test
import Login from "./pages/Login";

function App() {
  return (
    <>
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <p>Login</p>
        </header>
        {/* test */}
        <Login />

        <Navbar />
        <Home />
      </div>
    </>
  );
}

export default App;

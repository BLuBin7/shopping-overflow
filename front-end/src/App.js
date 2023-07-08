import './App.css';
// add modules bootstrap
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import Navbar from './layout/Navbar';
import Home from './pages/Home';
// test 
import Login from './Login.js';


function App() {
  return (
    <div className="App">
      {/* test */}
      <Login/>
      
      <Home />
      <Navbar />
    </div>
  );
}

export default App;

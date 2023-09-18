import React from 'react';
import './NavBar.css';

const NavBar = () => {
  return (
      <div className="navbar">
        <div className="navbar-logo">FinTech Social</div>
        <div className="navbar-links">
          <a href="/">Home</a>
          <a href="/login">Login</a>
          <a href="/signup">Signup</a>
        </div>
      </div>
  );
}

export default NavBar;
import React, { useState } from 'react';
import './SignupPage.css';

const SignupPage = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');

  const handleSignup = (e) => {
    e.preventDefault();
    // TODO:Add logic to check if passwords match
    if (password === confirmPassword) {
      // TODO: call your API to sign the user up
      console.log(email, password);
    } else {
      alert("Passwords don't match!");
    }
  }

  return (
      <div className="signup-container">
        <h2>Signup</h2>
        <form onSubmit={handleSignup}>
          <div className="input-group">
            <label>Email:</label>
            <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} required />
          </div>
          <div className="input-group">
            <label>Password:</label>
            <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} required />
          </div>
          <div className="input-group">
            <label>Confirm Password:</label>
            <input type="password" value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)} required />
          </div>
          <button type="submit">Signup</button>
        </form>
      </div>
  );
}

export default SignupPage;
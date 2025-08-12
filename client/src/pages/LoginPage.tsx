import { Link } from 'react-router-dom';
import '../App.css';

function LoginPage() {
  return (
    <div className="page-container">
      <div className="auth-form-container">
        <h2>Login to ChatY</h2>
        <form className="auth-form">
          <div className="form-group">
            <label htmlFor="email">Email or Username</label>
            <input type="text" id="email" name="email" placeholder="Enter your email or username" required />
          </div>
          <div className="form-group">
            <label htmlFor="password">Password</label>
            <input type="password" id="password" name="password" placeholder="Enter your password" required />
          </div>
          <button type="submit" className="btn btn-primary">Login</button>
        </form>
        <p className="auth-link-text">
          Don't have an account? <Link to="/signup" className="auth-link">Sign Up</Link>
        </p>
      </div>
    </div>
  );
}

export default LoginPage;
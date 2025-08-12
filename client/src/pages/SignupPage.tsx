import { Link } from 'react-router-dom';
import '../App.css';

function SignupPage() {
  return (
    <div className="page-container">
      <div className="auth-form-container">
        <h2>Sign Up for ChatY</h2>
        <form className="auth-form">
          <div className="form-group">
            <label htmlFor="email">Email or Username</label>
            <input type="text" id="email" name="email" placeholder="Enter your email or username" required />
          </div>
          <div className="form-group">
            <label htmlFor="password">Password</label>
            <input type="password" id="password" name="password" placeholder="Create a password" required />
          </div>
          <div className="form-group">
            <label htmlFor="confirm-password">Confirm Password</label>
            <input type="password" id="confirm-password" name="confirm-password" placeholder="Confirm your password" required />
          </div>
          <button type="submit" className="btn btn-primary">Sign Up</button>
        </form>
        <p className="auth-link-text">
          Already have an account? <Link to="/login" className="auth-link">Log In</Link>
        </p>
      </div>
    </div>
  );
}

export default SignupPage;
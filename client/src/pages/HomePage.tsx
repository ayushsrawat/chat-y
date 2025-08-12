import '../App.css';
import { Link } from 'react-router-dom';

function HomePage() {
  return (
    <div className="App">
      <header className="app-header">
        <div className="container">
          <h1 className="logo">ChatY</h1>
          <nav>
            <Link to="/login" className="btn btn-secondary">Log In</Link>
            <Link to="/signup" className="btn btn-primary">Sign Up</Link>
          </nav>
        </div>
      </header>

      <main>
        <section className="hero">
          <div className="container">
            <div className="hero-content">
              <h2>The ultimate chat experience.</h2>
              <p>
                Connect with friends, family, and colleagues seamlessly. ChatY offers
                reliable group and one-on-one messaging, keeping your conversations
                private and secure.
              </p>
              <Link to="/signup" className="btn btn-primary btn-lg">Get Started</Link>
            </div>
            <div className="hero-image">
              {/* Placeholder for an image or graphic */}
              <div className="placeholder-graphic"></div>
            </div>
          </div>
        </section>

        <section className="features">
          <div className="container">
            <div className="feature">
              <h3>Group Conversations</h3>
              <p>Stay in touch with the important people in your life.</p>
            </div>
            <div className="feature">
              <h3>Personal Messaging</h3>
              <p>Enjoy private and secure one-on-one conversations.</p>
            </div>
            <div className="feature">
              <h3>Works on Web</h3>
              <p>Access your chats right from your desktop browser.</p>
            </div>
          </div>
        </section>
      </main>

      <footer className="app-footer">
        <div className="container">
          <p>&copy; 2025 ChatY. All rights reserved.</p>
        </div>
      </footer>
    </div>
  );
}

export default HomePage;
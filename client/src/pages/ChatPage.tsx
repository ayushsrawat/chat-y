import '../App.css';
import { useNavigate } from 'react-router-dom';

function ChatPage() {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem('accessToken');
    localStorage.removeItem('refreshToken');
    navigate('/login');
  };

  return (
    <div className="App">
      <header className="app-header">
        <div className="container">
          <h1 className="logo">ChatY</h1>
          <nav>
            <button onClick={handleLogout} className="btn btn-secondary">Logout</button>
          </nav>
        </div>
      </header>

      <main>
        <div className="container">
          <h2>Welcome to the Chat!</h2>
          <p>This is where your conversations will appear.</p>
          {/* Chat interface components will go here */}
        </div>
      </main>
    </div>
  );
}

export default ChatPage;
import { useState } from 'react';
import { loginFormStyle } from '../styles/app.styles';
import type { LoginFormParams } from '../types/app.types';

export const LoginForm = ({ onLogin }: LoginFormParams) => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState<string | null>(null);

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    fetch("/auth/login", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ username, password })
    })
      .then(async res => {
        if (!res.ok) {
          const text = await res.text();
          throw new Error(`HTTP ${res.status}: ${text}`);
        }
        return res.json();
      })
      .then(data => {
        const jwt = data.token; // <-- extract the raw token
        onLogin(jwt, username);  // <-- pass only the token
      })
      .catch(err => setError(err.message));
  };

  return (
    <form onSubmit={handleSubmit} style={loginFormStyle}>
      <h2>Login</h2>
      {error && <p style={{ color: 'red' }}>{error}</p>}
      <input type="text" placeholder="Username" value={username} onChange={e => setUsername(e.target.value)} />
      <input type="password" placeholder="Password" value={password} onChange={e => setPassword(e.target.value)} />
      <button type="submit">Login</button>
    </form>
  );
};
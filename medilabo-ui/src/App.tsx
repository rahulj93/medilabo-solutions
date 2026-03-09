import { useEffect, useState } from 'react';
import './App.css';
import { Patients } from './components/Patients';
import { Notes } from './components/Notes';
import { DiabetesReport } from './components/DiabetesReport';
import { LoginForm } from './components/LoginForm';

function App() {
  const [token, setToken] = useState<string | null>(null);
  const [user, setUser] = useState<string | null>(null);
  const [loading, setLoading] = useState(true);
  const [patientNotes, setPatientNotes] = useState<any>({});
  const [diabetesReport, setDiabetesReport] = useState<any>({});

  // Load token from localStorage on app start
  useEffect(() => {
    const savedToken = localStorage.getItem('jwt');
    if (savedToken) setToken(savedToken);
    else setLoading(false);
    setPatientNotes({});
    setDiabetesReport({});
  }, []);

  // Fetch current user whenever token changes
  useEffect(() => {
    if (!token) {
      setUser(null);
      setLoading(false);
      return;
    }

    fetch('/api/user/me', {
      headers: { 'Authorization': `Bearer ${token}` }
    })
      .then(res => {
        if (!res.ok) {
          setToken(null);
          localStorage.removeItem('jwt');
          setUser(null);
          setLoading(false);
          return;
        }
        return res.text();
      })
      .then(username => {
        if (username) setUser(username);
        setLoading(false);
      })
      .catch(() => {
        setToken(null);
        localStorage.removeItem('jwt');
        setUser(null);
        setLoading(false);
      });
  }, [token]);

  const handleLoadNotes = (id: string) => {
    if (!token) return;
    fetch(`/notes?id=${id}`, {
      headers: { 'Authorization': `Bearer ${token}` }
    })
      .then(res => res.json())
      .then(data => setPatientNotes({ id, ...data })
      );
  };

  const handleAddNewNote = async (id: string, patient: string, note: string) => {
    const payload = { id, patient, notes: [note] }
    const response = await fetch(`/notes/${id}`, {
      method: 'POST',
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(payload)
    })

    const data = await response.json();
    console.log(data);
    setPatientNotes(data);
  }

  const handleLoadDiabetesReport = (id: string) => {
    if (!token) return;
    fetch(`/risk-assessment/diabetes-report/${id}`, {
      headers: { 'Authorization': `Bearer ${token}` }
    })
      .then(res => res.json())
      .then(data => setDiabetesReport(data));
  };

  const handleLogout = () => {
    setToken(null);
    setUser(null);
    localStorage.removeItem('jwt');
  };

  if (loading) return <div>Loading Medilabo Solutions...</div>;

  return user ? (
    <div style={{ display: 'flex', flexDirection: 'column' }}>
      <button onClick={handleLogout} style={{ alignSelf: 'flex-end' }}>Logout</button>
      <h1>Medilabo Solutions</h1>
      <Patients handleLoadNotes={handleLoadNotes} handleLoadDiabetesReport={handleLoadDiabetesReport} />
      <Notes patientNotes={patientNotes} handleAddNewNote={handleAddNewNote} />
      <DiabetesReport diabetesReport={diabetesReport} />
    </div>
  ) : (
    <LoginForm onLogin={(jwt, username) => {
      setToken(jwt);
      localStorage.setItem('jwt', jwt);
      setUser(username);
    }} />
  );
}

export default App;


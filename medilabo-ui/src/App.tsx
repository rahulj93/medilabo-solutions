import { useEffect, useState } from 'react'
import './App.css'

function App() {
  const [patients, setPatients] = useState<any>({});
  const [notes, setNotes] = useState<any[]>([]);

  useEffect(() => {
    // Fetch patient by query params
    fetch("/patient?firstName=John&lastName=Doe")
      .then(res => res.json())
      .then(data => {
        console.log("Patient:", data)
        setPatients(data);
      });

    // Fetch all notes
    fetch("/notes")
      .then(res => res.json())
      .then(data => {
        console.log("Notes:", data)
        setNotes(data);
      });
  }, [])

  return (
    <>
      <h1>Medilabo Solutions</h1>
      <div style={{ width: '70vw', display: 'flex', flexDirection: 'row', justifyContent: 'space-between' }}>
        <form onSubmit={() => { }}>
          <h2>Patient</h2>
          <div style={{ display: 'flex', flexDirection: 'column' }}>
            <label>
              Last Name:
              <input type="text" value={undefined} onChange={() => { }} placeholder="Enter patient's last name" />
            </label>
            <label>
              First Name:
              <input type="text" value={undefined} onChange={() => { }} placeholder="Enter patient's first name" />
            </label>
            <label>
              Date of birth:
              <input type="text" value={undefined} onChange={() => { }} placeholder="Enter patient's date of birth" />
            </label>
            <label>
              Gender:
              <input type="text" value={undefined} onChange={() => { }} placeholder="Enter patient name's gender" />
            </label>
            <label>
              Address:
              <input type="text" value={undefined} onChange={() => { }} placeholder="Enter patient's address" />
            </label>
            <label>
              Phone:
              <input type="text" value={undefined} onChange={() => { }} placeholder="Enter patient's phone number" />
            </label>
          </div>
          <button type="submit">Lookup</button>
          <button type="submit">Add</button>
          <button type="submit">Edit</button>
          <button type="submit">Delete</button>
        </form>
        <form onSubmit={() => { }}>
          <h2>Note</h2>
          <div style={{ display: 'flex', flexDirection: 'column' }}>
            <label>
              ID:
              <input type="text" value={undefined} onChange={() => { }} placeholder="Enter ID" />
            </label>
            <label>
              Patient Name:
              <input type="text" value={undefined} onChange={() => { }} placeholder="Enter patient's full name" />
            </label>
            <label>
              Note:
              <input type="text" value={undefined} onChange={() => { }} placeholder="Enter your note" />
            </label>
            <label>
              patId:
              <input type="text" value={undefined} onChange={() => { }} placeholder="Enter patient's ID" />
            </label>
          </div>
          <button type="submit">Lookup</button>
          <button type="submit">Add</button>
          <button type="submit">Edit</button>
          <button type="submit">Delete</button>
        </form>
      </div>
      <div>
      {patients.lastName}, {patients.firstName} , {patients.dateOfBirth}, {patients.gender}, {patients.address}, {patients.phone}
      </div>
      <div>
        {notes.map(note => (
          <div>{note.patient}:  {note.note}</div>
        ))}
      </div>
    </>
  )
}

export default App

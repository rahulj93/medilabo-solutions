import { useEffect, useState } from 'react'
import './App.css'
import { Patients } from './components/Patients';
import { Notes } from './components/Notes';
import { DiabetesReport } from './components/DiabetesReport';

function App() {
  // const [patients, setPatients] = useState<any>({});
  // const [notes, setNotes] = useState<any[]>([]);

  // useEffect(() => {
  //   // Fetch patient by query params
  //   // fetch("/patient?firstName=John&lastName=Doe")
  //   //   .then(res => res.json())
  //   //   .then(data => {
  //   //     console.log("Patient:", data)
  //   //     setPatients(data);
  //   //   });

  //   // Fetch all notes
  //   // fetch("/notes")
  //   //   .then(res => res.json())
  //   //   .then(data => {
  //   //     console.log("Notes:", data)
  //   //     setNotes(data);
  //   //   });
  // }, [])

  // const handleAddNew = () => {} 

  // const columns = ['last_name', 'first_name', 'date_of_birth', 'gender', 'address', 'phone']
  // const columns = ['patient', 'note', 'patId']

  // const handleChange = (a,b) => {} 
  // const saveEdit = () => {} 
  // const cancelEdit = () => {} 
  // const startEdit = (x) => {} 
  // const handleDelete = (x) => {} 

  // const [rows, setRows] = useState(notes);
  // // const [editingId, setEditingId] = useState(null);
  // // const [draftRow, setDraftRow] = useState<any>({});

  // useEffect(() => {
  //   setRows(notes)
  // },[notes])

  return (
    <div style={{display:' flex', flexDirection: 'column'}}>
      <Patients/>
      <Notes/>
      <DiabetesReport/>
    </div>
  )

  // return (
  //   <div>
  //   <button onClick={handleAddNew}>Add Row</button>

  //   <table style={{ borderCollapse: "collapse", width: "100%" }}>
  //     <thead>
  //       <tr>
  //         {columns.map((col) => (
  //           <th key={col} style={{ border: "1px solid #ccc", padding: 8 }}>
  //             {col}
  //           </th>
  //         ))}
  //         <th style={{ border: "1px solid #ccc", padding: 8 }}>
  //           Actions
  //         </th>
  //       </tr>
  //     </thead>

  //     <tbody>
  //       {rows.map((row: any) => {
  //         const isEditing = editingId === row.id;

  //         return (
  //           <tr key={row.id}>
  //             {columns.map((col) => (
  //               <td key={col} style={{ border: "1px solid #ccc", padding: 8 }}>
  //                 {isEditing ? (
  //                   <input
  //                     value={draftRow[col]}
  //                     onChange={(e) =>
  //                       handleChange(col, e.target.value)
  //                     }
  //                   />
  //                 ) : (
  //                   row[col]
  //                 )}
  //               </td>
  //             ))}

  //             <td style={{ border: "1px solid #ccc", padding: 8 }}>
  //               {isEditing ? (
  //                 <>
  //                   <button onClick={saveEdit}>Save</button>
  //                   <button onClick={cancelEdit}>Cancel</button>
  //                 </>
  //               ) : (
  //                 <>
  //                   <button onClick={() => startEdit(row)}>Edit</button>
  //                   <button onClick={() => handleDelete(row.id)}>
  //                     Delete
  //                   </button>
  //                 </>
  //               )}
  //             </td>
  //           </tr>
  //         );
  //       })}
  //     </tbody>
  //   </table>
  // </div>
  // )

  // return (
  //   <>
  //     <h1>Medilabo Solutions</h1>
  //     <div style={{ width: '70vw', display: 'flex', flexDirection: 'row', justifyContent: 'space-between' }}>
  //       <form onSubmit={() => { }}>
  //         <h2>Patient</h2>
  //         <div style={{ display: 'flex', flexDirection: 'column' }}>
  //           <label>
  //             Last Name:
  //             <input type="text" value={undefined} onChange={() => { }} placeholder="Enter patient's last name" />
  //           </label>
  //           <label>
  //             First Name:
  //             <input type="text" value={undefined} onChange={() => { }} placeholder="Enter patient's first name" />
  //           </label>
  //           <label>
  //             Date of birth:
  //             <input type="text" value={undefined} onChange={() => { }} placeholder="Enter patient's date of birth" />
  //           </label>
  //           <label>
  //             Gender:
  //             <input type="text" value={undefined} onChange={() => { }} placeholder="Enter patient name's gender" />
  //           </label>
  //           <label>
  //             Address:
  //             <input type="text" value={undefined} onChange={() => { }} placeholder="Enter patient's address" />
  //           </label>
  //           <label>
  //             Phone:
  //             <input type="text" value={undefined} onChange={() => { }} placeholder="Enter patient's phone number" />
  //           </label>
  //         </div>
  //         <button type="submit">Lookup</button>
  //         <button type="submit">Add</button>
  //         <button type="submit">Edit</button>
  //         <button type="submit">Delete</button>
  //       </form>
  //       <form onSubmit={() => { }}>
  //         <h2>Note</h2>
  //         <div style={{ display: 'flex', flexDirection: 'column' }}>
  //           <label>
  //             ID:
  //             <input type="text" value={undefined} onChange={() => { }} placeholder="Enter ID" />
  //           </label>
  //           <label>
  //             Patient Name:
  //             <input type="text" value={undefined} onChange={() => { }} placeholder="Enter patient's full name" />
  //           </label>
  //           <label>
  //             Note:
  //             <input type="text" value={undefined} onChange={() => { }} placeholder="Enter your note" />
  //           </label>
  //           <label>
  //             patId:
  //             <input type="text" value={undefined} onChange={() => { }} placeholder="Enter patient's ID" />
  //           </label>
  //         </div>
  //         <button type="submit">Lookup</button>
  //         <button type="submit">Add</button>
  //         <button type="submit">Edit</button>
  //         <button type="submit">Delete</button>
  //       </form>
  //     </div>
  //     <div>
  //     {patients.lastName}, {patients.firstName} , {patients.dateOfBirth}, {patients.gender}, {patients.address}, {patients.phone}
  //     </div>
  //     <div>
  //       {notes.map(note => (
  //         <div>{note.patient}:  {note.note}</div>
  //       ))}
  //     </div>
  //   </>
  // )
}

export default App

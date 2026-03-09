import { useState } from "react"
import type { NotesParams } from "../types/app.types";
import { notesPageStyle } from "../styles/app.styles";

export const Notes = ({ patientNotes: { id, patient, notes }, handleAddNewNote }: NotesParams) => {
  const [newNote, setNewNote] = useState<string>("");

  const onChangeNewNote = (e: any) => {
    e.preventDefault();
    setNewNote(e.target.value);
  }

  const onSubmitNewNote = (e: any) => {
    e.preventDefault();
    console.log(id, patient, newNote)
    handleAddNewNote(id, patient, newNote);
    setNewNote("");
  }

  return (
    <div style={notesPageStyle}>
      {/* <br/> */}
      <h3>Notes for {patient || "[Unknown Patient]"}:</h3>
      {!notes && <div>No notes found</div>}
      <div key={id}>
        <ul style={{ margin: 0, paddingLeft: 20, listStylePosition: 'inside' }}>
          {notes?.map((note: string, index: number) => <li key={index}>{note}</li>)}
          <li>
            <form onSubmit={onSubmitNewNote} style={{ display: "inline-flex", gap: "6px" }}>
              <input
                value={newNote} placeholder="Add new note..." onChange={onChangeNewNote}
                style={{
                  height: "20px",
                  padding: "2px 6px",
                  fontSize: "12px"
                }} />
              <button type="submit" title="Add New Note">Add</button>
            </form>
          </li>
        </ul>
      </div>
    </div>
  )
}
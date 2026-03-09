import { useState, useEffect, type CSSProperties, useCallback } from "react";

const buttonStyle = {
  fontSize: "12px",
  padding: "2px 6px",
  lineHeight: 1.2,
  cursor: "pointer"
}

export interface Patient {
  id?: string,
  name?: string,
  dateOfBirth?: string,
  address?: string,
  gender?: string,
  phone?: string
}

export interface InputBoxParams {
  row: Patient,
  col: keyof Patient,
  rowState: Patient,
  handleChangeInput: (b: any, c: any) => void
}

export const tableRowStyle: CSSProperties = {
  border: "1px solid #ccc",
  padding: 8,
  boxSizing: "border-box"
}

const InputBox = ({ row, col, rowState, handleChangeInput }: InputBoxParams) => {
  // console.log(col, row[col])
  return (
    <input
      style={{ width: '100%', boxSizing: 'border-box' }}
      value={rowState[col] ?? ""}
      onChange={(e) => {
        console.log(row.id);
        handleChangeInput(col, e.target.value);
      }}
    />
  )
}

export const Patients = ({ handleLoadNotes, handleLoadDiabetesReport }: {
  handleLoadNotes: (id: string) => void;
  handleLoadDiabetesReport: (id: string) => void;
}) => {
  // const API_BASE = 'http://localhost:20000'; 
  // const API_BASE = import.meta.env.VITE_API_GATEWAY_URL; 
  // const API_BASE = ''; 
  // const API_BASE = 'http://gateway-api:20000'; 
  const API_BASE = window.location.hostname === 'localhost' 
  ? 'http://localhost:20000' 
  : 'http://gateway-api:20000';
  const [patients, setPatients] = useState<any[]>([]);
  const [editingId, setEditingId] = useState<string | undefined>(undefined);
  const [rowToEdit, setRowToEdit] = useState<Patient>({});
  const [isAddNewClicked, setIsAddNewClicked] = useState<boolean>(false);
  const [newRow, setNewRow] = useState<Patient>({});
  const [isSaved, setIsSaved] = useState<boolean>(true);

  const fetchPatients = useCallback(() => {
    fetch(`${API_BASE}/patients`)
      .then(res => res.json())
      .then(data => {
        setPatients(data
          .sort((a: { id: any; }, b: { id: any; }) => Number(a.id) - Number(b.id))
          .map(({ id, lastName, firstName, dateOfBirth, address, gender, phone }: {
            id: string,
            lastName: string,
            firstName: string,
            dateOfBirth: string,
            address: string,
            gender: string,
            phone: string
          }) => ({ id, name: `${firstName} ${lastName}`, dateOfBirth, address, gender, phone })
          ));
      });
  }, [])

  useEffect(() => {
    fetchPatients();
  }, [fetchPatients])

  const handleAddNew = () => {
    {
      setIsAddNewClicked(true)
      setIsSaved(false);
      setNewRow({});
    }
  }

  const handleNewInput = (field: keyof Patient, value: string) => setNewRow({ ...newRow, [field]: value }); 

  const saveNew = async () => {
    const newPatient = {
      firstName: newRow.name?.split(' ')[0], 
      lastName: newRow.name?.split(' ')[1],
      ...newRow
    }
    await fetch(`${API_BASE}/patient`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(newPatient)
    });
    setIsSaved(true);
    setIsAddNewClicked(false);
    setNewRow({});
    fetchPatients();
  };

  const cancelNew = () => {
    setIsSaved(true)
    setIsAddNewClicked(false)
  }

  const columns = ['id', 'name', 'gender', 'dateOfBirth', 'address', 'phone']

  const startEdit = (row: Patient) => {
    setEditingId(row.id);
    setRowToEdit(row);
  }

  const handleEditInput = (field: keyof Patient, value: string) => setRowToEdit({ ...rowToEdit, [field]: value }); 

  const saveEdit = async (_row: Patient) => {
    if (!rowToEdit.id) return;

    const updatedPatient = {
      firstName: rowToEdit.name?.split(' ')[0], 
      lastName: rowToEdit.name?.split(' ')[1],
      ...rowToEdit
    }

    try {
      const response = await fetch(`${API_BASE}/patient/${updatedPatient.id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(updatedPatient)
      })
      if (!response.ok) {
        throw new Error(`Failed to update patient: ${response.status}`);
      }
      setEditingId(undefined)
      setRowToEdit({});
      fetchPatients();
    } catch (error) {
      console.error(error);
    }
  }

  const cancelEdit = () => {
    setEditingId(undefined)
    setRowToEdit({})
  }

  const handleDelete = async (id: string) => {
    if (!id) return; 

    const confirmDelete = window.confirm(`Are you sure you want to delete Patient # ${id}?`)
    if (!confirmDelete) return; 

    try {
      const response = await fetch(`${API_BASE}/patient/${id}`, {method: 'DELETE'}); 
      if (!response.ok) {
        throw new Error(`Failed to delete patient: ${response.status}`)
      }
      fetchPatients();
    } catch (error) {
      console.error(error); 
    }
  }

  return (
    <div>
      <table style={{ borderCollapse: "collapse", width: "940px", tableLayout: 'fixed', fontSize: '14px' }}>
        <thead>
          <tr>
            <th colSpan={columns.length + 1} style={{ border: "1px solid #ccc", padding: 8 }}>
              <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center" }}>
                <span style={{ fontSize: 24 }}>Patients</span>
                <button onClick={handleAddNew} title="Add New Patient">➕</button>
              </div>
            </th>
          </tr>
          <tr>
            {columns.map((col) => (
              <th key={col} style={{ ...tableRowStyle, width: '90px' }}>
                {col}
              </th>
            ))}
            <th style={{ ...tableRowStyle, width: '400px' }}>
              Actions
            </th>
          </tr>
        </thead>

        <tbody>
          {patients.map((row: any) => {
            const isEditing = editingId === row.id;
            return (
              <tr key={row.id}>
                {columns.map((col) => (
                  <td key={col} style={tableRowStyle}>
                    {isEditing && col !== 'id' ? (
                      <InputBox row={row} col={col as keyof Patient} rowState={rowToEdit} handleChangeInput={handleEditInput} />
                    ) : (
                      row[col]
                    )}
                  </td>
                ))}
                <td style={tableRowStyle}>
                  {isEditing ? (
                    <div style={{ display: 'flex', flexDirection: 'row', gap: 4 }}>
                      <button onClick={() => saveEdit(row)} style={buttonStyle}>Save</button>
                      <button onClick={cancelEdit} style={buttonStyle}>Cancel</button>
                    </div>
                  ) : (
                    <div style={{ display: 'flex', flexDirection: 'row', gap: 4, flexWrap: 'wrap' }}>
                      <button onClick={() => startEdit(row)} style={buttonStyle} title="Edit">
                        ✏️
                      </button>
                      <button onClick={() => handleDelete(row.id)} style={buttonStyle} title="Delete">
                        🗑️
                      </button>
                      <button onClick={() => handleLoadNotes(row.id)} style={buttonStyle} title="Load Notes">
                        📝
                      </button>
                      <button onClick={() => handleLoadDiabetesReport(row.id)} style={buttonStyle} title="Diabetes Risk Report">
                        📊
                      </button>
                    </div>
                  )}
                </td>
              </tr>
            );
          })}
          {isAddNewClicked && (
            <tr>
              {columns.map((col) => (
                <td key={col} style={tableRowStyle}>
                  {!isSaved && col !== 'id' && (
                    <InputBox
                      row={{}}
                      col={col as keyof Patient}
                      rowState={newRow}
                      handleChangeInput={handleNewInput}
                    />
                  )}
                </td>
              ))}
              <td style={tableRowStyle}>
                {!isSaved && (
                  <div style={{ display: 'flex', flexDirection: 'row', gap: 4 }}>
                    <button onClick={saveNew} style={buttonStyle}>Save</button>
                    <button onClick={cancelNew} style={buttonStyle}>Cancel</button>
                  </div>
                )}
              </td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  )
}
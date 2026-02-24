import { useState, useEffect } from "react";


const buttonStyle = {
    fontSize: "12px",
    padding: "2px 6px",
    lineHeight: 1.2,
    cursor: "pointer"
  }

export const Patients = ({handleLoadNotes, handleLoadDiabetesReport} : {
    handleLoadNotes: (id: string) => void; 
    handleLoadDiabetesReport: (id: string) => void;
}) => {
    const [patients, setPatients] = useState<any[]>([]);
    const [isSaved, setIsSaved] = useState<boolean>(true); 
    const [isAddNewClicked, setIsAddNewClicked] = useState<boolean>(false); 


    useEffect(() => {
        // Fetch patient by query params
        // fetch("/patient?firstName=John&lastName=Doe")
        //   .then(res => res.json())
        //   .then(data => {
        //     console.log("Patient:", data)
        //     setPatients(data);
        //   });    
        fetch("/patients")
            .then(res => res.json())
            .then(data => {
                console.log("Patient:", data)
                setPatients(data.map(({id, lastName, firstName, dateOfBirth, address, gender, phone}: {
                    id: string,
                    lastName: string,
                    firstName: string,
                    dateOfBirth: string,
                    address: string,
                    gender: string,
                    phone: string  
                }) => ({id, name: `${firstName} ${lastName}`, dateOfBirth, address, gender, phone})
            ));
            });
    }, [])

    const handleAddNew = () => { {
        setIsAddNewClicked(true)
        setIsSaved(false);
    }}
    const saveNew = () => {
        setIsSaved(true)
        setIsAddNewClicked(false)
    }
    const cancelNew = () => {
        setIsSaved(true)
        setIsAddNewClicked(false)
    }

    // const columns = ['last_name', 'first_name', 'date_of_birth', 'gender', 'address', 'phone']
    //   const columns = ['id', 'lastName', 'firstName']
    const columns = ['id', 'name', 'gender', 'dateOfBirth', 'address', 'phone']

    const handleChange = (a, b) => { }
    const saveEdit = () => { }
    const cancelEdit = () => {setEditingId(null)}
    const startEdit = (x) => { setEditingId(x.id)}
    const handleDelete = (x) => { }

    const [editingId, setEditingId] = useState(null);
    const [draftRow, setDraftRow] = useState<any>({});
    const [rows, setRows] = useState(patients);

    useEffect(() => setRows(patients), [patients]);



    return (
        <div>
            {/* <h3>Patients</h3> */}
            {/* <div> */}
                {/* <button onClick={handleAddNew}>Add New Patient</button> */}

                <table style={{ borderCollapse: "collapse", width: "940px", tableLayout: 'fixed', fontSize: '14px' }}>
                    <thead>
                        <tr>
                            <th colSpan={columns.length + 1} style={{ border: "1px solid #ccc", 
                            padding: 8, 
                            // textAlign: 'left', fontSize: 24
                            }}>
                                {/* Patients
                                <button onClick={handleAddNew}>Add New Patient</button> */}
                                <div style={{display: "flex", justifyContent: "space-between", alignItems: "center"}}>
                                    <span style={{ fontSize: 24 }}>Patients</span>
                                    {/* <button onClick={handleAddNew}>Add New Patient</button> */}
                                    <button onClick={handleAddNew} title="Add New Patient">➕</button>
                                </div>
                            </th>

                        </tr>
                        <tr>
                            {columns.map((col) => (
                                <th key={col} style={{ border: "1px solid #ccc", padding: 8, width: '90px' }}>
                                    {col}
                                </th>
                            ))}
                            <th style={{ border: "1px solid #ccc", padding: 8, width: '400px' }}>
                                Actions
                            </th>
                        </tr>
                    </thead>

                    <tbody>
                        {rows.map((row: any) => {
                            const isEditing = editingId === row.id;

                            return (
                                <tr key={row.id}>
                                    {columns.map((col) => (
                                        <td key={col} style={{ border: "1px solid #ccc", padding: 8}}>
                                            {isEditing ? (
                                                <input
                                                    style={{width: '100%', boxSizing: 'border-box'}}
                                                    value={draftRow[col]}
                                                    onChange={(e) =>
                                                        handleChange(col, e.target.value)
                                                    }
                                                />
                                            ) : (
                                                row[col]
                                            )}
                                        </td>
                                    ))}

                                    <td style={{ border: "1px solid #ccc", padding: 8}}>
                                        {isEditing ? (
                                            <div 
                                            style={{display: 'flex', flexDirection: 'row', gap: 4}}
                                            // style={{display: 'flex', flexDirection: 'column', gap: 4}}
                                            >
                                                <button onClick={saveEdit} style={buttonStyle}>Save</button>
                                                <button onClick={cancelEdit} style={buttonStyle}>Cancel</button>
                                            </div>
                                        ) : (
                                            <div 
                                            style={{display: 'flex', flexDirection: 'row', gap: 4, flexWrap: 'wrap'}}
                                            // style={{display: 'flex', flexDirection: 'column', gap: 4}}
                                            >
                                                <button onClick={() => startEdit(row)} style={buttonStyle} title="Edit">
                                                    {/* Edit */}
                                                    ✏️
                                                    </button>
                                                <button onClick={() => handleDelete(row.id)} style={buttonStyle} title="Delete">
                                                    {/* Delete */}
                                                    🗑️
                                                </button>
                                                <button onClick={() => handleLoadNotes(row.id)} style={buttonStyle} title="Load Notes">
                                                    {/* Load Notes */}
                                                    📝
                                                </button>
                                                <button onClick={() => handleLoadDiabetesReport(row.id)} style={buttonStyle} title="Diabetes Report">
                                                    {/* Load Diabetes Risk Report */}
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
                                        <td key={col} style={{ border: "1px solid #ccc", padding: 8 }}>
                                            {!isSaved && (
                                                <input
                                                    style={{width: '100%', boxSizing: 'border-box'}}
                                                    value={draftRow[col]}
                                                    onChange={(e) =>
                                                        handleChange(col, e.target.value)
                                                    }
                                                />
                                            )}
                                        </td>
                                    ))}

                                <td style={{ border: "1px solid #ccc", padding: 8 }}>
                                        {!isSaved && (
                                            <>
                                                <button onClick={saveNew}>Save</button>
                                                <button onClick={cancelNew}>Cancel</button>
                                            </>
                                        ) }
                                    </td>

                            </tr>
                        )}
                    </tbody>
                </table>
            {/* </div> */}
        </div>
    )
}
import { useState, useEffect } from "react";


export const Patients = ({handleLoadNotes, handleLoadDiabetesReport} : {
    handleLoadNotes: (id: string) => void; 
    handleLoadDiabetesReport: (id: string) => void;
}) => {
    const [patients, setPatients] = useState<any[]>([]);


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

    const handleAddNew = () => { }

    // const columns = ['last_name', 'first_name', 'date_of_birth', 'gender', 'address', 'phone']
    //   const columns = ['id', 'lastName', 'firstName']
    const columns = ['id', 'name', 'gender', 'dateOfBirth', 'address', 'phone']

    const handleChange = (a, b) => { }
    const saveEdit = () => { }
    const cancelEdit = () => { }
    const startEdit = (x) => { }
    const handleDelete = (x) => { }

    const [editingId, setEditingId] = useState(null);
    const [draftRow, setDraftRow] = useState<any>({});
    const [rows, setRows] = useState(patients);

    useEffect(() => setRows(patients), [patients]);



    return (
        <div>
            <h3>Patients</h3>
            <div>
                <button onClick={handleAddNew}>Add Row</button>

                <table style={{ borderCollapse: "collapse", width: "100%" }}>
                    <thead>
                        <tr>
                            {columns.map((col) => (
                                <th key={col} style={{ border: "1px solid #ccc", padding: 8 }}>
                                    {col}
                                </th>
                            ))}
                            <th style={{ border: "1px solid #ccc", padding: 8 }}>
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
                                        <td key={col} style={{ border: "1px solid #ccc", padding: 8 }}>
                                            {isEditing ? (
                                                <input
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

                                    <td style={{ border: "1px solid #ccc", padding: 8 }}>
                                        {isEditing ? (
                                            <>
                                                <button onClick={saveEdit}>Save</button>
                                                <button onClick={cancelEdit}>Cancel</button>
                                            </>
                                        ) : (
                                            <>
                                                <button onClick={() => startEdit(row)}>Edit</button>
                                                <button onClick={() => handleDelete(row.id)}>
                                                    Delete
                                                </button>
                                                <button onClick={() => handleLoadNotes(row.id)}>
                                                    Load Notes
                                                </button>
                                                <button onClick={() => handleLoadDiabetesReport(row.id)}>
                                                    Load Diabetes Risk Report
                                                </button>
                                            </>
                                        )}
                                    </td>
                                </tr>
                            );
                        })}
                    </tbody>
                </table>
            </div>
        </div>
    )
}
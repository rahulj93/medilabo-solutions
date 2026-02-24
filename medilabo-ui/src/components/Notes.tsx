import { useState, useEffect } from "react";

export const Notes = ({patientNotes: {id, patient, notes}} : {patientNotes: any}) => {
    // const [notes, setNotes] = useState<any[]>([]);


    // useEffect(() => {
    //     // Fetch all notes
    //     fetch("/notes")
    //         .then(res => res.json())
    //         .then(data => {
    //             console.log("Notes:", data)
    //             setNotes(data);
    //         });
    // }, [])

    // useEffect(() => console.log(notes), [notes])


    return (
        <div style={{textAlign: 'left', fontSize: 14, width: '900px'}}>
            <h3>Notes for {patient}: </h3>
            {!notes && <div>No notes found</div>}
                <div key={id}>
                    <ul style={{margin: 0, paddingLeft: 20, listStylePosition: 'inside'}}>
                        {notes?.map((note: string, index: string) => <li key={index}>{note}</li>)}
                    </ul>
                </div>
            {notes?.length > 0 && null
            // (
            //     <table style={{
            //         // border: "1px solid #ccc", 
            //         borderCollapse: "collapse",
            //         // width: "75%"
            //         }}>
            //         <thead>
            //             <tr>
            //                 {/* <th style={{ border: "1px solid #ccc"}}>Timestamp</th> */}
            //                 <th style={{ border: "1px solid #ccc"}}>Notes for {patient}: </th>
            //             </tr>
            //         </thead>
            //         <tbody>
            //             {notes?.map((note: string, index: string) => (
            //                 <tr key={index}>
            //                     {/* <td>{new Date().getTime()}</td> */}
            //                     <td style={{ border: "1px solid #ccc"}}>{note}</td>
            //                 </tr>)
            //             )}
            //         </tbody>
            //     </table>
            // )
            }
        </div>
    )
}
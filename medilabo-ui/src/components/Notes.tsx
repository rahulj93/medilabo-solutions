import { useState, useEffect } from "react";

export const Notes = ({patientNotes: {id, notes}} : {patientNotes: any}) => {
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
        <div>
            <h3>Notes</h3>
            {/* {patientNotes?.notes?.length === 0 && <div>No notes found</div>} */}

            {/* {patientNotes.notes.map(({ id, notes }: {id: string, notes: string[]}) => ( */}
                <div key={id}>
                    <ul style={{margin: 0, paddingLeft: 20, listStylePosition: 'inside'}}>
                        {notes?.map((note: string, index: string) => <li key={index}>{note}</li>)}
                    </ul>
                </div>
            {/* ))} */}

            {/* {notes.map(({ id, notes }) => (
                <div key={id}>
                    <ul style={{margin: 0, paddingLeft: 20, listStylePosition: 'inside'}}>
                        {notes?.map((note: string) => <li>{note}</li>)}
                    </ul>
                </div>
            ))} */}
        </div>
    )
}
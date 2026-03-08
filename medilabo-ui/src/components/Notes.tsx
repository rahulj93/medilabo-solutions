export const Notes = ({patientNotes: {id, patient, notes}} : {patientNotes: any}) => {
    return (
        <div style={{textAlign: 'left', fontSize: 14, width: '900px'}}>
            {/* <br/> */}
            <h3>Notes for {patient}: </h3>
            {!notes && <div>No notes found</div>}
                <div key={id}>
                    <ul style={{margin: 0, paddingLeft: 20, listStylePosition: 'inside'}}>
                        {notes?.map((note: string, index: string) => <li key={index}>{note}</li>)}
                    </ul>
                </div>
        </div>
    )
}
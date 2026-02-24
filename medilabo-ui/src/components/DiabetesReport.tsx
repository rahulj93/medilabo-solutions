

export const DiabetesReport = ({diabetesReport: {patientName, riskLevel}}: {diabetesReport: any}) => {
    return (
        <div>
            <h3>Diabetes Report</h3>
            {patientName ? (
                <div>                    
                <p>{patientName} is <b>{riskLevel}</b> for having diabetes.</p>
                <p>Note: Risk level is  determined based on several factors including: number of trigger terms found in the patients' notes, the patient's age, and the patient's gender. </p>
                </div>
            ): <p>There is not enough information to determine this patient's risk level</p>}
        </div>
    )
}
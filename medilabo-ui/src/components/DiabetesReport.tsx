import { diabetesReportStyle } from "../styles/app.styles"
import type { DiabetesReportParams } from "../types/app.types"

export const DiabetesReport = ({ diabetesReport: { patientName, riskLevel } }: DiabetesReportParams) => {
  return (
    <div style={diabetesReportStyle}>
      {patientName ? (
        <div>
          <h3>Diabetes Report for {patientName}: </h3>
          {patientName && (
            <div>
              <p>{patientName} is at <b>Risk Level: {riskLevel}</b> for having diabetes.</p>
              <p>Note: Risk level is  determined based on several factors including: number of trigger terms found in the patients' notes, the patient's age, and the patient's gender. </p>
            </div>
          )}
        </div>
      ) : (
        <div>
          <h3>Diabetes Report:</h3>
          <p>There is not enough information to determine the patient's risk level at this time.</p>
        </div>
      )}
    </div>
  )
}
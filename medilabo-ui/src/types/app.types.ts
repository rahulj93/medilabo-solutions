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

export interface NotesParams {
  patientNotes: {
    id: string,
    patient: string,
    notes: string[]
  }
  handleAddNewNote: (id: string, patient: string, note: string) => void
}

export interface DiabetesReportParams {
  diabetesReport: {
    patientName: string,
    riskLevel: string
  }
}

export interface LoginFormParams {
  onLogin: (jwt: string, username: string) => void
}
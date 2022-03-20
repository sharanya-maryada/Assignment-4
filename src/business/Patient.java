package business;

/**
 *
 * @author sharanya maryada
 */
public class Patient {

    private String patientID;
    private String primaryDoctorName;
    private String prefferedPharmacy;
    private VitalSignRecord vitalSignHistory;

    public Patient() {
        this.vitalSignHistory = new VitalSignRecord();
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getPrimaryDoctorName() {
        return primaryDoctorName;
    }

    public void setPrimaryDoctorName(String primaryDoctorName) {
        this.primaryDoctorName = primaryDoctorName;
    }

    public String getPrefferedPharmacy() {
        return prefferedPharmacy;
    }

    public void setPrefferedPharmacy(String prefferedPharmacy) {
        this.prefferedPharmacy = prefferedPharmacy;
    }

    public VitalSignRecord getVitalSignHistory() {
        return vitalSignHistory;
    }

    public void setVitalSignHistory(VitalSignRecord vitalSignHistory) {
        this.vitalSignHistory = vitalSignHistory;
    }

    @Override
    public String toString()
    {
        return String.valueOf(this.patientID);
    }
}

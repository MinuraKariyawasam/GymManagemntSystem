package sample;

public class StudentMember extends DefaultMember {

    private String schoolName;
    private String guardianName;

    public StudentMember(String memberShipNumber, String memberName, Date startMemberShipDate, String memberEmail ,boolean isMemberHasHealthInsurance, String schoolName, String guardianName) {
        super(memberShipNumber, memberName, startMemberShipDate, memberEmail, isMemberHasHealthInsurance);
        this.schoolName = schoolName;
        this.guardianName = guardianName;
    }


    // setting getters and setters
    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }
}

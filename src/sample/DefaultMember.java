package sample;
import java.io.Serializable;

public class DefaultMember implements Comparable<DefaultMember>, Serializable {
    private String memberShipNumber;
    private String memberName;
    private Date startMemberShipDate;
    private String memberEmail;

    private boolean isMemberHasHealthInsurance;

    public DefaultMember(String memberShipNumber, String memberName, Date startMemberShipDate, String memberEmail, boolean isMemberHasHealthInsurance) {
        super();
        this.memberShipNumber = memberShipNumber;
        this.memberName = memberName;
        this.startMemberShipDate = startMemberShipDate;
        this.isMemberHasHealthInsurance = isMemberHasHealthInsurance;
        setMemberEmail(memberEmail);
    }

    public String getMemberShipNumber() {
        return memberShipNumber;
    }

    public void setMemberShipNumber(String memberShipNumber) {
        this.memberShipNumber = memberShipNumber;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Date getStartMemberShipDate() {
        return startMemberShipDate;
    }

    public void setStartMemberShipDate(Date startMemberShipDate) {
        this.startMemberShipDate = startMemberShipDate;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
       this.memberEmail = memberEmail;
    }

    public boolean isMemberHasHealthInsurance() {
        return isMemberHasHealthInsurance;
    }

    public void setMemberHasHealthInsurance(boolean memberHasHealthInsurance) {
        isMemberHasHealthInsurance = memberHasHealthInsurance;
    }

    @Override
    public int compareTo(DefaultMember o) {
        int compareMembers = memberName.compareTo(o.memberName);
        if (compareMembers == 0){
            // if there are same name in we look for membership id
            compareMembers = Integer.compare(Integer.parseInt(o.memberShipNumber), Integer.parseInt(memberShipNumber));
        }
        return compareMembers;
    }

    @Override
    public String toString() {
        return "Member ID: " + memberShipNumber + "; Member Name: " + memberName + "; Start Membership Date: " + startMemberShipDate + "; Member Email: " + memberEmail + "\n";
    }
}
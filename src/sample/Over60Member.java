package sample;

public class Over60Member extends DefaultMember{

    private int memberAge;
    private String memberRelative;

    public Over60Member(String memberShipNumber, String memberName, Date startMemberShipDate, String memberEmail, boolean isMemberHasHealthInsurance,  int memberAge, String memberRelative) {
        super(memberShipNumber, memberName, startMemberShipDate, memberEmail, isMemberHasHealthInsurance);
        this.memberAge = memberAge;
        this.memberRelative = memberRelative;
    }


    // setting getters and setters
    public int getMemberAge() {
        return memberAge;
    }

    public void setMemberAge(int memberAge) {
        //validate the age
        this.memberAge = memberAge;
    }

    public String getMemberRelative() {
        return memberRelative;
    }

    public void setMemberRelative(String memberRelative) {
        this.memberRelative = memberRelative;
    }
}

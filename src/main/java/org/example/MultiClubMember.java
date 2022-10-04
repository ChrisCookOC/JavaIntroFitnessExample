package org.example;

public class MultiClubMember extends Member {

    private int membershipPoints;

    public MultiClubMember(int pMemberID, String pName, double pFees, int membershipPoints) {
        super(MemberType.MultiBranch, pMemberID, pName, pFees);
        this.membershipPoints = membershipPoints;
    }

    public int getMembershipPoints() {
        return membershipPoints;
    }

    public void setMembershipPoints(int membershipPoints) {
        this.membershipPoints = membershipPoints;
    }

    @Override
    public String toString() {
        return super.toString() + "; Membership points: " + membershipPoints;
    }

    @Override
    public String toCSV() {
        return super.toCSV() + ", " + membershipPoints;
    }
}

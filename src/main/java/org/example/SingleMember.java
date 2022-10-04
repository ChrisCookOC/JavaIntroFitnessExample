package org.example;

public class SingleMember extends Member {

    int clubID;

    public SingleMember(int pMemberID, String pName, double pFees, int pClub) {
        super(MemberType.SingleBranch, pMemberID, pName, pFees);
        clubID = pClub;

    }

    public int getClubID() {
        return clubID;
    }

    public void setClubID(int clubID) {
        this.clubID = clubID;
    }

    @Override
    public String toString() {
        return super.toString() + "; Club: " + clubID;
    }

    @Override
    public String toCSV() {
        return super.toCSV() + ", " + clubID;
    }
}

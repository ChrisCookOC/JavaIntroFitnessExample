package org.example;

public class Member {

    protected MemberType memberType;
    protected int memberID;
    protected String name;
    protected double fees;

    public Member(MemberType pMemberType, int pMemberID, String pName, double pFees) {
        memberType = pMemberType;
        memberID = pMemberID;
        name = pName;
        fees = pFees;
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    @Override
    public String toString() {
        return "Membership type: " + memberType.toString() + "; Member ID: " + memberID
                + "; Member: " + name + "; Fee: £" + String.format("%.2f", fees);
    }

    public String toCSV() {
        return memberType.constant + ", " + memberID + ", " + name + ", " + fees;
    }
}

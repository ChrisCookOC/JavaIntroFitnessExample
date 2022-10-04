package org.example;

public enum MemberType {
    SingleBranch("Single branch", "S"),
    MultiBranch("All branches", "M");

    final String description;
    final String constant;

    MemberType(String desc, String pConstant) {
        description = desc;
        constant = pConstant;
    }

    @Override
    public String toString() {
        return description;
    }
}

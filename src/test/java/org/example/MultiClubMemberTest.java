package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MultiClubMemberTest {

    @Test
    void testToString() {

        MultiClubMember mark = new MultiClubMember(1, "Mark Markez", 25, 87);

        assertEquals("Membership type: All branches; Member ID: 1; Member: Mark Markez; Fee: £25.00; Membership points: 87", mark.toString());
    }

    @Test
    void testToCSV() {

        MultiClubMember mark = new MultiClubMember(1, "Mark Markez", 25, 87);

        assertEquals("M, 1, Mark Markez, 25.0, 87", mark.toCSV());
    }
}
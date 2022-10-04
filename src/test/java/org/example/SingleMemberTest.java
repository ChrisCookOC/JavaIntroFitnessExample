package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SingleMemberTest {

    @Test
    void testToString() {

        SingleMember mark = new SingleMember(1, "Mark Markez", 25, 2);

        assertEquals("Membership type: Single branch; Member ID: 1; Member: Mark Markez; Fee: £25.00; Club: 2", mark.toString());
    }

    @Test
    void testToCSV() {

        SingleMember mark = new SingleMember(1, "Mark Markez", 25, 2);

        assertEquals("S, 1, Mark Markez, 25.0, 2", mark.toCSV());
    }
}
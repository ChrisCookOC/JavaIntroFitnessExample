package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class MembershipManagementTest {


    @Test
    void isValidClubTest() {
        assertTrue(MembershipManagement.isValidClub(1));
        assertTrue(MembershipManagement.isValidClub(2));
        assertTrue(MembershipManagement.isValidClub(4));
        assertFalse(MembershipManagement.isValidClub(-1));
        assertFalse(MembershipManagement.isValidClub(48844));
    }

    @Nested
    @DisplayName("getNextMemberID tests")
    class getNextMemberIDTests {

        @Test
        @DisplayName("When list is empty, start at 1")
        void getNextMemberIDWhenEmptyList() {
            LinkedList<Member> members = new LinkedList<>();

            assertEquals(1, MembershipManagement.getNextMemberID(members));

        }

        @Test
        @Tag("getNextMemberID")
        @DisplayName("When list is populated increment the ID based on that")
        void getNextMemberIDWhenPopulatedList() {
            LinkedList<Member> members = new LinkedList<>();

            members.add(new SingleMember(98, "Maria", 32, 1));
            assertEquals(99, MembershipManagement.getNextMemberID(members));

        }

    }
}
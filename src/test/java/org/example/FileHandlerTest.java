package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {

    private FileHandler fileHandler;

    @BeforeEach
    void setUpTest() {
        fileHandler = new FileHandler();
        fileHandler.csvFileName = Paths.get("src/test/java/membersTestData.csv");
    }

    @Test
    @DisplayName("Read the list from the CSV")
    void ListPopulatedIfCSVIs() {

        LinkedList<Member> members = fileHandler.readFile();
        assertEquals(5, members.size());

        SingleMember singleMember;
        MultiClubMember multiMember;

        singleMember = (SingleMember) members.get(0);
        assertEquals(MemberType.SingleBranch, singleMember.getMemberType());
        assertEquals(1, singleMember.getMemberID());
        assertEquals("Yvonne", singleMember.getName());
        assertEquals(950, singleMember.getFees());
        assertEquals(2, singleMember.getClubID());

        singleMember = (SingleMember) members.get(1);
        assertEquals(MemberType.SingleBranch, singleMember.getMemberType());
        assertEquals(2, singleMember.getMemberID());
        assertEquals("Martin", singleMember.getName());
        assertEquals(837.32, singleMember.getFees());
        assertEquals(3, singleMember.getClubID());

        multiMember = (MultiClubMember) members.get(2);
        assertEquals(MemberType.MultiBranch, multiMember.getMemberType());
        assertEquals(3, multiMember.getMemberID());
        assertEquals("Alex", multiMember.getName());
        assertEquals(1220, multiMember.getFees());
        assertEquals(231, multiMember.getMembershipPoints());

        singleMember = (SingleMember) members.get(3);
        assertEquals(MemberType.SingleBranch, singleMember.getMemberType());
        assertEquals(4, singleMember.getMemberID());
        assertEquals("Lucy", singleMember.getName());
        assertEquals(213, singleMember.getFees());
        assertEquals(1, singleMember.getClubID());

        multiMember = (MultiClubMember) members.get(4);
        assertEquals(MemberType.MultiBranch, multiMember.getMemberType());
        assertEquals(5, multiMember.getMemberID());
        assertEquals("Marge", multiMember.getName());
        assertEquals(1224.3, multiMember.getFees());
        assertEquals(43, multiMember.getMembershipPoints());

    }


    @Test
    @DisplayName("New members should be appended to the CSV file")
    void AppendNewMemberToCSV() {

        Path appendFileName = Paths.get("src/test/java/membersTestDataAppend.csv");

        //Copy CSV across, so we don't mess up the data for other tests
        try {

            Files.copy(fileHandler.csvFileName, appendFileName, StandardCopyOption.REPLACE_EXISTING);

            //Now make sure we run the test on the copy of the file
            fileHandler.csvFileName = appendFileName;


        } catch (Exception e) {
            //Feels like a hack
            fail("Exception occurred: " + e.getMessage());
        }

        String memberToAppend = "S, 6, Creed, 224.99, 2";
        fileHandler.appendFile(memberToAppend);

        try {

            long result = Files.mismatch(appendFileName, Paths.get("src/test/java/membersTestDataAppendedResult.csv"));
            assertEquals(-1, result, "Files do not match");

        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        } finally {

            try {
                Files.deleteIfExists(appendFileName);
            } catch (Exception e2) {
                fail("Exception occurred trying to delete file" + e2.getMessage());

            }
        }

    }

    @Test
    void RewriteFileIfDeletingMembers() {

        //Setup members list for the test
        LinkedList<Member> members = fileHandler.readFile();
        members.remove(3);
        Member newMember = new MultiClubMember(9, "Marlon", 84, 988);
        members.add(newMember);

        Path testFileName = Paths.get("src/test/java/membersTestDataCopy.csv");

        //Make copy to work with in the test
        try {

            Files.copy(fileHandler.csvFileName, testFileName, StandardCopyOption.REPLACE_EXISTING);

            //Now make sure we run the test on the copy of the file
            fileHandler.csvFileName = testFileName;

        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }

        fileHandler.overwriteFile(members);

        try {

            long result = Files.mismatch(testFileName, Paths.get("src/test/java/membersTestDataRewrite.csv"));

            assertEquals(-1, result, "Files do not match");

        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        } finally {
            try {
                Files.deleteIfExists(testFileName);
            } catch (Exception e2) {
                fail("Exception occurred trying to delete file" + e2.getMessage());

            }
        }

    }

    @Test
    void EmptyIfCantFindCSVFile() {

        fileHandler.csvFileName = Paths.get("src/test/java/badFileName.csv");

        LinkedList<Member> members = fileHandler.readFile();

        assertEquals(0, members.size());
    }
}

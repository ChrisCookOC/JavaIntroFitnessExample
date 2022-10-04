package org.example;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class MembershipManagement {

    protected final Scanner reader;

    public MembershipManagement(Scanner scanner) {
        this.reader = scanner;
    }

    private int getIntInput() {

        int choice = 0;

        while (choice == 0) {
            try {
                choice = reader.nextInt();

                if (choice == 0) {
                    throw new InputMismatchException("Zero is not valid here");
                }

                reader.nextLine();

            } catch (InputMismatchException e) {
                reader.nextLine();

                System.out.println("Please enter a valid (non-zero) integer");
            }
        }
        return choice;
    }

    private void printClubOptions() {
        System.out.println("1) Club Mercury\n2) Club Neptune\n3) Club Jupiter\n4) Multi Clubs\n");
    }

    public int getChoice() {
        int choice;

        System.out.println("\n");
        System.out.println("WELCOME TO OZONE FITNESS CENTRE");
        System.out.println("===============================");
        System.out.println("1) Add Member");
        System.out.println("2) Remove Member");
        System.out.println("3) Display Member Information");
        System.out.println();
        System.out.println("Please select an option (or enter -1 to quit): ");

        choice = getIntInput();

        return choice;
    }

    public String addMembers(LinkedList<Member> members) {

        String name;
        int club;
        double fees;
        int memberID;
        Member member;
        Calculator<Integer> calculator;

        System.out.print("Enter the user's name: ");
        name = reader.nextLine();

        printClubOptions();
        System.out.print("Enter the user's club: ");
        club = getIntInput();

        while (!isValidClub(club)) {
            System.out.println("Entered club is invalid");
            printClubOptions();
            club = getIntInput();
        }

        memberID = getNextMemberID(members);

        if (club == 4) {
            fees = 1200;

            member = new MultiClubMember(memberID, name, fees, 100);

        } else {

            calculator = (n) -> switch (n) {
                case 1 -> 900;
                case 2 -> 950;
                case 3 -> 1000;
                default -> -1;
            };

            fees = calculator.calculateFees(club);

            member = new SingleMember(memberID, name, fees, club);
        }

        members.add(member);

        return member.toCSV();
    }

    protected static int getNextMemberID(LinkedList<Member> members) {
        int memberID;

        if (members.size() > 0) {
            memberID = members.getLast().getMemberID() + 1;
        } else {
            memberID = 1;
        }
        return memberID;
    }

    protected static boolean isValidClub(int club) {
        return club > 0 && club <= 4;
    }


    public void removeMember(LinkedList<Member> members) {
        int memberID;

        System.out.println("Enter member ID to remove: ");
        memberID = getIntInput();

        int finalMemberID = memberID;
        Member memberToDelete = members.stream().filter(m -> m.getMemberID() == finalMemberID).findFirst().orElse(null);

        if (memberToDelete != null) {
            members.remove(memberToDelete);
            System.out.println("User has been deleted");

        } else {
            System.out.println("Member " + memberID + " could not be found and so was not removed");
        }


    }

    public void printMemberInfo(LinkedList<Member> members) {
        System.out.println("Enter member ID to lookup: ");
        int memberID = getIntInput();
        String memberString;
        String[] memberInfo;

        Member memberToDisplay = members.stream().filter(m -> m.getMemberID() == memberID).findFirst().orElse(null);

        if (memberToDisplay != null) {
            memberString = memberToDisplay.toString();
            memberInfo = memberString.split(", ");

            System.out.println("Details for member:\n");

            for (int i = 0; i < Arrays.stream(memberInfo).count(); i++) {
                System.out.println(memberInfo[i] + '\n');
            }

        } else {
            System.out.println("Member " + memberID + " could not be found");
        }

    }

}

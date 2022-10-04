package org.example;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String memberString;

        MembershipManagement membershipManagement = new MembershipManagement(new Scanner(System.in));
        FileHandler fileHandler = new FileHandler();

        LinkedList<Member> members = fileHandler.readFile();
        int choice;

        do {

            choice = membershipManagement.getChoice();

            switch (choice) {
                case 1 -> {
                    memberString = membershipManagement.addMembers(members);
                    fileHandler.appendFile(memberString);
                }
                case 2 -> {
                    membershipManagement.removeMember(members);
                    fileHandler.overwriteFile(members);
                }
                case 3 -> membershipManagement.printMemberInfo(members);
                case -1 -> System.out.println("Exiting");
                default -> System.out.println("Invalid option selected");
            }


        } while (choice != -1);

    }
}
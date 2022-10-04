package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedList;

public class FileHandler {

    public static final String MEMBERS_TEMP = "members.temp";
    protected Path csvFileName;

    public FileHandler() {
        csvFileName = Paths.get("members.csv");
    }

    public LinkedList<Member> readFile() {
        LinkedList<Member> members = new LinkedList<>();

        Member memberPlaceholder;

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFileName.toString()))) {

            String lineRead = reader.readLine();

            while (lineRead != null) {

                String[] fields = lineRead.split(", ");

                //TODO index fields or something
                //TODO errors

                if (fields[0].equals("S")) {
                    memberPlaceholder = new SingleMember(Integer.parseInt(fields[1]), fields[2], Double.parseDouble(fields[3]), Integer.parseInt(fields[4]));
                } else {
                    memberPlaceholder = new MultiClubMember(Integer.parseInt(fields[1]), fields[2], Double.parseDouble(fields[3]), Integer.parseInt(fields[4]));
                }
                members.add(memberPlaceholder);
                lineRead = reader.readLine();
            }

        } catch (IOException e) {
            System.out.println("Failed to parse data: " + e.getMessage());
            members.clear();
        }

        return members;
    }

    public void appendFile(String member) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFileName.toString(), true))) {
            writer.write(member + '\n');

        } catch (IOException e) {
            System.out.println("Failed to update membership file: " + e.getMessage());
        }

    }

    public void overwriteFile(LinkedList<Member> members) {

        boolean moveFile = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MEMBERS_TEMP))) {

            for (Member member : members) {
                writer.write(member.toCSV());
                writer.newLine();
            }

            moveFile = true;
        } catch (IOException e) {
            System.out.println("Failed to write data: " + e.getMessage());
        }

        if (moveFile) {
            Path tempFile = Paths.get(MEMBERS_TEMP);

            try {
                Files.move(tempFile, csvFileName, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                System.out.println("Failed to move temporary data file: " + e.getMessage());
            }
        }

    }
}

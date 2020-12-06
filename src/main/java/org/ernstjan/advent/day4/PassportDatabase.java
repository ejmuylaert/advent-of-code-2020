package org.ernstjan.advent.day4;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PassportDatabase {
    private final List<Passport> passports = new LinkedList<>();

    public void addPassports(List<String> lines) {
        PassportBuilder passportBuilder = new PassportBuilder();
        for (String line : lines) {
            if (line.isBlank()) {
                passportBuilder
                        .createPassport()
                        .ifPresent(passports::add);

                passportBuilder = new PassportBuilder();
            }
            Scanner lineScanner = new Scanner(line);
            while (lineScanner.hasNext()) {
                String field = lineScanner.next();
                Scanner fieldScanner = new Scanner(field).useDelimiter(":");
                switch (fieldScanner.next()) {
                    case "byr":
                        passportBuilder.setBirthYear(fieldScanner.nextInt());
                        break;
                    case "iyr":
                        passportBuilder.setIssueYear(fieldScanner.nextInt());
                        break;
                    case "eyr":
                        passportBuilder.setExpirationYear(fieldScanner.nextInt());
                        break;
                    case "hgt":
                        passportBuilder.setHeight(fieldScanner.next());
                        break;
                    case "hcl":
                        passportBuilder.setHairColor(fieldScanner.next());
                        break;
                    case "ecl":
                        passportBuilder.setEyeColor(fieldScanner.next());
                        break;
                    case "pid":
                        passportBuilder.setPassportId(fieldScanner.next());
                        break;
                    case "cid":
                        passportBuilder.setCountryId(fieldScanner.next());
                        break;
                }
            }
        }

        passportBuilder
                .createPassport()
                .ifPresent(passports::add);
    }

    public int size() {
        return passports.size();
    }

    public long validPassports() {
        return passports
                .stream()
                .filter(Passport::isValid)
                .count();
    }
}

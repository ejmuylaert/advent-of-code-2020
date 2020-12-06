package org.ernstjan.advent.day4;

import java.util.Optional;

public class PassportBuilder {
    private int birthYear;
    private int issueYear;
    private int expirationYear;
    private String height;
    private String hairColor;
    private String eyeColor;
    private String passportId;
    private Optional<String> countryId;

    public PassportBuilder setBirthYear(int birthYear) {
        this.birthYear = birthYear;
        return this;
    }

    public PassportBuilder setIssueYear(int issueYear) {
        this.issueYear = issueYear;
        return this;
    }

    public PassportBuilder setExpirationYear(int expirationYear) {
        this.expirationYear = expirationYear;
        return this;
    }

    public PassportBuilder setHeight(String height) {
        this.height = height;
        return this;
    }

    public PassportBuilder setHairColor(String hairColor) {
        this.hairColor = hairColor;
        return this;
    }

    public PassportBuilder setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
        return this;
    }

    public PassportBuilder setPassportId(String passportId) {
        this.passportId = passportId;
        return this;
    }

    public PassportBuilder setCountryId(String countryId) {
        this.countryId = Optional.of(countryId);
        return this;
    }

    public Optional<Passport> createPassport() {
        if (birthYear == 0) return Optional.empty();
        if (issueYear == 0) return Optional.empty();
        if (expirationYear == 0) return Optional.empty();
        if (height == null) return Optional.empty();
        if (hairColor == null) return Optional.empty();
        if (eyeColor == null) return Optional.empty();
        if (passportId == null) return Optional.empty();

        Passport passport = new Passport(birthYear, issueYear, expirationYear, height, hairColor, eyeColor, passportId, countryId);
        return Optional.of(passport);
    }
}
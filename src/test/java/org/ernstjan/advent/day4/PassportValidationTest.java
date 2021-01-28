package org.ernstjan.advent.day4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PassportValidationTest {

    private Passport.PassportBuilder builder;
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @BeforeEach
    void setupBuilderWithValidValues() {
        builder = Passport.builder()
                .birthYear(1980)
                .issueYear(2020)
                .expirationYear(2021)
                .height(180)
                .hairColor("#123456")
                .eyeColor("amb")
                .passportId("012345678")
                .countryId("202");
    }

    @ParameterizedTest
    @MethodSource("birthYear")
    @DisplayName("Birth year should between 1920 and 2002")
    void validateBirthYear(int year, int numberOfErrors) {
        Passport passport = builder.birthYear(year).build().get();
        assertThat(validator.validate(passport)).hasSize(numberOfErrors);
    }

    static Stream<Arguments> birthYear() {
        return Stream.of(
                arguments(1919, 1),
                arguments(1920, 0),
                arguments(1950, 0),
                arguments(2002, 0),
                arguments(2003, 1));
    }

    @ParameterizedTest
    @MethodSource("issueYear")
    @DisplayName("Issue year should between 2010 and 2020")
    void validateIssueYear(int year, int numberOfErrors) {
        Passport passport = builder.issueYear(year).build().get();
        assertThat(validator.validate(passport)).hasSize(numberOfErrors);
    }

    static Stream<Arguments> issueYear() {
        return Stream.of(
                arguments(2009, 1),
                arguments(2010, 0),
                arguments(2015, 0),
                arguments(2020, 0),
                arguments(2021, 1));
    }

    @ParameterizedTest
    @MethodSource("expirationYear")
    @DisplayName("Expiration year should between 2020 and 2030")
    void validateExpirationYear(int year, int numberOfErrors) {
        Passport passport = builder.expirationYear(year).build().get();
        assertThat(validator.validate(passport)).hasSize(numberOfErrors);
    }

    static Stream<Arguments> expirationYear() {
        return Stream.of(
                arguments(20019, 1),
                arguments(2020, 0),
                arguments(2025, 0),
                arguments(2030, 0),
                arguments(2031, 1));
    }

    @ParameterizedTest
    @MethodSource("height")
    @DisplayName("Height should be between 150 and 193 cm")
    void validateHeight(int height, int numberOfErrors) {
        Passport passport = builder.height(height).build().get();
        assertThat(validator.validate(passport)).hasSize(numberOfErrors);
    }

    static Stream<Arguments> height() {
        return Stream.of(
                arguments(149, 1),
                arguments(150, 0),
                arguments(179, 0),
                arguments(193, 0),
                arguments(194, 1));
    }

    @ParameterizedTest
    @MethodSource("hairColor")
    @DisplayName("hair color should be length 7")
    void validateHairColor(String color, int numberOfErrors) {
        Passport passport = builder.hairColor(color).build().get();
        assertThat(validator.validate(passport)).hasSize(numberOfErrors);
    }

    static Stream<Arguments> hairColor() {
        return Stream.of(
                arguments("#12345", 1),
                arguments("#123456", 0),
                arguments("#1234567", 1));
    }

    @ParameterizedTest
    @MethodSource("eyeColor")
    @DisplayName("Eye color must be one of 'amb blu brn gry grn hzl oth'")
    void validateEye(String color, int numberOfErrors) {
        Passport passport = builder.eyeColor(color).build().get();
        assertThat(validator.validate(passport)).hasSize(numberOfErrors);
    }

    static Stream<Arguments> eyeColor() {
        return Stream.of(
                arguments("amb", 0),
                arguments("blu", 0),
                arguments("brn", 0),
                arguments("gry", 0),
                arguments("grn", 0),
                arguments("hzl", 0),
                arguments("oth", 0),
                arguments("OTH", 1),
                arguments("#123456", 1));
    }

    @ParameterizedTest
    @MethodSource("passportId")
    @DisplayName("PassportId always a 9 digit number")
    void validatePassportId(String id, int numberOfErrors) {
        Passport passport = builder.passportId(id).build().get();
        assertThat(validator.validate(passport)).hasSize(numberOfErrors);
    }

    static Stream<Arguments> passportId() {
        return Stream.of(
                arguments("12345678", 1),
                arguments("123456789", 0),
                arguments("1234567890", 1));
    }
}

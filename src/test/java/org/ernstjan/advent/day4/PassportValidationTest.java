package org.ernstjan.advent.day4;

import org.ernstjan.advent.config.ValidationConfig;
import org.ernstjan.advent.day4.Passport.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import javax.validation.Validator;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.ernstjan.advent.day4.Passport.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@SpringBootTest
@ContextConfiguration(classes = ValidationConfig.class)
class PassportValidationTest {

    private PassportBuilder builder;
    @Autowired
    private Validator validator;


    @BeforeEach
    void setupBuilderWithValidValues() {
        builder = builder()
                .birthYear(PassportTypes.Year.value(1980))
                .issueYear(PassportTypes.Year.value(2020))
                .expirationYear(PassportTypes.Year.value(2021))
                .height(PassportTypes.Height.value("180cm"))
                .hairColor(PassportTypes.HexColor.value("#123456"))
                .eyeColor(PassportTypes.NamedColor.value("amb"))
                .passportId(PassportTypes.Id.value("012345678"))
                .countryId(PassportTypes.Id.value("202"));
    }

    @Test
    @DisplayName("Unconverted values are not valid")
    void unconvertedValuesAreInvalid() {
        Passport passport = builder.birthYear(PassportTypes.Year.raw("twenty")).build().get();

        assertThat(validator.validate(passport)).hasSize(1);
    }

    @ParameterizedTest
    @MethodSource("birthYear")
    @DisplayName("Birth year should between 1920 and 2002")
    void validateBirthYear(int year, int numberOfErrors) {
        Passport passport = builder.birthYear(PassportTypes.Year.value(year)).build().get();
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
        Passport passport = builder.issueYear(PassportTypes.Year.value(year)).build().get();
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
        Passport passport = builder.expirationYear(PassportTypes.Year.value(year)).build().get();
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
        Passport passport = builder.height(PassportTypes.Height.value(height + "cm")).build().get();
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
        Passport passport = builder.hairColor(PassportTypes.HexColor.value(color)).build().get();
        assertThat(validator.validate(passport)).hasSize(numberOfErrors);
    }

    static Stream<Arguments> hairColor() {
        return Stream.of(
                arguments("#12345", 1),
                arguments("#123456", 0),
                arguments("#1234567", 1));
    }

    // No need for an eye color test, the lexer takes care of only setting the correct values

    @ParameterizedTest
    @MethodSource("passportId")
    @DisplayName("PassportId always a 9 digit number")
    void validatePassportId(String id, int numberOfErrors) {
        Passport passport = builder.passportId(PassportTypes.Id.value(id)).build().get();
        assertThat(validator.validate(passport)).hasSize(numberOfErrors);
    }

    static Stream<Arguments> passportId() {
        return Stream.of(
                arguments("12345678", 1),
                arguments("123456789", 0),
                arguments("1234567890", 1));
    }
}

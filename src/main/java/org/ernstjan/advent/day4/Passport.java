package org.ernstjan.advent.day4;

import lombok.Builder;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.Optional;

@Value
@Builder
public class Passport {
    @Min(1920) @Max(2002) int birthYear;
    @Min(2010) @Max(2020) int issueYear;
    @Min(2020) @Max(2030) int expirationYear;
    @Min(150) @Max(193) int height; // in cm
    @Length(min = 7, max = 7) String hairColor; // parser makes sure it only contains hex digits
    @Pattern(regexp = "amb|blu|brn|gry|grn|hzl|oth") String eyeColor;
    @Length(min = 9, max = 9) String passportId; // parser makes sure it only contains digits
    String countryId;

    public static class PassportBuilder {
        public Optional<Passport> build() {
            if (birthYear == 0) return Optional.empty();
            if (issueYear == 0) return Optional.empty();
            if (expirationYear == 0) return Optional.empty();
            if (height == 0) return Optional.empty();
            if (hairColor == null) return Optional.empty();
            if (eyeColor == null) return Optional.empty();
            if (passportId == null) return Optional.empty();

            Passport passport = new Passport(birthYear, issueYear, expirationYear, height, hairColor, eyeColor, passportId, countryId);
            return Optional.of(passport);
        }
    }
}

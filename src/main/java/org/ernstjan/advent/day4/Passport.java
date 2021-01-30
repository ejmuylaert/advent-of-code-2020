package org.ernstjan.advent.day4;

import lombok.Builder;
import lombok.Value;
import org.ernstjan.advent.day4.constraints.Required;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Optional;

import static org.ernstjan.advent.day4.PassportTypes.*;

@Value
@Builder
public class Passport {
    @Required @Min(1920) @Max(2002) Year birthYear;
    @Required @Min(2010) @Max(2020) Year issueYear;
    @Required @Min(2020) @Max(2030) Year expirationYear;
    @Required @Min(150) @Max(193) Height height; // in cm
    @Required @Length(min = 7, max = 7) HexColor hairColor; // parser makes sure it only contains hex digits
    @Required NamedColor eyeColor; // parse makes sure it contains only valid names
    @Required @Length(min = 9, max = 9) Id passportId; // parser makes sure it only contains digits
    Id countryId;

    public static class PassportBuilder {
        public Optional<Passport> build() {
            if (birthYear == null) return Optional.empty();
            if (issueYear == null) return Optional.empty();
            if (expirationYear == null) return Optional.empty();
            if (height == null) return Optional.empty();
            if (hairColor == null) return Optional.empty();
            if (eyeColor == null) return Optional.empty();
            if (passportId == null) return Optional.empty();

            Passport passport = new Passport(birthYear, issueYear, expirationYear, height, hairColor, eyeColor, passportId, countryId);
            return Optional.of(passport);
        }
    }
}

package org.ernstjan.advent.day4;

import org.ernstjan.advent.day4.PassportTypes.NamedColor;
import org.ernstjan.advent.day4.PassportTypes.Year;

import static org.ernstjan.advent.day4.Passport.*;
import static org.ernstjan.advent.day4.PassportTypes.*;

public class PassportValuesVisitor extends PassportBaseVisitor<PassportBuilder> {
    private final PassportBuilder builder = builder();

    @Override
    public PassportBuilder visitBirthYear(PassportParser.BirthYearContext ctx) {
        Year year = ctx.any().isEmpty() ? Year.value(Integer.parseInt(ctx.NUMBER().getText())) : Year.raw(ctx.getText());
        return builder.birthYear(year);
    }

    @Override
    public PassportBuilder visitIssueYear(PassportParser.IssueYearContext ctx) {
        Year year = ctx.any().isEmpty() ? Year.value(Integer.parseInt(ctx.NUMBER().getText())) : Year.raw(ctx.getText());
        return builder.issueYear(year);
    }

    @Override
    public PassportBuilder visitExpirationYear(PassportParser.ExpirationYearContext ctx) {
        Year year = ctx.any().isEmpty() ? Year.value(Integer.parseInt(ctx.NUMBER().getText())) : Year.raw(ctx.getText());
        return builder.expirationYear(year);
    }

    @Override
    public PassportBuilder visitHeight(PassportParser.HeightContext ctx) {
        Height height = ctx.any().isEmpty() ? Height.value(ctx.HEIGHT().getText()) : Height.raw(ctx.getText());
        return builder.height(height);
    }

    @Override
    public PassportBuilder visitHairColor(PassportParser.HairColorContext ctx) {
        HexColor color = ctx.any().isEmpty() ? HexColor.value(ctx.HEX_COLOR().getText()) : HexColor.raw(ctx.getText());
        return builder.hairColor(color);
    }

    @Override
    public PassportBuilder visitEyeColor(PassportParser.EyeColorContext ctx) {

        NamedColor color = ctx.any().isEmpty() ? NamedColor.value(ctx.COLOR_NAME().getText()) : NamedColor.raw(ctx.getText());
        return builder.eyeColor(color);
    }

    @Override
    public PassportBuilder visitPassportId(PassportParser.PassportIdContext ctx) {
        if (ctx.NUMBER() != null) {
            return builder.passportId(Id.value(ctx.NUMBER().getText()));
        } else if (ctx.DIGITS() != null) {
            return builder.passportId(Id.value(ctx.DIGITS().getText()));
        } else {
            return builder.passportId(Id.raw(ctx.getText()));
        }
    }

    @Override
    public PassportBuilder visitCountryId(PassportParser.CountryIdContext ctx) {
        if (ctx.NUMBER() != null) {
            return builder.countryId(Id.value(ctx.NUMBER().getText()));
        } else if (ctx.DIGITS() != null) {
            return builder.countryId(Id.value(ctx.DIGITS().getText()));
        } else {
            return builder.countryId(Id.raw(ctx.getText()));
        }
    }

    @Override
    protected PassportBuilder defaultResult() {
        return builder;
    }
}

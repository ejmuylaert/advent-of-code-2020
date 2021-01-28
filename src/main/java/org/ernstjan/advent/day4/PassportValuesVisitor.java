package org.ernstjan.advent.day4;

public class PassportValuesVisitor extends PassportBaseVisitor<Passport.PassportBuilder> {
    private final Passport.PassportBuilder builder = Passport.builder();

    @Override
    public Passport.PassportBuilder visitBirthYear(PassportParser.BirthYearContext ctx) {
        return builder.birthYear(Integer.parseInt(ctx.NUMBER().getText()));
    }

    @Override
    public Passport.PassportBuilder visitIssueYear(PassportParser.IssueYearContext ctx) {
        return builder.issueYear(Integer.parseInt(ctx.NUMBER().getText()));
    }

    @Override
    public Passport.PassportBuilder visitExpirationYear(PassportParser.ExpirationYearContext ctx) {
        return builder.expirationYear(Integer.parseInt(ctx.NUMBER().getText()));
    }

    @Override
    public Passport.PassportBuilder visitHeight(PassportParser.HeightContext ctx) {
        if (ctx.UNIT().getText().equals("cm")) {
            return builder.height(Integer.parseInt(ctx.NUMBER().getText()));
        } else { // inches
            int inches = Integer.parseInt(ctx.NUMBER().getText());
            int centimeters = Math.round((float) inches * 2.54f);

            return builder.height(centimeters);
        }
    }

    @Override
    public Passport.PassportBuilder visitHairColor(PassportParser.HairColorContext ctx) {
        return builder.hairColor(ctx.COLOR_HEX().getText());
    }

    @Override
    public Passport.PassportBuilder visitEyeColor(PassportParser.EyeColorContext ctx) {
        return builder.eyeColor(ctx.COLOR_NAME().getText());
    }

    @Override
    public Passport.PassportBuilder visitPassportId(PassportParser.PassportIdContext ctx) {
        if (ctx.NUMBER() != null) {
            return builder.passportId(ctx.NUMBER().getText());
        } else {
            return builder.passportId(ctx.DIGITS().getText());
        }
    }

    @Override
    public Passport.PassportBuilder visitCountryId(PassportParser.CountryIdContext ctx) {
        if (ctx.NUMBER() != null) {
            return builder.countryId(ctx.NUMBER().getText());
        } else {
            return builder.countryId(ctx.DIGITS().getText());
        }
    }

    @Override
    protected Passport.PassportBuilder defaultResult() {
        return builder;
    }
}

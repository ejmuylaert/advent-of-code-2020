package org.ernstjan.advent.day4;

import java.util.LinkedList;

public class PassportFileVisitor extends PassportBaseVisitor<LinkedList<Passport.PassportBuilder>> {
    private final LinkedList<Passport.PassportBuilder> lines = new LinkedList<>();

    @Override
    public LinkedList<Passport.PassportBuilder> visitPassport(PassportParser.PassportContext ctx) {
        PassportValuesVisitor visitor = new PassportValuesVisitor();
        Passport.PassportBuilder passportBuilder = visitor.visit(ctx);
        lines.add(passportBuilder);

        return lines;
    }

    @Override
    protected LinkedList<Passport.PassportBuilder> defaultResult() {
        return lines;
    }
}

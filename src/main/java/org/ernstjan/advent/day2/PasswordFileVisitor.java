package org.ernstjan.advent.day2;

import java.util.ArrayList;
import java.util.List;

public class PasswordFileVisitor extends PasswordBaseVisitor<List<PasswordLine>> {
    private final ArrayList<PasswordLine> lines = new ArrayList<>();

    @Override
    public List<PasswordLine> visitLine(PasswordParser.LineContext ctx) {
        PasswordLineVisitor lineVisitor = new PasswordLineVisitor();
        PasswordLine passwordLine = lineVisitor.visit(ctx).createPasswordLine();
        lines.add(passwordLine);

        return lines;
    }

    @Override
    protected List<PasswordLine> defaultResult() {
        return lines;
    }
}
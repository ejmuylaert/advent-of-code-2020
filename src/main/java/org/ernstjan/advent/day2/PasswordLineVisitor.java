package org.ernstjan.advent.day2;

public class PasswordLineVisitor extends PasswordBaseVisitor<PasswordLineBuilder> {
    private final PasswordLineBuilder builder = new PasswordLineBuilder();

    @Override
    public PasswordLineBuilder visitLeftNumber(PasswordParser.LeftNumberContext ctx) {
        return builder.setLeftNumber(Integer.parseInt(ctx.NUM().getText()));
    }

    @Override
    public PasswordLineBuilder visitRightNumber(PasswordParser.RightNumberContext ctx) {
        return builder.setRightNumber(Integer.parseInt(ctx.NUM().getText()));
    }

    @Override
    public PasswordLineBuilder visitTarget(PasswordParser.TargetContext ctx) {
        return builder.setTargetChar(ctx.CHAR().getText().charAt(0));
    }

    @Override
    public PasswordLineBuilder visitPassword(PasswordParser.PasswordContext ctx) {
        return builder.setPassword(ctx.getText());
    }

    @Override
    protected PasswordLineBuilder defaultResult() {
        return builder;
    }
}

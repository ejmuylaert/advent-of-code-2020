package org.ernstjan.advent.day2;

public class PasswordLineBuilder {
    private int leftNumber;
    private int rightNumber;
    private char targetChar;
    private String password;

    public PasswordLineBuilder setLeftNumber(int leftNumber) {
        this.leftNumber = leftNumber;
        return this;
    }

    public PasswordLineBuilder setRightNumber(int rightNumber) {
        this.rightNumber = rightNumber;
        return this;
    }

    public PasswordLineBuilder setTargetChar(char targetChar) {
        this.targetChar = targetChar;
        return this;
    }

    public PasswordLineBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public PasswordLine createPasswordLine() {
        return new PasswordLine(leftNumber, rightNumber, targetChar, password);
    }
}
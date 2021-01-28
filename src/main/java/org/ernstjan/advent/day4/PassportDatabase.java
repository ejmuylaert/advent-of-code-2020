package org.ernstjan.advent.day4;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.LinkedList;
import java.util.Optional;

public class PassportDatabase {

    private final LinkedList<Passport> passports = new LinkedList<>();
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public void addPassports(CharStream inputStream) {
        PassportLexer lexer = new PassportLexer(inputStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);


        PassportParser parser = new PassportParser(tokenStream);

        PassportParser.PassportsContext tree = parser.passports();
        PassportFileVisitor visitor = new PassportFileVisitor();

        LinkedList<Passport.PassportBuilder> builders = visitor.visit(tree);
        builders.stream()
                .map(Passport.PassportBuilder::build)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(passports::add);
    }

    public int size() {
        return passports.size();
    }

    public long numberOfValidPassports() {
        return passports.stream()
                .filter(passport -> validator.validate(passport).size() == 0)
                .count();
    }
}

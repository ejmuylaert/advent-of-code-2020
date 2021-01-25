package org.ernstjan.advent.day2;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.List;

public class PasswordFileParser {

    public static List<PasswordLine> parse(CharStream inputStream) {
        PasswordLexer lexer = new PasswordLexer(inputStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        PasswordParser passwordParser = new PasswordParser(tokenStream);

        PasswordParser.ListContext tree = passwordParser.list();
        PasswordFileVisitor fileVisitor = new PasswordFileVisitor();

        return fileVisitor.visit(tree);
    }
}

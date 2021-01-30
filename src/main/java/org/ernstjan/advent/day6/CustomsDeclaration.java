package org.ernstjan.advent.day6;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.LinkedList;

public class CustomsDeclaration {

    private final LinkedList<Group> groups = new LinkedList<>();

    public void addForm(CharStream inputStream) {
        CustomsLexer lexer = new CustomsLexer(inputStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);

        CustomsParser parser = new CustomsParser(tokenStream);
        CustomsParser.ListContext tree = parser.list();
        FileVisitor visitor = new FileVisitor();

        groups.addAll(visitor.visit(tree));
    }

    public int declareAll() {
        return groups.stream()
                .mapToInt(Group::allYes)
                .sum();
    }

    public int declareCommon() {
        return groups.stream()
                .mapToInt(Group::commonYes)
                .sum();
    }
}

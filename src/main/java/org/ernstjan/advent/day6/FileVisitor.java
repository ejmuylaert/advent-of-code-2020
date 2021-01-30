package org.ernstjan.advent.day6;

import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileVisitor extends CustomsBaseVisitor<List<Group>> {
    private final List<Group> groups = new ArrayList<>();

    @Override
    public List<Group> visitGroup(CustomsParser.GroupContext ctx) {
        GroupVisitor groupVisitor = new GroupVisitor();
        Group group = groupVisitor.visit(ctx);

        groups.add(group);

        return groups;
    }

    @Override
    protected List<Group> defaultResult() {
        return groups;
    }

    static class GroupVisitor extends CustomsBaseVisitor<Group> {
        private final Group group = new Group();

        @Override
        public Group visitForm(CustomsParser.FormContext ctx) {
            Set<Character> form = new HashSet<>();
            for (TerminalNode node : ctx.QUESTION()) {
                form.add(node.getText().charAt(0));
            }

            group.addForm(form);

            return group;
        }

        @Override
        protected Group defaultResult() {
            return group;
        }
    }
}

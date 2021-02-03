package org.ernstjan.advent.day7;

import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

public class BagFileVisitor extends BagsBaseVisitor<Bags> {
    private final Bags bags = new Bags();

    @Override
    public Bags visitEmpty(BagsParser.EmptyContext ctx) {
        String name = bagName(ctx.bag());
        bags.add(name);

        return bags;
    }


    @Override
    public Bags visitContaining(BagsParser.ContainingContext ctx) {
        String parentBag = bagName(ctx.bag());

        ctx.contents().forEach(contentsContext -> {
            String childBag = bagName(contentsContext.bag());
            int numberOfBags = Integer.parseInt(contentsContext.NUMBER().getText());

            bags.fillBag(parentBag, childBag, numberOfBags);
        });

        return bags;
    }

    @Override
    protected Bags defaultResult() {
        return bags;
    }

    private String bagName(BagsParser.BagContext ctx) {
        // Bag contains by definition to 'STRING's, so it is safe to access them by index
        List<TerminalNode> strings = ctx.STRING();
        return strings.get(0) + " " + strings.get(1);
    }
}

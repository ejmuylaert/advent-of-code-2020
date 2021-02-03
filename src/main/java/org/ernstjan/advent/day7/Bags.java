package org.ernstjan.advent.day7;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.HashMap;

/**
 * Bags holds a collection of bags, and can answer the questions of day 7 of Advent of Code.
 * <p>
 * The collection cn be parsed from a (ANTLR) stream with 'fromCharStream', to manually fill the collection, use the
 * `add` & `fillBag` methods.
 */
public class Bags {

    private final HashMap<String, Bag> bags = new HashMap<>();

    public static Bags fromCharStream(CharStream stream) {
        BagsLexer lexer = new BagsLexer(stream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);

        BagsParser parser = new BagsParser(tokenStream);
        BagsParser.RulesContext tree = parser.rules();
        BagFileVisitor visitor = new BagFileVisitor();

        return visitor.visit(tree);
    }

    /**
     * Add the bag to this container, if the bag is already added, do nothing.
     *
     * @param colorCode the unique identifier of this bag
     * @return the newly added bag, or the already existing bag
     */
    public Bag add(String colorCode) {
        return bags.computeIfAbsent(colorCode, Bag::new);
    }

    /**
     * Register how many bags the `parentBag` must contain.
     *
     * @param colorCodeOfParent        bag which contain `numberOfBags` bags
     * @param colorCodeOfContainingBag the bag contained in `parentBag`
     * @param numberOfBags             the amount of bags contained in `parentBag`
     */
    public void fillBag(String colorCodeOfParent, String colorCodeOfContainingBag, int numberOfBags) {
        Bag parentBag = add(colorCodeOfParent);
        Bag childBag = add(colorCodeOfContainingBag);

        parentBag.contains(childBag, numberOfBags);
    }

    /**
     * Returns the number of bags which can contain (or contains a bag which can contain, etc.) can contain the bag
     * with the given color code.
     *
     * @param colorCode color code of the bag
     * @return number of possible outer bags
     */
    public long numberOfPossibleOuterBags(String colorCode) {
        Bag targetBag = bags.get(colorCode);

        return bags.values().stream()
                .map(b -> b.isOuterBagFor(targetBag))
                .filter(Boolean::booleanValue)
                .count();
    }

    /**
     * Calculates the total number of bags the given bag can contain.
     *
     * @param colorCode color code of the bag
     * @return total number of bags 'bagName' must contain
     */
    public long numberOfContainingBags(String colorCode) {
        Bag targetBag = bags.get(colorCode);

        return targetBag.numberOfContainingBags();
    }
}

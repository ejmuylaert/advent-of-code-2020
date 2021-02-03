package org.ernstjan.advent.day7;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashMap;

/**
 * Bag stores how many other bags it must store.
 * <p>
 * The class is a tree node with `bags` as children. `canContain` & `numberOfBags` recurse down the 'tree' to calculate
 * the answer.
 */
@EqualsAndHashCode
@ToString
public class Bag {
    private final String colorCode;
    private final HashMap<Bag, Integer> bags = new HashMap<>();

    public Bag(String colorCode) {
        this.colorCode = colorCode;
    }

    public void contains(Bag bag, int number) {
        bags.put(bag, number);
    }

    /**
     * Checks if the targetBag can be stored in this bag, or in of the bags this bag can contain.
     *
     * @param targetBag which should be carried
     * @return true if the bag can be carried in this bag
     */
    public boolean isOuterBagFor(Bag targetBag) {
        return bags.keySet().stream()
                .map(bag -> {
                    if (bag.equals(targetBag)) {
                        return true;
                    } else {
                        return bag.isOuterBagFor(targetBag);
                    }
                })
                .filter(Boolean::booleanValue)
                .findFirst()
                .orElse(false);
    }

    /**
     * Calculates the total number of bags this bags must contain
     *
     * @return total number of bags this bag must carry
     */
    public long numberOfContainingBags() {
        if (bags.isEmpty()) {
            return 0L;
        }

        return bags.entrySet().stream()
                .mapToLong(entry -> entry.getValue() + entry.getValue() * entry.getKey().numberOfContainingBags())
                .sum();
    }
}

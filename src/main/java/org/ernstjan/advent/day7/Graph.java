package org.ernstjan.advent.day7;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Graph {

    private final HashMap<String, Node> nodes = new HashMap<>();

    void add(String nodeName) {
        nodes.computeIfAbsent(nodeName, Node::new);
    }

    void connect(String parentNodeName, String childNodeName, int weight) {
        Node parent = nodes.computeIfAbsent(parentNodeName, Node::new);
        Node child = nodes.computeIfAbsent(childNodeName, Node::new);

        parent.addChild(child, weight);
        child.addParent(parent, weight);
    }

    Node get(String nodeName) {
        return nodes.get(nodeName);
    }

    public long possibilities(String nodeName) {
        return nodes.values().stream()
                .map(n -> n.contains(nodeName))
                .filter(canContain -> canContain)
                .count();
    }

    public void read(List<String> lines) {
        for (String line : lines) {
            line = removeLastChar(line); // remove `.`

            Scanner scanner = new Scanner(line).useDelimiter("contain");
            String rawBagName = scanner.next();
            String bagName = singularName(rawBagName);
            add(bagName);

            Scanner contentScanner = new Scanner(scanner.next()).useDelimiter(",");
            while (contentScanner.hasNext()) {
                String rawContent = contentScanner.next();
                if (rawContent.trim().equals("no other bags")) continue;

                Scanner containingScanner = new Scanner(rawContent).useDelimiter(" ");
                int number = Integer.parseInt(containingScanner.next());
                containingScanner.useDelimiter("-");
                String containingBag = singularName(containingScanner.next());

                connect(bagName, containingBag, number);
            }
        }
    }

    private String singularName(String rawName) {
        rawName = rawName.trim();
        int s = rawName.lastIndexOf('s');
        if (s == rawName.length() - 1) {
            return removeLastChar(rawName);
        }
        return rawName;
    }

    private String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }

    public long contains(String nodeName) {
        return nodes.get(nodeName).bags();
    }
}

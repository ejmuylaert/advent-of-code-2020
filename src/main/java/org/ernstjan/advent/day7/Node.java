package org.ernstjan.advent.day7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Node {

    private final String name;
    private final HashMap<String, Connection> children = new HashMap<>();
    private final HashMap<String, Connection> parents = new HashMap<>();

    public Node(String name) {
        this.name = name;
    }

    void addChild(Node child, int weight) {
        Connection connection = new Connection(child, weight);

        if (children.containsKey(child.getName())) throw new RuntimeException("Child already connected!!");
        children.put(child.getName(), connection);
    }

    public void addParent(Node parent, int weight) {
        Connection connection = new Connection(parent, weight);

        if (parents.containsKey(parent.getName())) throw new RuntimeException("Parent already connected!!");
        parents.put(parent.getName(), connection);
    }

    public String getName() {
        return name;
    }

    Node getParent() {
        if (parents.size() == 0) throw new RuntimeException("No parent found");
        if (parents.size() > 1) throw new RuntimeException("More then one parent");

        return parents.values().stream()
                .findFirst()
                .map(c -> c.node)
                .orElseThrow(() -> new RuntimeException("No parent found"));
    }

    Map<String, Node> getChildren() {
        return children.values().stream().map(c -> c.node).collect(Collectors.toMap(Node::getName, node -> node));
    }

    List<Connection> connections() {
        return new ArrayList<>(children.values());
    }

    public boolean contains(String nodeName) {
        return children.values().stream()
                .map(connection -> connection.node)
                .map(node -> {
                    if (node.getName().equals(nodeName)) {
                        return true;
                    } else {
                        return node.contains(nodeName);
                    }
                })
                .filter(canContain -> canContain)
                .findFirst()
                .orElse(false);
    }

    public long bags() {
        if (connections().isEmpty()) {
            return 0;
        }

        return this.connections().stream()
                .map(connection -> connection.weight + connection.weight * connection.getNode().bags())
                .mapToLong(i -> i)
                .sum();
    }

    private static class Connection {
        private final Node node;
        private final int weight;

        public Connection(Node node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        public Node getNode() {
            return node;
        }
    }
}

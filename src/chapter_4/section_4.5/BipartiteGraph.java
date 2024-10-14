/*
 * Create a version of Graph from the previous exercise to support bipartite
graphs (graphs whose edges all connect a vertex of one generic comparable type to
a vertex of another generic comparable type).
 */

import lib.SET;
import lib.Graph;

public class BipartiteGraph<T extends Comparable<T>> extends Graph<T> {

    // Constructor to create a bipartite graph
    public BipartiteGraph(SET<T> setA, SET<T> setB) {
        super(setA.size() + setB.size());

        // Add vertices from both sets to the graph
        for (T vertex : setA) {
            addVertex(vertex);
        }
        for (T vertex : setB) {
            addVertex(vertex);
        }

        // Add edges between the two sets (no edges within the same set)
        for (T vA : setA) {
            for (T vB : setB) {
                addEdge(vA, vB);  // Only between setA and setB
            }
        }
    }

    public static void main(String[] args) {
        SET<String> setA = new SET<>();
        SET<String> setB = new SET<>();

        // Example set A and B
        setA.add("A");
        setA.add("B");

        setB.add("1");
        setB.add("2");

        // Create a bipartite graph
        BipartiteGraph<String> bipartiteGraph = new BipartiteGraph<>(setA, setB);

        System.out.println("Bipartite Graph:");
        System.out.println(bipartiteGraph);
    }
}

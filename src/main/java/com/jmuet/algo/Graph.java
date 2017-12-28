package com.jmuet.algo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Graph {

    private ArrayList<ArrayList<Integer>> adjacencyList;
    private long edgesCount;

    private Graph(ArrayList<ArrayList<Integer>> adjacencyList) {
        this.adjacencyList = adjacencyList;
        edgesCount = this.adjacencyList.stream().flatMap(edges -> edges.stream()).count();
    }

    public int getVerticesCount() {
        return adjacencyList.size();
    }

    public Iterator<Integer> getVertex(int i) {
        return adjacencyList.get(i).iterator();
    }

    public long getEdgesCount() {
        return edgesCount;
    }

    public static class GraphBuilder {

        private boolean isInputNumberedFromZero;

        private ArrayList<ArrayList<Integer>> adjacencyList;

        private GraphBuilder(boolean isInputNumberedFromZero) {
            this.adjacencyList = new ArrayList<>();
        }

        public static GraphBuilder acceptingOneBasedVertices() {
            return new GraphBuilder(false);
        }

        public GraphBuilder addVertex(List<Integer> vertex) {
            ArrayList<Integer> v = new ArrayList<>();
            for (Integer edge : vertex) {
                v.add(isInputNumberedFromZero ? edge : edge - 1);
            }
            adjacencyList.add(v);
            return this;
        }

        public GraphBuilder addVertex(int[] vertex) {
            ArrayList<Integer> v = new ArrayList<>();
            for (Integer edge : vertex) {
                v.add(isInputNumberedFromZero ? edge : edge - 1);
            }
            adjacencyList.add(v);
            return this;
        }

        public Graph build() {
            if (adjacencyList == null)
                throw new RuntimeException("graph was already built");
            validate();
            Graph res = new Graph(adjacencyList);
            adjacencyList = null;
            return res;
        }

        private void validate() {
            adjacencyList.stream().flatMap(edges -> edges.stream()).forEach(edge -> {
                if (edge < 0)
                    throw new RuntimeException("malformed graph, negative edge");
                if (edge >= adjacencyList.size())
                    throw new RuntimeException("malformed graph, edge outside range");
            });
        }

    }


}

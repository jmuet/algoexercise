package com.jmuet.algo;

import java.util.*;

public class UndirectedGraph {

    private ArrayList<Integer> vertices;
    private ArrayList<ArrayList<Integer>> adjacencyList;
    private int edgesCount;

    private UndirectedGraph(ArrayList<ArrayList<Integer>> adjacencyList) {
        this.adjacencyList = adjacencyList;
        edgesCount = (int) (this.adjacencyList.stream().flatMap(edges -> edges.stream()).count() / 2);
        vertices = new ArrayList<>();
        for (int i = 0; i < adjacencyList.size(); i++)
            vertices.add(i);
    }

    public List<Integer> getVertices() {
        return new ArrayList<>(vertices);
    }

    public int getVerticesCount() {
        return vertices.size();
    }

    /**
        @return edges of given vertex. No guarantees as of edges ordering.
     */
    public List<Integer> getEdges(int vertex) {
        return Collections.unmodifiableList(adjacencyList.get(vertex));
    }

    public int getEdgesCount() {
        return edgesCount;
    }

    /**
     * Merges target vertex pointed by chosen edge with the sourceVertex, effectively removing it from graph.
     * All edges pointing to that vertex will be redirected to sourceVertex.
     * After this method completes, when invoking getEdges with target vertex index, empty list will be returned.
     * @param sourceVertex
     * @param edgeIndex
     */
    public void contract(int sourceVertex, int edgeIndex) {
        ArrayList<Integer> sourceEdges = adjacencyList.get(sourceVertex);
        Integer targetVertex = sourceEdges.get(edgeIndex);
//        System.out.println(sourceVertex+":"+edgeIndex+"->"+targetVertex);
        List<Integer> targetVertexEdges = adjacencyList.get(targetVertex);
        //rewiring edges targeting targetVertex to sourceVertex, will create loops in sourceVertex
        for (Integer vertexLinkedToTargetVertex : targetVertexEdges) {
            List<Integer> linkedVertexEdges = adjacencyList.get(vertexLinkedToTargetVertex);
            rewireEdges(linkedVertexEdges, targetVertex, sourceVertex);
        }
        //adding targetVertex edges to sourceVertex edges, except edges to sourceVertex to prevent additional loops
        for (Integer vertexLinkedToTargetVertex : targetVertexEdges) {
            if (vertexLinkedToTargetVertex == sourceVertex)
                continue;
            sourceEdges.add(vertexLinkedToTargetVertex);
        }
        //removing edges from targetVertex
        targetVertexEdges.clear();
        vertices.remove(targetVertex);
        //removing loops in source
        removeLoops(sourceVertex);
    }

    private void removeLoops(int vertex) {
        List<Integer> edges = adjacencyList.get(vertex);
        ArrayList<Integer> edgeIndexesForRemoval = new ArrayList<>();
        for (int i = 0; i < edges.size(); i++)
            if (edges.get(i) == vertex)
                edgeIndexesForRemoval.add(i - edgeIndexesForRemoval.size()); //to make those indexes directly usable in removal scenario :P
        for (Integer i : edgeIndexesForRemoval)
            edges.remove((int)i); //gawd it caught me
        edgesCount -= edgeIndexesForRemoval.size();
    }

    /**
     * mutates edges, changing edges pointing to fromVertex to toVertex
     * @param edges
     * @param fromVertex
     * @param toVertex
     */
    private void rewireEdges(List<Integer> edges, int fromVertex, int toVertex) {
        HashSet<Integer> edgeIndexesForUpdate = new HashSet<>();
        for (int i = 0; i < edges.size(); i++)
            if (edges.get(i) == fromVertex)
                edgeIndexesForUpdate.add(i);
        for (Integer i : edgeIndexesForUpdate) {
            edges.add(i, toVertex);
            edges.remove(i + 1);
        }
    }

    public static class GraphBuilder {

        private boolean isInputNumberedFromZero;

        private ArrayList<ArrayList<Integer>> adjacencyList;

        private GraphBuilder(boolean isInputNumberedFromZero) {
            this.adjacencyList = new ArrayList<>(); this.isInputNumberedFromZero = isInputNumberedFromZero;
        }

        public static GraphBuilder acceptingOneBasedVertices() {
            return new GraphBuilder(false);
        }

        public GraphBuilder addVertex(List<Integer> vertex) {
            if (vertex.size() == 0)
                throw new RuntimeException("illegal vertex without edges");
            ArrayList<Integer> v = new ArrayList<>();
            for (Integer edge : vertex) {
                v.add(isInputNumberedFromZero ? edge : edge - 1);
            }
            adjacencyList.add(v);
            return this;
        }

        public GraphBuilder addVertex(int[] vertex) {
            if (vertex.length == 0)
                throw new RuntimeException("illegal vertex without edges");
            ArrayList<Integer> v = new ArrayList<>();
            for (Integer edge : vertex) {
                v.add(isInputNumberedFromZero ? edge : edge - 1);
            }
            adjacencyList.add(v);
            return this;
        }

        public UndirectedGraph build() {
            if (adjacencyList == null)
                throw new RuntimeException("graph was already built");
            validate();
            UndirectedGraph res = new UndirectedGraph(adjacencyList);
            adjacencyList = null;
            return res;
        }

        private void validate() {
            HashMap<String, Integer> edges = new HashMap<>();
            for (int i = 0; i < adjacencyList.size(); i++) {
                final int vertexPos = i;
                ArrayList<Integer> vertex = adjacencyList.get(i);
                vertex.stream().forEach(edge -> {
                    if (edge < 0)
                        throw new RuntimeException(String.format("malformed graph, negative edge from %d to %d", vertexPos, edge));
                    if (edge >= adjacencyList.size())
                        throw new RuntimeException(String.format("malformed graph, edge from %d to %d outside range", vertexPos, edge));
                    String edgeId;
                    Integer edgeVal;
                    if (vertexPos == edge)
                        throw new RuntimeException(String.format("malformed graph, edge %d-%d is looped", vertexPos, edge));
                    if (vertexPos < edge) {
                        edgeId = vertexPos + "-" + edge;
                        edgeVal = 1;
                    } else {
                        edgeId = edge + "-" + vertexPos;
                        edgeVal = -1;
                    }
                    if (!edges.containsKey(edgeId))
                        edges.put(edgeId, edgeVal);
                    else
                        edges.put(edgeId, edges.get(edgeId) + edgeVal);
                });


            }

            for (String edgeId : edges.keySet())
                if (edges.get(edgeId) != 0)
                    throw new RuntimeException(String.format("malformed undirected graph, edge %s is directed", edgeId));
        }

    }


}

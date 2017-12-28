package com.jmuet.algo;

import java.util.*;

public class UndirectedGraph {

    private ArrayList<ArrayList<Integer>> adjacencyList;
    private long edgesCount;
    private int verticesCount;

    private UndirectedGraph(ArrayList<ArrayList<Integer>> adjacencyList) {
        this.adjacencyList = adjacencyList;
        edgesCount = this.adjacencyList.stream().flatMap(edges -> edges.stream()).count() / 2;
        verticesCount = adjacencyList.size();
    }

    public int getVerticesCount() {
        return verticesCount;
    }

    /**
        @return edges of given vertex. No guarantees as of edges ordering.
     */
    public Iterator<Integer> getVertex(int i) {
        return adjacencyList.get(i).iterator();
    }

    public long getEdgesCount() {
        return edgesCount;
    }

    /**
     * Merges target vertex pointed by chosen edge with the sourceVertex, effectively removing it from graph.
     * All edges pointing to that vertex will be redirected to sourceVertex.
     * After this method completes, when invoking getVertex with target vertex index, empty list will be returned.
     * @param sourceVertex
     * @param edgeIndex
     */
    public void contract(int sourceVertex, int edgeIndex) {
        ArrayList<Integer> sourceEdges = adjacencyList.get(sourceVertex);
        Integer targetVertex = sourceEdges.get(edgeIndex);
        List<Integer> targetVertexEdges = adjacencyList.get(targetVertex);
        //rewiring edges targeting targetVertex to sourceVertex, will create loops in sourceVertex
        for (Integer vertexLinkedToTargetVertex : targetVertexEdges) {
            List<Integer> linkedVertexEdges = adjacencyList.get(vertexLinkedToTargetVertex);
            HashSet<Integer> edgeIndexesForUpdate = new HashSet<>();
            for (int i = 0; i < linkedVertexEdges.size(); i++)
                if (linkedVertexEdges.get(i) == targetVertex)
                    edgeIndexesForUpdate.add(i);
            for (Integer i : edgeIndexesForUpdate) {
                linkedVertexEdges.add(i, sourceVertex);
                linkedVertexEdges.remove(i + 1);
            }
        }
        //adding targetVertex edges to sourceVertex edges, except edges to sourceVertex to prevent additional loops
        for (Integer vertexLinkedToTargetVertex : targetVertexEdges) {
            if (vertexLinkedToTargetVertex == sourceVertex)
                continue;
            sourceEdges.add(vertexLinkedToTargetVertex);
        }
        //removing edges from targetVertex
        targetVertexEdges.clear();
        verticesCount--;
        //removing loops in source
        ArrayList<Integer> edgeIndexesForRemoval = new ArrayList<>();
        for (int i = 0; i < sourceEdges.size(); i++)
            if (sourceEdges.get(i) == sourceVertex)
                edgeIndexesForRemoval.add(i - edgeIndexesForRemoval.size()); //to make those indexes directly usable in removal scenario :P
        for (Integer i : edgeIndexesForRemoval)
            sourceEdges.remove(i);
        edgesCount -= edgeIndexesForRemoval.size();
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

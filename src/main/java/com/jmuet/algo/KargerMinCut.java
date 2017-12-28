package com.jmuet.algo;

import java.util.Random;

public class KargerMinCut {

    public static long randomMinCut(UndirectedGraph g) {
        Random rnd = new Random(System.currentTimeMillis());
        while (g.getVerticesCount() > 2) {
            int edgeIndex = rnd.nextInt(g.getEdgesCount());
//            System.out.printf("vertices:%d, edges:%d\r\n", g.getVerticesCount(), g.getEdgesCount());
//            System.out.println("edge to be contracted:" + edgeIndex);
            for (Integer vertex : g.getVertices()) {
                int edgesCount = g.getEdges(vertex).size();
                if (edgesCount > edgeIndex) {
                    g.contract(vertex, (int) edgeIndex);
                    break;
                }
                else
                    edgeIndex -= edgesCount;
            }
        }
        return g.getEdgesCount();
    }

}

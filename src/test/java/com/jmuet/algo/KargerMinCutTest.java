package com.jmuet.algo;

import org.junit.Test;

public class KargerMinCutTest {

    @Test
    public void minCut() {
        for (int i = 0; i < 10; i++) {
            UndirectedGraph g = UndirectedGraph.GraphBuilder.acceptingOneBasedVertices()
                    .addVertex(new int[]{2, 2, 3, 3})
                    .addVertex(new int[]{1, 1, 3, 4})
                    .addVertex(new int[]{1, 1, 2})
                    .addVertex(new int[]{2})
                    .build();
            //0 === 1 --- 3
            // \\ /
            //   2

            System.out.printf("result:%d\r\n", KargerMinCut.randomMinCut(g));
        }

    }

}
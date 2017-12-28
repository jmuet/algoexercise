package com.jmuet.algo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class GraphTest {

    @Test
    public void builderCreatesEmptyGraph() {
        Graph g = Graph.GraphBuilder.acceptingOneBasedVertices().build();
        assertEquals(0, g.getVerticesCount());
    }

    @Test
    public void builderCreatesGraph() {
        Graph g = Graph.GraphBuilder.acceptingOneBasedVertices()
                .addVertex(new ArrayList<Integer>(){{add(2);add(3);}})
                .addVertex(new ArrayList<Integer>(){{add(1);}})
                .addVertex(new ArrayList<Integer>(){{add(1);add(2);}})
                .build();
        assertEquals(3, g.getVerticesCount());
        Iterator<Integer> i = g.getVertex(0);
        assertThat(i.next(), equalTo(1));
        assertThat(i.next(), equalTo(2));
    }

}
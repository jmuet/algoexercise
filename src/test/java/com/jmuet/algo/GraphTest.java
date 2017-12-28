package com.jmuet.algo;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class GraphTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

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
        assertEquals(5, g.getEdgesCount());
        Iterator<Integer> i = g.getVertex(0);
        assertThat(i.next(), equalTo(1));
        assertThat(i.next(), equalTo(2));
    }

    @Test
    public void builderCreatesGraphFromArray() {
        Graph g = Graph.GraphBuilder.acceptingOneBasedVertices()
                .addVertex(new int[]{2,3})
                .addVertex(new int[]{1})
                .addVertex(new int[]{1,2})
                .build();
        assertEquals(3, g.getVerticesCount());
        assertEquals(5, g.getEdgesCount());
        Iterator<Integer> i = g.getVertex(0);
        assertThat(i.next(), equalTo(1));
        assertThat(i.next(), equalTo(2));
    }

    @Test
    public void builderThrowsExceptionOnSecondBuildInvocation() {
        Graph.GraphBuilder builder = Graph.GraphBuilder.acceptingOneBasedVertices()
                .addVertex(new int[]{2,3})
                .addVertex(new int[]{1})
                .addVertex(new int[]{1,2});
        builder.build();

        thrown.expect(RuntimeException.class);
        thrown.expectMessage("graph was already built");
        builder.build();
    }

    @Test
    public void builderThrowsErrorWhenBuildingMalformedGraph_edgeBelowZero() {
        Graph.GraphBuilder builder = Graph.GraphBuilder.acceptingOneBasedVertices()
                .addVertex(new int[]{2, -2})
                .addVertex(new int[]{1});

        thrown.expect(RuntimeException.class);
        thrown.expectMessage("malformed graph, negative edge");
        builder.build();
    }

    @Test
    public void builderThrowsErrorWhenBuildingMalformedGraph_edgeAboveSize() {
        Graph.GraphBuilder builder = Graph.GraphBuilder.acceptingOneBasedVertices()
                .addVertex(new int[]{2,30})
                .addVertex(new int[]{1});

        thrown.expect(RuntimeException.class);
        thrown.expectMessage("malformed graph, edge outside range");
        builder.build();
    }

}
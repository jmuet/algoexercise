package com.jmuet.algo;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class UndirectedGraphTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void builderCreatesEmptyGraph() {
        UndirectedGraph g = UndirectedGraph.GraphBuilder.acceptingOneBasedVertices().build();
        assertEquals(0, g.getVerticesCount());
    }

    @Test
    public void builderCreatesUndirectedGraph() {
        UndirectedGraph g = UndirectedGraph.GraphBuilder.acceptingOneBasedVertices()
                .addVertex(new ArrayList<Integer>(){{add(2);add(3);}})
                .addVertex(new ArrayList<Integer>(){{add(1);add(3);}})
                .addVertex(new ArrayList<Integer>(){{add(1);add(2);}})
                .build();
        assertEquals(3, g.getVerticesCount());
        assertEquals(3, g.getEdgesCount());
        Iterator<Integer> i = g.getEdges(0).iterator();
            assertThat(i.next(), equalTo(1));
            assertThat(i.next(), equalTo(2));
    }

    @Test
    public void builderCreatesUndirectedGraphFromArray() {
        UndirectedGraph g = UndirectedGraph.GraphBuilder.acceptingOneBasedVertices()
                .addVertex(new int[]{2,3})
                .addVertex(new int[]{1,3})
                .addVertex(new int[]{1,2})
                .build();
        assertEquals(3, g.getVerticesCount());
        assertEquals(3, g.getEdgesCount());
        Iterator<Integer> i = g.getEdges(0).iterator();
            assertThat(i.next(), equalTo(1));
            assertThat(i.next(), equalTo(2));
    }

    @Test
    public void builderAllowsForParallelEdges() {
        UndirectedGraph g = UndirectedGraph.GraphBuilder.acceptingOneBasedVertices()
                .addVertex(new int[]{2,3,3})
                .addVertex(new int[]{1,3})
                .addVertex(new int[]{1,1,2})
                .build();
        assertEquals(3, g.getVerticesCount());
        assertEquals(4, g.getEdgesCount());
        Iterator<Integer> i = g.getEdges(0).iterator();
            assertThat(i.next(), equalTo(1));
            assertThat(i.next(), equalTo(2));
            assertThat(i.next(), equalTo(2));
    }

    @Test
    public void builderThrowsExceptionOnSecondBuildInvocation() {
        UndirectedGraph.GraphBuilder builder = UndirectedGraph.GraphBuilder.acceptingOneBasedVertices()
                .addVertex(new int[]{2,3})
                .addVertex(new int[]{1,3})
                .addVertex(new int[]{1,2});
        builder.build();

        thrown.expect(RuntimeException.class);
        thrown.expectMessage("graph was already built");
        builder.build();
    }

    @Test
    public void builderThrowsErrorWhenAddingEmptyVertex() {
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("illegal vertex without edges");

        UndirectedGraph.GraphBuilder.acceptingOneBasedVertices()
                .addVertex(new ArrayList());
    }

    @Test
    public void builderThrowsErrorWhenAddingEmptyVertex_fromArray() {
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("illegal vertex without edges");

        UndirectedGraph.GraphBuilder.acceptingOneBasedVertices()
                .addVertex(new int[0]);
    }

    @Test
    public void builderThrowsErrorWhenBuildingMalformedUndirecetdGraph_edgeBelowZero() {
        UndirectedGraph.GraphBuilder builder = UndirectedGraph.GraphBuilder.acceptingOneBasedVertices()
                .addVertex(new int[]{2, -2})
                .addVertex(new int[]{1});

        thrown.expect(RuntimeException.class);
        thrown.expectMessage("malformed graph, negative edge from 0 to -3");
        builder.build();
    }

    @Test
    public void builderThrowsErrorWhenBuildingMalformedUndirectedGraph_edgeAboveSize() {
        UndirectedGraph.GraphBuilder builder = UndirectedGraph.GraphBuilder.acceptingOneBasedVertices()
                .addVertex(new int[]{2,30})
                .addVertex(new int[]{1});

        thrown.expect(RuntimeException.class);
        thrown.expectMessage("malformed graph, edge from 0 to 29 outside range");
        builder.build();
    }

    @Test
    public void builderThrowsErrorWhenBuildingMalformedUndirectedGraph_edgeDirected() {
        UndirectedGraph.GraphBuilder builder = UndirectedGraph.GraphBuilder.acceptingOneBasedVertices()
                .addVertex(new int[]{2,3})
                .addVertex(new int[]{3})
                .addVertex(new int[]{1,2});

        thrown.expect(RuntimeException.class);
        thrown.expectMessage("malformed undirected graph, edge 0-1 is directed");
        builder.build();
    }

    @Test
    public void builderThrowsErrorWhenBuildingMalformedUndirectedGraph_edgeToSelf() {
        UndirectedGraph.GraphBuilder builder = UndirectedGraph.GraphBuilder.acceptingOneBasedVertices()
                .addVertex(new int[]{2,3})
                .addVertex(new int[]{1,3})
                .addVertex(new int[]{1,2,3});

        thrown.expect(RuntimeException.class);
        thrown.expectMessage("malformed graph, edge 2-2 is looped");
        builder.build();
    }

    @Test
    public void graphThrowsErrorOnContractingNonexistentEdge() {
        UndirectedGraph g = UndirectedGraph.GraphBuilder.acceptingOneBasedVertices()
                .addVertex(new int[]{2,3})
                .addVertex(new int[]{1,3})
                .addVertex(new int[]{1,2})
                .build();

        thrown.expect(IndexOutOfBoundsException.class);
        g.contract(1,2);
    }

    @Test
    public void graphContractsEdge() {
        UndirectedGraph g = UndirectedGraph.GraphBuilder.acceptingOneBasedVertices()
                .addVertex(new int[]{2,3})
                .addVertex(new int[]{1,3})
                .addVertex(new int[]{1,2})
                .build();
        //0 --- 1
        //  \ /
        //   2

        g.contract(0,0);
        //0 === 2

        assertEquals(2, g.getVerticesCount());
        assertEquals(2, g.getEdgesCount());
        Iterator<Integer> i = g.getEdges(0).iterator();
            assertThat(i.next(), equalTo(2));
            assertThat(i.next(), equalTo(2));
        i = g.getEdges(1).iterator();
            assertThat(i.hasNext(), equalTo(false));
        i = g.getEdges(2).iterator();
            assertThat(i.next(), equalTo(0));
            assertThat(i.next(), equalTo(0));

        i = g.getVertices().iterator();
            assertThat(i.next(), equalTo(0));
            assertThat(i.next(), equalTo(2));
    }

    @Test
    public void graphContractsEdge_preservingParallelEdges() {
        UndirectedGraph g = UndirectedGraph.GraphBuilder.acceptingOneBasedVertices()
                .addVertex(new int[]{2,2,3,3})
                .addVertex(new int[]{1,1,3,4})
                .addVertex(new int[]{1,1,2})
                .addVertex(new int[]{2})
                .build();
        //0 === 1 --- 3
        // \\ /
        //   2

        g.contract(0,0);
        //0 --- 3
        // \\\
        //   2

        assertEquals(3, g.getVerticesCount());
        assertEquals(4, g.getEdgesCount());
        Iterator<Integer> i = g.getEdges(0).iterator();
            assertThat(i.next(), equalTo(2));
            assertThat(i.next(), equalTo(2));
            assertThat(i.next(), equalTo(2));
        i = g.getEdges(1).iterator();
            assertThat(i.hasNext(), equalTo(false));
        i = g.getEdges(2).iterator();
            assertThat(i.next(), equalTo(0));
            assertThat(i.next(), equalTo(0));
            assertThat(i.next(), equalTo(0));
        i = g.getEdges(3).iterator();
            assertThat(i.next(), equalTo(0));

        i = g.getVertices().iterator();
            assertThat(i.next(), equalTo(0));
            assertThat(i.next(), equalTo(2));
            assertThat(i.next(), equalTo(3));
    }

    @Test
    public void graphContractsEdge2() {
        UndirectedGraph g = UndirectedGraph.GraphBuilder.acceptingOneBasedVertices()
                .addVertex(new int[]{2,2,3,3})
                .addVertex(new int[]{1,1,3,4})
                .addVertex(new int[]{1,1,2})
                .addVertex(new int[]{2})
                .build();
        //0 === 1 --- 3
        // \\ /
        //   2

        g.contract(0,3);
        //0 === 1 --- 3
        //  ---

        assertEquals(3, g.getVerticesCount());
        assertEquals(4, g.getEdgesCount());
        Iterator<Integer> i = g.getEdges(0).iterator();
        assertThat(i.next(), equalTo(1));
        assertThat(i.next(), equalTo(1));
        assertThat(i.next(), equalTo(1));
        i = g.getEdges(1).iterator();
        assertThat(i.next(), equalTo(0));
        assertThat(i.next(), equalTo(0));
        assertThat(i.next(), equalTo(0));
        assertThat(i.next(), equalTo(3));
        i = g.getEdges(2).iterator();
        assertThat(i.hasNext(), equalTo(false));
        i = g.getEdges(3).iterator();
        assertThat(i.next(), equalTo(1));

        i = g.getVertices().iterator();
        assertThat(i.next(), equalTo(0));
        assertThat(i.next(), equalTo(1));
        assertThat(i.next(), equalTo(3));

        g.contract(0,0);
        //0 --- 3

        assertEquals(2, g.getVerticesCount());
        assertEquals(1, g.getEdgesCount());

    }

}
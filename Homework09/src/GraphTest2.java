import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

/**
 * More student tests for the GraphSearch class.
 *
 * @author Eleanor Eason
 * @version 1.0
 */
public class GraphTest2 {
    private static final int TIMEOUT = 200;
    private Map<String, List<String>> adjList1, adjList2;
    private Map<String, List<VertexDistancePair<String>>> weightedGraph;

    @Before
    public void setUp() {
        // Adj1 looks like A -> B -> C -> D  E (E has no connections)
        adjList1 = new HashMap<>();

        List<String> aList = new LinkedList<>();
        aList.add("B");
        adjList1.put("A", aList);

        List<String> bList = new LinkedList<>();
        bList.add("C");
        adjList1.put("B", bList);

        List<String> cList = new LinkedList<>();
        cList.add("D");
        adjList1.put("C", cList);

        List<String> dList = new LinkedList<>();
        adjList1.put("D", dList);

        List<String> eList = new LinkedList<>();
        adjList1.put("E", eList);



        adjList2 = new HashMap<>();

        List<String> a2List = new LinkedList<>();
        a2List.add("B");
        adjList2.put("A", a2List);

        List<String> b2List = new LinkedList<>();
        b2List.add("A");
        adjList2.put("B", b2List);


        //Creates an undirected, weighted graph.
        weightedGraph = new HashMap<>();

        List<VertexDistancePair<String>> aListWeighted = new LinkedList<>();
        aListWeighted.add(new VertexDistancePair<String>("B", 7));
        weightedGraph.put("A", aListWeighted);

        List<VertexDistancePair<String>> bListWeighted = new LinkedList<>();
        bListWeighted.add(new VertexDistancePair<String>("A", 7));
        bListWeighted.add(new VertexDistancePair<String>("C", 1));
        bListWeighted.add(new VertexDistancePair<String>("D", 5));
        weightedGraph.put("B", bListWeighted);

        List<VertexDistancePair<String>> cListWeighted = new LinkedList<>();
        cListWeighted.add(new VertexDistancePair<String>("B", 1));
        cListWeighted.add(new VertexDistancePair<String>("D", 2));
        weightedGraph.put("C", cListWeighted);

        List<VertexDistancePair<String>> dListWeighted = new LinkedList<>();
        dListWeighted.add(new VertexDistancePair<String>("B", 5));
        dListWeighted.add(new VertexDistancePair<String>("C", 2));
        weightedGraph.put("D", dListWeighted);

        List<VertexDistancePair<String>> eListWeighted = new LinkedList<>();
        weightedGraph.put("E", eListWeighted);
    }

    @Test(timeout = TIMEOUT)
    public void testDepthSearch1() {
        assertTrue(GraphSearch.depthFirstSearch("A", adjList1, "A"));
        assertTrue(GraphSearch.depthFirstSearch("A", adjList1, "B"));
        assertTrue(GraphSearch.depthFirstSearch("A", adjList1, "D"));
        assertTrue(GraphSearch.depthFirstSearch("E", adjList1, "E"));

        assertFalse(GraphSearch.depthFirstSearch("A", adjList1, "E"));
        assertFalse(GraphSearch.depthFirstSearch("C", adjList1, "A"));
    }

    @Test(timeout = TIMEOUT)
    public void testBreadthSearch1() {
        assertTrue(GraphSearch.breadthFirstSearch("A", adjList1, "A"));
        assertTrue(GraphSearch.breadthFirstSearch("A", adjList1, "B"));
        assertTrue(GraphSearch.breadthFirstSearch("A", adjList1, "C"));
        assertTrue(GraphSearch.breadthFirstSearch("E", adjList1, "E"));

        assertFalse(GraphSearch.breadthFirstSearch("A", adjList1, "E"));
        assertFalse(GraphSearch.breadthFirstSearch("C", adjList1, "A"));
    }

    @Test(timeout = TIMEOUT)
    public void testDepthSearch2() {
        assertTrue(GraphSearch.depthFirstSearch("A", adjList2, "B"));
        assertTrue(GraphSearch.depthFirstSearch("B", adjList2, "A"));
    }

    @Test(timeout = TIMEOUT)
    public void testBreadthSearch2() {
        assertTrue(GraphSearch.breadthFirstSearch("A", adjList2, "B"));
        assertTrue(GraphSearch.breadthFirstSearch("B", adjList2, "A"));
    }


    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testException1d() {
        GraphSearch.depthFirstSearch("A", adjList1, "J");
    }
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testException2d() {
        GraphSearch.depthFirstSearch("J", adjList1, "A");
    }
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testException3d() {
        GraphSearch.depthFirstSearch(null, adjList1, "A");
    }
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testException4d() {
        GraphSearch.depthFirstSearch("A", adjList1, null);
    }
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testException5d() {
        GraphSearch.depthFirstSearch("A", null, "A");
    }


    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testException1b() {
        GraphSearch.breadthFirstSearch("A", adjList1, "J");
    }
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testException2b() {
        GraphSearch.breadthFirstSearch("J", adjList1, "A");
    }
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testException3b() {
        GraphSearch.breadthFirstSearch(null, adjList1, "A");
    }
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testException4b() {
        GraphSearch.breadthFirstSearch("A", adjList1, null);
    }
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testException5b() {
        GraphSearch.breadthFirstSearch("A", null, "A");
    }


    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testException1j() {
        GraphSearch.dijkstraShortestPathAlgorithm("A",
                weightedGraph, "J");
    }
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testException2j() {
        GraphSearch.dijkstraShortestPathAlgorithm("J",
                weightedGraph, "A");
    }
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testException3j() {
        GraphSearch.dijkstraShortestPathAlgorithm(null,
                weightedGraph, "A");
    }
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testException4j() {
        GraphSearch.dijkstraShortestPathAlgorithm("A",
                weightedGraph, null);
    }
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testException5j() {
        GraphSearch.dijkstraShortestPathAlgorithm("A",
                null, "J");
    }


    @Test(timeout = TIMEOUT)
    public void testDijkstras1() {
        assertEquals(7, GraphSearch.dijkstraShortestPathAlgorithm("A",
                weightedGraph, "B"));
        assertEquals(3, GraphSearch.dijkstraShortestPathAlgorithm("B",
                weightedGraph, "D"));
        assertEquals(-1, GraphSearch.dijkstraShortestPathAlgorithm("E",
                weightedGraph, "B"));
        assertEquals(-1, GraphSearch.dijkstraShortestPathAlgorithm("A",
                weightedGraph, "E"));
        assertEquals(10, GraphSearch.dijkstraShortestPathAlgorithm("D",
                weightedGraph, "A"));
    }

}
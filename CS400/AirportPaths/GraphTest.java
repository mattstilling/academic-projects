// --== CS400 File Header Information ==--
// Name: Matt Stilling
// Email: mstilling@wisc.edu
// Team: CN
// TA: Evan
// Lecturer: Florian
// Notes to Grader: none

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
// import org.junit.platform.console.ConsoleLauncher;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Tests the implementation of CS400Graph for the individual component of
 * Project Three: the implementation of Dijsktra's Shortest Path algorithm.
 */
public class GraphTest {

    private CS400Graph<String> graph;
    
    /**
     * Instantiate graph from last week's shortest path activity.
     */
    @BeforeEach
    public void createGraph() {
        graph = new CS400Graph<>();
        // insert vertices A-F
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");
        graph.insertVertex("F");
        // insert edges from Week 11. Shortest Path Activity
        graph.insertEdge("A","B",6);
        graph.insertEdge("A","C",2);
        graph.insertEdge("A","D",5);
        graph.insertEdge("B","E",1);
        graph.insertEdge("B","C",2);
        graph.insertEdge("C","B",3);
        graph.insertEdge("C","F",1);
        graph.insertEdge("D","E",3);
        graph.insertEdge("E","A",4);
        graph.insertEdge("F","A",1);
        graph.insertEdge("F","D",1);
    }

    /**
     * Checks the distance/total weight cost from the vertex A to F.
     */
    @Test
    public void testPathCostAtoF() {
        assertTrue(graph.getPathCost("A", "F") == 3);
    }

    /**
     * Checks the distance/total weight cost from the vertex A to D.
     */
    @Test
    public void testPathCostAtoD() {
        assertTrue(graph.getPathCost("A", "D") == 4);
    }

    /**
     * Checks the ordered sequence of data within vertices from the vertex A to D.
     */
    @Test
    public void testPathAtoD() {
        assertTrue(graph.shortestPath("A", "D").toString().equals(
            "[A, C, F, D]"
        ));
    }

    /**
     * Checks the ordered sequence of data within vertices from the vertex A to F.
     */
    @Test
    public void testPathAtoF() {
        assertTrue(graph.shortestPath("A", "F").toString().equals(
            "[A, C, F]"
        ));
    }
    
    /**
     * Step 9: checks the distance/total weight cost from the vertex D to B.
     */
    @Test
    public void testPathCostDtoB() {
        assertTrue(graph.getPathCost("D", "B") == 12);
    }
    
    /**
     * Step 10: checks the ordered sequence of data within vertices from the vertex D to B.
     */
    @Test
    public void testPathDtoB() {
        assertTrue(graph.shortestPath("D", "B").toString().equals(
            "[D, E, A, C, B]"
        ));
    }
    
    /**
     * Step 11: checks the distance/total weight cost from the vertex E to F.
     */
    @Test
    public void testPathCostEtoF() {
        assertTrue(graph.getPathCost("E", "F") == 7);
    }
    
    /**
     * Step 12: checks the predecessor to node B along the shortest path from F to B.
     */
    @Test
    public void testPredecessorB() {
        List<String> path = graph.shortestPath("F", "B");
        assertTrue(path.get(path.size() - 2).equals("C"));
    }
    
    /**
     * Step 13 (test method of my choice): checks that the distance/total weight cost from a vertex 
     * to itself is 0.
     */
    @Test
    public void testPathCostAtoA() {
        assertTrue(graph.getPathCost("A", "A") == 0);
    }
    
    /**
     * Step 13 (test method of my choice): checks that the expected exceptions are thrown
     */
    @Test
    public void testExceptions() {
        // Starting vertex not in graph
        assertThrows(NoSuchElementException.class, () -> graph.shortestPath("G", "A"));
        
        // Ending vertex not in graph
        assertThrows(NoSuchElementException.class, () -> graph.shortestPath("G", "A"));
        
        // No path from start to end
        graph.insertVertex("G");
        graph.insertEdge("G", "E", 5);
        assertThrows(NoSuchElementException.class, () -> graph.shortestPath("A", "G"));
    }

    public static void main(String[] args) {
        String className = MethodHandles.lookup().lookupClass().getName();
        String classPath = System.getProperty("java.class.path").replace(" ", "\\ ");
        String[] arguments = new String[] {
          "-cp",
          classPath,
          "--include-classname=.*",
          "--select-class=" + className };
        // ConsoleLauncher.main(arguments);
    }

}
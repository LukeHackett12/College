import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/*
 *  Choice of data structures:
 *     - For the adjacency table in the Graph class I used an array of Hashsets
 *       as this results in a faster runtime due to accesses.
 *     - I held the distances in a 2d array as it was very easy to access and
 *       compare values.
 *     
 *  Differences between Dijkstra and Floyd-Warshall
 *      - In this problem we need to find the longest possible
 *          time taken from any point so floyd warshall suits
 *          better for this problem as it returns the required 2d
 *          array
 *      - Dijstra is not effected by the denser graphs as much as 
 *          it uses a hashset list instead of a matrix, floyd warshall improves
 *          on denser datasets however as it is not dependant on a queue. I would chose
 *          mu dijkstra for both as it would beat out floyd warshall.
 */

public class CompetitionTests {

    @Test
    public void testDijkstraConstructor() {
        int minTime = new CompetitionDijkstra("/home/luke/Documents/College/2nd year/Algorithms/Graphs/main/src/1000EWD.txt", 75, 56, 87).timeRequiredforCompetition();
        assertEquals("1000 input returns 25", minTime, 25);

        minTime = new CompetitionDijkstra("/home/luke/Documents/College/2nd year/Algorithms/Graphs/main/src/tinyEWD.txt", 75, 56, 87).timeRequiredforCompetition();
        assertEquals("Tiny input returns 34", minTime, 34);

        minTime = new CompetitionDijkstra("/home/luke/Documents/College/2nd year/Algorithms/Graphs/main/src/testIn.txt", 75, 56, 87).timeRequiredforCompetition();
        assertEquals("Custom input with no path returns -1", minTime, -1);

        minTime = new CompetitionDijkstra("sdfsdfasdfas", 56, 78, 57).timeRequiredforCompetition();
        assertEquals("Invalid name returns -1", minTime, -1);

        minTime = new CompetitionDijkstra(null, 56, 78, 57).timeRequiredforCompetition();
        assertEquals("Null name returns -1", minTime, -1);
    }

    @Test
    public void testFWConstructor() {
        int minTime = new CompetitionFloydWarshall("/home/luke/Documents/College/2nd year/Algorithms/Graphs/main/src/1000EWD.txt", 75, 56, 87).timeRequiredforCompetition();
        assertEquals("1000 input returns 25", minTime, 25);

        minTime = new CompetitionFloydWarshall("/home/luke/Documents/College/2nd year/Algorithms/Graphs/main/src/tinyEWD.txt", 75, 56, 87).timeRequiredforCompetition();
        assertEquals("Tiny input returns 34", minTime, 34);

        minTime = new CompetitionFloydWarshall("/home/luke/Documents/College/2nd year/Algorithms/Graphs/main/src/testIn.txt", 75, 56, 87).timeRequiredforCompetition();
        assertEquals("Custom input with no path returns -1", minTime, -1);

        minTime = new CompetitionFloydWarshall("sdfsdfasdfas", 56, 78, 57).timeRequiredforCompetition();
        assertEquals("Invalid name returns -1", minTime, -1);

        minTime = new CompetitionFloydWarshall(null, 56, 78, 57).timeRequiredforCompetition();
        assertEquals("Null name returns -1", minTime, -1);
    }

    //TODO - more tests
    
}

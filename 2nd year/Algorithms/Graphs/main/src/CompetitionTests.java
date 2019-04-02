import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

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

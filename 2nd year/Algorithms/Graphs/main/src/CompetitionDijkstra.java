/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants’
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *     Each contestant walks at a given estimated speed.
 *     The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Dijkstra's algorithm
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class CompetitionDijkstra {

    private int N;
    private int S;

    private int sA;
    private int sB;
    private int sC;

    private EdgeWeightedDigraph graph;

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA,       sB, sC: speeds for 3 contestants
     */
    CompetitionDijkstra(String filename, int sA, int sB, int sC) {
        this.sA = sA;
        this.sB = sB;
        this.sC = sC;

        BufferedReader br;
        try {
            if (filename != null) {
                br = new BufferedReader(new FileReader(filename));
                int lineNum = 0;
                try {
                    String line = br.readLine();
                    while (line != null) {
                        if (lineNum == 0) {
                            N = Integer.parseInt(line);
                        } else if (lineNum == 1) {
                            S = Integer.parseInt(line);
                            graph = new EdgeWeightedDigraph(N, S);
                        } else {
                            String[] split = line.trim().split("\\s+");

                            assert graph != null;
                            graph.addIntersection(Integer.parseInt(split[0]),
                                    Integer.parseInt(split[1]),
                                    Double.parseDouble(split[2]));
                        }

                        lineNum++;
                        line = br.readLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                graph = null;
            }
        } catch (FileNotFoundException e) {
            graph = null;
        }
    }

    /**
     * @return int: minimum minutes that will pass before the three contestants can meet
     */
    int timeRequiredforCompetition() {
        if (graph != null) {
            if(sA < 50 || sB < 50 || sC < 50
                    || sA > 100 || sB > 100 || sC > 100) return -1;

            double[][] dists = new double[graph.V][graph.V];
            for (int i = 0; i < graph.V; i++) {
                dists[i] = getShortestAtVertex(i);
            }

            double d = getLargestDistance(dists);
            if (Double.POSITIVE_INFINITY == d) {
                return -1;
            } else {
                int slowestSpeed = getSlowestSpeed();
                slowestSpeed = (int) Math.ceil((d * 1000) / slowestSpeed);
                return slowestSpeed;
            }
        }

        return -1;
    }

    private int getSlowestSpeed() {
        int[] nums = new int[]{sA, sB, sC};
        Arrays.sort(nums);
        return nums[0];
    }

    private double getLargestDistance(double[][] dists) {
        double largest = 0;
        for (double[] array : dists) {
            for (double dist : array) {
                if (largest < dist)
                    largest = dist;
            }
        }

        return largest;
    }

    private double[] getShortestAtVertex(int i) {
        double[] distTo = new double[graph.V];

        for (int v = 0; v < graph.V; v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[i] = 0.0;

        Comparator<Path> comparator = new MyComparator();
        PriorityQueue<Path> paths = new PriorityQueue<Path>(graph.V, comparator);

        paths.add(new Path(i, distTo[i]));
        while (!paths.isEmpty()) {
            Path path = paths.poll();
            for (DirectedEdge edge : graph.adj[path.v]) {
                int v = edge.v;
                int w = edge.w;
                if (distTo[w] > distTo[v] + edge.weight) {
                    distTo[w] = distTo[v] + edge.weight;

                    if (!alternateContains(paths, w)) {
                        paths.add(new Path(w, distTo[w]));
                    } else {
                        paths = replaceSpecial(paths, w, distTo[w]);
                    }
                }
            }
        }

        return distTo;
    }

    private PriorityQueue<Path> replaceSpecial(PriorityQueue<Path> paths, int w, double newWeight) {
        for (Path path : paths) {
            if (path.v == w) {
                paths.remove(path);
                path.weight = newWeight;
                paths.add(path);
                break;
            }
        }

        return paths;
    }

    private boolean alternateContains(PriorityQueue<Path> paths, int w) {
        for (Path path : paths) {
            if (path.v == w)
                return true;
        }
        return false;
    }
}

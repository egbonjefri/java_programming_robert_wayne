/*
 * Watts and Strogatz proposed a hybrid model that contains typical links of vertices
near each other (people know their geographic neighbors), plus some random
long-range connection links. Plot the effect of adding random edges to an n-by-n
grid graph on the average path length and on the cluster coefﬁcient, for n = 100. Do
the same for k-ring graphs on V vertices, for V = 10,000 and various values of k up
to 10 log V.
 */

import lib.Graph;
import generators.GraphGenerator;
import lib.SmallWorld;
import lib.ArrayList;
import lib.StdDraw;



public class WattsStrogatzGraphModel {
  
        public static void main(String[] args) {
            // Analysis for grid graph
            analyzeGridGraph(100);
    
            // Analysis for k-ring graphs
            analyzeKRingGraphs(10000);
        }
    
        private static void analyzeGridGraph(int n) {
            Graph<String> gridGraph = GraphGenerator.generateGridGraph(n);
            int totalEdges = n * n * 2; // Approximate number of edges in an n x n grid
            ArrayList<Double> apls = new ArrayList<>();
            ArrayList<Double> clusteringCoefficients = new ArrayList<>();
    
            for (int i = 0; i <= totalEdges / 10; i++) { // Add up to 10% more edges
                apls.add(SmallWorld.averagePathLength(gridGraph));
                clusteringCoefficients.add(SmallWorld.clusteringCoefficient(gridGraph));
                gridGraph.addRandomLongRangeEdges(i);;
            }
    
            plotResults("Grid Graph (100x100)", apls, clusteringCoefficients);
        }
    
        private static void analyzeKRingGraphs(int vertices) {
            int maxK = (int) (10 * Math.log(vertices));
            for (int k = 1; k <= maxK; k++) {
                Graph<Integer> kRingGraph = GraphGenerator.generateKRingGraph(vertices, k);
                int totalEdges = vertices * k;
                ArrayList<Double> apls = new ArrayList<>();
                ArrayList<Double> clusteringCoefficients = new ArrayList<>();
    
                for (int i = 0; i <= totalEdges / 10; i++) { // Add up to 10% more edges
                    apls.add(SmallWorld.averagePathLength(kRingGraph));
                    clusteringCoefficients.add(SmallWorld.clusteringCoefficient(kRingGraph));
                    kRingGraph.addRandomLongRangeEdges(i);
                }
    
                plotResults("K-Ring Graph (V=" + vertices + ", k=" + k + ")", apls, clusteringCoefficients);
            }
        }
    
        private static void plotResults(String title, ArrayList<Double> apls, ArrayList<Double> clusteringCoefficients) {
            StdDraw.setCanvasSize(800, 400);
            StdDraw.setXscale(0, apls.size());
            StdDraw.setYscale(0, 1);
            StdDraw.setPenRadius(0.005);
    
            // Plot APL
            StdDraw.setPenColor(StdDraw.RED);
            for (int i = 1; i < apls.size(); i++) {
                StdDraw.line(i - 1, apls.get(i - 1), i, apls.get(i));
            }
    
            // Plot Clustering Coefficient
            StdDraw.setPenColor(StdDraw.BLUE);
            for (int i = 1; i < clusteringCoefficients.size(); i++) {
                StdDraw.line(i - 1, clusteringCoefficients.get(i - 1), i, clusteringCoefficients.get(i));
            }
    
            // Add title and legend
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.text(apls.size() / 2, 0.95, title);
            StdDraw.text(apls.size() - 20, 0.1, "Red: APL");
            StdDraw.text(apls.size() - 20, 0.05, "Blue: Clustering Coefficient");
        }
    }

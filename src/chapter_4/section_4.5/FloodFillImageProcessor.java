/*
 * A Picture is a two-dimensional array of Color
values (see SECTION 3.1) that represent pixels. A blob is a collection of neighboring
pixels of the same color. Write a Graph client whose constructor creates a grid graph
(see EXERCISE 4.5.27) from a given image and supports the flood fill operation. Given
pixel coordinates col and row and a color color, change the color of that pixel and
all the pixels in the same blob to color.
 */

 import java.awt.Color;
 import lib.Graph;
 import lib.Queue;
 
 public class FloodFillImageProcessor {
     private final Graph<Integer> graph;
     private final Color[][] image;
     private final int rows;
     private final int cols;
 
     public FloodFillImageProcessor(Color[][] image) {
         this.image = image;
         this.rows = image.length;
         this.cols = image[0].length;
         this.graph = createGridGraph();
     }
 
     private Graph<Integer> createGridGraph() {
         Graph<Integer> g = new Graph<>(rows * cols);
         for (int r = 0; r < rows; r++) {
             for (int c = 0; c < cols; c++) {
                 int v = r * cols + c;
                 if (r > 0) g.addEdge(v, (r-1) * cols + c);  // Up
                 if (r < rows-1) g.addEdge(v, (r+1) * cols + c);  // Down
                 if (c > 0) g.addEdge(v, r * cols + (c-1));  // Left
                 if (c < cols-1) g.addEdge(v, r * cols + (c+1));  // Right
             }
         }
         return g;
     }
 
     public void floodFill(int row, int col, Color newColor) {
         if (row < 0 || row >= rows || col < 0 || col >= cols) {
             throw new IllegalArgumentException("Invalid pixel coordinates");
         }
 
         Color oldColor = image[row][col];
         if (oldColor.equals(newColor)) {
             return;  // No need to fill if the color is already the same
         }
 
         int startVertex = row * cols + col;
         Queue<Integer> queue = new Queue<>();
         queue.enqueue(startVertex);
 
         while (!queue.isEmpty()) {
             int v = queue.dequeue();
             int r = v / cols;
             int c = v % cols;
 
             if (image[r][c].equals(oldColor)) {
                 image[r][c] = newColor;
                 for (int w : graph.adj(v)) {
                     queue.enqueue(w);
                 }
             }
         }
     }
 
     // Getter for the image (for testing purposes)
     public Color[][] getImage() {
         return image;
     }
 }

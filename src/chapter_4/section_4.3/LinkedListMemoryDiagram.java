import lib.StdDraw;
import lib.LinkedList;
import java.util.Iterator;
/*
 * Draw a memory-usage diagram in the style of the diagrams in SECTION 4.1
for the three-node example used to introduce linked lists in this section
 */
public class LinkedListMemoryDiagram {
    public static void main(String[] args) {
        int n = 5; // Number of nodes in the linked list

        LinkedList<Integer> list = new LinkedList<>();

        for(int i = 0; i < n; i++){
            list.add(i);
        }
        
        // Set up the drawing canvas
        StdDraw.setCanvasSize(800, 400);
        StdDraw.setXscale(0, 100);
        StdDraw.setYscale(0, 50);
        
        // Draw the memory diagram
        drawMemoryDiagram(list);
    }
    

    private static void drawMemoryDiagram(LinkedList<Integer> list) {
        double x = 10;
        double y = 25;
        Iterator<Integer> iterator = list.iterator();
        Integer current = null;

        while (iterator.hasNext()) {
            current = iterator.next();
            // Draw node
            StdDraw.rectangle(x, y, 4, 2);
            StdDraw.text(x - 2, y, String.valueOf(current));
           
            StdDraw.text(x + 2, y, iterator.hasNext() ? "•" : "null");
            
            // Draw arrow to next node
            if (iterator.hasNext()) {
                StdDraw.line(x + 4, y, x + 10, y);
                StdDraw.line(x + 10, y, x + 8, y + 0.5);
                StdDraw.line(x + 10, y, x + 8, y - 0.5);
            }
            
            // Draw memory address
            StdDraw.text(x, y + 4, "0x" + Integer.toHexString(System.identityHashCode(current)));
            
            // Move to next node position
            x += 18;
        }
        
        // Draw list name and head arrow
        StdDraw.text(5, 40, "head");
        StdDraw.line(5, 38, 10, 27);
        StdDraw.line(10, 27, 8, 28);
        StdDraw.line(10, 27, 9, 25);
    }
}
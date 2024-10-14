/*
 * Consider the following variant of the towers of
 * Hanoi problem. There are 2n discs of increasing size stored on three poles. Initially
 * all of the discs with odd size (1, 3, ..., 2n-1) are piled on the left pole from top to bottom
 *  in increasing order of size; all of the discs with even size (2, 4, ..., 2n)
 * are piled on the right pole. Write a program to provide instructions for
 * moving the odd discs to the right pole and the even discs to the left pole,
 * obeying the same rules as for towers of Hanoi.
 */


public class TowerofHanoiVariant {

        public static void main(String[] args) {
            int n = 3; // Example for 2n discs, n odd and n even
            moveDiscs(n, "Left", "Right", "Middle", true); // Move odd discs to right pole
            moveDiscs(n, "Right", "Left", "Middle", false); // Move even discs to left pole
        }
    
        /**
         * Moves the discs from one pole to another.
         *
         * @param n        the number of discs to move.
         * @param fromPole the pole from which to move the discs.
         * @param toPole   the pole to which to move the discs.
         * @param auxPole  the auxiliary pole to use for moving.
         * @param isOdd    if true, move odd discs; if false, move even discs.
         */
        public static void moveDiscs(int n, String fromPole, String toPole, String auxPole, boolean isOdd) {
            if (n > 0) {
                moveDiscs(n - 1, fromPole, auxPole, toPole, isOdd);
                if (isOdd) {
                    System.out.println("Move disk " + (2 * n - 1) + " from " + fromPole + " to " + toPole);
                } else {
                    System.out.println("Move disk " + (2 * n) + " from " + fromPole + " to " + toPole);
                }
                moveDiscs(n - 1, auxPole, toPole, fromPole, isOdd);
            }
        }
    }

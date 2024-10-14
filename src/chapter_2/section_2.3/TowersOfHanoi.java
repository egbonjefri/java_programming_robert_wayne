import lib.StdOut;



public class TowersOfHanoi{
    /**
 * Proves by induction the minimum number of moves required to solve the Towers of Hanoi for n disks.
 * 
 * Theorem: The minimum number of moves required to transfer n disks from one peg to another, following the game's rules, is 2^n - 1.
 * 
 * Proof:
 * - Base Case: For n = 1 (one disk), it takes exactly one move (2^1 - 1 = 1) to transfer the disk to the target peg, satisfying the theorem.
 * 
 * - Inductive Step:
 *   Assume the theorem holds for n = k, i.e., it takes 2^k - 1 moves for k disks. We must show it holds for n = k + 1 disks.
 *   To move k + 1 disks, the process is:
 *     1. Move k disks to an auxiliary peg (2^k - 1 moves, by induction hypothesis),
 *     2. Move the (k+1)th disk to the target peg (1 move),
 *     3. Move the k disks from the auxiliary peg to the target peg, on top of the (k+1)th disk (2^k - 1 moves, by induction hypothesis).
 *   The total moves for k + 1 disks are 2^k - 1 + 1 + 2^k - 1 = 2 * 2^k - 1 = 2^(k+1) - 1, proving the theorem for n = k + 1.
 * 
 * - Conclusion: By mathematical induction, the theorem is proven true for all natural numbers n, demonstrating that the Towers of Hanoi problem requires 2^n - 1 moves for n disks.
 * 
 * This proof highlights the exponential growth in the number of moves required as the number of disks increases, reflecting the complexity of the Towers of Hanoi problem.
 */

        public static void moves(int n, boolean left){
            if (n == 0) return;
            moves(n-1, !left);
            if (left) StdOut.println(n + " left");
            else StdOut.println(n + " right");
            moves(n-1, !left);
        }
        /**
         * @param n number of disks
         * @param start the first tower
         * @param end the last tower
         */

        public static void hanoi(int n, int start, int end){
            if(n==1){
                StdOut.println(start +" -> "+ end);
            }
            else{
                int other = 6 - (start + end);
                hanoi(n-1, start, other);
                StdOut.println(start +" -> "+ end);
                hanoi(n-1, other, end);
            }
        }
        public static void main(String[] args) {
        // Read n, print moves to move n discs left.
        int n = Integer.parseInt(args[0]);
        hanoi(n, 1,3);
        //moves(n,true);
}
}
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/*
 * In the game of chess, if a board position is repeated three times with the same side to move,
 *  the side to move can declare a draw.
Describe how you could test this condition using a computer program.
 */

 /*
  * Approach
Hash Representation of Board States:

Assign a unique hash value to each board position. 
This can be done using a hashing technique like Zobrist hashing, 
which generates a unique identifier (hash) for each possible board configuration, 
accounting for the pieces' positions, castling rights, en passant rights, and the side to move.


Store Board States:

As each move is made, compute the hash for the current board position and 
store this hash in a list or a hash map (hash table).


Check for Repetition:

After each move, check how many times the current board's hash has appeared in the list. 
If it appears three times, the current side can declare a draw by threefold repetition.
  */
class ZobristHashing {
    private static final int BOARD_SIZE = 8;
    private static final int NUM_PIECES = 12; // 6 piece types for each color
    private static final long[][][] zobristTable = new long[BOARD_SIZE][BOARD_SIZE][NUM_PIECES];
    private static final long sideToMove;
    private static final long[] castlingRights = new long[4]; // K, Q, k, q
    private static final long[] enPassantFile = new long[8];

    static {
        Random rand = new Random(123); // Fixed seed for reproducibility
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                for (int k = 0; k < NUM_PIECES; k++) {
                    zobristTable[i][j][k] = rand.nextLong();
                }
            }
        }
        sideToMove = rand.nextLong();
        for (int i = 0; i < 4; i++) {
            castlingRights[i] = rand.nextLong();
        }
        for (int i = 0; i < 8; i++) {
            enPassantFile[i] = rand.nextLong();
        }
    }

    public static long hash(ChessBoard board) {
        long h = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                int piece = board.getPiece(i, j);
                if (piece != -1) {
                    h ^= zobristTable[i][j][piece];
                }
            }
        }
        if (board.isWhiteToMove()) {
            h ^= sideToMove;
        }
        for (int i = 0; i < 4; i++) {
            if (board.getCastlingRight(i)) {
                h ^= castlingRights[i];
            }
        }
        int enPassantFile = board.getEnPassantFile();
        if (enPassantFile != -1) {
            h ^= enPassantFile;
        }
        return h;
    }
}

class ChessBoard {
    private int[][] board;
    private boolean whiteToMove;
    private boolean[] castlingRights;
    private int enPassantFile;

    public ChessBoard() {
        board = new int[8][8];
        whiteToMove = true;
        castlingRights = new boolean[4];
        enPassantFile = -1;
        // Initialize board, castling rights, etc.
    }

    public int getPiece(int row, int col) {
        return board[row][col];
    }

    public boolean isWhiteToMove() {
        return whiteToMove;
    }

    public boolean getCastlingRight(int index) {
        return castlingRights[index];
    }

    public int getEnPassantFile() {
        return enPassantFile;
    }

}

public class RepetitionDrawInChess {
    private static final Map<Long, Integer> positionCount = new HashMap<>();

    public static void makeMove(ChessBoard board, String move) {


        long hash = ZobristHashing.hash(board);
        int count = positionCount.getOrDefault(hash, 0) + 1;
        positionCount.put(hash, count);

        if (count >= 3) {
            System.out.println("Threefold repetition! The current side can declare a draw.");
        }
    }

    public static void main(String[] args) {
        ChessBoard game = new ChessBoard();
        
        // Simulate some moves
        makeMove(game, "e4");
        makeMove(game, "e5");
        makeMove(game, "Nf3");
        makeMove(game, "Nf6");
        makeMove(game, "Ng1");
        makeMove(game, "Ng8");
        makeMove(game, "Nf3");
        makeMove(game, "Nf6");
        makeMove(game, "Ng1");
        makeMove(game, "Ng8");
    }
}
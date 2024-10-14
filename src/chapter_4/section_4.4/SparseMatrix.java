/*
 * An n-by-n matrix is sparse if its number of nonzeros is
proportional to n (or less). Your goal is to represent a matrix with space proportion-
al to n, and to be able to add and multiply two sparse matrices in time proportional
to the total number of nonzeros (perhaps with an extra log n factor). 

Implement a class that supports the following API:
        public class SparseMatrix
                SparseMatrix()                  create a matrix
void            put(int i, int j, double v)     set a_ij to v
double          get(int i, int j)               return aij
SparseMatrix    plus(SparseMatrix b)            matrix addition
SparseMatrix    times(SparseMatrix b)           matrix product

 */

import lib.ST;
public class SparseMatrix {
    // Map to store non-zero elements with keys as (i, j) pairs
    private ST<Pair, Double> elements;

    // Pair class to represent matrix indices
    private static class Pair implements Comparable<Pair> {
        int row, col;

        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return row == pair.row && col == pair.col;
        }

        @Override
        public int hashCode() {
            return 31 * row + col;
        }
        @Override
        public int compareTo(Pair other) {
            if (this.row != other.row) {
                return Integer.compare(this.row, other.row);
            } else {
                return Integer.compare(this.col, other.col);
            }
        }
    }

    public SparseMatrix() {
        elements = new ST<>();
    }

    public void put(int i, int j, double v) {
        Pair key = new Pair(i, j);
        if (v != 0) {
            elements.put(key, v);
        } else {
            elements.remove(key);
        }
    }

    public double get(int i, int j) {
        Pair key = new Pair(i, j);
        return elements.getOrDefault(key, 0.0);
    }

   
    public SparseMatrix plus(SparseMatrix b) {
        SparseMatrix result = new SparseMatrix();

        // Add elements from this matrix to the result
        for (Pair key : this.elements.keys()) {
            result.put(key.row, key.col, this.get(key.row, key.col));
        }

        // Add elements from matrix b to the result, updating existing keys
        for (Pair key : b.elements.keys()) {
            result.put(key.row, key.col, result.get(key.row, key.col) + b.get(key.row, key.col));
        }

        return result;
    }

    public SparseMatrix times(SparseMatrix b) {
        SparseMatrix result = new SparseMatrix();

        // Iterate over non-zero elements of this matrix
        for (Pair key1 : this.elements.keys()) {
            double value1 = this.get(key1.row, key1.col);

            // Iterate over non-zero elements of matrix b
            for (Pair key2 : b.elements.keys()) {
                if (key1.col == key2.row) {  // Ensure the columns and rows match for multiplication
                    Pair resultKey = new Pair(key1.row, key2.col);
                    double value2 = b.get(key2.row, key2.col);
                    result.put(resultKey.row, resultKey.col, result.get(resultKey.row, resultKey.col) + value1 * value2);
                }
            }
        }

        return result;
    }
}
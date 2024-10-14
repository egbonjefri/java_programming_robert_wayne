/*
 * A d-dimensional vector is sparse if its number of nonzero
values is small. Your goal is to represent a vector with space proportional to its
number of nonzeros, and to be able to add two sparse vectors in time proportional
to the total number of nonzeros. 

Implement a class that supports the following API:
    public class SparseVector
                SparseVector()             create a vector
void            put(int i, double v)       set a_i to v
double          get(int i)                 return a_i
double          dot(SparseVector b)        vector dot product
SparseVector    plus(SparseVector b)       vector addition
 */

import lib.ST;
import lib.StdOut;

public class SparseVector {
    private ST<Integer, Double> vector; // Map to store index-value pairs

    // Constructor to initialize the sparse vector
    public SparseVector() {
        vector = new ST<>();
    }

    // Set the value of the vector at index i to v
    public void put(int i, double v) {
        if (v != 0.0) {
            vector.put(i, v);
        } else {
            vector.remove(i); // We remove the entry if value is 0 to save space
        }
    }

    // Get the value at index i (return 0.0 if no such entry)
    public double get(int i) {
        return vector.getOrDefault(i, 0.0);
    }

    // Compute the dot product of this vector with another SparseVector
    public double dot(SparseVector b) {
        double result = 0.0;
        for (int index : this.vector.keys()) { 
            result += this.get(index) * b.get(index); // Get values from both vectors
        }
        return result;
    }
    
    // Add two sparse vectors and return the resulting SparseVector
    public SparseVector plus(SparseVector b) {
        SparseVector result = new SparseVector();

        // Add elements from this vector to the result
        for (int index : this.vector.keys()) {
            result.put(index, this.get(index));
        }

        // Add elements from vector b to the result, updating existing keys
        for (int index : b.vector.keys()) {
            result.put(index, result.get(index) + b.get(index));
        }

        return result;
    }

    // Print the sparse vector for debugging
    public void print() {
        for (int index : vector.keys()) {
            StdOut.println("Index " + index + ": " + vector.get(index));
        }
    }
}

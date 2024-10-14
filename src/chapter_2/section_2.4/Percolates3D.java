import lib.BooleanMatrix3D;



public class Percolates3D {

        private boolean[][][] openSites; // true if open, false if blocked
        private boolean[][][] visited; // to mark visited sites during DFS
        private int n; // size of the cube
    
        public Percolates3D(int n, double p) {
            this.n = n;
            openSites = BooleanMatrix3D.generateRandomBooleanMatrix3D(3,4,5);
            visited = new boolean[n][n][n];
        }
    
        private boolean isValid(int x, int y, int z) {
            return x >= 0 && x < n && y >= 0 && y < n && z >= 0 && z < n;
        }
    
        private void dfs(int x, int y, int z) {
            if (!isValid(x, y, z) || visited[x][y][z] || !openSites[x][y][z]) {
                return;
            }
            visited[x][y][z] = true;
    
            // Recursive calls for six neighbors
            dfs(x + 1, y, z); // right
            dfs(x - 1, y, z); // left
            dfs(x, y + 1, z); // up
            dfs(x, y - 1, z); // down
            dfs(x, y, z + 1); // front
            dfs(x, y, z - 1); // back
        }
    
        public boolean percolates() {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    visited = new boolean[n][n][n]; // reset visited for each top face site
                    dfs(0, i, j); // start DFS from each site on the top plane
                    // Check for connection to bottom plane
                    for (int k = 0; k < n; k++) {
                        if (visited[n-1][k][j]) {
                            return true; // percolates if any site on the bottom is reached
                        }
                    }
                }
            }
            return false;
        }
    
        // Example usage
        public static void main(String[] args) {
            int n = 10; // cube size
            double p = 0.5; // open probability
            Percolates3D percolation = new Percolates3D(n, p);
            System.out.println("Percolates: " + percolation.percolates());
        }
    }


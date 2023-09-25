public class  IslandPerimeter {
    public int islandPerimeter(int[][] grid) {
        int ans = 0;
        int m = grid.length;
        int n = grid[0].length;

        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j] == 1) {
                    ans += 4;

                    if (i+1 < m && grid[i+1][j]  == 1) {
                        ans -=2;
                    }

                    if (j+1 < n && grid[i][j+1]  == 1) {
                        ans -=2;
                    }
                }
            }
        }

        return ans;
    }

    public static void main (String args[]) {
        int [][] grid = new int[][] {{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}};
        IslandPerimeter obj = new IslandPerimeter();
        obj.islandPerimeter(grid);
    }
}

/**
 * You are given row x col grid representing a map where grid[i][j] = 1 represents land and grid[i][j] = 0 represents water.
 * Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island
 * (i.e., one or more connected land cells).
 *
 * The island doesn't have "lakes", meaning the water inside isn't connected to the water around the island. One cell is a square with side length 1
 * The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.
 *
 * https://leetcode.com/problems/island-perimeter/
 *
 * The island cells are 4-connected which means that any square (row, column) for which holds 0<row<n-1 and 0<column<m-1, it is connected to sqaures
 * (row+1, column), (row-1, column), (row, column+1) and (row, column-1). The squares in first and last row and first and last column have 2-3 connected cells. 
 * So, we can thnk of the grid as a graph with connected cells being adjacent vertexes. Then the perimeter of the island is the sum of all edges between land cells
 * and water cells. We walk though the graph using DFS algorithm and sum up all edges between land and water cells.
 *
 * Time complexity: O(sum of number of connected cells)
 * Space complexity: O(1)
 */
class CalculatePerimeterOfIsland {
    private int perimiter;
    public int islandPerimeter(int[][] grid) {       
        int[][] marked = new int[grid.length][grid[0].length];
        process(grid, 0, 0, marked);
        return perimiter;
    }
    
    private void process(int[][] grid, int row, int col, int[][] marked) {
        marked[row][col]++; 
        
        // cell is in the first row, hence it is always nnected to at least one water cell (in grid visualisation, on its top border).
        if (grid[row][col] == 1 && row == 0) {            
            perimiter++;
        }
        
        // cell is in the last row, hence it is always nnected to at least one water cell (in grid visualisation, on its bottom border).
        if (grid[row][col] == 1 && row == grid.length-1) {            
            perimiter++;
        }
        
        // cell is in the first column, hence it is always nnected to at least one water cell (in grid visualisation, on its left border).
        if (grid[row][col] == 1 && col == 0) {
            perimiter++;
        }
        
        / /cell is in the last column, hence it is always nnected to at least one water cell (in grid visualisation, on its right border).
        if (grid[row][col] == 1 && col == grid[0].length-1) {
            perimiter++;
        }
         
        // cell is connected to a cell above it.
        if (row-1 >= 0) {           
            if (grid[row-1][col]==0 && grid[row][col]==1) { // it is an edge between an island and water cells, so incclude it in the perimiter count.
                perimiter++;            
            }
            
            if (marked[row-1][col] == 0) {
                process(grid, row-1, col, marked);
            }
        }
        
        // cell is connected to a cell below it.
        if (row+1 < grid.length) {           
            if (grid[row+1][col]==0 && grid[row][col]==1) { // it is an edge between an island and water cells, so incclude it in the perimiter count.
                perimiter++;
            }
            
            if (marked[row+1][col] == 0) {
                process(grid, row+1, col, marked);
            }
        }
        
        // cell is connected to a cell on left.
        if (col-1 >= 0) {            
            if (grid[row][col-1]==0 && grid[row][col]==1) { // it is an edge between an island and water cells, so incclude it in the perimiter count.
                perimiter++;
            }

            if (marked[row][col-1] == 0) {
                process(grid, row, col-1, marked);
            }
        } 
        
        // cell is connected to a cell on the right.
        if (col+1 < grid[0].length) {      
            if (grid[row][col+1]==0 && grid[row][col]==1) { // it is an edge between an island and water cells, so incclude it in the perimiter count.
                perimiter++;
            }
            
            if (marked[row][col+1] == 0) {
                process(grid, row, col+1, marked);
            }
        }
    }
}

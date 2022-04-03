/**
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 * https://leetcode.com/problems/number-of-islands/
 *
 * The cells in an island are 4-connected, i.e. connected to a cell directly above, under, to the left and to the right of it. A given cell at grid[row][col]
 * can be connect to any or all of the following cells: grid[row-1][col], gird[row+1][col], grid[row][col-1] and grid[row][col+1]. Those cells needs to be
 * in the grid, i.e. their row and col coordinates need to be within the following ranges: 0 <= row < grid.length and 0 <= col <= grid[0].length.
 * We find a land cell, then mark all cellss connected to it, increment the count, search for a next unmarked land cell and repeat till we mark all the land cells.
 * Then count will contaain the number of islands (number of connected components).
 *
 * Time complexity: O(n) where n is the number of cells since we examine all cells and their connections
 * Space complexity: O(1)
 *
 */
class NumberOfIslands {
    public int numIslands(char[][] grid) {
        int count = 0;
        int[][] marked = new int[grid.length][grid[0].length];
        
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == '1' && marked[row][col] == 0) {
                    traverseAllConnectedCells(grid, row, col, marked);
                    count++;
                }
            }
        }
        
        return count;
    }
    
    private void traverseAllConnectedCells(char[][] grid, int row, int col, int[][] marked) {
        marked[row][col]++;
        
        if (row-1 >= 0 && marked[row-1][col] == 0 && grid[row-1][col] == '1') {
            traverseAllConnectedCells(grid, row-1, col, marked);
        }
        
        if (row+1 < grid.length && marked[row+1][col] == 0 && grid[row+1][col] == '1') {
            traverseAllConnectedCells(grid, row+1, col, marked);
        }
        
        if (col-1 >= 0 && marked[row][col-1] == 0 && grid[row][col-1] == '1') {
            traverseAllConnectedCells(grid, row, col-1, marked);
        }
        
        if (col+1 < grid[0].length && marked[row][col+1] == 0 && grid[row][col+1] == '1') {
            traverseAllConnectedCells(grid, row, col+1, marked);
        }
    }
}

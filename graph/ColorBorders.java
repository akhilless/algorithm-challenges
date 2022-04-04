/**
 * You are given an m x n integer matrix grid, and three integers row, col, and color. Each value in the grid represents the color of the grid square at that location.
 * Two squares belong to the same connected component if they have the same color and are next to each other in any of the 4 directions.
 * The border of a connected component is all the squares in the connected component that are either 4-directionally adjacent to a square not in the component,
 * or on the boundary of the grid (the first or last row or column).
 * You should color the border of the connected component that contains the square grid[row][col] with color.
 * Return the final grid.
 *
 * https://leetcode.com/problems/coloring-a-border/
 *
 * We travesre the grid starting from the grid[row][col] cell marking same-color cellss 4-connected to it. Those cells comprise s single component with the provided cell.
 * Unmarked cells then do not belong to that component. Then we traverse the marked cells changing color of the cells on border of the grid or bordering unmarked cells.
 * 
 * Time complexity: O(n) where n is the number of cells
 * Space complexity: O(n) where n is the number of cells
 */
class ColorBorders {   
    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        int[][] marked = new int[grid.length][grid[0].length];
        
        findConnectedCells(grid, row, col, marked); 
        paintBorderCells(marked, grid, row, col, color);
        
        return grid;
    }
    
    private void findConnectedCells(int[][] grid, int row, int col, int[][] marked) {
        marked[row][col]++;
        
        // if cell has the same color and neighbours the row,col cell from below, then they are in the same component
        if (row-1 >= 0 && grid[row-1][col]==grid[row][col] && marked[row-1][col]==0) {
            findConnectedCells(grid, row-1, col, marked);
        }
        
        // if cell has the same color and neighbours the row,col cell from above, then they are in the same component
        if (row+1 < grid.length && grid[row+1][col]==grid[row][col] && marked[row+1][col]==0) {
            findConnectedCells(grid, row+1, col, marked);
        }
        
        // if cell has the same color and neighbours the row,col cell from the left, then they are in the same component
        if (col-1 >= 0 && grid[row][col-1]==grid[row][col] && marked[row][col-1]==0) {
            findConnectedCells(grid, row, col-1, marked);
        }
        
        // if cell has the same color and neighbours the row,col cell from the right, then they are in the same component
        if (col+1 < grid[0].length && grid[row][col+1]==grid[row][col] && marked[row][col+1]==0) {
            findConnectedCells(grid, row, col+1, marked);
        }
    }
    
    private void paintBorderCells(int[][] marked, int[][] grid, int sourceRow, int sourceCol, int color) {        
       for (int row = 0; row < grid.length; row++) {           
           for (int col = 0; col < grid[0].length; col++) {
               if (marked[row][col] == 0) {
                   continue; // the cell does not belong to the component of the provided cell, so we skip it
               }
               
               /** 
               * if the cell is in the first or last row or first or last column
                * or neighbours a cell outside the component from below or above or right or left,
                * change its color
                */
               if (row == 0 || row == grid.length-1 || (row-1 >= 0 && marked[row-1][col] == 0) || (row+1 < grid.length && marked[row+1][col] == 0)) {
                   grid[row][col]=color;
               } else if (col == 0 || col == grid[0].length-1 || (col-1 >= 0 && marked[row][col-1] == 0) || (col+1 < grid[0].length && marked[row][col+1] == 0)) {
                   grid[row][col]=color;
               }
           }
       }
    }    
}

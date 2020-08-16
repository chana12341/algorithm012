//最小路径和
class LeetCode_64{
	public int minPathSum(int[][] grid) {
        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[0].length;j++) {
				//处理边界，第一行
                if(i==0 && j>0) {
                    grid[i][j] += grid[i][j-1];
                }
				//处理边界，第一列
                else if(j == 0 && i>0) {
                    grid[i][j] += grid[i-1][j];
                }
                else if(i>0 && j>0) {
                    grid[i][j] += Math.min(grid[i-1][j],grid[i][j-1]);
                }
            }
        }
        return grid[grid.length-1][grid[0].length-1];
    }
}
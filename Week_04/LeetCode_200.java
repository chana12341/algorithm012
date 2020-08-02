class LeetCode_200{
	//岛屿数量 ,dfs
	public int numIslands(char[][] grid) {
        if(grid.length == 0) {
            return 0;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        int landNums = 0;
        for(int i=0;i<rows;i++) {
            for(int j=0;j<cols;j++) {
                if(grid[i][j] == '1') {
                    dfs(i,j,grid);
                    landNums ++;
                }
            }
        }
        return landNums;
    }
	
    public void dfs(int i,int j,char[][] grid) {
		//越界，或者dfs到'0'，回溯
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length || grid[i][j] == '0') {
            return;
        }
		//将遍历过的'1'置'0'
        grid[i][j] = '0';
        dfs(i+1,j,grid);
        dfs(i-1,j,grid);
        dfs(i,j+1,grid);
        dfs(i,j-1,grid);
    }
}
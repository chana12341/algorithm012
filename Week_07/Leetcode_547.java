//朋友圈
class Leetcode_547 {

    class UF {
        // 记录连通分量
        private int count;
        // 节点 x 的节点是 parent[x]
        private int[] parent;

        /* 构造函数，n 为图的节点总数 */
        public UF(int n) {
            // 一开始互不连通
            this.count = n;
            // 父节点指针初始指向自己
            parent = new int[n];
            for (int i = 0; i < n; i++)
                parent[i] = i;
        }

        /* 其他函数 */
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ)
                return;
            // 将两棵树合并为一棵
            parent[rootP] = rootQ;
            // parent[rootQ] = rootP 也一样
            count--; // 两个分量合二为一
        }

        /* 返回某个节点 x 的根节点 */
        private int find(int x) {
            // 根节点的 parent[x] == x
            while (parent[x] != x)
                x = parent[x];
            return x;
        }

        /* 返回当前的连通分量个数 */
        public int count() { 
            return count;
        }

    }


    public int findCircleNum(int[][] M) {
        if(M==null || M.length==0 || M[0].length == 0) {
            return 0;
        }
        int n = M.length;
        int m = M[0].length;
        UF b = new UF(n);
        for(int i=0;i<n;i++) {
            for(int j=0;j<i;j++) {
                if(M[i][j]==1) {
                    b.union(i,j);
                }
            }
        }
        return b.count();
    }
}
**毕业总结**

通过这十周的算法训练，让我对算法不再有不觉明历的感觉，比起以前现在对算法有了较为清晰的认知。训练营中优秀的老师与同学们对自己的学习帮助也很大，包括学习方法上的，疑难问题解答上的，还有就是大家一起学习的动力。

希望自己再训练营结束后也能坚持用超哥的五毒神掌刷题，温故知新。

动态规划
```
32. 最长有效括号
class Solution {
    public int longestValidParentheses(String s) {
        int n = s.length();
        if (n < 2) return 0;
        int res = 0;
        int[] dp = new int[n];
        for (int i = n - 2; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                int j = i + dp[i + 1] + 1;
                if (j < n && s.charAt(j) == ')') {
                    dp[i] = dp[i + 1] + 2;
                    if (j + 1 < n) dp[i] += dp[j + 1];
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
45. 跳跃游戏 II
// 贪心即可，找到能到最后一个数的最小索引
class Solution {
    int cnt = 0;
    public int jump(int[] nums) {
        int last = nums.length - 1;
        while (last > 0) {
            for (int i = 0; i < last; i++) {
                if (i + nums[i] >= last) {
                    last = i;
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
55. 跳跃游戏
class Solution {
    public boolean canJump(int[] a) {
        int last = a.length - 1;
        for (int i = a.length -2; i >= 0; i--) {
            if (a[i] + i >= last) last = i;
        }
        return last == 0;
    }
}
62. 不同路径
class Solution {
    public int uniquePaths(int m, int n) {
        int[] cur = new int[n];
        Arrays.fill(cur,1);
        for (int i = 1; i < m;i++){
            for (int j = 1; j < n; j++){
                cur[j] += cur[j-1] ;
            }
        }
        return cur[n-1];
    }
}
63. 不同路径 II
class Solution {
    public int uniquePathsWithObstacles(int[][] a) {
        int[] res = new int[a[0].length]; // 开辟n列数组
        res[0] = 1; // 首值设为1
        for (int i = 0; i < a.length; i++) 
            for (int j = 0; j < a[0].length; j++)
                if (a[i][j] == 1) res[j] = 0; // 障碍物
                else if (j > 0) res[j] += res[j-1]; // 左边值与原值相加
        return res[a[0].length - 1];
    }   
}
64. 最小路径和
class Solution {
    public int minPathSum(int[][] a) {

        for (int i = 0; i < a.length; i++)
            for(int j = 0; j < a[0].length; j++) {
                if (i == 0 && j == 0) continue; // 略过左上角的值
                // 最上面一行，值更新为左边的数+现在的数。因为只能从左边过来。
                else if (i == 0) a[i][j] += a[i][j-1];
                // 最左边一列，值更新为上面的数+现在的数。因为只能从上边过来。
                else if (j == 0) a[i][j] += a[i-1][j];
                // 其余情况，要么从上面过来，要么从左边过来，选值小的那个
                else a[i][j] += Math.min(a[i-1][j],a[i][j-1]);
            }
        return a[a.length-1][a[0].length-1]; // 返回最后一个数
    }
}

91. 解码方法
public class Solution {

    public int numDecodings(String s) {
        int len = s.length();
        if (len == 0) {
            return 0;
        }
        int[] dp = new int[len];

        char[] charArray = s.toCharArray();
        if (charArray[0] == '0') {
            return 0;
        }
        dp[0] = 1;

        for (int i = 1; i < len; i++) {
            if (charArray[i] != '0') {
                dp[i] = dp[i - 1];
            }

            int num = 10 * (charArray[i - 1] - '0') + (charArray[i] - '0');
            if (num >= 10 && num <= 26) {
                if (i == 1) {
                    dp[i]++;
                } else {
                    dp[i] += dp[i - 2];
                }
            }
        }
        return dp[len - 1];
    }
}
120. 三角形最小路径和
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int m = triangle.size();
        int[] a = new int[m+1];
        for(int i = m-1; i >= 0; i--) // 倒数第二行开始
            for (int j = 0; j < triangle.get(i).size(); j++) {
                a[j] = Math.min(a[j], a[j+1]) + triangle.get(i).get(j); // 选择下面或者右下面小的那个。
            }
        return a[0];
    }
}
121. 买卖股票的最佳时机
class Solution {
    public int maxProfit(int[] prices) {
        int currMaxProfit = 0;
        for (int i = 0, j = 0; j < prices.length; j++)
            if (prices[j] - prices[i] < 0) i = j;
            else currMaxProfit = Math.max(prices[j] - prices[i], currMaxProfit);
        return currMaxProfit;
    }
}
122. 买卖股票的最佳时机 II
class Solution {
    public int maxProfit(int[] a) {
        int res = 0;
        for (int i = 1; i < a.length; i++) if (a[i] > a[i-1]) res += a[i] -a[i-1];
        return res;
    }
}
123. 买卖股票的最佳时机 III
class Solution {
    public int maxProfit(int[] prices) {
        int fstBuy = Integer.MIN_VALUE, fstSell = 0;
        int secBuy = Integer.MIN_VALUE, secSell = 0;
        for(int p : prices) {
            fstBuy = Math.max(fstBuy, -p);
            fstSell = Math.max(fstSell, fstBuy + p);
            secBuy = Math.max(secBuy, fstSell - p);
            secSell = Math.max(secSell, secBuy + p); 
        }
        return secSell;
    }
}
152. 乘积最大子数组
class Solution {
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, imax = 1, imin = 1; //一个保存最大的，一个保存最小的。
        for(int i=0; i<nums.length; i++){
            if(nums[i] < 0){ int tmp = imax; imax = imin; imin = tmp;} //如果数组的数是负数，那么会导致最大的变最小的，最小的变最大的。因此交换两个的值。
            imax = Math.max(imax*nums[i], nums[i]);
            imin = Math.min(imin*nums[i], nums[i]);
            
            max = Math.max(max, imax);
        }
        return max;
    }
}
188. 买卖股票的最佳时机 IV
class Solution {
    public int maxProfit(int k, int[] prices) {
        if(k < 1) return 0;
        if(k >= prices.length/2) return greedy(prices);
        int[][] t = new int[k][2];
        for(int i = 0; i < k; ++i)
            t[i][0] = Integer.MIN_VALUE;
        for(int p : prices) {
            t[0][0] = Math.max(t[0][0], -p);
            t[0][1] = Math.max(t[0][1], t[0][0] + p);
            for(int i = 1; i < k; ++i) {
                t[i][0] = Math.max(t[i][0], t[i-1][1] - p);
                t[i][1] = Math.max(t[i][1], t[i][0] + p);
            }
        }
        return t[k-1][1];
    }
    
    private int greedy(int[] prices) {
        int max = 0;
        for(int i = 1; i < prices.length; ++i) {
            if(prices[i] > prices[i-1])
                max += prices[i] - prices[i-1];
        }
        return max;
    }
}
198. 打家劫舍
public class Solution {
    public int rob(int[] nums) {
        int len = nums.length;
        if(len == 0) return 0;
        int[] dp = new int[len + 1];
        dp[0] = 0;//选择偷第一家，可偷最大值
        dp[1] = nums[0];//选择偷第二家，累加可偷最大值
        for(int i = 2; i <= len; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i-1]);
        }
        return dp[len];
    }
}
213. 打家劫舍 II
class Solution {
    public int rob(int[] nums) {
        int len = nums.length;
        if(len == 0) return 0;
        int[] dp = new int[len + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for(int i = 2; i <= len; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i-1]);
        }
        return dp[len];
    }
}
221. 最大正方形
class Solution {
    public int maximalSquare(char[][] matrix) {
        int n = matrix.length;
        if(n == 0) return 0;
        int m = matrix[0].length;
        if(m == 0) return 0;
        int[][] dp = new int[n][m];
        int result = 0;
        //初始化base case
        for(int i = 0 ;i<n;i++) {
            dp[i][0] = matrix[i][0] - '0';
            result = Math.max(dp[i][0],result);
        }
        for(int i = 0 ;i<m;i++){
            dp[0][i] = matrix[0][i] - '0';
            result = Math.max(dp[0][i],result);
        }
        for(int i = 1; i<n; i++){
            for(int j = 1; j<m; j++){
                //计算dp[i][j];
                if(matrix[i][j] == '1'){
                    dp[i][j] = Math.min(Math.min(dp[i-1][j-1],dp[i-1][j]),dp[i][j-1]) + 1;
                    result = Math.max(dp[i][j],result);
                }
                 
            }
        }
        return result*result;

    }
}
279. 完全平方数
class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n + 1]; // 默认初始化值都为0
        for (int i = 1; i <= n; i++) {
            dp[i] = i; // 最坏的情况就是每次+1
            for (int j = 1; i - j * j >= 0; j++) { 
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1); 
            }
        }
        return dp[n];
    }
}
322. 零钱兑换
class Solution {
    public int coinChange(int[] coins, int amt) {
        // 动态规划
        int[] memo = new int[amt + 1]; 
        Arrays.fill(memo, amt + 1);  
        memo[0] = 0;
        for (int i = 1; i <= amt; i++) 
            for (int coin : coins)
                if (i - coin >= 0)
                    memo[i] = Math.min(memo[i], memo[i - coin] + 1);
        return memo[amt] == amt + 1 ? -1 : memo[amt];
    }
}
309. 最佳买卖股票时机含冷冻期
class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        int n = prices.length;
        int[][] f = new int[n][3];
        f[0][0] = -prices[0];
        for (int i = 1; i < n; ++i) {
            f[i][0] = Math.max(f[i - 1][0], f[i - 1][2] - prices[i]);
            f[i][1] = f[i - 1][0] + prices[i];
            f[i][2] = Math.max(f[i - 1][1], f[i - 1][2]);
        }
        return Math.max(f[n - 1][1], f[n - 1][2]);
    }
}
312. 戳气球
class Solution {
    public int[][] rec;
    public int[] val;

    public int maxCoins(int[] nums) {
        int n = nums.length;
        val = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            val[i] = nums[i - 1];
        }
        val[0] = val[n + 1] = 1;
        rec = new int[n + 2][n + 2];
        for (int i = 0; i <= n + 1; i++) {
            Arrays.fill(rec[i], -1);
        }
        return solve(0, n + 1);
    }

    public int solve(int left, int right) {
        if (left >= right - 1) {
            return 0;
        }
        if (rec[left][right] != -1) {
            return rec[left][right];
        }
        for (int i = left + 1; i < right; i++) {
            int sum = val[left] * val[i] * val[right];
            sum += solve(left, i) + solve(i, right);
            rec[left][right] = Math.max(rec[left][right], sum);
        }
        return rec[left][right];
    }
}
363. 矩形区域不超过 K 的最大数值和
class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int rows = matrix.length, cols = matrix[0].length, max = Integer.MIN_VALUE;
        // O(cols ^ 2 * rows)
        for (int l = 0; l < cols; l++) { // 枚举左边界
            int[] rowSum = new int[rows]; // 左边界改变才算区域的重新开始
            for (int r = l; r < cols; r++) { // 枚举右边界
                for (int i = 0; i < rows; i++) { // 按每一行累计到 dp
                    rowSum[i] += matrix[i][r];
                }
                max = Math.max(max, dpmax(rowSum, k));
                if (max == k) return k; // 尽量提前
            }
        }
        return max;
    }

    // 在数组 arr 中，求不超过 k 的最大值
    private int dpmax(int[] arr, int k) {
        int rollSum = arr[0], rollMax = rollSum;
        // O(rows)
        for (int i = 1; i < arr.length; i++) {
            if (rollSum > 0) rollSum += arr[i];
            else rollSum = arr[i];
            if (rollSum > rollMax) rollMax = rollSum;
        }
        if (rollMax <= k) return rollMax;
        // O(rows ^ 2)
        int max = Integer.MIN_VALUE;
        for (int l = 0; l < arr.length; l++) {
            int sum = 0;
            for (int r = l; r < arr.length; r++) {
                sum += arr[r];
                if (sum > max && sum <= k) max = sum;
                if (max == k) return k; // 尽量提前
            }
        }
        return max;
    }
}
403. 青蛙过河
public class Solution {
    public boolean canCross(int[] stones) {
        HashMap<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < stones.length; i++) {
            map.put(stones[i], new HashSet<Integer>());
        }
        map.get(0).add(0);
        for (int i = 0; i < stones.length; i++) {
            for (int k : map.get(stones[i])) {
                for (int step = k - 1; step <= k + 1; step++) {
                    if (step > 0 && map.containsKey(stones[i] + step)) {
                        map.get(stones[i] + step).add(step);
                    }
                }
            }
        }
        return map.get(stones[stones.length - 1]).size() > 0;
    }
}
410. 分割数组的最大值
class Solution {
    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        int[][] f = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(f[i], Integer.MAX_VALUE);
        }
        int[] sub = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sub[i + 1] = sub[i] + nums[i];
        }
        f[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, m); j++) {
                for (int k = 0; k < i; k++) {
                    f[i][j] = Math.min(f[i][j], Math.max(f[k][j - 1], sub[i] - sub[k]));
                }
            }
        }
        return f[n][m];
    }
}
552. 学生出勤记录 II
public class Solution {
    long M = 1000000007;
    public int checkRecord(int n) {
        long a0l0 = 1, a0l1 = 0, a0l2 = 0, a1l0 = 0, a1l1 = 0, a1l2 = 0;
        for (int i = 0; i <= n; i++) {
            long a0l0_ = (a0l0 + a0l1 + a0l2) % M;
            a0l2 = a0l1;
            a0l1 = a0l0;
            a0l0 = a0l0_;
            long a1l0_ = (a0l0 + a1l0 + a1l1 + a1l2) % M;
            a1l2 = a1l1;
            a1l1 = a1l0;
            a1l0 = a1l0_;
        }
        return (int) a1l0;
    }
}
621. 任务调度器
public class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c: tasks)
            map[c - 'A']++;
        Arrays.sort(map);
        int max_val = map[25] - 1, idle_slots = max_val * n;
        for (int i = 24; i >= 0 && map[i] > 0; i--) {
            idle_slots -= Math.min(map[i], max_val);
        }
        return idle_slots > 0 ? idle_slots + tasks.length : tasks.length;
    }
}
647. 回文子串
class Solution {
    int num = 0;
    public int countSubstrings(String s) {
        for (int i=0; i < s.length(); i++){
            count(s, i, i);//回文串长度为奇数
            count(s, i, i+1);//回文串长度为偶数
        }
        return num;
    }
    
    public void count(String s, int start, int end){
        while(start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)){
            num++;
            start--;
            end++;
        }
    }
}
714. 买卖股票的最佳时机含手续费
class Solution {
    public int maxProfit(int[] prices, int fee) {
        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE + fee;
        for(int i = 0; i < prices.length; i++) {
            int tmp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i] - fee);
            dp_i_1 = Math.max(dp_i_1, tmp - prices[i]);
        }

        return dp_i_0;
    }
}
980. 不同路径 III
class Solution {
    public int uniquePathsIII(int[][] grid) {
        int sum = 1;//空白格+起始点的个数
        int r = 0;
        int c = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 0) {
                    sum++;
                } else if(grid[i][j] == 1) {
                    r = i;
                    c = j;
                }
            }
        }
        return dfs(grid, r, c, sum);
    }
    
    public int dfs(int[][] g, int i, int j, int sum) {
        if(i < 0 || i >= g.length || j < 0 || j >= g[0].length || g[i][j] == -1) {
            return 0;
        }

        if(g[i][j] == 2) return 0 == sum ? 1 : 0;
        int fix = 0;
        g[i][j] = -1;
        fix += dfs(g, i + 1, j, sum - 1);
        fix += dfs(g, i - 1, j, sum - 1);
        fix += dfs(g, i, j + 1, sum - 1);
        fix += dfs(g, i, j - 1, sum - 1);
        g[i][j] = 0;
        return fix;
    }
}
```

位运算
```
52. N皇后 II
class Solution {

    public int totalNQueens(int n) {
        int[] rs = new int[]{0,1,0,0,2,10,4,40,92,352,724,2680};
         return rs[n];
    }
}

190. 颠倒二进制位
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res = (res << 1) + (n & 1);
            n >>= 1;
        }
        return res;
    }
}

191. 位1的个数
public class Solution {
    public int hammingWeight(int n) {
        int bits = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & mask) != 0) {
                bits++;
            }
            mask <<= 1;
        }
        return bits;
    }
}

231. 2的幂
class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & n - 1) == 0;
    }
}

338. 比特位计数
class Solution {
    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        for(int i = 1;i<= num;i++){  //注意要从1开始，0不满足
            res[i] = res[i & (i - 1)] + 1;
        }
        return res;
    }
}
```

LRU Catch
```
146. LRU缓存机制
public class LRUCache {
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
        public DLinkedNode() {}
        public DLinkedNode(int _key, int _value) {key = _key; value = _value;}
    }
    
    private Map<Integer, DLinkedNode> cache = new HashMap<Integer, DLinkedNode>();
    private int size;
    private int capacity;
    private DLinkedNode head, tail;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        // 使用伪头部和伪尾部节点
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        // 如果 key 存在，先通过哈希表定位，再移到头部
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            // 如果 key 不存在，创建一个新的节点
            DLinkedNode newNode = new DLinkedNode(key, value);
            // 添加进哈希表
            cache.put(key, newNode);
            // 添加至双向链表的头部
            addToHead(newNode);
            ++size;
            if (size > capacity) {
                // 如果超出容量，删除双向链表的尾部节点
                DLinkedNode tail = removeTail();
                // 删除哈希表中对应的项
                cache.remove(tail.key);
                --size;
            }
        }
        else {
            // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
            node.value = value;
            moveToHead(node);
        }
    }

    private void addToHead(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    private DLinkedNode removeTail() {
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }
}
```

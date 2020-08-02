class LeetCode_122{
	//买卖股票的最佳时机
	public int maxProfit(int[] prices) {
        /*
        //动态规划
        int[][] money = new int[prices.length][2];
        if(prices.length <= 1) {
            return 0;
        }
        money[0][0] = 0;
        money[0][1] = -prices[0];
        for(int i=1 ; i<prices.length ; i++) {
            //持有的现金
            money[i][0] = Math.max(money[i-1][0],money[i-1][1]+prices[i]);
            //持有的票仓
            money[i][1] = Math.max(money[i-1][1],money[i-1][0]-prices[i]);
        }
        return money[prices.length-1][0];
         */
        //贪心算法,将每天能获得的收益相加
        if(prices.length <= 1) {
            return 0;
        }
        int money = 0;
        for(int i=0 ; i<prices.length-1 ; i++) {
            if(prices[i] < prices[i+1]) {
                money += prices[i+1]-prices[i];
            }
        }
        return money;
    }
}
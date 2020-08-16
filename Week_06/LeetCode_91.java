class LeetCode_91{
	
	//解码方法
	public int numDecodings(String s) {
        //定义 dp[i] 代表包含s的第i字符结尾的字串的种类
        int[] dp = new int[s.length()];
        char[] nums = s.toCharArray();
        if(nums[0] == '0') {
            return 0;
        }
        dp[0] = 1;
        for(int i=1; i<s.length() ;i++) {
            if(s.charAt(i) != '0') {
                dp[i] = dp[i-1];
            }
            int num = (s.charAt(i-1)-'0') * 10 + (s.charAt(i)-'0');
            //满足条件时，dp公式为 dp[i] = dp[i-1]+dp[i-2];
            if(num >= 10 && num <= 26) {
                if(i == 1) {
                    dp[i]++;
                }
                else {
                    dp[i] += dp[i-2];
                } 
            }
        }
        return dp[s.length()-1];
    }
}
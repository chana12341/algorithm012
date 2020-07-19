class UglyNumber{
	
	public int nthUglyNumber(int n) {
        int[] ans = new int[n];
        int a=0,b=0,c=0;
        ans[0] = 1;
        for(int i=1; i<n;i++) {
            int x1 = ans[a]*2, x2 = ans[b]*3, x3 = ans[c]*5;
            ans[i] = Math.min(Math.min(x1,x2),x3);
            if(ans[i] == x1) {
                a++;
            }
            if(ans[i] == x2) {
                b++;
            }
            if(ans[i] == x3) {
                c++;
            }
        }
        return ans[n-1];
    }
}
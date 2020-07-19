//有效字母异位词
class IsAnaGram{
	public boolean isAnagram(String s, String t) {
        char[] ss = s.toCharArray();
        char[] tt = t.toCharArray();
        Arrays.sort(ss);
        Arrays.sort(tt);
        if(ss.length != tt.length) {
            return false;
        }
        for(int i = 0; i<ss.length ;i++) {
            if(ss[i] != tt[i]) {
                return false;
            }
        }
        return true;
    }
}
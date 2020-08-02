class LeetCode_860{
	//柠檬水找零
	public boolean lemonadeChange(int[] bills) {
        int money = 0;
        int five = 0;
        int ten = 0;
        for(int i=0;i<bills.length;i++) {
            if (bills[i] == 5) {
                five++;
            }
            else if(bills[i] == 10) {
                ten++;
                five--;
            }
            else {
				//付20元时，找零一个10和5，或者找零三个5
                if(ten > 0 && five > 0) {
                    ten--;
                    five--;
                }
                else if(five >= 3) {
                    five -= 3;
                }
                else {
                    return false;
                }
            }
            if(five < 0 ) {
                return false;
            }
        }
        return true;
    }
}
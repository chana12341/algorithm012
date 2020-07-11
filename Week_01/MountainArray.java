class MountainArray {
	public boolean validMountainArray(int[] A) {
        /* 自己的解法
        if (A == null || A.length == 0 || A.length == 1) {
            return false;
        }
        int max = 0;
        for (int i = 0; i<A.length-1 ; i++) {
            if(A[i] > A[i+1]) {
                if (i == 0) {
                    return false;
                }
                max = i;
                break;
            }
        }
        for(int i = max ; i < A.length-1 ; i++) {
            if(A[i] <= A[i+1]) {
                return false;
            }
        }
        return true;
        */
        //参考的解法
        if (A.length < 3) {
            return false;
        }
        int left = 0;
        int right = A.length - 1;
        while (left < A.length - 2 && A[left] < A[left + 1]) {
            left ++;
        }
        while (right > 1 && A[right] < A[right-1]) {
            right --;
        }
        if (left == right) {
            return true;
        }
        else {
            return false;
        }
    }
}
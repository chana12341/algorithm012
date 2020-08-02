class LeetCode_31{
	//搜索旋转排序数组
	public int search(int[] nums, int target) {
        if(nums == null||nums.length==0) {
            return -1;
        }
        int l = 0;
        int r = nums.length-1;
        
        int mid;
        while (l <= r) {
            mid = l+(r-l)/2;
            if(nums[mid] == target) {
                return mid;
            }
			//左边有序
            if(nums[l] <= nums[mid]) {
				//target在mid左边
                if(nums[l] <= target && target < nums[mid]) {
                    r = mid-1;
                }
				//在右边
                else {
                    l = mid+1;
                }
            }
			//右边有序
            else {
				//target在mid右边
                if(nums[r] >= target && target > nums[mid]) {
                    l = mid+1;
                }
				//在左边
                else {
                    r = mid-1;
                }
            }
        }
        return -1;
    }
}
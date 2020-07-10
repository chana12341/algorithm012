class Sum_of_two_numbers {
   public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i = 0 ;i < nums.length; i++) {
            map.put(nums[i],i);
        }
        for (int i = 0;i < nums.length; i++) {
            int temp = target - nums[i];
            if (map.containsKey(temp) && map.get(temp) != i) {
                return new int[]{map.get(nums[i]),map.get(temp)};
            }
        }
        return null;
    }
}


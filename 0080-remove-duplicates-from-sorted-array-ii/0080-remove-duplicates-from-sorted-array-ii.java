class Solution {
    public int removeDuplicates(int[] nums) {
        int k = 0; // pointer for placing valid elements
        
        for (int i = 0; i < nums.length; i++) {
            // Allow first 2 elements OR if current != element at k-2
            if (k < 2 || nums[i] != nums[k - 2]) {
                nums[k] = nums[i];
                k++;
            }
        }
        
        return k;
    }
}
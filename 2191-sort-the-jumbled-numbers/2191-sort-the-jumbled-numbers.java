import java.util.*;

class Solution {
    public int[] sortJumbled(int[] mapping, int[] nums) {
        int n = nums.length;
        
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }
        
        Arrays.sort(indices, (a, b) -> {
            int valA = getMapped(nums[a], mapping);
            int valB = getMapped(nums[b], mapping);
            
            if (valA != valB) {
                return valA - valB;
            }
            return a - b;
        });
        
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = nums[indices[i]];
        }
        
        return result;
    }
    
    private int getMapped(int num, int[] mapping) {
        if (num == 0) return mapping[0];
        
        int mapped = 0;
        int place = 1;
        
        while (num > 0) {
            int digit = num % 10;
            mapped = mapping[digit] * place + mapped;
            place *= 10;
            num /= 10;
        }
        
        return mapped;
    }
}
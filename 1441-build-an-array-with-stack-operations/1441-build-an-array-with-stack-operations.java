import java.util.*;

class Solution {
    public List<String> buildArray(int[] target, int n) {
        List<String> result = new ArrayList<>();
        
        int i = 0; // pointer for target
        
        for (int num = 1; num <= n; num++) {
            
            if (i == target.length) break;
            
            result.add("Push");
            
            if (num == target[i]) {
                i++; // correct element
            } else {
                result.add("Pop"); // remove unwanted element
            }
        }
        
        return result;
    }
}
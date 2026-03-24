import java.util.*;

class Solution {
    public int[] canSeePersonsCount(int[] heights) {
        int n = heights.length;
        int[] answer = new int[n];
        
        Stack<Integer> stack = new Stack<>();
        
        // traverse from right to left
        for (int i = n - 1; i >= 0; i--) {
            
            int count = 0;
            
            // pop all shorter people
            while (!stack.isEmpty() && heights[i] > stack.peek()) {
                stack.pop();
                count++;
            }
            
            // if someone taller exists
            if (!stack.isEmpty()) {
                count++;
            }
            
            answer[i] = count;
            
            // push current person
            stack.push(heights[i]);
        }
        
        return answer;
    }
}
import java.util.*;

class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        int[] result = new int[k];

        for (int i = Math.max(0, k - n); i <= Math.min(k, m); i++) {
            int[] part1 = maxSubsequence(nums1, i);
            int[] part2 = maxSubsequence(nums2, k - i);
            int[] candidate = merge(part1, part2);

            if (greater(candidate, 0, result, 0)) {
                result = candidate;
            }
        }

        return result;
    }

    
    private int[] maxSubsequence(int[] nums, int k) {
        int n = nums.length;
        int[] stack = new int[k];
        int top = -1;
        int remain = n;

        for (int num : nums) {
            while (top >= 0 && stack[top] < num && remain > k - top - 1) {
                top--;
            }
            if (top + 1 < k) {
                stack[++top] = num;
            }
            remain--;
        }
        return stack;
    }

    
    private int[] merge(int[] a, int[] b) {
        int[] res = new int[a.length + b.length];
        int i = 0, j = 0, r = 0;

        while (i < a.length || j < b.length) {
            if (greater(a, i, b, j)) {
                res[r++] = (i < a.length) ? a[i++] : b[j++];
            } else {
                res[r++] = (j < b.length) ? b[j++] : a[i++];
            }
        }
        return res;
    }

   
    private boolean greater(int[] a, int i, int[] b, int j) {
        while (i < a.length && j < b.length && a[i] == b[j]) {
            i++;
            j++;
        }
        return j == b.length || (i < a.length && a[i] > b[j]);
    }
}
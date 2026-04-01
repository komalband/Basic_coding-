import java.util.*;

class Solution {
    public List<String> letterCasePermutation(String s) {
        List<String> result = new ArrayList<>();
        backtrack(s.toCharArray(), 0, result);
        return result;
    }

    private void backtrack(char[] arr, int index, List<String> result) {
        if (index == arr.length) {
            result.add(new String(arr));
            return;
        }

        if (Character.isLetter(arr[index])) {
            // lowercase
            arr[index] = Character.toLowerCase(arr[index]);
            backtrack(arr, index + 1, result);

            // uppercase
            arr[index] = Character.toUpperCase(arr[index]);
            backtrack(arr, index + 1, result);
        } else {
            // digit → no change
            backtrack(arr, index + 1, result);
        }
    }
}
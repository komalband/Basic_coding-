import java.util.*;

class Solution {
    public String getPermutation(int n, int k) {
        List<Integer> numbers = new ArrayList<>();
        int fact = 1;

        // Prepare numbers list and factorial
        for (int i = 1; i < n; i++) {
            fact *= i;
            numbers.add(i);
        }
        numbers.add(n);

        // Convert k to 0-based index
        k = k - 1;

        StringBuilder result = new StringBuilder();

        while (true) {
            int index = k / fact;
            result.append(numbers.get(index));
            numbers.remove(index);

            if (numbers.size() == 0) break;

            k = k % fact;
            fact = fact / numbers.size();
        }

        return result.toString();
    }
}
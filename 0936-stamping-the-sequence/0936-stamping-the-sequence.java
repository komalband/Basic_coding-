import java.util.*;

class Solution {

    public int[] movesToStamp(String stamp, String target) {
        char[] S = stamp.toCharArray();
        char[] T = target.toCharArray();
        int m = S.length, n = T.length;

        boolean[] visited = new boolean[n];
        List<Integer> res = new ArrayList<>();

        int stars = 0;

        while (stars < n) {
            boolean doneReplace = false;

            for (int i = 0; i <= n - m; i++) {

                if (!visited[i] && canReplace(T, i, S)) {
                    int replaced = doReplace(T, i, m);
                    stars += replaced;
                    visited[i] = true;
                    doneReplace = true;
                    res.add(i);

                    if (stars == n) break;
                }
            }

            if (!doneReplace) return new int[0]; // not possible
        }

        Collections.reverse(res);

        return res.stream().mapToInt(i -> i).toArray();
    }

    // check if we can replace
    private boolean canReplace(char[] T, int pos, char[] S) {
        for (int i = 0; i < S.length; i++) {
            if (T[i + pos] != '?' && T[i + pos] != S[i]) {
                return false;
            }
        }
        return true;
    }

    // replace with '?'
    private int doReplace(char[] T, int pos, int len) {
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (T[i + pos] != '?') {
                T[i + pos] = '?';
                count++;
            }
        }
        return count;
    }
}
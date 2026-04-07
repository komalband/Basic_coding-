import java.util.*;

class Solution {

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word; // stores complete word at end
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();

        // Step 1: Build Trie
        TrieNode root = buildTrie(words);

        int m = board.length, n = board[0].length;

        // Step 2: DFS from each cell
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, i, j, root, result);
            }
        }

        return result;
    }

    private void dfs(char[][] board, int i, int j, TrieNode node, List<String> result) {
        char c = board[i][j];

        if (c == '#' || node.children[c - 'a'] == null) return;

        node = node.children[c - 'a'];

        // Found a word
        if (node.word != null) {
            result.add(node.word);
            node.word = null; // avoid duplicate
        }

        board[i][j] = '#'; // mark visited

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        for (int d = 0; d < 4; d++) {
            int x = i + dx[d];
            int y = j + dy[d];

            if (x >= 0 && y >= 0 && x < board.length && y < board[0].length) {
                dfs(board, x, y, node, result);
            }
        }

        board[i][j] = c; // backtrack
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();

        for (String word : words) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new TrieNode();
                }
                node = node.children[c - 'a'];
            }
            node.word = word;
        }

        return root;
    }
}
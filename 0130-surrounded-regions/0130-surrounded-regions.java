class Solution {
    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;

        // Step 1: Mark boundary-connected O's
        for (int i = 0; i < m; i++) {
            dfs(board, i, 0);        // left boundary
            dfs(board, i, n - 1);    // right boundary
        }

        for (int j = 0; j < n; j++) {
            dfs(board, 0, j);        // top boundary
            dfs(board, m - 1, j);    // bottom boundary
        }

        // Step 2: Convert surrounded O → X and T → O
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';   // captured
                } else if (board[i][j] == 'T') {
                    board[i][j] = 'O';   // safe
                }
            }
        }
    }

    private void dfs(char[][] board, int i, int j) {
        int m = board.length, n = board[0].length;

        if (i < 0 || j < 0 || i >= m || j >= n || board[i][j] != 'O')
            return;

        board[i][j] = 'T'; // mark as safe

        dfs(board, i + 1, j);
        dfs(board, i - 1, j);
        dfs(board, i, j + 1);
        dfs(board, i, j - 1);
    }
}
import java.util.*;

class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);

        if (!dict.contains(endWord)) return result;

        // Map to store parents
        Map<String, List<String>> map = new HashMap<>();

        // BFS
        Set<String> currentLevel = new HashSet<>();
        currentLevel.add(beginWord);

        boolean found = false;

        while (!currentLevel.isEmpty() && !found) {
            dict.removeAll(currentLevel);
            Set<String> nextLevel = new HashSet<>();

            for (String word : currentLevel) {
                char[] arr = word.toCharArray();

                for (int i = 0; i < arr.length; i++) {
                    char original = arr[i];

                    for (char c = 'a'; c <= 'z'; c++) {
                        arr[i] = c;
                        String newWord = new String(arr);

                        if (!dict.contains(newWord)) continue;

                        nextLevel.add(newWord);

                        map.computeIfAbsent(newWord, k -> new ArrayList<>()).add(word);

                        if (newWord.equals(endWord)) {
                            found = true;
                        }
                    }
                    arr[i] = original;
                }
            }
            currentLevel = nextLevel;
        }

        if (!found) return result;

        // Backtracking
        List<String> path = new ArrayList<>();
        path.add(endWord);
        backtrack(endWord, beginWord, map, path, result);

        return result;
    }

    private void backtrack(String word, String beginWord, Map<String, List<String>> map,
                           List<String> path, List<List<String>> result) {

        if (word.equals(beginWord)) {
            List<String> temp = new ArrayList<>(path);
            Collections.reverse(temp);
            result.add(temp);
            return;
        }

        if (!map.containsKey(word)) return;

        for (String parent : map.get(word)) {
            path.add(parent);
            backtrack(parent, beginWord, map, path, result);
            path.remove(path.size() - 1);
        }
    }
}
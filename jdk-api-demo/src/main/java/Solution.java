import org.example.algorithm.TreeNode;

/**
 *
 */
class Solution {
    private void printMsg(Object... objs) {
        for (Object obj :
                objs) {
            System.out.print(obj + ",");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        Integer[] arr = {10, 5, 16, 1, 7, 11, 20, null, 3, 6};
        TreeNode node = TreeNode.buildTreeNode(arr);
//        solution.post(node);
        int[] ar = {10, 9, 2, 5, 3, 7, 101, 18};
        int ka = solution.minCut("abcccb"
        );
        System.out.println(ka);
    }

    public int minCut(String s) {
        if (s == null || s.length() <= 0) {
            return 0;
        }

        int[][] memo = new int[s.length()][s.length()];
        int[] memolen = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (i < s.length() - 1 && s.charAt(i) == s.charAt(i + 1)) {
                memo[i][i + 1] = 1;
                memo[i + 1][i] = 1;
            }
            memo[i][i] = 1;
            memolen[i] = i;
        }

        for (int i = 1; i < s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if ((s.charAt(i) == s.charAt(j)) && (memo[j + 1][i - 1] == 1)) {
                    memo[j][i] = 1;
                    if (j != 0) {
                        memolen[i] = Math.min(memolen[i], memolen[j - 1] + 1);
                    }else {
                        memolen[i]=0;
                    }
                }
            }
            memolen[i] = Math.min(memolen[i], memolen[i - 1] + 1);
        }

        return memolen[s.length() - 1];

    }

    /**
     * [0,idx] 最长回文子川；
     *
     * @param memo
     * @param idx
     * @return
     */

    public int minPalin(int[] memolen, int[][] memo, int idx, String str) {
        if (idx < 0) return -1;
        if (idx == 0) return 0;

        if (memolen[idx] != 0) {
            return memolen[idx];
        }
        int min = idx + 1;
//        找曾经出现过得；
        for (int i = 0; i <= idx; i++) {
            if (memo[i][idx] == 1) {
                min = Math.min(minPalin(memolen, memo, i - 1, str) + 1, min);
            }
        }
        min = Math.min(minPalin(memolen, memo, idx - 1, str) + 1, min);
        memolen[idx] = min;
        return min;
    }

}
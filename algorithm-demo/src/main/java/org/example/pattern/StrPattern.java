package org.example.pattern;

/**
 * 字符串匹配，
 * 简单：暴力解法
 * 一般：DP
 * 复杂：kmp，将记忆利用到极限
 */
public class StrPattern {
    public static void main(String[] args) {
        String text = "aabaaabaaac";
        String pattern = "aabaaac";
//        暴力求解
        System.out.println(violate(text, pattern));
//         递归方式
        System.out.println(recur(text, pattern));

        System.out.println(dp(text, pattern));
        System.out.println(kmp(text, pattern));

    }

    /**
     * DP 本质上没有减少遍历的次数，只不过相对于暴力遍历来说 更加简单了一些
     * 思想是相通的； 即DP 也是想利用之前的状态，减少比较次数。
     * kmp：也是想利用记忆法，减少比较的次数；
     * 核心思想：
     * 形成一个next数组，减少无效的对比过程；
     * next[i] 表示以pat[i]结尾的最长 前缀、后缀相同的长度
     * 举例：
     * acaababacc
     * 00110101120
     * 利用这个可以快速找到
     * <p>
     * 匹配
     * ac ac ac d
     * ac ac d
     * <p>
     * 思路：
     * 1 找到next数组
     * 2 根据next数组，保证text上面的指针不会向后移动，一直往前移动的，
     * 3  if text[i]==pattern[j],i++,j++;
     * else text[i] ==pattern[next[j]] ,i++;
     *
     * @param text
     * @param pattern
     * @return
     */
    public static int kmp(String text, String pattern) {
        int[] next = findNext(pattern);

        int i = 0;
        int j = 0;

        while (i < text.length() && j < pattern.length()) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else if (j == 0) {
                i++;
            } else {
                j = next[j - 1];
            }
        }
        if (j >= pattern.length()) {
            return i - pattern.length();
        }

        return -1;
    }

    /**
     * 找到next数组，
     * next[i]的含义表示 pat[i]为结尾的字符串的前缀和后缀相同的最大值；
     * 也是保持i不变，频繁利用已经找到的前后缀相同的字串，来加速寻找过程。
     * 比如：
     * aaacaaacdaa
     * 01101234012
     * <p>
     * next[i] = next[i-1]+1, if pat[next[i-1]]==pat[i]
     * ,  if pat[next[i-1]]!=pat[i]  i = next[i-1] 再去比较。
     * next[i] = 0, if i==0;
     *
     * @param pattern
     * @return
     */
    private static int[] findNext(String pattern) {
        int[] next = new int[pattern.length()];
//        当前指针i， [0,i)的最长公共前后缀的长度；即next[i-1]，也就是从前缀开始的第一个和后缀不相等的元素的指针；
        int prefixTmp = 0;
        int i = 1;
        for (; i < pattern.length(); ) {
//            1 如果前缀的第一个不相等 和pat[i]相等；
            if (pattern.charAt(prefixTmp) == pattern.charAt(i)) {
                next[i] = prefixTmp + 1;
                prefixTmp = next[i];
                i++;
            } else {
//                如果prefixTmp=0；说明没有
                if (prefixTmp == 0) {
                    next[i]=0;
                    i++;
                } else {
//                    递归寻找前子串中是不是还有 前缀后缀相同的，再次高效利用。
                    prefixTmp = next[prefixTmp-1];
                }
            }
        }
        return next;
    }

    /**
     * DP：
     * 重叠子问题，最优子结构，无后效性；
     * f(i,j) = 1, a[i]=b[j] && f(i-1,j-1)==1
     * = 0, a[i]!=b[j] || f(i-1,j-1)==0
     *
     * @param text
     * @param pattern
     * @return
     */
    public static int dp(String text, String pattern) {
        if (text == null | pattern == null) return -1;
        if (text.length() == pattern.length() && text.equals(pattern)) return 0;
        int[][] memo = new int[text.length()][pattern.length()];
//        初始化边界条件
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == pattern.charAt(0)) {
                if (pattern.length() == 1) {
                    return 1;
                }
                memo[i][0] = 1;
            }
        }


        for (int i = 1; i < text.length(); i++) {
            for (int j = 1; j < pattern.length(); j++) {
                if (memo[i - 1][j - 1] == 1 && text.charAt(i) == pattern.charAt(j)) {
                    memo[i][j] = 1;
                    if (j == pattern.length() - 1) {
                        return i - pattern.length() + 1;
                    }
                }
            }
        }

        return -1;
    }

    /**
     * 使用递归的方式进行查询
     * 发现：有重叠子问题，通过递归，可以分解成小问题；
     *
     * @param text
     * @param pattern
     * @return
     */
    public static int recur(String text, String pattern) {
        if (text == null | pattern == null) return -1;
        for (int i = 0; i < text.length(); i++) {
            if (recurTextMatch(text, i, pattern, 0)) {
                return i;
            }
        }

        return -1;
    }

    private static boolean recurTextMatch(String text, int i, String pattern, int j) {
        if (j == pattern.length()) {
            return true;
        }
        if (i >= text.length()) {
            return false;
        }

        if (text.charAt(i) == pattern.charAt(j)) {
            return recurTextMatch(text, i + 1, pattern, j + 1);
        }
        return false;
    }

    /**
     * 遍历所有的结果，找到是否有完全匹配的情况，时间复杂度O(mn)
     *
     * @param text
     * @param pattern
     */
    public static int violate(String text, String pattern) {
        if (text == null | pattern == null) return -1;


        for (int i = 0; i < text.length(); i++) {
            int tmpI = i;
            for (int j = 0; j < pattern.length(); j++) {
                if (text.charAt(tmpI) == pattern.charAt(j)) {
                    tmpI++;
//                    最后一次匹配也是成功的，也算；
                    if (tmpI - i == pattern.length()) {
                        return i;
                    }
//                    终止条件
                    if (tmpI >= text.length()) {
                        return -1;
                    }
                } else {
//                    不相等，就移动i ，下一位开始从头比较；
                    break;
                }
            }
        }

        return -1;
    }
}

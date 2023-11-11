package org.example;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        App app = new App();
        List<String> arr = Arrays.asList("cha","r","act","ers");
        System.out.println(app.maxLength(arr));

    }

    int maxLen = 0;
    public int maxLength(List<String> arr) {
        int prevLen = 0;
        int idx =0;
        int[] visitSet = new int[26];
        maxLengthHelper(visitSet, arr, idx, prevLen);
        return maxLen;
    }

    public void maxLengthHelper(int[] visitSet, List<String> arr, int idx, int prevLen){
        if(prevLen>maxLen){
            maxLen = prevLen;
        }
//        递归终止条件；
        if(idx==arr.size()) {
            return;
        }

        for (int i = idx; i < arr.size(); i++) {
            String str = arr.get(i);
            if(isDuplicate(visitSet, str)){
                continue;
            }
            if(addVisitSet(visitSet,str)){
                maxLengthHelper(visitSet, arr, i + 1, prevLen + str.length());
                removeVisitSet(visitSet, str);
            }
        }
    }

    private boolean addVisitSet(int[] visitSet, String str) {
        int[] arr = new int[26];
        for (int i = 0; i < str.length(); i++) {
            if(arr[str.charAt(i)-97]==1) {
                for (int j = 0; j < arr.length; j++) {
                    if(arr[j]==1) {
                        visitSet[j]=0;
                    }
                }
                return false;
            }
            visitSet[str.charAt(i)-97]=1;
            arr[str.charAt(i)-97] = 1;
        }
        return true;
    }

    private void removeVisitSet(int[] visitSet, String str) {
        for (int i = 0; i < str.length(); i++) {
            visitSet[str.charAt(i)-97]=0;
        }
    }

    private boolean isDuplicate(int[] visitSet, String str) {
        for (int i = 0; i < str.length(); i++) {
            if(visitSet[str.charAt(i)-97]==1){
                return true;
            }
        }
        return false;
    }
}

package org.example.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static TreeNode buildTreeNode(Integer[] arr) {
        List<TreeNode> nodeList  = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            if(arr[i]==null) {
                nodeList.add(null);
            }else {
                nodeList.add(new TreeNode(arr[i]));
            }
        }
        for (int i = 0; i < nodeList.size(); i++) {
            TreeNode node = nodeList.get(i);
            if(node ==null) continue;
            int leftChildIdx= 2*i+1;
            int rightChildIdx = 2*i+2;
            if(leftChildIdx< nodeList.size()) {
                node.left = nodeList.get(leftChildIdx);
            }
            if(rightChildIdx<nodeList.size()) {
                node.right = nodeList.get(rightChildIdx);
            }
        }
        return nodeList.get(0);
    }

    public static void printTreeNode(TreeNode root) {
        if(root ==null) return;
        Queue<TreeNode> nodes = new LinkedBlockingQueue<>();
        nodes.add(root);
        StringBuilder sb = new StringBuilder();
        while(!nodes.isEmpty()) {
            TreeNode node = nodes.poll();
            sb.append(node.val + ",");
            if(node.left!=null) {
                nodes.add(node.left);
            }
            if(node.right!=null) {
                nodes.add(node.right);
            }
        }
        System.out.println(sb.toString());
    }

    @Override
    public String toString() {
        return val+"";
    }
}
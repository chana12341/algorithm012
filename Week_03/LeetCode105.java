/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 //从前序与中序遍历序列构造二叉树
class LeetCode105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode root = new TreeNode();
        if(preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<inorder.length;i++) {
            map.put(inorder[i],i);
        }

        return buildTree(preorder,0,preorder.length-1,map,0,inorder.length-1);
    }

    public TreeNode buildTree(int[] preorder,int pleft,int pright,HashMap<Integer,Integer> map,int ileft,int iright) {
        if(pleft > pright || ileft > iright) {
            return null;
        }
        TreeNode root = new TreeNode();
        root.val = preorder[pleft];
        int index = map.get(preorder[pleft]);
        root.left = buildTree(preorder,pleft+1,index-ileft+pleft,map,ileft,index-1);
        root.right = buildTree(preorder,index-ileft+pleft+1,pright,map,index+1,iright);
        return root;
    }
}
class InOrderTravelsal{
	public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null) {
            return list;
        }
        //1.创建栈
        Stack<TreeNode> stack = new Stack<>();
        //2.遍历树的当前元素
        TreeNode current = root;
        while(current != null || !stack.isEmpty()) {
            //3.先当前元素的左子树压栈
            while(current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            //4.将左子树的元素添加进list
            list.add(current.val);
            //5.遍历当前元素的右子树
            current = current.right;
        }
        return list;
    }
}
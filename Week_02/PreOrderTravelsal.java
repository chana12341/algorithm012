class PreOrderTravelsal{
	public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if(root != null) {
            stack.push(root);
        }
        while(!stack.isEmpty()) {
            TreeNode current = stack.pop();
            list.add(current.val);
            if(current.right != null) {
                stack.push(current.right);
            }
            if(current.left != null) {
                stack.push(current.left);
            }
        }
        return list;
    }
}
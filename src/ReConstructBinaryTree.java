/**
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 * 递归思路：通过前序遍历找出二叉树的节点，把中序序列分为两部分，对左右两个序列分别递归寻找根节点直到序列为空则返回空
 */
public class ReConstructBinaryTree {
    public static void main(String[] args) {
        int[] pre = new int[]{1, 2, 3, 4, 5, 6, 7};
        int[] in = new int[]{3, 2, 4, 1, 6, 5, 7};
        ReConstructBinaryTree tree = new ReConstructBinaryTree();
        TreeNode node = tree.reConstructBinaryTree(pre, in);
        System.out.println(node);
    }

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        return buildTree(pre, 0, in, 0, in.length - 1);

    }

    private TreeNode buildTree(int[] pre, int index, int[] in, int start, int end) {
        if (end < start) {
            return null;
        }
        //当前节点肯定是该子树的根节点
        TreeNode root = new TreeNode(pre[index]);

        for (int i = start; i <= end; i++) {
            //用当前节点将当前中序序列分为两部分
            if (pre[index] == in[i]) {
                //递归构建左子树
                root.left = buildTree(pre, index + 1, in, start, i - 1);
                //递归构建右子树，当前节点左子树的节点个数即为当前节点在中序序列中的位置
                // (i - start + 1)为当前节点左子树的个数，即右子树的根节点在前序序列中的位置为index + (i - start + 1)
                root.right = buildTree(pre, index + (i - start + 1), in, i + 1, end);
                break;
            }
        }
        return root;
    }


}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}


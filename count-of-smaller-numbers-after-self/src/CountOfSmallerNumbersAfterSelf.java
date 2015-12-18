import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Created by alexj on 2015/12/18.
 */
public class CountOfSmallerNumbersAfterSelf {
    public List<Integer> countSmaller(int[] nums) {
        Integer[] resultArray = (Integer[]) Array.newInstance(Integer.class, nums.length);
        BSTreeNode root = null;
        for(int i=nums.length-1; i>=0; --i){
            BSTreeNode node = new BSTreeNode(nums[i], 0);
            root = insertNode(root, node);
            resultArray[i] = query(root, nums[i]);
        }
        return Arrays.asList(resultArray);
    }

    class BSTreeNode {
        int value;
        int count;
        BSTreeNode left = null;
        BSTreeNode right = null;

        public BSTreeNode(int value, int count) {
            this.value = value;
            this.count = count;
        }
    }

    private BSTreeNode insertNode(BSTreeNode root, BSTreeNode node) {
        if (root == null)
            return node;

        BSTreeNode current = root;
        while (current != null) {
            if (node.value < current.value) {
                current.count++;
                if (current.left == null) {
                    current.left = node;
                    break;
                } else {
                    current = current.left;
                }
            } else {
                if (current.right == null) {
                    current.right = node;
                    break;
                } else {
                    current = current.right;
                }
            }
        }
        return root;
    }


    private int query(BSTreeNode root, int value) {
        if (root == null)
            return 0;
        BSTreeNode current = root;
        int count = 0;
        while (current != null) {
            if (value > current.value) {
                count += current.count + 1;
                current = current.right;
            } else if (value < current.value) {
                current = current.left;
            } else {
                count += current.count;
                return count;
            }
        }
        return count;
    }
}

package PostedFacebookQuestionsNew;

import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 2. 二叉树的traverse by level和print in vertical order. 前面那个简单,
 * 就是BFS, 后面这个要先遍历, 边遍历边给每个节点一个index, 比如root为0, 做
 * left减1, right加1. 然后建立一个HashMap, key是index, value是list<TreeNode>
 */
public class PrintInVerticalOrder {

    static int[] maxAndMin={0, 0};

    public static void printInVerticalOrder(TreeNode node){
        if(node==null)
            return;
        findMaxAndMin(node, 0);
        int length=maxAndMin[1]-maxAndMin[0]+1;
        List<List<TreeNode>> list=new ArrayList<List<TreeNode>>();
        for(int i=0;i<length;i++){
            list.add(new ArrayList<TreeNode>());
        }
        addNode(node, list, Math.abs(maxAndMin[0]));
        printVerticalOrder(list);
    }

    private static void findMaxAndMin(TreeNode node, int index){
        if(node==null)
            return;
        if(index<maxAndMin[0])
            maxAndMin[0]=index;
        if(index>maxAndMin[1])
            maxAndMin[1]=index;
        findMaxAndMin(node.left, index-1);
        findMaxAndMin(node.right, index+1);
    }

    private static void addNode(TreeNode node, List<List<TreeNode>> list, int index){
        if(node==null)
            return;
        list.get(index).add(node);
        addNode(node.left, list, index-1);
        addNode(node.right, list, index+1);
    }

    private static void printVerticalOrder(List<List<TreeNode>> list){
        for(int i=0;i<list.size();i++){
            List<TreeNode> tempList=list.get(i);
            for(int j=0;j<tempList.size();j++){
                System.out.print(tempList.get(j).data+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args){
        TreeNode node1=new TreeNode(1);
        TreeNode node2=new TreeNode(2);
        TreeNode node3=new TreeNode(3);
        TreeNode node4=new TreeNode(4);
        TreeNode node5=new TreeNode(5);
        TreeNode node6=new TreeNode(6);
        TreeNode node7=new TreeNode(7);
        node1.left=node2;
        node1.right=node3;
        node2.left=node4;
        node2.right=node5;
        node3.left=node6;
        node3.right=node7;
        printInVerticalOrder(node1);
    }
}

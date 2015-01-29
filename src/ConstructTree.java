
import java.util.ArrayList;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bhumikasaivamani
 */
public class ConstructTree {
    TreeNode root;
    
    
    public TreeNode growTree(TreeNode parent,ArrayList<DataSetRow> dataSet) {
        if(root == null)
        {
            //first iteration
            Random rand = new Random();
            String bestAttr = Integer.toString(rand.nextInt(100));
            root = new TreeNode(bestAttr);
            //Get 0's ArrayList
            root.left = growTree(root,null);
            
            //get 1's ArrayList
            root.right = growTree(root,null);
            
            return root;
        }
        else 
        {
            Random rand = new Random();
            int number = rand.nextInt(10);
            if(number%2 == 0)
                return null;
            String bestAttr = Integer.toString(number);
            
            
            TreeNode newNode = new TreeNode(bestAttr);
            //Get 0's ArrayList
            newNode.left = growTree(newNode,null);
            
            //get 1's ArrayList
            newNode.right = growTree(newNode,null);
            
            return newNode;
        }
        
    }
    
    public static void main(String args[])
    {
        //main logic 
        /*
        ConstructTree ct = new ConstructTree();
        TreeNode root = ct.growTree(null,null);
        System.out.println("asdasdad");
                */
    }
    
}


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
public class MainClass 
{
    
    TreeNode root;
    EntropyCalculation calculation;
    
    public MainClass()
    {
        calculation=new EntropyCalculation();
    }
    
    
     public TreeNode growTree(ArrayList<DataSetRow> dataSet,TreeNode node) {
        
        if(dataSet==null)
            return null;
        
        if(calculation.CheckAllOneStopConstraint(dataSet,"Class")==true)
        {
            TreeNode newLeafNode =new TreeNode("Leaf");
            newLeafNode.left=null;
            newLeafNode.right=null;
            newLeafNode.LeafValue=1;
            return newLeafNode;
        }
        if(calculation.CheckAllZeroStopConstraint(dataSet,"Class")==true)
        {
            TreeNode newLeafNode =new TreeNode("Leaf");
            newLeafNode.left=null;
            newLeafNode.right=null;
            newLeafNode.LeafValue=0;
            return newLeafNode;
        }
        
        if(root == null)
        {
            //first iteration
            String bestAttribute=calculation.ChooseNextBestAttribute(dataSet);
            root = new TreeNode(bestAttribute);
            
            ArrayList<ArrayList<DataSetRow>> dividedDataSet=new ArrayList<>();
            dividedDataSet=calculation.ExtractDatawithZeroesAndOnesAttributeValue(dataSet, bestAttribute);
            //Get 0's ArrayList
            root.left = growTree(dividedDataSet.get(0),root);
            
            //get 1's ArrayList
            root.right = growTree(dividedDataSet.get(1),root);
            
            return root;
        }
        else 
        {
            String bestAttribute=calculation.ChooseNextBestAttribute(dataSet);
            
            TreeNode newNode = new TreeNode(bestAttribute);
            
            ArrayList<ArrayList<DataSetRow>> dividedDataSet=new ArrayList<>();
            dividedDataSet=calculation.ExtractDatawithZeroesAndOnesAttributeValue(dataSet, bestAttribute);
            
            newNode.left = growTree(dividedDataSet.get(0),newNode);
            
            //get 1's ArrayList
            newNode.right = growTree(dividedDataSet.get(1),newNode);
            
            return newNode;
        }
        
    }
    
    public TreeNode growTreeByVarianceHeuristic(ArrayList<DataSetRow> dataSet) 
    {
        
        if(dataSet==null)
            return null;
        
        if(calculation.CheckAllOneStopConstraint(dataSet,"Class")==true || calculation.CheckAllZeroStopConstraint(dataSet,"Class")==true)
            return null;
        
        if(root == null)
        {
            //first iteration
            String bestAttribute=calculation.ChooseNextAttributeByVariance(dataSet);
            root = new TreeNode(bestAttribute);
            
            ArrayList<ArrayList<DataSetRow>> dividedDataSet=new ArrayList<>();
            dividedDataSet=calculation.ExtractDatawithZeroesAndOnesAttributeValue(dataSet, bestAttribute);
            //Get 0's ArrayList
            root.left = growTreeByVarianceHeuristic(dividedDataSet.get(0));
            
            //get 1's ArrayList
            root.right = growTreeByVarianceHeuristic(dividedDataSet.get(1));
            
            
            return root;
        }
        else 
        {
            String bestAttribute=calculation.ChooseNextAttributeByVariance(dataSet);
            
            TreeNode newNode = new TreeNode(bestAttribute);
            
            ArrayList<ArrayList<DataSetRow>> dividedDataSet=new ArrayList<>();
            dividedDataSet=calculation.ExtractDatawithZeroesAndOnesAttributeValue(dataSet, bestAttribute);
            
            newNode.left = growTreeByVarianceHeuristic(dividedDataSet.get(0));
            
            //get 1's ArrayList
            newNode.right = growTreeByVarianceHeuristic(dividedDataSet.get(1));
           
            
            return newNode;
        }
        
    }
    
    public void Visited(TreeNode node)
    {
        node.visited=1;
    }
    public boolean checkVisited(TreeNode node)
    {
        if(node.visited==1)
            return true;
        else
            return false;
    }
    public void PrintTree(TreeNode rootNode, int level, int value)
    {
        if(rootNode == null)
            return ;
        
        boolean leftFlag=false;
        if(rootNode.left!=null)
        Visited(rootNode);
        String prefix="";
        for(int i=0;i<level;i++)
            prefix=prefix+"| ";
        
        prefix=prefix+rootNode.value + " = "+value+" : ";
        System.out.print(prefix);
       // if(rootNode.left.value.equals("Leaf"))
            //System.out.print(rootNode.LeafValue);
            //System.out.println("");
            
        if(rootNode.left!=null && checkVisited(rootNode.left)==false)
        {
            if(rootNode.left.value.equals("Leaf")) {
                leftFlag=true;
                System.out.println(rootNode.left.LeafValue);
                //PrintTree(rootNode, level, 1);
            }
            else {
                System.out.println("");
                PrintTree(rootNode.left, level+1, 0);
            }
        }
        if(!(rootNode.left.value.equals("Leaf") && rootNode.right.value.equals("Leaf")))
        {
        String prefix1="";
        for(int i=0;i<level;i++)
            prefix1=prefix1+"| ";
        prefix1=prefix1+rootNode.value + " = 1"+" : ";
        if(rootNode.right.value.equals("Leaf"))
        {
            prefix1=prefix1+rootNode.right.LeafValue;
        }
        System.out.println(prefix1);
        }
        
        if(rootNode.right!=null && checkVisited(rootNode.right)==false)
        {
            if(rootNode.right.value.equals("Leaf")) {
                if(leftFlag==true)
                {
                    String prefix2="";
                    for(int i=0;i<level;i++)
                       prefix2=prefix2+"| ";
                    prefix2=prefix2+rootNode.value + " = 1"+" : ";
                    System.out.print(prefix2);
                    System.out.println(rootNode.right.LeafValue);
                }
                //PrintTree(rootNode, level, 1);
            }
            else{
                if(leftFlag==false &&((rootNode.left.value.equals("Leaf") && rootNode.right.value.equals("Leaf"))))
                {
                System.out.println("");
                }
                PrintTree(rootNode.right, level+1, 0);
            }
        }
        
        
    }
    
    public static void main(String args[])
    {
        ExtractData dataExtraction=new ExtractData();
        //dataExtraction.ExtractDataFromDataSet();
        ArrayList<DataSetRow> data=dataExtraction.ExtractDataFromDataSet();
        MainClass m=new MainClass();
        TreeNode resultantTreeByInformationGainHeuristic=m.growTree(data,null);
        TreeNode resultantTreeByVarianceHeuristic=m.growTreeByVarianceHeuristic(data);
        m.PrintTree(resultantTreeByInformationGainHeuristic,0,0);
        System.out.println("");
        
        /*variance check
        EntropyCalculation e=new EntropyCalculation();
        e.ChooseNextAttributeByVariance(data);
        System.out.println("");*/

   
    }
 }

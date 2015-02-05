
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
    DataManipulations calculation;
    ExtractData dataExtraction;
    int leafCount;
    int nonLeafCount;
    int returnLeafValue;
    ArrayList<DataSetRow> validationData;
    
    public MainClass()
    {
        calculation=new DataManipulations();
        dataExtraction=new ExtractData();
        
    }
    
    /**
     * Function to grow Tree with Information Gain Heuristics
     * @param dataSet (subset of data in recursion)
     * @param root Node (sunsequent nodes in recursion)
     * @return Node of a Tree
     */
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
            if(bestAttribute==null)
            {
            TreeNode newLeafNode =new TreeNode("Leaf");
            newLeafNode.left=null;
            newLeafNode.right=null;
            newLeafNode.LeafValue=0;
            return newLeafNode;
            }
            root = new TreeNode(bestAttribute);
            
            ArrayList<ArrayList<DataSetRow>> dividedDataSet=new ArrayList<>();
            dividedDataSet=calculation.ExtractDatawithZeroesAndOnesAttributeValue(dataSet, bestAttribute);
            root.data=dataSet;
            //Get 0's ArrayList
            root.left = growTree(dividedDataSet.get(0),root);
            
            //get 1's ArrayList
            root.right = growTree(dividedDataSet.get(1),root);
          
            
            return root;
        }
        else 
        {
            String bestAttribute=calculation.ChooseNextBestAttribute(dataSet);
            
            if(bestAttribute==null)
            {
            TreeNode newLeafNode =new TreeNode("Leaf");
            newLeafNode.left=null;
            newLeafNode.right=null;
            newLeafNode.LeafValue=0;
            return newLeafNode;
            }
            
            TreeNode newNode = new TreeNode(bestAttribute);
            
            
            ArrayList<ArrayList<DataSetRow>> dividedDataSet=new ArrayList<>();
            dividedDataSet=calculation.ExtractDatawithZeroesAndOnesAttributeValue(dataSet, bestAttribute);
             newNode.data=dataSet;
            newNode.left = growTree(dividedDataSet.get(0),newNode);
            
            //get 1's ArrayList
            newNode.right = growTree(dividedDataSet.get(1),newNode);
            
           
            return newNode;
        }
        
    }
    
     /**
     * Function to grow Tree with Variance Heuristics
     * @param dataSet (subset of data in recursion)
     * @param root Node (sunsequent nodes in recursion)
     * @return Node of a Tree
     */
    public TreeNode growTreeByVarianceHeuristic(ArrayList<DataSetRow> dataSet,TreeNode node) 
    {
        
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
            String bestAttribute=calculation.ChooseNextAttributeByVariance(dataSet);
            if(bestAttribute==null)
            {
            TreeNode newLeafNode =new TreeNode("Leaf");
            newLeafNode.left=null;
            newLeafNode.right=null;
            newLeafNode.LeafValue=0;
            return newLeafNode;
            }
            root = new TreeNode(bestAttribute);
            
            ArrayList<ArrayList<DataSetRow>> dividedDataSet=new ArrayList<>();
            dividedDataSet=calculation.ExtractDatawithZeroesAndOnesAttributeValue(dataSet, bestAttribute);
            root.data=dataSet;
            //Get 0's ArrayList
            root.left = growTreeByVarianceHeuristic(dividedDataSet.get(0),root);
            
            //get 1's ArrayList
            root.right = growTreeByVarianceHeuristic(dividedDataSet.get(1),root);
            
            
            return root;
        }
        else 
        {
            String bestAttribute=calculation.ChooseNextAttributeByVariance(dataSet);
            if(bestAttribute==null)
            {
            TreeNode newLeafNode =new TreeNode("Leaf");
            newLeafNode.left=null;
            newLeafNode.right=null;
            newLeafNode.LeafValue=0;
            return newLeafNode;
            }
            
            TreeNode newNode = new TreeNode(bestAttribute);
            
            ArrayList<ArrayList<DataSetRow>> dividedDataSet=new ArrayList<>();
            dividedDataSet=calculation.ExtractDatawithZeroesAndOnesAttributeValue(dataSet, bestAttribute);
            newNode.data=dataSet;
            
            newNode.left = growTreeByVarianceHeuristic(dividedDataSet.get(0),newNode);
            
            //get 1's ArrayList
            newNode.right = growTreeByVarianceHeuristic(dividedDataSet.get(1),newNode);
           
            
            return newNode;
        }
        
    }
    
    /**
     * Methods to check whether particular node in a tree is visited.(Used to print Tree)
     * @param node 
     */
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
    /**
     * Function to Print Tree
     * @param rootNode (subsequent nodes given in recursion)
     * @param level (starts with zero level)
     * @param value (determines whether to give zero or one)
     */
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
    
    /**
     * Function to copy a tree,assign node number and count number of leaf and non leaf node 
     * @param node
     * @return 
     */ 
    public TreeNode copyTree(TreeNode node)
    {
        if(node==null)
            return null;
        
        TreeNode newNode=new TreeNode(node.value);
        newNode.LeafValue=node.LeafValue;
        newNode.data=node.data;
        newNode.left=copyTree(node.left);
        newNode.right=copyTree(node.right);
        if(!newNode.value.equals("Leaf"))
        {
            if(newNode.left.value.equals("Leaf") && newNode.right.value.equals("Leaf"))
                leafCount++;
            else {
                nonLeafCount++;
                newNode.nodeNumber = nonLeafCount;
            }
        }
        return newNode;
    }
    
    /**
     * Function to create a copied structure where leafcount and non leaf count is included along with a copied tree
     * @param root
     * @return 
     */
   public CopiedTreeInfo CloneTree(TreeNode root)
   {
       leafCount=0;
       nonLeafCount=0;
       CopiedTreeInfo copiedTreeInfo=new CopiedTreeInfo();
       copiedTreeInfo.root=copyTree(root);
       copiedTreeInfo.numberOfLeaves=leafCount;
       copiedTreeInfo.numberOfNonLeaves=nonLeafCount;
       return copiedTreeInfo;
       
   }
   
   /**
    * Function to FInd replacement Leaf class for a particular node given the node number
    * @param node
    * @param nodeNumber
    * @return 
    */
   public int FindLeafClass(TreeNode node,int nodeNumber)
    {
        
        TreeNode nodeToBePruned=FindReplacementLeafNode(node,nodeNumber);
        ArrayList<DataSetRow> data=new ArrayList<DataSetRow>();
        data=nodeToBePruned.data;
        ArrayList<ArrayList<DataSetRow>> result=calculation.ExtractDatawithZeroesAndOnesAttributeValue(data,"Class");
        int numberOfZeroes=result.get(0)!=null?result.get(0).size():0;
        int numberOfOnes=result.get(1)!=null?result.get(1).size():0;
        
        if (numberOfZeroes>numberOfOnes)
            return 0;
        else
            return 1;
        
    }
   
   /**
    * Function to prune a given tree and replace with appropriate leaf by analyzing subset of data
    * @param node
    * @param nodeNumber 
    */
   public void PruneTree(TreeNode node,int nodeNumber)
   {
       if(root==null)
           return;
       if(node.nodeNumber==nodeNumber)
       {
           int leafValue=FindLeafClass(node,nodeNumber);
           
           node.left=null;
           node.right=null;
           node.value="Leaf";
           node.nodeNumber=-1;
           node.LeafValue=leafValue;
           return;
       }
       if(node.left!=null)
        PruneTree(node.left,nodeNumber);
       if(node.right!=null)
       PruneTree(node.right,nodeNumber);
       
   }
   
   public  TreeNode FindReplacementLeafNode(TreeNode node,int nodeNumber)
   {
       if(node==null)
           return null;
       if(node.nodeNumber==nodeNumber)
           return node;
       if(node.left!=null)
           FindReplacementLeafNode(node.left,nodeNumber);
       if(node.right!=null)
           FindReplacementLeafNode(node.right,nodeNumber);
       return null;
   }
    /**
     * Function to perform post pruning as given in algorithm
     * @param tree
     * @param k
     * @param l
     * @return 
     */
    public TreeNode PostPruning(TreeNode tree,int k,int l)
    {
       
        CopiedTreeInfo copiedTree=new CopiedTreeInfo();
        copiedTree=CloneTree(tree);
        TreeNode DBest=copiedTree.root;
        double bestAccuracy=CalculateAccuracy(tree,validationData);
        for(int i=1;i<=l;i++)
        {
            CopiedTreeInfo DPrime=new CopiedTreeInfo();
            DPrime=CloneTree(tree);
            
            Random random = new Random();
            if(k<=1)
                continue;
            int M = random.nextInt(k - 1) + 1;
            
            for(int j=1;j<=M;j++)
            {
                CopiedTreeInfo DPrimeClone=new CopiedTreeInfo();
                DPrimeClone=CloneTree(DPrime.root);
                int numberOfNonLeafNodes=DPrimeClone.numberOfNonLeaves;
                if(numberOfNonLeafNodes<=1)
                    break;
                int P=random.nextInt(numberOfNonLeafNodes-1)+1;
                
                PruneTree(DPrimeClone.root,P);
                DPrime = DPrimeClone;
            }
            double accuracy=CalculateAccuracy(DPrime.root,validationData);//FindAccuracy(DPrime.root);
            if(accuracy>bestAccuracy)
            {
                bestAccuracy=accuracy;
                DBest=DPrime.root;
            }
        }
       return DBest; 
    }
   /**
    * Find leaf value for calculating accuracy-start from a articular node and go until root according to each row of data and find leaf value for comparison
    * @param rowNumber
    * @param data
    * @param node 
    */ 
   public void FindLeafValueForAccuracyByTraversingValidationSet(int rowNumber,ArrayList<DataSetRow> data,TreeNode node)
   {
       if(node==null)
           return;
       if(node.value.equals("Leaf"))
       {
           returnLeafValue=node.LeafValue;
           return;
       }
   
        String attributeName=node.value;
        int indexOfAttribute=calculation.FindAttributeIndexWithLabel(data,attributeName);
        int attributeValue=data.get(indexOfAttribute).attributeValues.get(rowNumber);
        if(attributeValue==0)
        {
            if(node.left!=null)
            {
                FindLeafValueForAccuracyByTraversingValidationSet(rowNumber,data,node.left);
            }
        }
            
        if(attributeValue==1)
        {
            if(node.right!=null)
            {
                FindLeafValueForAccuracyByTraversingValidationSet(rowNumber,data,node.right);
            }
        }
       
   }
   /**
    * Function to calculate accuracy of tree by comparing with validation set or test set
    * @param node
    * @param data
    * @return 
    */
   public double CalculateAccuracy(TreeNode node,ArrayList<DataSetRow> data)
    {
        int numberOfAttributes=data.size();
        int accuracyCount=0;
        if(node==null)
            return 0;
        
        for(int i=0;i<data.get(0).attributeValues.size();i++)
        {
           returnLeafValue=-999;
           FindLeafValueForAccuracyByTraversingValidationSet(i,data,node);  
           int returnValue=returnLeafValue;
           int indexOfClass=calculation.FindAttributeIndexWithLabel(data,"Class");
           int attributeValue=data.get(indexOfClass).attributeValues.get(i);
           
           if(returnValue==attributeValue)
               accuracyCount++;
         }
        double accuracy=(double)accuracyCount/(data.get(0).attributeValues.size());
        return (accuracy*100);
        
    }
   
   public static void main(String args[])
    {
        int L=Integer.parseInt(args[0]);
        int K=Integer.parseInt(args[1]);
        String filePathForTrainingData=args[2];
        String filePathForTestData=args[3];
        String filePathForValidationData=args[4];
        String to_Print=args[5];
     
        MainClass m=new MainClass();
        ArrayList<DataSetRow> data=m.dataExtraction.ExtractDataFromDataSet(filePathForTrainingData);
        m.validationData=m.dataExtraction.ExtractDataFromDataSet(filePathForValidationData);
        ArrayList<DataSetRow> testData=m.dataExtraction.ExtractDataFromDataSet(filePathForTestData);
        
        m.leafCount=0;
        m.nonLeafCount=0;
        
        TreeNode resultantTreeByInformationGainHeuristic=m.growTree(data,null);
        TreeNode resultantTreeByVarianceHeuristic=m.growTreeByVarianceHeuristic(data,null);
        System.out.println("\tACCURACIES BEFORE PRUNING\t");
        double accuracyIGHeuristics=m.CalculateAccuracy(resultantTreeByInformationGainHeuristic, testData);
        double accuracyVarHeuristics=m.CalculateAccuracy(resultantTreeByVarianceHeuristic, testData);
        System.out.println("Information Gain Heuristics "+accuracyIGHeuristics);
        System.out.println("Variance Heuristics"+accuracyVarHeuristics);
        
        System.out.println("\tACCURACIES AFTER PRUNING\t");
        TreeNode prunedTreeInformationGainHeuristics=m.PostPruning(resultantTreeByInformationGainHeuristic,L,K);
        TreeNode prunedTreeVarianceHeuristics=m.PostPruning(resultantTreeByVarianceHeuristic, L, K);
        double accuracyIGHeuristicsAfterPruning=m.CalculateAccuracy(prunedTreeInformationGainHeuristics, m.validationData);
        double accuracyVarHeuristicsAfterPruning=m.CalculateAccuracy(prunedTreeVarianceHeuristics,m.validationData);
        System.out.println("Information Gain Heuristics "+accuracyIGHeuristicsAfterPruning);
        System.out.println("Variance Heuristics"+accuracyVarHeuristicsAfterPruning);
        
        if(to_Print.equalsIgnoreCase("yes"))
        {
            System.out.println("\tPRINTING TREE\t");
            System.out.println("Tree From Information Gain Heuristics-(After Pruning)");
            m.PrintTree(prunedTreeInformationGainHeuristics,0,0);
            System.out.println("Tree From Variance Heuristics-(After Pruning)");
            m.PrintTree(prunedTreeVarianceHeuristics,0,0);
        }
       
    }

 }

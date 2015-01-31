
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
    
    
     public TreeNode growTree(ArrayList<DataSetRow> dataSet) {
        
        if(dataSet==null)
            return null;
        
        if(calculation.CheckAllOneStopConstraint(dataSet,"Class")==true || calculation.CheckAllZeroStopConstraint(dataSet,"Class")==true)
            return null;
        
        if(root == null)
        {
            //first iteration
            String bestAttribute=calculation.ChooseNextBestAttribute(dataSet);
            root = new TreeNode(bestAttribute);
            
            ArrayList<ArrayList<DataSetRow>> dividedDataSet=new ArrayList<>();
            dividedDataSet=calculation.ExtractDatawithZeroesAndOnesAttributeValue(dataSet, bestAttribute);
            //Get 0's ArrayList
            root.left = growTree(dividedDataSet.get(0));
            
            //get 1's ArrayList
            root.right = growTree(dividedDataSet.get(1));
            
            return root;
        }
        else 
        {
            String bestAttribute=calculation.ChooseNextBestAttribute(dataSet);
            
            TreeNode newNode = new TreeNode(bestAttribute);
            
            ArrayList<ArrayList<DataSetRow>> dividedDataSet=new ArrayList<>();
            dividedDataSet=calculation.ExtractDatawithZeroesAndOnesAttributeValue(dataSet, bestAttribute);
            
            newNode.left = growTree(dividedDataSet.get(0));
            
            //get 1's ArrayList
            newNode.right = growTree(dividedDataSet.get(1));
            
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
            root.left = growTree(dividedDataSet.get(0));
            
            //get 1's ArrayList
            root.right = growTree(dividedDataSet.get(1));
            
            return root;
        }
        else 
        {
            String bestAttribute=calculation.ChooseNextAttributeByVariance(dataSet);
            
            TreeNode newNode = new TreeNode(bestAttribute);
            
            ArrayList<ArrayList<DataSetRow>> dividedDataSet=new ArrayList<>();
            dividedDataSet=calculation.ExtractDatawithZeroesAndOnesAttributeValue(dataSet, bestAttribute);
            
            newNode.left = growTree(dividedDataSet.get(0));
            
            //get 1's ArrayList
            newNode.right = growTree(dividedDataSet.get(1));
            
            return newNode;
        }
        
    }
    
    public static void main(String args[])
    {
        ExtractData dataExtraction=new ExtractData();
        //dataExtraction.ExtractDataFromDataSet();
        ArrayList<DataSetRow> data=dataExtraction.ExtractDataFromDataSet();
        MainClass m=new MainClass();
        TreeNode resultantTreeByInformationGainHeuristic=m.growTree(data);
        TreeNode resultantTreeByVarianceHeuristic=m.growTreeByVarianceHeuristic(data);
        System.out.println("");
        
        /*variance check
        EntropyCalculation e=new EntropyCalculation();
        e.ChooseNextAttributeByVariance(data);
        System.out.println("");*/

   
    }
 }

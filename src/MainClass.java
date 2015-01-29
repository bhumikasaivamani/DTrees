
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
    
    
    public TreeNode growTree(ArrayList<DataSetRow> dataSet) {
        if(root == null)
        {
            //first iteration
            Random rand = new Random();
            String bestAttr = Integer.toString(rand.nextInt(100));
            root = new TreeNode(bestAttr);
            //Get 0's ArrayList
            root.left = growTree(null);
            
            //get 1's ArrayList
            root.right = growTree(null);
            
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
            newNode.left = growTree(null);
            
            //get 1's ArrayList
            newNode.right = growTree(null);
            
            return newNode;
        }
        
    }
    
    public static void main(String atgs[])
    {
        ExtractData dataExtraction=new ExtractData();
        //dataExtraction.ExtractDataFromDataSet();
        ArrayList<DataSetRow> data=dataExtraction.ExtractDataFromDataSet();



    /*
    EntropyCalculation calc=new EntropyCalculation();
    ArrayList<ArrayList<DataSetRow>> returnData = calc.ExtractDatawithZeroesAndOnesAttributeValue(data, "XB");
    System.out.print("asd");


    double classEntropy=calc.ClassEntropyCalculation(data); 
    double maxGain=-1.0;
    //System.out.println(classEntropy);
    for(int i=0;i<21;i++)
    {
        double gain=calc.AttributeGainCalculation(data,i,classEntropy);
        if(gain>maxGain)
        {
            maxGain=gain;
        }
        System.out.println(i+1+ "-"+gain);
    }
    System.out.println("**"+maxGain);
    //AttributeGainCalculation


    //check labels
    ArrayList<Integer> classValues=new ArrayList<>();
    for(int i=0;i<data.size();i++)
    {
        if(data.get(i).attributName.equals("Class"))
        {

            classValues=data.get(i).attributeValues;
        }
    }

    for(int i=0;i<classValues.size();i++)
    {
        System.out.println(classValues.get(i));
    }
    */ 
    }
 }

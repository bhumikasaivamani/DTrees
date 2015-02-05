
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bhumikasaivamani
 */
public class DataManipulations 
{
    /**
     * Function returns true if class value of data or subset of data is all zeroes
     * @param data
     * @param attributeName
     * @return 
     */
    public boolean CheckAllZeroStopConstraint(ArrayList<DataSetRow> data,String attributeName)
    {
        int indexOfClass =data.size()-1;
        int flag=0;
    
        int totalRowsinClassColumn=data.get(indexOfClass).attributeValues.size();
        
        for(int i=0;i<totalRowsinClassColumn;i++)
        {
            if(data.get(indexOfClass).attributeValues.get(i)==1)
              flag=1;  
        }
        if(flag==0)
            return true;
        else
            return false;
        
    }
    
    /**
     * Function returns true if class value of data or subset of data is all ones
     * @param data
     * @param attributeName
     * @return 
     */
    public boolean CheckAllOneStopConstraint(ArrayList<DataSetRow> data,String attributeName)
    {
        int indexOfClass =data.size()-1;
        int flag=0;
    
        int totalRowsinClassColumn=data.get(indexOfClass).attributeValues.size();
        
        for(int i=0;i<totalRowsinClassColumn;i++)
        {
            if(data.get(indexOfClass).attributeValues.get(i)==0)
              flag=1;  
        }
        if(flag==0)
            return true;
        else
            return false;
        
    }
    
    /**
     * Returns two arrayLists with data having one and zero as attribute values seperately
     * @param data
     * @param attributeName
     * @return 
     */
    public ArrayList<ArrayList<DataSetRow>> ExtractDatawithZeroesAndOnesAttributeValue(ArrayList<DataSetRow> data,String attributeName)
    {
        
        ArrayList<DataSetRow> output0 = new ArrayList<>();
        ArrayList<DataSetRow> output1 = new ArrayList<>();
        int attIndex = -1;
        for(int i=0; i<data.size(); i++) {
            if(data.get(i).attributeName.equals(attributeName)){
                attIndex = i;
            }
            else {
                output0.add(new DataSetRow(data.get(i).attributeName));
                output1.add(new DataSetRow(data.get(i).attributeName));
            }
        }
        for(int i=0; i<data.get(attIndex).attributeValues.size(); i++) {
            if(data.get(attIndex).attributeValues.get(i)==0) {
                int outputIndex = 0;
                for(int j=0; j<data.size(); j++) {
                    if(j == attIndex)
                        continue;
                    output0.get(outputIndex++).attributeValues.add(data.get(j).attributeValues.get(i));
                    
                }
            }
            else {
                int outputIndex = 0;
                for(int j=0; j<data.size(); j++) {
                    if(j == attIndex)
                        continue;
                    output1.get(outputIndex++).attributeValues.add(data.get(j).attributeValues.get(i));
                    
                }
            }
        }
        ArrayList<ArrayList<DataSetRow>> output = new ArrayList<>();
        output.add(output0);
        output.add(output1);
        return output;
    }
    /**
     * Given Index find the index of the attribute given the attribute name
     * @param data
     * @param attributeLabel
     * @return 
     */
    public int FindAttributeIndexWithLabel(ArrayList<DataSetRow> data,String attributeLabel)
    {
        int attributeIndex=-1;
         for(int i=0;i<data.size();i++)
         {
            if(data.get(i).attributeName.equals(attributeLabel))
            {
                attributeIndex=i;
            }
         }
        return attributeIndex;

    }
    
    /**
     * Function returns values of particular attribute given the attribute name
     * @param data
     * @param attributeLabel
     * @return 
     */
    public ArrayList<Integer> findAttributeValuesWithLabel(ArrayList<DataSetRow> data,String attributeLabel)
    {
        ArrayList<Integer> attributeValues=new ArrayList<>();
        for(int i=0;i<data.size();i++)
        {
            if(data.get(i).attributeName.equals(attributeLabel))
            {

                attributeValues=data.get(i).attributeValues;
            }
        }
        return attributeValues;
    }
    
    /**
     * Given a list of data returns arraylist containing indexes of attribute row having value one
     * @param data
     * @return 
     */
    public ArrayList<Integer> FindOneIndexes(ArrayList<Integer> data)
    {
        //returns indexed of the array where the value is zero
        ArrayList<Integer> oneIndexes=new ArrayList<>();
        for(int i=0;i<data.size();i++)
        {
            if(data.get(i)==1)
            {
                oneIndexes.add(i);
            }
        }
        return oneIndexes;
    }
   
    /**
     * Given a list of data returns arraylist containing indexes of attribute row having value zero
     * @param data
     * @return 
     */
    public ArrayList<Integer> FindZeroIndexes(ArrayList<Integer> data)
    {
        //returns indexes of the array where the value is zero
        ArrayList<Integer> zeroIndexes=new ArrayList<>();
        for(int i=0;i<data.size();i++)
        {
            if(data.get(i)==0)
            {
                zeroIndexes.add(i);
            }
        }
        return zeroIndexes;
    }
    /**
     * Function to calculate variance for class
     * @param data
     * @return 
     */
    public double ClassVarianceCalculation(ArrayList<DataSetRow> data)
    {
        int totalAttributes=data.size();
        int numberOfZeroes;
        int numberOfOnes;
        int totalNumber;
        double pplus=(double)0.0;
        double pminus=(double)0.0;
        
        ArrayList<Integer> zeroIndexes;
        ArrayList<Integer> oneIndexes;
        ArrayList<Integer> classColumn=new ArrayList<>();
        classColumn=findAttributeValuesWithLabel(data,data.get(totalAttributes-1).attributeName);//Data.get(totalAttributes-1).attributeValues;//ExtractColumn(Data,totalAttributes-1);
        
        
        zeroIndexes=FindZeroIndexes(classColumn);
        oneIndexes=FindOneIndexes(classColumn);
        numberOfZeroes=zeroIndexes.size();
        numberOfOnes=oneIndexes.size();
        totalNumber=numberOfZeroes+numberOfOnes;
        
        pplus=(double)numberOfZeroes/totalNumber;
        pminus=(double)(numberOfOnes)/totalNumber;
        
        double classVariance=(double)(pplus*pminus);
        return classVariance;
    }
    
    /**
     * Given a list having attribute values returns variance
     * @param listOfValues
     * @return 
     */
    public double FindVarianceValue(ArrayList<Integer> listOfValues)
    {
        int numberOfZeroes;
        int numberOfOnes;
        int totalNumber;
        double pplus=(double)0.0;
        double pminus=(double)0.0;
        
        ArrayList<Integer> zeroIndexes;
        ArrayList<Integer> oneIndexes;
        
        zeroIndexes=FindZeroIndexes(listOfValues);
        oneIndexes=FindOneIndexes(listOfValues);
        numberOfZeroes=zeroIndexes.size();
        numberOfOnes=oneIndexes.size();
        totalNumber=numberOfZeroes+numberOfOnes;
        
        
       pplus=(double)numberOfZeroes/totalNumber;
       pminus=(double)(numberOfOnes)/totalNumber;
        
        if(numberOfZeroes==0 || numberOfOnes==0)
          return 99999;
        
        double attributeVariance=(double)(pplus*pminus);
        
        return attributeVariance;
    }
    
    /**
     * Function to calculate entropy of class
     * @param Data
     * @return 
     */
    public double ClassEntropyCalculation(ArrayList<DataSetRow> Data)
    {
        int totalAttributes=Data.size();
        int numberOfZeroes;
        int numberOfOnes;
        int totalNumber;
        double pplus=(double)0.0;
        double pminus=(double)0.0;
        
        ArrayList<Integer> zeroIndexes;
        ArrayList<Integer> oneIndexes;
        ArrayList<Integer> classColumn=new ArrayList<>();
        classColumn=findAttributeValuesWithLabel(Data,Data.get(totalAttributes-1).attributeName);//Data.get(totalAttributes-1).attributeValues;//ExtractColumn(Data,totalAttributes-1);
        
        zeroIndexes=FindZeroIndexes(classColumn);
        oneIndexes=FindOneIndexes(classColumn);
        numberOfZeroes=zeroIndexes.size();
        numberOfOnes=oneIndexes.size();
        totalNumber=numberOfZeroes+numberOfOnes;
        
        pplus=(double)numberOfZeroes/totalNumber;
        pminus=(double)(numberOfOnes)/totalNumber;
        double entropy=(double)((-1)*pplus*(Math.log(pplus)/Math.log(2))-pminus*(Math.log(pminus)/Math.log(2)));  
        return entropy;
    }
    
    /**
     * Function to calculate entropy of attributes
     * @param listOfValues
     * @return 
     */
    public double FindEntropyValue(ArrayList<Integer> listOfValues)
    {
      int numberOfZeroes;
      int numberOfOnes;
      int totalNumber;
      double pplus=(double)0.0;
      double pminus=(double)0.0; 
      
      ArrayList<Integer> zeroIndexes=new ArrayList<>();
      ArrayList<Integer> oneIndexes=new ArrayList<>();
      
      zeroIndexes=FindZeroIndexes(listOfValues);
      oneIndexes=FindOneIndexes(listOfValues);
      numberOfZeroes=zeroIndexes.size();
      numberOfOnes=oneIndexes.size();
      totalNumber=numberOfZeroes+numberOfOnes;
      
      if(numberOfZeroes==0 || numberOfOnes==0)
          return 99999;
      pplus=(double)numberOfZeroes/totalNumber;
      pminus=(double)(numberOfOnes)/totalNumber;
      double entropy=(double)((-1)*pplus*(Math.log(pplus)/Math.log(2))-pminus*(Math.log(pminus)/Math.log(2)));  

      return entropy;
      
    }
    /**
     * Function to calculate Gain given entropy
     * @param Data
     * @param attributeIndex
     * @param classEntropy
     * @return 
     */
    public double AttributeGainCalculation(ArrayList<DataSetRow> Data,int attributeIndex,double classEntropy)
    {
        int totalAttributes=Data.size();
        int numberOfZeroes;
        int numberOfOnes;
        int totalNumber;
        
        ArrayList<Integer> zeroIndexes;
        ArrayList<Integer> oneIndexes;
        ArrayList<Integer> attributeColumn=new ArrayList<>();
        attributeColumn=Data.get(attributeIndex).attributeValues;
        
        zeroIndexes=FindZeroIndexes(attributeColumn);
        oneIndexes=FindOneIndexes(attributeColumn);
        numberOfZeroes=zeroIndexes.size();
        numberOfOnes=oneIndexes.size();
        totalNumber=numberOfZeroes+numberOfOnes;
        
        
        ArrayList<Integer> zeroIndexValues=new ArrayList<>();
        ArrayList<Integer> oneIndexValues =new ArrayList<>();
        
        for(int i=0;i<zeroIndexes.size();i++)
        {
            zeroIndexValues.add(Data.get(totalAttributes-1).attributeValues.get(zeroIndexes.get(i)));
            //zeroIndexValues.add(Data.get(zeroIndexes.get(i)).rowList[totalAttributes-1]);
        }
        
        for(int i=0;i<oneIndexes.size();i++)
        {
            oneIndexValues.add(Data.get(totalAttributes-1).attributeValues.get(oneIndexes.get(i)));
        }
        
        double zeroEntropy=FindEntropyValue(zeroIndexValues);
        double oneEntropy=FindEntropyValue(oneIndexValues);
        
        if(zeroEntropy==99999 || oneEntropy==99999)
            return 99999;
        
        double positive=(double)0.0;
        positive=(double)((double)numberOfZeroes/(double)totalNumber)*zeroEntropy;
        double negative=(double)0.0;
        negative=(double)((double)numberOfOnes/(double)totalNumber)*oneEntropy;
        double gain=classEntropy-positive-negative;
        return gain;
    }
    
    /**
     * Function to calculate Gain given variance
     * @param Data
     * @param attributeIndex
     * @param classVariance
     * @return 
     */
    public double AttributeGainCalculationByVariance(ArrayList<DataSetRow> Data,int attributeIndex,double classVariance)
    {
        int totalAttributes=Data.size();
        int numberOfZeroes;
        int numberOfOnes;
        int totalNumber;
        
        ArrayList<Integer> zeroIndexes;
        ArrayList<Integer> oneIndexes;
        ArrayList<Integer> attributeColumn=new ArrayList<>();
        attributeColumn=Data.get(attributeIndex).attributeValues;
        
        zeroIndexes=FindZeroIndexes(attributeColumn);
        oneIndexes=FindOneIndexes(attributeColumn);
        numberOfZeroes=zeroIndexes.size();
        numberOfOnes=oneIndexes.size();
        totalNumber=numberOfZeroes+numberOfOnes;
        
        
        ArrayList<Integer> zeroIndexValues=new ArrayList<>();
        ArrayList<Integer> oneIndexValues =new ArrayList<>();
        
        for(int i=0;i<zeroIndexes.size();i++)
        {
            zeroIndexValues.add(Data.get(totalAttributes-1).attributeValues.get(zeroIndexes.get(i)));
            //zeroIndexValues.add(Data.get(zeroIndexes.get(i)).rowList[totalAttributes-1]);
        }
        
        for(int i=0;i<oneIndexes.size();i++)
        {
            oneIndexValues.add(Data.get(totalAttributes-1).attributeValues.get(oneIndexes.get(i)));
        }
        
        double zeroEntropy=FindVarianceValue(zeroIndexValues);
        double oneEntropy=FindVarianceValue(oneIndexValues);
        
        if(zeroEntropy==99999 || oneEntropy==99999)
            return 99999;
        
        double positive=(double)0.0;
        positive=(double)((double)numberOfZeroes/(double)totalNumber)*zeroEntropy;
        double negative=(double)0.0;
        negative=(double)((double)numberOfOnes/(double)totalNumber)*oneEntropy;
        double gain=classVariance-positive-negative;
        return gain;
    }
    /**
     * Function to choose next attribute by considering variance heuristics
     * @param data
     * @return 
     */
    public String ChooseNextAttributeByVariance(ArrayList<DataSetRow> data)
    {
        if(data.size()==1)
            return null;
        int numberOfAttributes=data.size()-1;
        double maxGain=-1;
        int attributeWithMaxGainIndex=-1;
        double classVariance=ClassVarianceCalculation(data); //change it so that it sees the label
        for(int i=0;i<numberOfAttributes;i++)
        {
            double gain=AttributeGainCalculationByVariance(data,i,classVariance);
            
            if(gain>maxGain)
            {
                maxGain=gain;
                attributeWithMaxGainIndex=i;
            }
        }
        //System.out.println("**"+maxGain);
        return data.get(attributeWithMaxGainIndex).attributeName;
    }
    public String ChooseNextBestAttribute(ArrayList<DataSetRow> data)
    {
        if(data.size()==1)
            return null;
        int numberOfAttributes=data.size()-1;
        double maxGain=-1;
        int attributeWithMaxGainIndex=-1;
        double classEntropy=ClassEntropyCalculation(data); //change it so that it sees the label
        for(int i=0;i<numberOfAttributes;i++)
        {
            double gain=AttributeGainCalculation(data,i,classEntropy);
            if(gain>maxGain)
            {
                maxGain=gain;
                attributeWithMaxGainIndex=i;
            }
        }
        return data.get(attributeWithMaxGainIndex).attributeName;
    }
}

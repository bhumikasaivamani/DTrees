
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
public class EntropyCalculation 
{
    public ArrayList<Integer> findAttributeValuesWithLabel(ArrayList<DataSetRow> data,String attributeLabel)
    {
        ArrayList<Integer> attributeValues=new ArrayList<>();
        for(int i=0;i<data.size();i++)
        {
            if(data.get(i).attributName.equals(attributeLabel))
            {

                attributeValues=data.get(i).attributeValues;
            }
        }
        return attributeValues;
    }
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
    
    public ArrayList<Integer> FindZeroIndexes(ArrayList<Integer> data)
    {
        //returns indexed of the array where the value is zero
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
    
    
    public double ClassEntropyCalculation(ArrayList<DataSetRow> Data)
    {
        int totalAttributes=21;
        int numberOfZeroes;
        int numberOfOnes;
        int totalNumber;
        double pplus=(double)0.0;
        double pminus=(double)0.0;
        
        ArrayList<Integer> zeroIndexes;
        ArrayList<Integer> oneIndexes;
        ArrayList<Integer> classColumn=new ArrayList<>();
        classColumn=findAttributeValuesWithLabel(Data,Data.get(totalAttributes-1).attributName);//Data.get(totalAttributes-1).attributeValues;//ExtractColumn(Data,totalAttributes-1);
        
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
      
      pplus=(double)numberOfZeroes/totalNumber;
      pminus=(double)(numberOfOnes)/totalNumber;
      double entropy=(double)((-1)*pplus*(Math.log(pplus)/Math.log(2))-pminus*(Math.log(pminus)/Math.log(2)));  

      return entropy;
      
    }
    
    public double AttributeGainCalculation(ArrayList<DataSetRow> Data,int attributeIndex,double classEntropy)
    {
        int totalAttributes=21;
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
        
        double positive=(double)0.0;
        positive=(double)((double)numberOfZeroes/(double)totalNumber)*zeroEntropy;
        double negative=(double)0.0;
        negative=(double)((double)numberOfOnes/(double)totalNumber)*oneEntropy;
        double gain=classEntropy-positive-negative;
        return gain;
    }
    
    
    public String ChooseNextBestAttribute(ArrayList<DataSetRow> data)
    {
        int numberOfAttributes=data.size();
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
        return data.get(attributeWithMaxGainIndex).attributName;
    }
}

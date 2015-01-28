
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
public class MainClass 
{
public static void main(String atgs[])
{
  ExtractData dataExtraction=new ExtractData();
  //dataExtraction.ExtractDataFromDataSet();
  ArrayList<DataSetRow> data=dataExtraction.ExtractDataFromDataSet();
  
  EntropyCalculation calc=new EntropyCalculation();
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
  
}
}

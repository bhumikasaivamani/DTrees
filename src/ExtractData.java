
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bhumikasaivamani
 */
public class ExtractData 
{
    public ArrayList<DataSetRow> ExtractDataFromDataSet(String filePath)
    {
        FileReader fileReader;
        ArrayList<DataSetRow> Data = new ArrayList<>();
        Data=new ArrayList<>();
        String line;
        int totalAttributes=21;
        try
        {
            fileReader=new FileReader(filePath);
            BufferedReader br=new BufferedReader(fileReader);
            String intialline=br.readLine();
            //first line
            StringTokenizer token=new StringTokenizer(intialline,",");

            while(token.hasMoreTokens())
            {
                DataSetRow row = new DataSetRow(token.nextToken().trim());
                Data.add(row);

            } //end of while token loop

         
         
         line=br.readLine();
         
         while(line!=null)
         {
             StringTokenizer token1=new StringTokenizer(line,",");
             
             int attributeIndex = 0;
             while(token1.hasMoreTokens())
             {
                 DataSetRow dsr = Data.get(attributeIndex++);
                 dsr.attributeValues.add(Integer.parseInt(token1.nextToken().trim()));
                
             } //end of while token loop
             line=br.readLine();
         }//end ofwhile line loop
        }//try
        catch(Exception e){
            System.out.println (e);
        }
        
        
        return Data;
        
    }
    
}


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
public class DataSetRow 
{
    public String attributName;
    public ArrayList<Integer> attributeValues;
    
    public DataSetRow(String name) {
        attributName = name;
        attributeValues = new ArrayList<>();
    }
}

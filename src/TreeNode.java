/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bhumikasaivamani
 */
public class TreeNode {
    public TreeNode left;
    public TreeNode right;
    public String value;
    public int LeafValue;
    public int visited;
    
    public TreeNode(String attr){
        LeafValue=-1;
        value = attr;
        visited=0;
    }
}

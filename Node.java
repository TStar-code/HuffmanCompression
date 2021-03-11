/**
* This class is used to build a list of nodes that through setting a parent and children can be used to make a tree.
*/
public class Node
{
    private Character key;
    
    private int value;
    
    private Node parent;
    
    private Node childone;
    
    private Node childtwo;
    
    private int binRep;
    
    private boolean doneQ;
    
    public Character getKey() {
        return key;
    }
    
    public int getValue() {
        return value;
    }
    
    public Node getParent() {
        return parent;
    }
    
    public Node getChildone() {
        return childone;
    }
    
    public Node getChildtwo() {
        return childtwo;
    }
    
    public int getBinRep() {
        return binRep;
    }
    
    public void setValue(int m) {
        this.value = m;
    }
    public void setParent(Node m) {
        this.parent = m;
    }
    public void setChildone(Node m) {
        this.childone = m;
    }
    public void setChildtwo(Node m) {
        this.childtwo = m;
    }
    public void setBinrep(int m) {
        this.binRep = m;
    }
    public void setDoneQ(boolean m) {
        this.doneQ = m;
    }
        
    public Node(Character i, int n, Node g, Node y, Node x) {
        this.key = i;
        this.value = n;
        this.parent = g;
        this.childone = y;
        this.childtwo = x;
    }
}

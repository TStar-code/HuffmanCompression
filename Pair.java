
/**
 * This class is used to store the binary representations of each character generated from the binary tree.
 */
public class Pair
{
    private Character key;
    
    private String binRep;
    
    public Character getKey() {
        return key;
    }
    public String getbinRep() {
        return binRep;
    }
    public void setbinRep(String m) {
        this.binRep = m;
    }
     
    public Pair(Character i, String n) {
        this.key = i;
        this.binRep = n;
    }
}

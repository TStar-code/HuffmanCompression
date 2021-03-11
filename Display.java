import java.util.*;  
/**
 * This class is made up of functions A, B AND C which have been taken from the source:
 * https://www.baeldung.com/java-print-binary-tree-diagram
 * They have then been adapted so that they can be used to display the binary trees, I generate elsewhere in the program, in a satisfying way.
 */
public class Display
{
    private List<Node> list;
    
    //A
    public static String traversePreOrder(Node root) {
        StringBuilder sb = new StringBuilder();
        if (root.getKey() == null) {
            sb.append(root.getValue());
        }
        else {
            sb.append(root.getKey());
        }

        String pointerRight = "└──";
        String pointerLeft = (root.getChildtwo() != null) ? "├──" : "└──";

        traverseNodes(sb, "", pointerLeft, root.getChildone(), root.getChildtwo() != null);
        traverseNodes(sb, "", pointerRight, root.getChildtwo(), false);

        return sb.toString();
    }
    
    //B
    public static void traverseNodes(StringBuilder sb, String padding, String pointer, Node node, boolean hasRightSibling) {
        if (node != null) {
            sb.append("\n");
            sb.append(padding);
            sb.append(pointer);
            
            if (node.getKey() == null) {
                sb.append(node.getValue());
            }
            else {
                sb.append(node.getKey());
            }

            StringBuilder paddingBuilder = new StringBuilder(padding);
            if (hasRightSibling) {
                paddingBuilder.append("│  ");
            } 
            else {
                paddingBuilder.append("   ");
            }

            String paddingForBoth = paddingBuilder.toString();
            String pointerRight = "└──";
            String pointerLeft = (node.getChildtwo() != null) ? "├──" : "└──";

            traverseNodes(sb, paddingForBoth, pointerLeft, node.getChildone(), node.getChildtwo() != null);
            traverseNodes(sb, paddingForBoth, pointerRight, node.getChildtwo(), false);
        }
    }
    
    //C
    public static void print(List<Node> l) {
        System.out.print(traversePreOrder(l.get(0)));
    }
    
    public void displayTree() {
        print(list);
    }
    
    public Display(List<Node> l) {
        this.list = l;
    }
}

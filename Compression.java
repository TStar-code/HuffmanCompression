import java.io.*;  
import java.util.*;  
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
* This is the main class of the program from which all functionality is derived from, principally the main method.
*/
public class Compression
{
    private static int COUNT = 0;
    private static double BITS_PER_CHAR = 0;
    private static boolean STILL_USING = true;
    private static boolean FOUND = false;
    private static String TEMP_STRING = "";
    private static String LINE_STRING = "";
    private static String NEW_BIN = "";
    
    
    private static ArrayList<String> lines = new ArrayList<String>();
    private static ArrayList<Character> chars = new ArrayList<Character>();
    private static Map<Character,Integer> allLetters = new Hashtable<Character,Integer>();
    private static List<Node> nodes = new LinkedList<Node>();
    private static List<Node> huffmanEncoding = new LinkedList<Node>();
    private static List<Pair> huffmanEncodingList = new LinkedList<Pair>();
    private static List<String> encodedText = new LinkedList<String>();
    private static List<String> defaultText = new LinkedList<String>();
    /**
    * This is the main method of the program and its where the menu is located and from where everywhere else in the program begins.
    */
    public static void main(String[] args) {
        Scanner inputOutput = new Scanner(System.in);
        while (STILL_USING == true) {
            System.out.println(System.lineSeparator() + "Menu: (Note: option 2 uses the most recently generated tree to encode, i.e the last option you chose)");
            System.out.println("1. New Encoding");
            System.out.println("2. Same Encoding different text");
            System.out.println("3. Display the current encoding as a binary tree ");
            System.out.println("0. Quit");
            Integer input = Integer.parseInt(inputOutput.nextLine()); 
            
            if (input == 1) {
                
                COUNT = 0;
                TEMP_STRING = "";
                LINE_STRING = "";
                NEW_BIN ="";
                
                lines.clear();
                chars.clear(); 
                allLetters.clear();
                nodes.clear(); 
                huffmanEncoding.clear(); 
                huffmanEncodingList.clear();
                encodedText.clear();
                defaultText.clear();
                
                System.out.println("Enter the name of the file you want to encode:");
                String fileName = inputOutput.nextLine(); 
          
                huffmanEncoding(fileName, false);
            }
            else if (input == 2) {
                COUNT = 0;
                TEMP_STRING = "";
                LINE_STRING = "";
                NEW_BIN ="";
                
                lines.clear();
                chars.clear(); 
                allLetters.clear();
                nodes.clear(); 
                huffmanEncoding.clear(); 
                //huffmanEncodingList.clear();
                encodedText.clear();
                defaultText.clear(); 
                
                System.out.println("Enter the name of the file you want to encode:");
                String fileName = inputOutput.nextLine(); 
          
                huffmanEncoding(fileName, true);
            }
            else if (input == 3) {
                if (huffmanEncoding.isEmpty() == false) {
                    Display display = new Display(huffmanEncoding);
                    display.displayTree();
                }
                else {
                    System.out.println("Sorry there is not a tree avalible at this time.");    
                }
            }
            else if (input == 0) {
                STILL_USING = false;
            }
            else {
                STILL_USING = true;
            }
        }
    }
    
    /**
    * This method is used to perform the encoding itself, both for option 1 and 2 of the menu.
    */ 
    public static void huffmanEncoding(String filename, boolean same) {
        if (same == true) {
            //Same Encoding different text option 2
            try {
                File file = new File(filename + ".txt");    
                FileReader f = new FileReader(file);    
                BufferedReader b = new BufferedReader(f);  
                String l; 
                while((l=b.readLine())!=null)  {
                    if (l.length() != 0) {
                        lines.add(l);
                    }
                }
            }
            catch(IOException I) {
                I.printStackTrace();
            }  
          
            lines.forEach( lin -> {
                for (int i = 0; i < lin.length(); i++) {
                    char temp = lin.charAt(i);
                    chars.add(temp);
                }
            });
            
            int totalChars = huffmanEncodingList.size();
            
            BITS_PER_CHAR = Math.ceil((Math.log(totalChars) / Math.log(2)));
            
            lines.forEach( lin -> {
                LINE_STRING = "";
                for (int i = 0; i < lin.length(); i++) {
                    char temp = lin.charAt(i);
                    huffmanEncodingList.forEach( h -> {
                        if (h.getKey() == temp) {
                            LINE_STRING = LINE_STRING + h.getbinRep();
                            FOUND = true;
                        }
                    });
                    if (FOUND == false) {
                        boolean correct = false;
                        while (correct == false) {
                            
                            for (int j = 0; j < BITS_PER_CHAR; j++) {
                                if (Math.random() < 0.5) {
                                    NEW_BIN += "0";
                                }
                                else {
                                    NEW_BIN += "1";
                                }
                            }
                            huffmanEncodingList.forEach( h -> {
                                if (h.getbinRep() != NEW_BIN) {
                                    COUNT++;
                                }
                            });
                            if (COUNT == huffmanEncodingList.size()) {
                                Pair bong = new Pair(temp, NEW_BIN);
                                huffmanEncodingList.add(bong);
                                FOUND = true;
                            }   
                        }
                        
                        huffmanEncodingList.forEach( h -> {
                        if (h.getKey() == temp) {
                            LINE_STRING = LINE_STRING + h.getbinRep();
                            FOUND = true;
                        }
                        });
                        
                    }
                }
                encodedText.add(LINE_STRING);
            });

            Path path = Paths.get(filename+"EncodedUsingLanguageX.txt");

            try {
                Files.write(path, encodedText);   
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            
            System.out.println("Successful encoding with different tree");
        }
        else {
            //New Encoding option 1
            try {
                File file = new File(filename + ".txt");    
                FileReader f = new FileReader(file);    
                BufferedReader b = new BufferedReader(f);  
                String l; 
                while((l=b.readLine())!=null)  {
                    if (l.length() != 0) {
                        lines.add(l);
                    }
                }
            }
            catch(IOException I) {
                I.printStackTrace();
            }  
          
            lines.forEach( lin -> {
                for (int i = 0; i < lin.length(); i++) {
                    char temp = lin.charAt(i);
                    chars.add(temp);
                }
            });
        
            chars.forEach( c -> {
                if (allLetters.get(c) == null) {
                    allLetters.put(c, 1);
                }
                else if (allLetters.get(c) > 0) {
                    int temp = allLetters.get(c) + 1;
                    allLetters.put(c, temp);
                }

            });
        
            LinkedHashMap<Character, Integer> allLettersSorted = new LinkedHashMap<>();
         
            allLetters.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> allLettersSorted.put(x.getKey(), x.getValue()));
        
            allLettersSorted.forEach((k, v) -> {
                Node temp = new Node(k,v,null,null,null);
                nodes.add(temp);
            });
            
            while(nodes.isEmpty() == false) {
                if (nodes.size() == 1) {
                    Collections.sort(huffmanEncoding, new SortById());
                
                    int lastele = huffmanEncoding.size() - 1;
                
                    Node last = nodes.get(0);
                
                    last.setChildone(huffmanEncoding.get(lastele));
                    last.setChildtwo(huffmanEncoding.get(lastele - 1));
                
                    huffmanEncoding.add(last);
                
                    nodes.remove(0);
                }
                else {
                    Node smallest = nodes.get(0);
                    Node smallesttwo = nodes.get(1);
            
                    Node parent = new Node(null,smallest.getValue() + smallesttwo.getValue(),null,smallest,smallesttwo);
                
                    smallest.setBinrep(0);
                    smallesttwo.setBinrep(1);
          
                    smallest.setParent(parent);
                    smallesttwo.setParent(parent);
        
                    nodes.remove(0);
                    nodes.remove(0);
                
                    nodes.add(parent);
                
                    huffmanEncoding.add(smallest);
                    huffmanEncoding.add(smallesttwo);
 
                    Collections.sort(nodes, new SortById()); 
                }
            }
        
            Collections.reverse(huffmanEncoding);
            
            huffmanEncoding.forEach( n -> {
                String tempString = "";
                if (n.getKey() != null) {
                    Node temp = n;
                    Pair tempPath = new Pair(n.getKey(), tempString);
                    while(temp.getValue() != huffmanEncoding.get(0).getValue()) {
                        tempString = temp.getBinRep() + tempString;
                        temp = temp.getParent();
                    }
                    tempPath.setbinRep(tempString);
                    huffmanEncodingList.add(tempPath);
                }
            
            });
            
            int totalChars = huffmanEncodingList.size();
            
            double bitsPerChar = Math.ceil((Math.log(totalChars) / Math.log(2)));
            
            for (int i = 1; i < bitsPerChar; i++) {
                TEMP_STRING += "0";
            }
            
            
            lines.forEach( lin -> {
                LINE_STRING = "";
                for (int i = 0; i < lin.length(); i++) {
                    char temp = lin.charAt(i);
                    huffmanEncodingList.forEach( h -> {
                        if (h.getKey() == temp) {
                            LINE_STRING = LINE_STRING + h.getbinRep();
                        }
                    });
                }
                encodedText.add(LINE_STRING);
            });
            
            lines.forEach( lin -> {
                LINE_STRING = "";
                for (int i = 0; i < lin.length(); i++) {
                    LINE_STRING = LINE_STRING + TEMP_STRING;
                }
                defaultText.add(LINE_STRING);
            });
            
            encodedText.add(".");
            
            huffmanEncodingList.forEach( h -> {
                encodedText.add(h.getKey() + "," + h.getbinRep());
            });
            
            
            Path path = Paths.get(filename+"Encoded.txt");

            try {
                Files.write(path, encodedText);   
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            
            Path pathtwo = Paths.get(filename+"Default.txt");

            try {
                Files.write(pathtwo, defaultText);   
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Succesful Encoding");
        }
    }
}

/**
* This class is used to help sort the list of nodes so that the huffman encoding can be performed. 
*/
class SortById implements Comparator<Node> {   
    public int compare(Node a, Node b)  {  
        return a.getValue() - b.getValue();  
    }  
}  
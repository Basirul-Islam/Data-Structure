package huffman.algorithm;

public class MainApplication {

    public static void main(String[] args) {

        String text = "HELLO_ALL";
        HuffManTree tree = new HuffManTree();
        tree.HuffManTreeBuilder(text);

        System.out.println("Character-Frequency-Code");
        tree.printFreq();
        
        byte[] data = tree.compressedIt();
        
        System.out.println("Before Compression: " + text + "\t[Size: " + Character.SIZE*text.length() + " bits]" );
        
        DecodeIt decode = new DecodeIt(tree.root,data);
        
        System.out.print("Encoded data is: " );
        decode.printDecodeData();
  
        System.out.println("\nAfter Compression: " + decode.getOrginalData());
        
        System.out.println("\t\t\t\t\t#HuffManTree#");
        System.out.println("\n");
        tree.printTree(tree.root);
        System.out.println("");
        

    }

}

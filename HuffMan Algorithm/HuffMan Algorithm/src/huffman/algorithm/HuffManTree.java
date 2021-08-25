package huffman.algorithm;

/*
    This class has several methods and attributes;
    
*/


public class HuffManTree {

    private FrequencyChart myFrequency; // it take a data field and make an array to store number of occurence of every character;
    private int[] arrayOfFrequency; // it holds the array of frequency 
    private MinHeap heap; // create a minimum heap for push() and pop() back purpose..act like priority queue;
    public Node root; // it holds the root Node of HuffManTree
    private String text; //Orginal data field will be store here;
    EncodedTable[] table; //Make a full size table with 'N' number of fields. Every field store Name of character,Number of occurrence and its unique code;
    public static int numberOfLastBitInEncodedCode; //It make sure that is any extra bit is availabe or not in the last index of 

    public void HuffManTreeBuilder(String text) {
        myFrequency = new FrequencyChart(text);
        heap = new MinHeap();
        this.text = text;
        arrayOfFrequency = myFrequency.getFrequency();
        for (int i = 0; i < arrayOfFrequency.length; i++) {
            if (arrayOfFrequency[i] != 0) {
                Node tmp = new Node();
                tmp.character = (char) i;
                tmp.freq = arrayOfFrequency[i];
                tmp.leftNode = null;
                tmp.rightNode = null;

                heap.insert(tmp);
            }
        }

        buildHuffManTree();
    }

    public void buildHuffManTree() {
        if (heap.is_empty()) {
            return;
        }

        if (heap.getSizeOfHeap() == 0) {
            Node parent = new Node();
            parent = heap.extract();
            root = parent;
            return;
        }

        while (true) {

            Node leftChild = new Node();
            Node rightChild = new Node();
            Node parent = new Node();

            leftChild = heap.extract();
            rightChild = heap.extract();

            parent.character = '\0';
            parent.freq = leftChild.freq + rightChild.freq;
            parent.leftNode = leftChild;
            parent.rightNode = rightChild;

            root = parent;

            if (heap.is_empty()) {
                return;
            }

            heap.insert(parent);
        }
    }

    public EncodedTable[] generateCode() {

        table = new EncodedTable[128];
        String code = "\0";
        Node tmp = root;
        Node parent = root;
        int index;

        if (root == null) {
            return null;
        }

        if (root.leftNode == null && root.rightNode == null) {
            index = (int) tmp.character;
            table[index] = new EncodedTable();
            table[index].character = tmp.character;
            table[index].freq = tmp.freq;
            table[index].code = "0";
            code = "\0";
            return table;
        }

        while (root.leftNode != null || root.rightNode != null) {
            tmp = root;
            while (true) {
                if (tmp.leftNode != null) {
                    parent = tmp;
                    code = code + "0";
                    tmp = tmp.leftNode;
                } else if (tmp.rightNode != null) {
                    parent = tmp;
                    code = code + "1";
                    tmp = tmp.rightNode;
                } else if (tmp.leftNode == null && tmp.rightNode == null && tmp.character != '\0') {
                    index = (int) tmp.character;
                    table[index] = new EncodedTable();
                    table[index].character = tmp.character;
                    table[index].freq = tmp.freq;
                    table[index].code = code;
                    code = "\0";
                    if (tmp == parent.leftNode) {
                        parent.leftNode = null;
                    } else if (tmp == parent.rightNode) {
                        parent.rightNode = null;
                    }

                    break;
                } else if (tmp.leftNode == null && tmp.rightNode == null && tmp.character == '\0') {
                    if (tmp == parent.leftNode) {
                        parent.leftNode = null;
                    } else if (tmp == parent.rightNode) {
                        parent.rightNode = null;
                    }
                    code = "\0";
                    break;
                }
            }
        }

        HuffManTreeBuilder(text);
        return table;

    }

    public String getEncodedData() {
        String encodedCode = "\0";
        EncodedTable[] table = generateCode();

        for (int i = 0; i < text.length(); i++) {
            encodedCode = encodedCode + table[(int) text.charAt(i)].code;
        }
        
        
        
        return encodedCode;
    }

    public void printTree(Node tmp) {
        if (root != null) {
                System.out.println("LC: " + tmp.leftNode + " (" + tmp.character + " , " + tmp.freq + ") " + "RC: " + tmp.rightNode + " (S: " + tmp + ")");
            

            if (tmp.leftNode != null) {
                printTree(tmp.leftNode);
            }
            if (tmp.rightNode != null) {
                printTree(tmp.rightNode);
            }
        }
    }
    
    public byte[] compressedIt()
    {
        String tmpCode = this.getEncodedData();
        char[] storeData = new char[tmpCode.length()];
        int length = 0;

        for(int i=0; i<tmpCode.length(); i++)
        {
            if(tmpCode.charAt(i)=='0' || tmpCode.charAt(i)=='1')
            { 
                storeData[length] = tmpCode.charAt(i);
                length++;
                
            }
                
        }
        
        byte[] encodedData;
        int count = 0;
        numberOfLastBitInEncodedCode = length%7;
        
        if(length%7==0)
            encodedData = new byte[length/7];
        else
            encodedData = new byte[(length/7)+1];
       
        int i,j=0;
        char[] tmpData = new char[7];
        for(i=0; i<(length/7)*7; i++)
        {
           tmpData[j] = storeData[i];
           j++;
           if(j==7)
           {
               j = 0;
               String subCode = String.valueOf(tmpData);
               encodedData[count] = Byte.parseByte(subCode,2);
               count++;
           }
        }
        if(length%7!=0)
        {
            tmpData = new char[(length%7)];
            j = 0;
            for(i=((length/7)*7); i<length; i++)
            {
                tmpData[j] = storeData[i];
                j++;
            }
            String subCode = String.valueOf(tmpData);
            encodedData[count] = Byte.parseByte(subCode,2);
        }
        
        return encodedData;
        
    }
    
    public void printFreq()
    {
        
        EncodedTable[] table = this.generateCode();
            
        if(table == null) return;
        
         for (int i = 0; i < 128; i++) {
            if (table[i] != null) {
                System.out.println(table[i]);
            }
        }

    }
    

}

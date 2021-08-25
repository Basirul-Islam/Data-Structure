package huffman.algorithm;

public class DecodeIt {

    private Node root;
    private Node tmp;
    private byte[] encodedCode;

    public DecodeIt(Node root, byte[] encodedCode) {
        this.root = root;
        this.encodedCode = encodedCode;
    }

    public String getOrginalData() {
        
        String orginalData = "\0";
        tmp = root;
        int i;
        
        if(root.leftNode == null && root.rightNode==null)
        {
            for(i=0; i<root.freq; i++)
                orginalData = orginalData + String.valueOf(root.character);
            
            return orginalData;
        }
        
        if(HuffManTree.numberOfLastBitInEncodedCode==0)
        {
            for(i=0; i<encodedCode.length; i++)
            {
                int mask = 64;
                while(mask>0)
                {
                    if(tmp.leftNode==null && tmp.rightNode==null)
                    {
                        orginalData = orginalData + String.valueOf(tmp.character);
                        tmp = root;
                    }
                    if((encodedCode[i]&mask) == 0)
                    {
                        System.out.print("0");
                        tmp = tmp.leftNode;
                    }
                    else 
                    {
                        System.out.print("1");
                        tmp = tmp.rightNode;
                    }
                    
                    mask = mask>>1;
                    
                }
                
                if(tmp.leftNode==null && tmp.rightNode==null)
                    {
                        orginalData = orginalData + String.valueOf(tmp.character);
                        tmp = root;
                    }
            }
        }
        
        else
        {
             for(i=0; i<encodedCode.length-1; i++)
            {
                int mask = 64;
                while(mask>0)
                {
                    if(tmp.leftNode==null && tmp.rightNode==null)
                    {
                        orginalData = orginalData + String.valueOf(tmp.character);
                        tmp = root;
                    }
                    if((encodedCode[i]&mask) == 0)
                    {
                        System.out.print("0");
                        tmp = tmp.leftNode;
                    }
                    else 
                    {
                        System.out.print("1");
                        tmp = tmp.rightNode;
                    }
                    
                    mask = mask>>1;
                    
                }
                
                if(tmp.leftNode==null && tmp.rightNode==null)
                    {
                        orginalData = orginalData + String.valueOf(tmp.character);
                        tmp = root;
                    }
            }
             
              int mask = (int)Math.pow(2,HuffManTree.numberOfLastBitInEncodedCode-1);
              while(mask>0)
                {
                    if(tmp.leftNode==null && tmp.rightNode==null)
                    {
                        orginalData = orginalData + String.valueOf(tmp.character);
                        tmp = root;
                    }
                    if((encodedCode[i]&mask) == 0)
                    {
                        System.out.print("0");
                        tmp = tmp.leftNode;
                    }
                    else 
                    {
                        System.out.print("1");
                        tmp = tmp.rightNode;
                    }
                    
                    mask = mask>>1;
                    
                }
              if(root.leftNode==null && root.rightNode==null)
                  orginalData =  String.valueOf(root.character);
              else
              {
               if(tmp.leftNode==null && tmp.rightNode==null)
                    {
                        orginalData = orginalData + String.valueOf(tmp.character);
                        tmp = root;
                    }
              }
             
        }
        
        return orginalData;
    }

    public void printDecodeData() {
        for (int i = 0; i < encodedCode.length; i++) {
            System.out.print(encodedCode[i] + " ");
        }

        System.out.println(" [Size: " + (Byte.SIZE * encodedCode.length) + " bits]");
        System.out.println("");
    }

}

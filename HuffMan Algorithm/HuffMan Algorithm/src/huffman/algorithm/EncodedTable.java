package huffman.algorithm;

/*
When we make an object of FrequencyChart its gives an array of frequency of each and every character;
Now we have to built a Table for Maping;
For example if the data field is "HELLO_ALL"
Then this field holds 6 unique character (A,E,H,L,O,_) and the frequency of those character;

So, the Table is:

Character   Frequency   Code
    A           1       1110

    E           1       110

    H           1       1111

    L           4        0

    O           1       101

    _           1       100

    each code of every character represent the unique path from root to that character.
    When we make a HuffMan Tree then the leaf node holds the Node type node and we found a unique path.
    left edge of the tree represent 0 and the right edge of the tree represent 1

    and this code comes from HuffManTree

    Code is required for mapping character with data field

   if we merge every code from this table we get an output like this
   1111 110 0 0 101 100 1110 0 0

   then we start scanning from index 0 to length and extract bit one by one and using this bit we can traverse the tree
   if we get a 1 we should go to the right side of the tree and if we get a 0 then we should go to the left side of the tree
   finally when we get a leaf node then we have to pick the character from Node field and print it.

    * toString() method return the every raw of the table;
    
*/

public class EncodedTable {

    char character;
    int freq;
    String code;

    @Override
    public String toString() {
        return "   " + character + "         " + freq + "\t   " + code + "\n";
    }

}

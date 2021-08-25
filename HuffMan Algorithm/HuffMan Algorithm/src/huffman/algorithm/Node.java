package huffman.algorithm;

/*
This is a Node Structure.
Every Node has 2 properties.
It holds every unique character from data field and also hold its frequency.
If a node is a leaf node then the left connection and right connection is by default null;
Note that every leaf node holds a unique character and number of frequency.
 */
public class Node {

    public char character;
    public int freq;
    public Node leftNode; //by default it is null
    public Node rightNode; //by default it is null

}

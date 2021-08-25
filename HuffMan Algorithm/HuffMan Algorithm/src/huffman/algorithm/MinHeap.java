package huffman.algorithm;

/*
This class is specially build for priority queue.
Minimum heap is used here for making a priority queue.
The atributes of this class is it holds an array of Node and it can return the size of a heap;
By default the size of the array is fixed. Because of a data field has at most 128 unique character if it is write using ASCII code;

1. boolean is_empty() returns is the heap is empty or not;
2. void insert(Node node) push a Node type data in the heap and then heapify it;
3. Node extract() returns a Node type data as like as pop() method in stack;
4. int getSizeOfHeap() return the size of heap;

 */
public class MinHeap {

    private Node[] heap;
    private int sizeOfHeap;

    public MinHeap() {
        heap = new Node[128];
        sizeOfHeap = -1;
    }

    public boolean is_empty() {
        if (sizeOfHeap == -1) {
            return true;
        } else {
            return false;
        }
    }

    public void insert(Node node) {
        sizeOfHeap++; //Increase the index of 'N' to point the data;[Note: Here 'N' is the size of a heap]
        heap[sizeOfHeap] = new Node(); // allocate a memory location and then push a node inside it;
        heap[sizeOfHeap] = node;

        int parent = sizeOfHeap; // assign 'Nth' number of index to newly added data as a parent;

        while (parent > 0) { // check until the 'i' value is greater than 0; [Note: here 'i' act as parent and 'N' act as numberOfNode in the heap;

            if (heap[parent].freq < heap[(parent - 1) / 2].freq) { // if child>parent then swap them;
                Node tmp;
                tmp = heap[parent];
                heap[parent] = heap[(parent - 1) / 2];
                heap[(parent - 1) / 2] = tmp;
            }

            parent = (parent - 1) / 2; //change parent to its grandparent;
        }
    }

    public Node extract() {
        if (is_empty()) { // Check if the heap is empty or not!
            return null;
        }

        Node returnNode = heap[0]; //assign the root index data to the variable "data";(for returning purpose)
        heap[0] = heap[sizeOfHeap]; // assign the last index data to root index;
        sizeOfHeap--; //then decrease the size of the heap;

        int parent = 0; //assign root node index to the variable 'parent' for Down Hepify

        while (true) {

            int leftChild = (2 * parent) + 1; // initialize the left and right child using this following formula;
            int rightChild = (2 * parent) + 2;

            if (leftChild <= sizeOfHeap && rightChild <= sizeOfHeap) { //if there exits two children in the target node;
                if (heap[leftChild].freq < heap[rightChild].freq) { //Check is the left child is lesser than the right child or not;
                    Node tmp;
                    tmp = heap[parent];
                    heap[parent] = heap[leftChild];     // if true then swap the target node and left child;
                    heap[leftChild] = tmp;
                    parent = leftChild;
                } else {
                    Node tmp;
                    tmp = heap[parent];
                    heap[parent] = heap[rightChild]; // if false the swap the target node and right child;
                    heap[rightChild] = tmp;
                    parent = rightChild;
                }
            } else if (leftChild <= sizeOfHeap && (heap[parent].freq > heap[leftChild].freq)) { // if only left child exists then check is the target node is greater than the left child or not;
                Node tmp;
                tmp = heap[parent];
                heap[parent] = heap[leftChild]; // if true then swap them;
                heap[leftChild] = tmp;
            }

            if (leftChild >= ((sizeOfHeap / 2) + 1)) { // if it is a leaf node then break the loop;
                break;                                 // formula to find a leaf node is --> from ( (i/2)+1 to n );   
            }
        }

        return returnNode;

    }

    public int getSizeOfHeap() {
        return sizeOfHeap;
    }

}

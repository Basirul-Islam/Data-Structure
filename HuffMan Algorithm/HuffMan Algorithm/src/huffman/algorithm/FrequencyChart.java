package huffman.algorithm;

/*
FrequencyChart can count the number of occurrence of each and every character in the data field and store it to an array;
The constructor of this class make a fixed size of array to store the frequency of data;
It scan from 0 to length and count the number of occurrence;
The mechanism to count the frequency is:
the array int frequency[] make a 128 size array and initially every element of the array is zero.(by default the value of instance primitive tytpe int variable is 0) 
For example if a data field is "bacabca" then it start scanning from "b" and increasing the value of frequency[(int)b];
so the value of array[98] will be 0 to 1 and go on; because the ascii value of 'b' is 98
and finally the remaining value of the array is 0;
*/

public class FrequencyChart {

    private int frequency[]; // make an array to store the number of occurrence of every character;

    public FrequencyChart(String text) {
        frequency = new int[128];   // initialize its size;
        for (int i = 0; i < text.length(); i++) {
            frequency[(int) text.charAt(i)]++;      //scan the data field and increasing the value of frequency[(int)targetCharacter];
        }
    }

    public int[] getFrequency() { // Return this array. For example the array is look like  ....0 2 3 0 0 5 0.....
        return frequency;
    }

}

/*******************************
Class: DSAHeap
Last Modified: 31/10/20
Purpose: A Java implementation of the Heap data structure
 Author: Austin Bevacqua
 CITATION: Originally submitted for DSA Practical 8 by Austin Bevacqua (20162896)
*******************************/
import java.util.*;
public class DSAHeap {

    /************************
     * Inner Class: DSAHeapEntry
     * Function: Stores data for the DSAHeap
     *  Author: Austin Bevacqua
     *  CITATION: Originally submitted for DSA Practical 8 by Austin Bevacqua (20162896)
     **************************/
    private class DSAHeapEntry {

        //Private Classfields
        double priority; //The order in which the elements are sorted
        Object value; //The actual value stored

        //CONSTRUCTORS
        /**********************
         Alternate Constructor
         **********************/
        public DSAHeapEntry(double inPriority, Object inValue) {
            priority = inPriority;
            value = inValue;
        }

        //ACCESSORS
        /******************
         Get Priority
         Author: Austin Bevacqua
         CITATION: Originally submitted for DSA Practical 8 by Austin Bevacqua (20162896)
         *****************/
        public double getPriority() {
            return priority;
        }

        /****************
         Get Value
         Author: Austin Bevacqua
         CITATION: Originally submitted for DSA Practical 8 by Austin Bevacqua (20162896)
         ****************/
        public Object getValue() {
            return value;
        }

    }
   
    public static final int DEFAULT_SIZE = 20;

    //Private Classfields
    int count;
    DSAHeapEntry[] heap;

    //CONSTRUCTORS
    /*********************
    Default Constructor
    ********************/
    public DSAHeap() {
        count = 0;
        heap = new DSAHeapEntry[DEFAULT_SIZE];
    }

    
    /*********************
    Aternate Constructor
    ********************/
    public DSAHeap(int maxSize) {
        count = 0;
        heap = new DSAHeapEntry[maxSize];
    }

    //ACCESSORS
    /******************
    Is Empty
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 8 by Austin Bevacqua (20162896)
    ******************/
    public boolean isEmpty() {
        //If count = 0, the array is empty
        return (count == 0);
    }

    /******************
    Is Full
     Purpose: Returns whether the heap is at maximum capacity
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 8 by Austin Bevacqua (20162896)
    *******************/
    public boolean isFull() {
        //If the number of elements is equal to the max array size, the heap is full
        return (count == heap.length);
    }

    /*******************
    Peek Top
    Purpose: Returns the value of the highest priority value in the heap
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 8 by Austin Bevacqua (20162896)
    ******************/
    public Object peekTop() {
        Object top = null;
        if (!isEmpty()) { //Checks if the array is empty
            top = heap[0].getValue(); //The heap is organised so the highest priority item will always be at index 0
        }
        else { //If it IS empty, an exception is thrown as there is no element to return
            throw new NoSuchElementException("Heap is currently empty");
        }
        return top; 
    }

    //MUTATORS
    /******************
    Insert
     Purpose: Inserts a new element into the heap
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 8 by Austin Bevacqua (20162896)
    ******************/
    public void insert(double inPriority, Object inValue) {
       DSAHeapEntry newEntry;
       if(isFull()) {
            throw new IllegalArgumentException("Heap is full! No more elements can be added");
        }
        else {
            newEntry = new DSAHeapEntry(inPriority, inValue);
            heap[count] = newEntry;
            trickleUp(count);
            count++;
        }
    }
    
    /******************
    Delete
     Purpose: Removes the element with the highest priority from the heap
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 8 by Austin Bevacqua (20162896)
    ******************/
    public Object delete() {
        Object out = null;
        if(isEmpty()) {
            throw new IllegalArgumentException("Heap is empty");
        }
        else {
            out = peekTop();
            count--;
            heap[0] = heap[count];
            trickleDown(0, count, heap);
        }
        return out;
    }

    //PRIVATE FUNCTIONS
    /**********************
    Trickle Up
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 8 by Austin Bevacqua (20162896)
    ***********************/
    private void trickleUp(int currIdx) {
        int parentIdx;

        parentIdx = getParent(currIdx);
        if (currIdx > 0) {
            if (heap[currIdx].getPriority() > heap[parentIdx].getPriority()) {
                swap(heap, parentIdx, currIdx);
                trickleUp(parentIdx);
            }
        }
    }
               
    /**********************
    Trickle Down
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 8 by Austin Bevacqua (20162896)
    ***********************/
    private void trickleDown(int currIdx, int countC, DSAHeapEntry[] heapC) {
        int lChildIdx, rChildIdx, largeIdx;

        lChildIdx = getLeftChild(currIdx);
        rChildIdx = getRightChild(currIdx);
        if (lChildIdx < countC) {
            largeIdx = lChildIdx;
            if (rChildIdx < countC) {
                if(heapC[lChildIdx].getPriority() < heapC[rChildIdx].getPriority()) {
                    largeIdx = rChildIdx;
                }
            }
            if (heapC[largeIdx].getPriority() > heapC[currIdx].getPriority()) {
                swap(heapC, largeIdx, currIdx);
                trickleDown(largeIdx, countC, heapC);
            }
        }
    }

    /*********************
    Swap
     Purpose: Swaps two items in an array at indexes newIdx and currIdx
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 8 by Austin Bevacqua (20162896)
    *********************/
    private void swap(DSAHeapEntry[] heapC, int newIdx, int currIdx) {
        DSAHeapEntry temp;
        
        temp = heapC[newIdx];
        heapC[newIdx] = heapC[currIdx];
        heapC[currIdx] = temp;
    }
    
    /******************
    Get Parent
     Purpose: Returns the index of the "parent" item from the imported int
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 8 by Austin Bevacqua (20162896)
    *******************/
    private int getParent(int child) {
        return (child-1)/2;
    }

    /******************
    Get Left Child
     Purpose: Returns the index of the "left child" item from the imported int
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 8 by Austin Bevacqua (20162896)
    *******************/
    private int getLeftChild(int parent) {
        return (parent*2)+1;
    }

    /******************
    Get Right Child
     Purpose: Returns the index of the "right child" item from the imported int
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 8 by Austin Bevacqua (20162896)
    *******************/
    private int getRightChild(int parent) {
        return (parent*2)+2;
    }
}
            

    
    
            

    
    
            

    
    

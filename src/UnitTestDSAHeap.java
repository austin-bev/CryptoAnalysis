/****************
 * Class: Heap Test Harness
 * Purpose: Tests the functions of the Heap data structure and all associated objects
 * Author: Austin Bevacqua
 * Last Modified: 31/10/20
 ******************/
public class UnitTestDSAHeap {
    public static void main(String[] args) {
        int testsPassed = 0;
        DSAHeap theHeap = new DSAHeap();
        DSAHeap limitedHeap = new DSAHeap(2);

        //Test isEmpty
        if(theHeap.isEmpty()) {
            testsPassed++;
            System.out.println("PASSED isEmpty test 1");
        }
        else {
            System.out.println("FAILED isEmpty test 1");
        }

        theHeap.insert(10, "Ten");
        theHeap.insert(4, "Four");
        theHeap.insert(9, "Nine");
        theHeap.insert(1, "One");
        theHeap.insert(7, "Seven");
        theHeap.insert(5, "Five");
        theHeap.insert(3, "Three");

        //Test insert
        if(theHeap.peekTop().equals("Ten")) {
            testsPassed++;
            System.out.println("PASSED Insert In Order Test 1");
        }
        else {
            System.out.println("FAILED Insert In Order Test 1");
        }

        //Test insert #2
        theHeap.insert(20, "Twenty");
        if(theHeap.peekTop().equals("Twenty")) {
            testsPassed++;
            System.out.println("PASSED Insert In Order Test 2");
        }
        else {
            System.out.println("FAILED Insert In Order Test 2");
        }

        //Test isEmpty #2
        if(!theHeap.isEmpty()) {
            testsPassed++;
            System.out.println("PASSED isEmpty Test 2");
        }
        else {
            System.out.println("FAILED isEmpty Test 2");
        }

        //Test isFull #2
        if(!theHeap.isFull()) {
            testsPassed++;
            System.out.println("PASSED isFull Test 1");
        }
        else {
            System.out.println("FAILED isFull Test 1");
        }

        theHeap.delete();
        //Test delete
        if(theHeap.peekTop().equals("Ten")) {
            testsPassed++;
            System.out.println("PASSED Delete Test 1");
        }
        else {
            System.out.println("FAILED Delete Test 1");
        }

        //Test delete #2
        try {
            limitedHeap.delete();
            System.out.println("FAILED Delete Test 2");
        }
        catch(IllegalArgumentException e) {
            testsPassed++;
            System.out.println("PASSED Delete Test 2");
        }
        limitedHeap.insert(10, "Ten");
        limitedHeap.insert(20, "20ey");

        //Test isFull #2
        if(limitedHeap.isFull()) {
            testsPassed++;
            System.out.println("PASSED isFull Test 2");
        }
        else {
            System.out.println("FAILED isFull Test 2");
        }

        //Test Insert #3
        try {
            limitedHeap.insert(500, "RIP");
            System.out.println("FAILED Insert While Full Test");
        }
        catch(IllegalArgumentException e) {
            testsPassed++;
            System.out.println("PASSED Insert While Full Test");
        }

        //Overall score
        System.out.println("PASSED " + testsPassed + "/9 tests!");
    }
}

/****************
 * Class: Linked List Test Harness
 * Purpose: Tests the functions of the linked list data structure and all associated objects
 * Author: Austin Bevacqua
 * Last Modified: 31/10/20
 ******************/
import java.util.Iterator;

public class UnitTestDSALinkedList {
    public static void main(String[] args) {
        int testsPassed = 0;
        Iterator itr;
        String testStringOne = "HELLO";
        String testStringTwo = "WORLD";

        DSALinkedList theList;

        theList = new DSALinkedList();

        //Test isEmpty
        if(theList.isEmpty()){
            testsPassed++;
            System.out.println("PASSED isEmpty Test 1");
        }
        else {
            System.out.println("FAILED isEmpty Test 1");
        }

        //Test insert
        theList.insertLast(testStringOne);
        if ((theList.peekFirst().equals(testStringOne)) && (theList.peekLast().equals(testStringOne))) {
            testsPassed++;
            System.out.println("PASSED insert Test");
        }
        else {
            System.out.println("FAILED insert Test");
        }
        theList.insertFirst(testStringTwo);

        //Test remove
        if(theList.removeFirst().equals(testStringTwo)) {
            testsPassed++;
            System.out.println("PASSED remove Test");
        }
        else {
            System.out.println("FAILED remove Test");
        }
        theList.insertFirst(2);
        theList.insertFirst(3);
        theList.insertFirst(4);
        //Test getCount
        if(theList.getCount() == 4) {
            testsPassed++;
            System.out.println("PASSED count Test");
        }
        //Test isEmpty #2
        if (!theList.isEmpty()) {
            testsPassed++;
            System.out.println("PASSED isEmpty Test 2");
        }
        else {
            System.out.println("FAILED isEmpty Test 2");
        }

        //Test removeLast
        if (theList.removeLast().equals(testStringOne)) {
            testsPassed++;
            System.out.println("PASSED removeLast Test");
        }
        else {
            System.out.println("FAILED removeLast Test");
        }


        //Iterator test 1
        itr = theList.iterator();
        if(itr.hasNext()) {
            testsPassed++;
            System.out.println("PASSED Iterator Test 1");
        }
        else {
            System.out.println("FAILED Iterator Test 1");
        }

        //Iterator test 1
        itr = theList.iterator();
        try {
            if (((int)itr.next() == 4)) {
                testsPassed++;
                System.out.println("PASSED Iterator Test 2");
            } else {
                System.out.println("FAILED Iterator Test 2");
            }

        }
        catch(Exception e) {
            System.out.println("FAILED Iterator Test 2");
        }

        //Overall score
        System.out.println("PASSED " + testsPassed + "/8 Tests!");
    }
}

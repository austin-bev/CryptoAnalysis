/****************
 * Class: Hash Test Harness
 * Purpose: Tests the functions of the Linked Hash Table data structure and all associated objects
 * Author: Austin Bevacqua
 * Last Modified: 31/10/20
 ******************/
import java.util.NoSuchElementException;

public class UnitTestDSALinkedHashTable {
    public static void main(String[] args) {
        int testsPassed = 0;
        String testStringOne = "HELLO";
        String testKeyOne = "FOO";
        String testStringTwo = "WORLD";
        String testKeyTwo = "BAR";

        DSALinkedHashTable theList;
        theList = new DSALinkedHashTable();

        //Test isEmpty
        if(theList.isEmpty()){
            testsPassed++;
            System.out.println("PASSED isEmpty Test 1");
        }
        else {
            System.out.println("FAILED isEmpty Test 1");
        }

        //Test insert
        theList.insert(testKeyOne, testStringOne);
        if (theList.hasKey(testKeyOne)) {
            testsPassed++;
            System.out.println("PASSED insert Test");
        }
        else {
            System.out.println("FAILED insert Test");
        }

        theList.insert(testKeyTwo, testStringTwo);
        //Test remove
        if(theList.remove(testKeyTwo).equals(testStringTwo)) {
            if (!theList.hasKey(testKeyTwo)) {
                testsPassed++;
                System.out.println("PASSED remove Test 1");
            }
            else{
                System.out.println("FAILED remove Test 1");
            }
        }
        else {
            System.out.println("FAILED remove Test");
        }

        //Test Remove #2
        try {
            theList.remove("DOESNOTHAVE");
            System.out.println("FAILED remove Test 2");
        }
        catch(NoSuchElementException e) {
            testsPassed++;
            System.out.println("PASSED remove Test 2");
        }


        theList.insert("ONE", 1);
        theList.insert("TWO", 2);
        theList.insert("THREE", 3);
        //Test getCount
        if(theList.getCount() == 4) {
            testsPassed++;
            System.out.println("PASSED count Test");
        }
        else {
            System.out.println("FAILED count Test");
        }

        //Test isEmpty #2
        if (!theList.isEmpty()) {
            testsPassed++;
            System.out.println("PASSED isEmpty Test 2");
        }
        else {
            System.out.println("FAILED isEmpty Test 2");
        }

        //Test get
        if(theList.get("TWO").equals(2)) {
            testsPassed++;
            System.out.println("PASSED get Test 1");
        }
        else {
            System.out.println("FAILED get Test 1");
        }

        //Test get #2
        try {
            theList.get("DOESNOTHAVE");
            System.out.println("FAILED get Test 2");
        }
        catch(NoSuchElementException e) {
            testsPassed++;
            System.out.println("PASSED get Test 2");
        }

        //Overall score
        System.out.println("PASSED " + testsPassed + "/8 Tests!");
    }
}

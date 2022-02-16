/********************
Class: DSALinkedHashTable
Author: Austin Bevacqua
Date: 26/09/2020
Purpose: A data structure which combines the functionality of a linked list with a hash table.
         Entries are added to an array with a hash hey, but can be iterated over.
 Author: Austin Bevacqua
 CITATION: Originally submitted for DSA Practical 7 by Austin Bevacqua (20162896)
********************/
import java.util.*;
import java.io.*;


public class DSALinkedHashTable implements Iterable, Serializable
{
    public static final int DEFAULT_SIZE = 17;
    public static final int MAX_STEP = 5;
    public static final double MAXTHRESHOLD = 0.7;
    public static final double MINTHRESHOLD = 0.2;

    public static final int DELETED = 1; //Deleted status for DSAHashEntry
    
    /*************************
    The Public Iterator class
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 7 by Austin Bevacqua (20162896)
    *************************/
    public Iterator iterator()
    {
        return new DSALinkedHashIteraor(this);
    }

    /*******************
    Inner Iterator Class
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 7 by Austin Bevacqua (20162896)
    ********************/
    //"implements Iterator" = defines the actual functionality of the ITERATOR
    private class DSALinkedHashIteraor implements Iterator
    {
        private DSALinkedHashEntry iterNext;

        //CONSTRUCTOR        
        public DSALinkedHashIteraor(DSALinkedHashTable theTable)
        {
            iterNext = theTable.head;
        }

        /************
        Has Next
         Author: Austin Bevacqua
         CITATION: Originally submitted for DSA Practical 7 by Austin Bevacqua (20162896)
        ************/
        //Returns TRUE if a next element exists in the list; FALSE if end of list.
        public boolean hasNext() { return (iterNext != null); }

        /***********
        Next
         Author: Austin Bevacqua
         CITATION: Originally submitted for DSA Practical 7 by Austin Bevacqua (20162896)
        ***********/
        //Returns the next value in the list
        public Object next()
        {
            Object value;
            if (iterNext == null) //If we have reached the end of the list, return null
                value = null;
            else
            {
                value = iterNext.getValue();
                iterNext = iterNext.getNext();
            }
            return value;
        }
    }
                        

    /***********************
    Inner Hash Entry Class
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 7 by Austin Bevacqua (20162896)
    **********************/ 
    private class DSALinkedHashEntry implements Serializable
    {
        String key;
        Object value;
        int state;
        DSALinkedHashEntry next;
        DSALinkedHashEntry prev;
    
        //CONSTRUCTORS
        /******************
        Default Constructor
        ******************/
        public DSALinkedHashEntry() {
            key = null;
            value = null;
            state = 0;
            next = null;
            prev = null;
        }

        /*****************
        Alternate Constructor
        *****************/
        public DSALinkedHashEntry(String inKey, Object inValue) {
            key = inKey;
            value = inValue;
            state = 0;
            next = null;
            prev = null;
        }
        
        //ACCESSORS
        /******************
        Get Key
         Author: Austin Bevacqua
         CITATION: Originally submitted for DSA Practical 7 by Austin Bevacqua (20162896)
        ******************/
        public String getKey() {
            return key;
        }   

        /******************
        Get Value
         Author: Austin Bevacqua
         CITATION: Originally submitted for DSA Practical 7 by Austin Bevacqua (20162896)
        *****************/
        public Object getValue() {
            return value;
        }

        /****************
        Get State / Is Deleted
         Author: Austin Bevacqua
         CITATION: Originally submitted for DSA Practical 7 by Austin Bevacqua (20162896)
        ****************/
        public boolean isDeleted() {
            return state != 0;
        }

        /************** 
        Get Next
         Author: Austin Bevacqua
        ***************/
        public DSALinkedHashEntry getNext() {
            return next;
        }

        /************** 
        Get Prev
         Author: Austin Bevacqua
        ***************/
        public DSALinkedHashEntry getPrev() {
            return prev;
        }
        
        //MUTATORS
        /***************
        Set Deleted
         Author: Austin Bevacqua
         CITATION: Originally submitted for DSA Practical 7 by Austin Bevacqua (20162896)
        ***************/
        public void setDeleted() {
            state = DELETED;
        }
        
        /************ *
        Set Next
         Author: Austin Bevacqua
        ***************/
        public void setNext(DSALinkedHashEntry inEntry) {
            next = inEntry;
        }

        /************ *
        Set Prev
         Author: Austin Bevacqua
        ***************/
        public void setPrev(DSALinkedHashEntry inEntry) {
            prev = inEntry;
        }

    }
    
    //Private Classfields
    DSALinkedHashEntry[] hashArray;
    int count;
    int minLength; //Does not change. The minimum size the array can be resized down to.
    DSALinkedHashEntry head;
    DSALinkedHashEntry tail;
        
    //CONSTRUCTORS
    /******************
    Default Constructor
    *******************/
    public DSALinkedHashTable() {
        hashArray = new DSALinkedHashEntry[DEFAULT_SIZE];
        count = 0;
        minLength = DEFAULT_SIZE;
        head = null;
        tail = null;
    }

    /*******************
    Alternate Constructor
    ******************/
    public DSALinkedHashTable(int tableSize) {
        int primeSize;    
        primeSize = nextPrime(tableSize);

        hashArray = new DSALinkedHashEntry[primeSize];
        count = 0;  
        minLength = primeSize;
        head = null;
        tail = null;
    }

    //ACCESSORS
    /************
    Is Empty
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 7 by Austin Bevacqua (20162896)
    ************/
    public boolean isEmpty() {
        return (count == 0); //If count == 0, the list is empty.
    }

    /************
    Get Count
     Author: Austin Bevacqua
    ************/
    public int getCount() {
        return count;
    }

    /************
    Has Key
     Purpose: Returns true or false depending if the current object contains the imported key
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 7 by Austin Bevacqua (20162896)
    ************/
    public boolean hasKey(String inKey) {
        boolean has;
        //Function find() will throw a NoSuchElementException if the key does not exist in the array.
        try {
            find(inKey); 
            has = true; //If NO exception was raised, the the element must exist in array.
        }
        catch(NoSuchElementException e) {
            has = false; //If an exception is raised, the element does not exist. We set boolean to false.
        }
        return has;
    }

    /***********
    Get
     Purpose: Given an imported key, will return the value stored in the hash table that is stored with the key
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 7 by Austin Bevacqua (20162896)
    ***********/
    public Object get(String inKey) {
        int key = find(inKey);
        Object value = null;

        if (hashArray[key] != null) {
            value = hashArray[key].getValue(); //The value stored with the key is returned.
        }
        else
        {
            throw new NoSuchElementException("Key does not exist in the table");
        }
        return value;
    }
    
    //MUTATORS
    /**************
    Insert
     Purpose: Inserts a new value into the hash table
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 7 by Austin Bevacqua (20162896)
    ***************/
    public void insert(String inKey, Object inValue) {
        if(hasKey(inKey)) { //We need to check if the key already exists in the array; as kest must be unique.
            throw new IllegalArgumentException("Key currently exists. Please enter a unique key");
        }
        else {
            //If we add an extra element to the array, will it reach the threshold to resize. If so, then resize the array.
            //It is important we do this BEFORE the element is inserted, or it will have to be inserted and then re-inserted in the resize function.      
            if((count+1) > (hashArray.length * MAXTHRESHOLD)) {
                resize(nextPrime(hashArray.length * 2));
            }

            put(inKey, inValue);
            setAdjacencies(find(inKey));
        }
    }

    /****************
    Put
     Purpose: Calculates the location the new value will be stored in the hash table
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 7 by Austin Bevacqua (20162896)
    ***************/
    private void put(String inKey, Object inValue) {
        DSALinkedHashEntry newEntry;
        boolean foundDel;
        int key, stepSize;

        foundDel = false;
        newEntry = new DSALinkedHashEntry(inKey, inValue);
        key = hash(inKey);
        stepSize = stepHash(inKey);

        //The loop continues until we find a free spot (null) OR a node labeled as deleted
        //We cannot put the conditions (hashArray[key].isDeleted) in the while loop condition, as a node that is null will not contain a .isDeleted function.
        while((hashArray[key] != null) && (!foundDel)) {
            if (hashArray[key].isDeleted()) {
                foundDel = true;
            }
            else { //If we have a collision, we re-hash the key to find the next location. 
                key = (key + stepSize) % hashArray.length;
            }   
        }

        count++;
        hashArray[key] = newEntry;
    }
    
    /****************
    Remove
     Purpose: Removes a value from the hash table
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 7 by Austin Bevacqua (20162896)
    ****************/
    public Object remove(String inKey) {
        Object value;
        int key;
        key = find(inKey);
        
        hashArray[key].setDeleted();
        count--;
        value = hashArray[key].getValue();
        removeAdjacencies(hashArray[key]);

        //If out new COUNT is lower than the "low threshold" for resizing an array; then we resize the array to a smaller prime number
        //Note: If the array is at its constructed size (minLength), it will not be re-sized.
        if(((count) < (hashArray.length * MINTHRESHOLD)) && (hashArray.length > minLength)) {
            resize(nextPrime(hashArray.length / 2));
        }
        return value;     
    }

    //PRIVATE FUNCTIONS
    /****************
    Find
     Purpose: Finds the location of a key within a hash table
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 7 by Austin Bevacqua (20162896)
    ***************/
    private int find(String inKey) {
        int key, stepSize;
        key = hash(inKey);
        stepSize = stepHash(inKey);
        
        //Loop continues until we find the key inside of the hash table.
        //Re-rashes the key if the key does not equal the inKey, OR the current element is labeled as deleted
        //Loop ends if we find a null element, as this shows the key is not in the hash table. 
        while((hashArray[key] != null) && ((!inKey.equals(hashArray[key].getKey())) || (hashArray[key].isDeleted()))) {
            key = (key + stepSize) % hashArray.length;
        }
        //If we find a blank element, the key is not in the table
        if (hashArray[key] == null) {
            throw new NoSuchElementException("Key is not in the hash table");
        }
        return key;
    }

    /*****************
    Hash
     Purpose: Calculates a hash key from a given string
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 7 by Austin Bevacqua (20162896)
    *****************/
    private int hash(String key) {
        //Uses Dan Bernstein's djb2 algorithm for hashing.
        int hashIdx = 5381;
    
        for (int ii = 0; ii < key.length(); ii++) {
            hashIdx = ((hashIdx << 5) + hashIdx) + key.charAt(ii);
        }
        return Math.abs(hashIdx % hashArray.length); //The absolute value of the hashIdx is returned, as we cannot index elements at negative points in an array.
    }

    /*****************
    Step Hash
     Purpose: If a collision exists, a new key is found through double hashing
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 7 by Austin Bevacqua (20162896)
    *****************/
    private int stepHash(String inKey) {
        //Uses double hashing to re-hash the key. Finds a new index based on the KEY.
        int hashStep, idx;
        idx = 0;
        for (int ii = 0; ii < inKey.length(); ii++) {
            idx = (33 * idx) + inKey.charAt(ii);
        }
        hashStep = MAX_STEP - Math.abs(idx % MAX_STEP);
        return hashStep;
    }
    
    /****************
    Next Prime
     Purpose: Calculates the next prime number from an integer
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 7 by Austin Bevacqua (20162896)
     CITATION: Code derived off DSA Lecture notes week 7 suppled by Curtin University.
    ****************/
    private int nextPrime(int inNum) {
        double rootVal;
        int primeVal, ii;
        boolean isPrime;

        //If the imported value is even, we decrease it by one. Even numbers cannot be prime numbers.
        if (inNum % 2 == 0) {
            primeVal = inNum-1;
        }
        else {
            primeVal = inNum;
        }

        isPrime = false;
        do {
            primeVal += 2;
            ii = 3;
            isPrime = true;
            rootVal = Math.sqrt(primeVal);
            do {
                if(primeVal % ii == 0) {
                    isPrime = false;
                }
                else {
                    ii += 2;
                }
            }while((ii <= rootVal) && (isPrime));
        }while(!isPrime);

        return primeVal;
    }
    
    /********************
    Resize
     Purpose: Resizes the hash table to be a larger size once a certain percentage of the array is full
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 7 by Austin Bevacqua (20162896)
    *****************/
    private void resize(int newLength) {
        DSALinkedHashEntry[] oldHashArray;
        int oldLength;
        
        oldLength = hashArray.length;
        oldHashArray = hashArray;
        hashArray = new DSALinkedHashEntry[newLength];
        reset();
        for(int i = 0; i < oldLength; i++) {
            if(oldHashArray[i] != null) { 
            //Iterates through every element in the array. If a non-blank element that is NOT labeled as deleted is found, we enter it into the new array.
                if (!oldHashArray[i].isDeleted()) {
                    put(oldHashArray[i].getKey(), oldHashArray[i].getValue());
                    setAdjacencies(find(oldHashArray[i].getKey()));
                }
            }
        }       

    }

    /********************
    Reset
     Purpose: Resets the properties of the hash table before resizing.
     Author: Austin Bevacqua
    *****************/
    private void reset() {
        head = null;
        tail = null;
        count = 0;
    }

    /********************
    Set Adjacencies
     Purpose: Sets the "next" and "previous" parameters of each entry in the hash table when an entry is inserted
     Author: Austin Bevacqua
    *****************/
    private void setAdjacencies(int key) {
        DSALinkedHashEntry inEntry = hashArray[key];
        if(head == null) {
            head = inEntry;
        }
        else {
            tail.setNext(inEntry);
            inEntry.setPrev(tail);
        }
        tail = inEntry;
    }

    /********************
    Remove Adjacencies
     Purpose: Sets the "next" and "previous" parameters of each entry in the hash table when an entry is deleted
     Author: Austin Bevacqua
    *****************/
    private void removeAdjacencies(DSALinkedHashEntry inEntry) {
        if (inEntry.getNext() == null) {
            tail = inEntry.getPrev();
        }
        else if (inEntry.getNext() != null) {
            inEntry.getNext().setPrev(inEntry.getPrev());
        }
        if (inEntry.getPrev() == null) {
            head = inEntry.getNext();
        }
        else if (inEntry.getPrev() != null) {
            inEntry.getPrev().setNext(inEntry.getNext());
        }
    }

}
    
    

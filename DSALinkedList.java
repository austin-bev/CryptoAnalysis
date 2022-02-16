/***********************
Class: DSALinkedList
Date: 27/08/2020 
Last Modified: 31/10/2020
Purpose: A Double-ended, doubly-linked list implementation with an Iterator
 Author: Austin Bevacqua
 CITATION: Originally submitted for DSA Practical 4 by Austin Bevacqua (20162896)
************************/
import java.util.*;
import java.io.*;

//"implements Iterable" = the class can be iterated over by using an ITERATOR
public class DSALinkedList implements Iterable, Serializable
{
    /*************************
    The Public Iterator class
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 4 by Austin Bevacqua (20162896)
    *************************/
    public Iterator iterator()
    {
        return new DSALinkedListIterator(this);
    }

    /*******************
    Inner Iterator Class
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 4 by Austin Bevacqua (20162896)
    ********************/
    //"implements Iterator" = defines the actual functionality of the ITERATOR
    private class DSALinkedListIterator implements Iterator
    {
        private DSAListNode iterNext;

        //CONSTRUCTOR        
        public DSALinkedListIterator(DSALinkedList theList)
        {
            iterNext = theList.head;
        }

        /************
        Has Next
        ************/
        //Returns TRUE if a next element exists in the list; FALSE if end of list.
        public boolean hasNext() { return (iterNext != null); }

        /***********
        Next
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
                        

    /**************
    DSAListNode Inner Class
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 4 by Austin Bevacqua (20162896)
    **************/
    private class DSAListNode implements Serializable
    {
        public Object m_value;
        public DSAListNode m_next;
        public DSAListNode m_last;
        
        //CONSTRUCTORS 
        public DSAListNode(Object inValue)
        {
            m_value = inValue;
            m_next = null;
            m_last = null;
        }

        //ACCESSORS
        /***********
        Get Value
         Author: Austin Bevacqua
         CITATION: Originally submitted for DSA Practical 4 by Austin Bevacqua (20162896)
        ***********/
        public Object getValue()
        {
            return m_value;
        }
        
        /***********
        Get Next
         Author: Austin Bevacqua
         CITATION: Originally submitted for DSA Practical 4 by Austin Bevacqua (20162896)
        ***********/
        public DSAListNode getNext()
        {
            return m_next;
        }
        /***********
        Get Last
         Author: Austin Bevacqua
         CITATION: Originally submitted for DSA Practical 4 by Austin Bevacqua (20162896)
        ***********/
        public DSAListNode getPrev()
        {
            return m_last;
        }
        //MUTATORS
        /***********
        Set Value
         Author: Austin Bevacqua
         CITATION: Originally submitted for DSA Practical 4 by Austin Bevacqua (20162896)
        ***********/
        public void setValue(Object inValue)
        {
            m_value = inValue;
        }
        /***********
        Set Next
         Author: Austin Bevacqua
         CITATION: Originally submitted for DSA Practical 4 by Austin Bevacqua (20162896)
        ***********/
        public void setNext(DSAListNode newNext)
        {
            m_next = newNext;
        }
        /***********
        Set Last
         Author: Austin Bevacqua
         CITATION: Originally submitted for DSA Practical 4 by Austin Bevacqua (20162896)
        ***********/
        public void setPrev(DSAListNode newLast)    
        {
            m_last = newLast;
        }
    }
      
        
    public DSAListNode head;
    public DSAListNode tail;
    public int count;

    //CONSTRUCTORS

    /****************
     * Default Constructor
     ******************/
    public DSALinkedList()
    {
        head = null;
        tail = null;
        count = 0;
    }

    //ACCESSORS
    /***********
    Is Empty
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 4 by Austin Bevacqua (20162896)
    ***********/
    public boolean isEmpty()
    {
        return (count == 0);
    }
    /***********
     Get Count
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 4 by Austin Bevacqua (20162896)
     ***********/
    public int getCount()
    {
        return count;
    }

    /***********
    Peek First
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 4 by Austin Bevacqua (20162896)
    ***********/
    public Object peekFirst()
    {
        Object first = null;
        if(!isEmpty()) //Checks if the linked list is empty. If it IS empty; then there will be no current first value and an error is returned
        {
            first = head.getValue();
        }
        else
        {
            throw new IllegalArgumentException("Linked list is currently empty");
        }
        return first;
    }
    
    /**************
    Peek Last
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 4 by Austin Bevacqua (20162896)
    **************/
    public Object peekLast()
    {
        Object last = null;
        if(!isEmpty())
        {
            last = tail.getValue();
        }
        else
        {
            throw new IllegalArgumentException("Linked list is currently empty");
        }
        return last;
    }
                
    //MUTATORS
    /*************
    Remove First
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 4 by Austin Bevacqua (20162896)
    *************/
    public Object removeFirst()
    {
        Object removed = null;
        if(!isEmpty())
        {
            removed = head.getValue(); //The first node's value is returned
            head = head.getNext(); //The head (first value) is set to the second node in the list. If only one element, set to null.
            if(isEmpty()) //If the list is NOW empty (e.g. only had one node to begin with), we set the tail to null.
            {
                tail = null;
            }
            else //We can only set the value of the previous node to null if a node exists in the list; e.g. the list is NOT empty
            {
                head.setPrev(null); //Sets the new head's previous value to NULL.
            }
            count--;
        }
        else
        {
            throw new IllegalArgumentException("Linked list is currently empty");
        }
        return removed;    
    }
    
    /*************
    Remove Last
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 4 by Austin Bevacqua (20162896)
    **************/
    public Object removeLast()
    {
        Object removed = null;
        DSAListNode prevNd;

        if(head.getNext() == null) //If there is only one node in the list, the operations are simple; we just set the list as empty
        {
            removed = head.getValue(); //The current head (e.g. only) value is returned
            head = null; //Both the head and tail values are set to null as there are no more nodes in the list
            tail = null;
            count--;
        }
        else if(!isEmpty()) //The list has to have a node inside of it in order for a node to be removed
        {
            removed = tail.getValue();
            prevNd = tail.getPrev(); //We store the node which is currently in the second last place. This will become out new tail
            tail = prevNd; //Tail is set to point to prevNd.
            tail.setNext(null); //The "next" value of the new tail is set to null, as there is no node after the final node
            count--;
        }
        else
        {
            throw new IllegalArgumentException("Linked list is currently empty");
        }
        return removed;
    }

    /***************
    Insert First
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 4 by Austin Bevacqua (20162896)
    ***************/
    public void insertFirst(Object newValue)
    {
        DSAListNode newNd = new DSAListNode(newValue);
        if (isEmpty()) //If the list is empty, the new node is set as the tail and end of the program as it is the first and last node.
        {
            head = newNd;
            tail = newNd;
        }
        else //Elsewise, just the head is changed, as the final node has not been altered.
        {
            head.setPrev(newNd); //The current head node is set to have its prevoius pointer set to the newNd.
            newNd.setNext(head); //Sets 'next' value in newNd to be the old first node. This doubly links the nodes
            head = newNd; //Sets the new node as the 'head' value
        }
        count++;
    }
    
    /**************
    Insert Last
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 4 by Austin Bevacqua (20162896)
    **************/
    public void insertLast(Object newValue)
    {
        DSAListNode newNd = new DSAListNode(newValue);

        if (isEmpty()) //If the list is empty, the new node is set as the tail and end of the program as it is the first and last node.
        {
            head = newNd;
            tail = newNd;
        }
        else
        {
            tail.setNext(newNd); //We set the old last node's next value to be the new node. Making the new last node the second-last node
            newNd.setPrev(tail); //Doubly links the new and old tails by setting the new tail's prevoius value to the old tail.
            tail = newNd; //Sets the tail to the new last node
            //newNd's 'next' is set to null by default, so we do not need to change this.
        }
        count++;
    }
} 

/***********************
  Graph Edge Class
 Purpose: An object representation of a directed graph edge;
        Edges can be labeled (weighted), and/or contain a value.
 Last Modified: 31/10/20
 Author: Austin Bevacqua
 CITATION: Originally submitted for DSA Practical 6 by Austin Bevacqua (20162896)
 ***********************/
import java.io.*;

public class DSAGraphEdge implements Serializable
{
    //Stores a singular DIRECTED trade
    private DSAGraphVertex from; //The trade origin
    private DSAGraphVertex to; //The trade destination
    private DSAGraphEdge oppositeEdge; //If an edge is bi-directional, stores the edge in the opposite direction
    private String label;
    private Object value;

    //CONSTRUCTORS
    /****************
     Alternate Consructor - Without value
     ***************/
    public DSAGraphEdge(DSAGraphVertex fromV, DSAGraphVertex toV, String inLabel) {
        from = fromV;
        to = toV;
        oppositeEdge = null;
        label = inLabel;
        value = null;
    }

    /****************
     Alternate Consructor - Without value OR label
     ***************/
    public DSAGraphEdge(DSAGraphVertex fromV, DSAGraphVertex toV) {
        from = fromV;
        to = toV;
        oppositeEdge = null;
        label = null;
        value = null;
    }


    //ACCESSORDS
    /****************
     Get Label
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 6 by Austin Bevacqua (20162896)
     **************/
    public String getLabel() {
        return label;
    }

    /****************
     Get Value
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 6 by Austin Bevacqua (20162896)
     **************/
    public Object getValue() {
        return value;
    }

    /****************
     Get From
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 6 by Austin Bevacqua (20162896)
     **************/
    public DSAGraphVertex getFrom() {
        return from;
    }

    /****************
     Get To
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 6 by Austin Bevacqua (20162896)
     **************/
    public DSAGraphVertex getTo() {
        return to;
    }

    /*****************
     Get Opposite Edge
     Author: Austin Bevacqua
     ****************/
    public DSAGraphEdge getOpposite() {
        return oppositeEdge;
    }

    /***************
     To String
     ***************/
    public String toString() {
        return label;
    }

    //MUTATORS
    /****************
     Set value
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 6 by Austin Bevacqua (20162896)
     ****************/
    public void setValue(Object inValue) {
        value = inValue;
    }

    /****************
     Set Opposite Edge
     Author: Austin Bevacqua
     ****************/
    public void setOpposite(DSAGraphEdge inEdge) {
        oppositeEdge = inEdge;
    }
}



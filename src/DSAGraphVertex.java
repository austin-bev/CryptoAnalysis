/***********************
  Graph Vertex Class
 Purpose: An object representation of graph vertex. Vertexes can contain a label or value
 Last Modified: 31/10/20
 Author: Austin Bevacqua
 CITATION: Originally submitted for DSA Practical 6 by Austin Bevacqua (20162896)
 ***********************/
import java.io.Serializable;
public class DSAGraphVertex implements Serializable{
    public String label;
    public Object value;
    public boolean visited; //Used for graph traversal. False by default
    public boolean ignored; //Uses for graph traversal. False by default

    /******************
     Alternate Constructor
     *****************/
    public DSAGraphVertex(String inLabel) {
        label = inLabel;
        value = null;
        visited = false;
        ignored = false;
    }

    /*********************************
     Alternate Constructor - with value
     ***********************************/
    public DSAGraphVertex(String inLabel, Object inValue) {
        label = inLabel;
        value = inValue;
        visited = false;
        ignored = false;
    }

    //ACCESSORS
    /****************
     Get Label
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 6 by Austin Bevacqua (20162896)
     ***************/
    public String getLabel() {
        return label;
    }

    /****************
     Get Value
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 6 by Austin Bevacqua (20162896)
     ***************/
    public Object getValue() {
        return value;
    }


    /****************
     Get Visited
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 6 by Austin Bevacqua (20162896)
     ***************/
    public boolean getVisited() {
        return visited;
    }

    /****************
     Get Ignored
     Author: Austin Bevacqua
     ***************/
    public boolean getIgnored() {
        return ignored;
    }

    /****************
     To String
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 6 by Austin Bevacqua (20162896)
     ***************/
    public String toString() {
        return label;
    }

    /****************
     Equals
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 6 by Austin Bevacqua (20162896)
     *****************/
    public boolean equals(DSAGraphVertex inVertex) {
        boolean same;

        same = false;
        if (label.equals(inVertex.getLabel())){
            same = true;
        }
        return same;
    }

    //MUTATORS
    /***************
     * Set Value
     *  Author: Austin Bevacqua
     **************/
    public void setValue(Object inValue) {
        value = inValue;
    }

    /****************
     Set Visited
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 6 by Austin Bevacqua (20162896)
     ***************/
    public void setVisited () {
        visited = true;
    }

    /****************
     Clear Visited
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 6 by Austin Bevacqua (20162896)
     ***************/
    public void clearVisited () {
        visited = false;
    }

    /**************
     Set Ignored
     Author: Austin Bevacqua
     **************/
    public void setIgnored() {
        ignored = true;
    }

    /****************
     Clear Ignored
     Author: Austin Bevacqua
     ***************/
    public void clearIgnored() {
        ignored = false;
    }

}

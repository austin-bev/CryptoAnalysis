/*********************
Author: Austin Bevacqua
Date: 19/09/2020
Last Mod: 13/10/2020
Purpose: An implementation of a directed, weighted graph using the Linked Hash Table data structure.
 CITATION: Originally submitted for DSA Practical 6 by Austin Bevacqua (20162896)
********************/
import java.util.*;
import java.io.*;

public class DSAGraph implements Serializable
{
    //Private Classfields
    private DSALinkedHashTable vertices;
    private DSALinkedHashTable edges;
    private Metadata metadata;

    //CONSTRUCTORS
    /****************
    Default Constructor
    ****************/
    public DSAGraph() {
        vertices = new DSALinkedHashTable();
        edges = new DSALinkedHashTable();
        metadata = new Metadata();
    }
    
    //ACCESSORS
    /*******************
    Is Empty
    Purpose: Returns TRUE or FALSE depending if the graph is empty or not
     Author: Austin Bevacqua
     // CITATION: Originally submitted for DSA Practical 6 by Austin Bevacqua (20162896)
    *******************/
    public boolean isEmpty() {
        return vertices.isEmpty();
    }

    /****************
     Get Vertices
     Author: Austin Bevacqua
     ****************/
    public DSALinkedHashTable getVerticies() {
        return vertices;
    }

    /****************
     Get Edges
     Author: Austin Bevacqua
     ****************/
    public DSALinkedHashTable getEdges() {
        return edges;
    }

    /****************
      Get Metadata
     Author: Austin Bevacqua
     ****************/
    public Metadata getMetadata() {
        return metadata;
    }

    /*****************
    Has Vertex 
    Purpose: Check whether a vertex with the label inLabel exists
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 6 by Austin Bevacqua (20162896)
    ****************/
    public boolean hasVertex(String inLabel) {
        boolean has;
        has = false;

        try {
            vertices.get(inLabel);
            has = true;
        }
        catch(NoSuchElementException e) {}
        return has;
    }

    /******************
    Get Vertex Count
    Purpose: Gets the number of vertexes in a graph
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 6 by Austin Bevacqua (20162896)
    *****************/
    public int getVertexCount() {
    //Get the number of elements in vertices;
        return vertices.getCount();
    }

    /******************
    Get Edge Count
    Purpose: Returns the number of edges in a Graph
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 6 by Austin Bevacqua (20162896)
    *****************/
    public int getEdgeCount() {
        return edges.getCount(); 
    }

    /******************
    Get Vertex
    Purpose: Returns a specific DSAGraphVertex whose label matches to the imported label
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 6 by Austin Bevacqua (20162896)
    *****************/
    public DSAGraphVertex getVertex(String inLabel) {
        DSAGraphVertex foundVertex = null;
        foundVertex = (DSAGraphVertex) vertices.get(inLabel);
        return foundVertex; 
    }

    /****************
     Ignore a Vertex
     Purpose: Sets a vertex within the graph as "ignored", excluding it from pathfinding algorithms
     Author: Austin Bevacqua
     ***************/
    public void ignoreVertex(String inLabel) {
        DSAGraphVertex node;
        node = getVertex(inLabel);
        node.setIgnored();
    }

    /***************
     Stop Ignoring a Vertex
     Purpose: Sets a vertex within the graph as "NOT ignored", including it in pathfinding algorithms
     Author: Austin Bevacqua
     **************/
    public void acknowledgeVertex(String inLabel) {
        DSAGraphVertex node;
        node = getVertex(inLabel);
        node.clearIgnored();
    }

    /****************
    Get Edge
     Purpose: Returns an edge object from the graph whose label matches the imported string.
     Author: Austin Bevacqua
    *****************/
    public DSAGraphEdge getEdge(String inLabel) {
        DSAGraphEdge foundEdge = null;
        foundEdge = (DSAGraphEdge) edges.get(inLabel);
        return foundEdge; 
    }

    /*****************
    Get Adjacent
    Purpose: Returns every vertex adjacent to an imported vertex
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 6 by Austin Bevacqua (20162896)
    *****************/
    public DSALinkedList getAdjacent(String inLabel) {
        DSALinkedList adjacent;
        DSAGraphVertex currNode, otherNode;
        DSAGraphEdge currEdge;
        adjacent = new DSALinkedList();

        if (hasVertex(inLabel)) {
            currNode = getVertex(inLabel);
            //Every edge of the graph is iterated through
            for (Object o : edges) {
                currEdge = (DSAGraphEdge) o; //We typecast o to a GrapgEdge
                // If the FROM vertex's label matches tbe imported label, we add the OTHER vertex (to) to the Linked List.
                if (currNode.getLabel().equals((currEdge.getFrom()).getLabel())) {
                    otherNode = currEdge.getTo();
                    adjacent.insertLast(otherNode);
                }
            }
        }
        else {
            throw new NoSuchElementException(inLabel + " does not exist in the graph");
        }
        return adjacent;
    }

    /***************
     Is Adjacent
     Purpose: Returns true or false depending if two vertexes are connected by an edge
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 6 by Austin Bevacqua (20162896)
     **************/
    public boolean isAdjacent(String nodeOne, String nodeTwo) {
        boolean isadj;
        Iterator itr;
        DSAGraphVertex vertexOne, vertexTwo;
        DSAGraphEdge edge = null;

        isadj = false;
        try {
            //Will cause a NoSuchElementException if one of the nodes do not exist
            vertexOne = setVertex(nodeOne);
            vertexTwo = setVertex(nodeTwo);

            isadj = false;
            itr = edges.iterator();
            //Iterates through every edge of the graph; stored in linked hash table "edges"
            while(itr.hasNext()) {
                edge = (DSAGraphEdge) (itr.next()); //We typecast the object to a DSAGraphEdge
                //The values of the edge are compared against the values of the imported parameters
                if ((vertexOne.equals(edge.getTo())) && (vertexTwo.equals(edge.getFrom()))) {
                    //If a match is found, we return TRUE.
                    isadj = true;
                }
            }
        }
        //Exception is only thrown if nodes do not exist. Therefore, the returned boolean will be false.
        catch (NoSuchElementException e) { }
        return isadj;
    }
    /***********************
    Get Paths
    Purpose: A wrapper method for the recursive getPaths algorithm, which returns a linked list of
            every route between two vertexes in a graph within a range.
     Author: Austin Bevacqua
    ************************/
    public DSALinkedList getPaths(String to, String from, int maxSize) {
        DSALinkedList currPath = new DSALinkedList();
        DSALinkedList allPaths = new DSALinkedList();
        DSAGraphVertex vertexOne, vertexTwo;

        //Checks if the graph contains the two imported vertexes
        if (hasVertex(to) && hasVertex(from)) {
            vertexOne = getVertex(to);
            vertexTwo = getVertex(from);
            //If the graph contains additional trade data, we are able to find all possible paths AND
            //find the cumulative price change between the path.
            if (getMetadata().hasTrades())
                pathsRecPrice(vertexOne, vertexTwo, currPath, maxSize, allPaths, 0.0);
            //If the graph does NOT contain any additional trade information, we just find all possible paths.
            else
                pathsRec(vertexOne, vertexTwo, currPath, maxSize, allPaths);

        }
        return allPaths;
    }

    /***********************
    Display all paths
    Purpose: A recursive algorithm which retrieves every possible path between two vertexes in a graph.
             Algorithm based off breadth-first search code submitted for DSA Practical 6.
     Author: Austin Bevacqua
    ************************/
    private void pathsRec(DSAGraphVertex to, DSAGraphVertex from, DSALinkedList currPath, int index, DSALinkedList allPaths) {
        Iterator itr;
        DSAGraphVertex currVertex;
        String finishedPath = "";

        //The CURRENT NODE is set as visited, and added to the "path" stack
        to.setVisited();
        currPath.insertLast(to);

        /*If to EQUALS from, we have reached the destination node. Recursion ends and we add this
        finished path to the "allpaths" stack */
        if (to.equals(from)) {
            itr = currPath.iterator();
            while(itr.hasNext()) {
                //Every node in the current path is transferred into a singular string representation of the path.
                currVertex = (DSAGraphVertex) itr.next();
                finishedPath += (currVertex);
                if (itr.hasNext()) {
                    finishedPath+=" -> ";
                }
            }
            //Once the string is generated, it is added to the all paths linked list.
            allPaths.insertLast(finishedPath);
        }

        /*The "index" tracks the current level of recursion.
         If we have reached the user-defined max level of recursion, the current path finishes */
        else if (index > 0) {
            itr = getAdjacent(to.getLabel()).iterator();
            while (itr.hasNext()) {
                currVertex = (DSAGraphVertex) itr.next();
                //If the node is marked as visited or ignored, we don't perform any processing on it.
                if((!currVertex.getVisited()) && (!currVertex.getIgnored())) {
                    pathsRec(currVertex, from, currPath, index-1, allPaths);
                }
            }
        }
        /*As we finish processing a node, we remove it off the current path and set it as "not visited" so it
        can be visited on another path */
        to.clearVisited();
        currPath.removeLast();
    }

    /***********************
     Display all paths Price
     Purpose: A recursive algorithm which retrieves every possible path between two vertexes in a graph.
                Algorithm based off breath-first search code submitted for DSA Practical 6.
                Additionally, finds the cumulative price change across trading paths.
     Author: Austin Bevacqua
     ************************/
    private void pathsRecPrice(DSAGraphVertex to, DSAGraphVertex from, DSALinkedList currPath, int index, DSALinkedList allPaths, double currPrice) {
        //Works IDENTICALLY to pathRec, expect calculates the overall price of a trading route.
        Iterator itr;
        DSAGraphVertex currVertex;
        String finishedPath = "";

        to.setVisited();
        currPath.insertLast(to);

        if (to.equals(from)) {
            itr = currPath.iterator();
            while(itr.hasNext()) {
                currVertex = (DSAGraphVertex) itr.next();
                finishedPath += (currVertex);
                if (itr.hasNext()) {
                    finishedPath+=" -> ";
                }
            }
            //We add the finished path AND the finished price to the linked list.
            allPaths.insertLast(new RouteData(finishedPath, currPrice));
        }
        else if (index > 0) {
            itr = getAdjacent(to.getLabel()).iterator();
            while (itr.hasNext()) {
                currVertex = (DSAGraphVertex) itr.next();
                if((!currVertex.getVisited()) && (!currVertex.getIgnored())) {
                    //We check if the trading route has a value stored. if it does, we can add its price to the overall price.
                    if (getEdge(currVertex.getLabel() + to.getLabel()).getValue() != null)
                        //We add the price of the current trade onto the cumulative price of the trading route.
                        currPrice += ((TradeInfo) getEdge((currVertex.getLabel() + to.getLabel())).getValue()).getChangePercent();
                    pathsRecPrice(currVertex, from, currPath, index-1, allPaths, currPrice);
                }
            }
        }
        to.clearVisited();
        currPath.removeLast();
    }

    /*****************
    Add Vertex
     Purpose: Adds a vertex to the graph with the imported String as the label
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 6 by Austin Bevacqua (20162896)
    *****************/
    public void addVertex(String inVertex) {
        DSAGraphVertex newVertex = new DSAGraphVertex(inVertex);
        vertices.insert(inVertex, newVertex);
    }

    /*****************
    Add Vertex - With value
     Purpose: Adds a vertex to the graph with the imported String as the label and imported Object as value
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 6 by Austin Bevacqua (20162896)
    *****************/
    public void addVertex(String inVertex, Object inValue) {
        DSAGraphVertex newVertex = new DSAGraphVertex(inVertex, inValue);
        vertices.insert(inVertex, newVertex);
    }

    /*****************
     * Function: Set Vertex Value
     * Purpose: Sets the value classfield of a DSAGraphVertex in the graph
     * Author: Austin Bevacqua
     *****************/
    public void setVertexValue(Object inValue, String vertexName) {
        DSAGraphVertex currVertex;

        if (hasVertex(vertexName)) {
            currVertex = getVertex(vertexName);
            currVertex.setValue(inValue);
        }
        else {
            throw new NoSuchElementException("Vertex does not exist in the graph");
        }
    }

    /***************
    Add Edge - With label
     Purpose: Adds a edge to the graph with the parameters imported.
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 6 by Austin Bevacqua (20162896)
    **************/
    public void addEdge(String to, String from, String inLabel) {
        DSAGraphVertex vertexOne, vertexTwo;
        DSAGraphEdge newEdge;
        vertexOne = setVertex(to);
        vertexTwo = setVertex(from);
        
        newEdge = new DSAGraphEdge(vertexOne, vertexTwo, inLabel);    
        edges.insert(inLabel, newEdge);
    }

    /**************** 
    Add Bidirectional Edges
     Purpose: As the graph is directed, this adds two edges to the graph between the same two vertexes, to make
                it bi-directional.
     Author: Austin Bevacqua
    ***************/
    public void addBidirectionalEdges(String pointOne, String pointTwo, String labelOne, String labelTwo) {
        DSAGraphVertex vertexOne, vertexTwo;
        DSAGraphEdge edgeOne, edgeTwo;
        vertexOne = setVertex(pointOne);
        vertexTwo = setVertex(pointTwo);

        edgeOne = new DSAGraphEdge(vertexOne, vertexTwo, labelOne);   
        edgeTwo = new DSAGraphEdge(vertexTwo, vertexOne, labelTwo);

        edgeOne.setOpposite(edgeTwo);
        edgeTwo.setOpposite(edgeOne);

        edges.insert(labelOne, edgeOne);
        edges.insert(labelTwo, edgeTwo);
    }
 
    /*******************
    Set Vertex
     Purpose: Checks if a vertex with the label of the imported string exists. If not, creates a new vertex.
                If it does exist, the already exiting node is returned.
     Author: Austin Bevacqua
     CITATION: Originally submitted for DSA Practical 6 by Austin Bevacqua (20162896)
    ********************/
    private DSAGraphVertex setVertex(String inLabel) {
        DSAGraphVertex newVertex = null;
        if(!hasVertex(inLabel)) {
            addVertex(inLabel);
        }
        newVertex = getVertex(inLabel);
        return newVertex;
    }
}
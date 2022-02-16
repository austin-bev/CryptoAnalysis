/****************
 * Class: Graph Test Harness
 * Purpose: Tests the functions of the Graph data structure and all associated objects
 * Author: Austin Bevacqua
 * Last Modified: 31/10/20
 ******************/
import java.util.NoSuchElementException;

public class UnitTestDSAGraph {
    public static void main(String[] args)
    {
        //Runs all 4 tests consecutively after each other
        //Tests are independent.
        System.out.println("~~~Vertex Test~~~");
        vertexTest();
        System.out.println("~~~Edge Test~~~");
        edgeTest();
        System.out.println("~~~Metadata Test~~~");
        metadataTest();
        System.out.println("~~~Graph Test~~~");
        graphTest();
    }
    /****************
     * Function: Vertex Test
     * Purpose: Tests the DSAGraphVertex object and all its functions
     * Author: Austin Bevacqua
     * Last Modified: 31/10/20
     ******************/
    public static void vertexTest() {
        DSAGraphVertex testOne, testTwo, testThree;
        int testsPassed = 0;
        testOne = new DSAGraphVertex("NO VALUE");
        testTwo = new DSAGraphVertex("VALUE", "Hello World!");
        testThree = new DSAGraphVertex("VALUE", "Hello World!");

        //Get Label Test
        if((testOne.getLabel().equals("NO VALUE")) && (testTwo.getLabel().equals("VALUE"))) {
            testsPassed++;
            System.out.println("PASSED test getLabel");
        }
        else {
            System.out.println("FAILED test getLabel");
        }

        //Get Value test
        if((testOne.getValue() == null) && (testTwo.getValue().equals("Hello World!"))) {
            testsPassed++;
            System.out.println("PASSED test getValue");
        }
        else {
            System.out.println("FAILED test getValue");
        }

        //Equals test
        if((testTwo.equals(testThree)) && (!testOne.equals(testThree))) {
            testsPassed++;
            System.out.println("PASSED test equals");
        }
        else {
            System.out.println("FAILED test equals");
        }
        testOne.setVisited();
        testTwo.setIgnored();

        //Visited Test 1
        if((testOne.getVisited()) && (!testTwo.getVisited())) {
            testsPassed++;
            System.out.println("PASSED test visited 1");
        }
        else {
            System.out.println("FAILED test visited 1");
        }

        //Ignored Test 1
        if((!testOne.getIgnored()) && (testTwo.getIgnored())) {
            testsPassed++;
            System.out.println("PASSED test ignored 1");
        }
        else {
            System.out.println("FAILED test ignored 1");
        }

        testOne.clearVisited();
        testTwo.clearIgnored();

        //Visited Test 2
        if(!testOne.getVisited()) {
            testsPassed++;
            System.out.println("PASSED test visited 2");
        }
        else {
            System.out.println("FAILED test visited 2");
        }

        //Ignored test 2
        if(!testTwo.getIgnored()) {
            testsPassed++;
            System.out.println("PASSED test ignored 2");
        }
        else {
            System.out.println("FAILED test ignored 2");
        }

        testOne.setValue("FOO");
        //Set Value test
        if(testOne.getValue().equals("FOO")) {
            testsPassed++;
            System.out.println("PASSED test setValue");
        }
        else {
            System.out.println("FAILED test setValue");
        }

        //Overall score
        System.out.println("PASSED " + testsPassed + "/8 tests!");
    }

    /****************
     * Function: Edge Test
     * Purpose: Tests the DSAGraphEdge object and all its functions
     * Author: Austin Bevacqua
     * Last Modified: 31/10/20
     ******************/
    public static void edgeTest() {
        int testsPassed = 0;
        DSAGraphEdge testOne, testTwo;
        DSAGraphVertex vertexOne, vertexTwo, vertexThree;

        vertexOne = new DSAGraphVertex("ONE");
        vertexTwo = new DSAGraphVertex("TWO");
        vertexThree = new DSAGraphVertex("THREE");

        testOne = new DSAGraphEdge(vertexOne, vertexTwo, "LABEL");
        testTwo = new DSAGraphEdge(vertexOne, vertexThree);

        //Get Value test
        if ((testOne.getValue() == null) && (testTwo.getValue() == null)) {
            testsPassed++;
            System.out.println("PASSED test getValue");
        }
        else {
            System.out.println("FAILED test getValue");
        }

        //Get To Test
        if((testOne.getFrom().equals(vertexOne)) && (testTwo.getFrom().equals(vertexOne))) {
            testsPassed++;
            System.out.println("PASSED test getTo");
        }
        else {
            System.out.println("FAILED test getTo");
        }

        //Get From Test
        if((testOne.getTo().equals(vertexTwo)) && (testTwo.getTo().equals(vertexThree))) {
            testsPassed++;
            System.out.println("PASSED test getFrom");
        }
        else {
            System.out.println("FAILED test getFrom");
        }

        //Get Label test
        if((testOne.getLabel().equals("LABEL")) && (testTwo.getLabel() == null)) {
            testsPassed++;
            System.out.println("PASSED test getLabel");
        }
        else {
            System.out.println("FAILED test getLabel");
        }

        testOne.setValue("ONE");
        testTwo.setValue("TWO");
        //Set Value Test
        if ((testOne.getValue().equals("ONE")) && (testTwo.getValue().equals("TWO"))) {
            testsPassed++;
            System.out.println("PASSED test setValue");
        }
        else {
            System.out.println("FAILED test getValue");
        }

        //Get Opposite Test
        if((testOne.getOpposite() == null) && (testTwo.getOpposite() == null)) {
            testsPassed++;
            System.out.println("PASSED test getOpposite");
        }
        else {
            System.out.println("FAILED test getOpposite");
        }

        testOne.setOpposite(testTwo);

        //Set Opposite Test
        if(testOne.getOpposite().getValue().equals("TWO")) {
            testsPassed++;
            System.out.println("PASSED test setOpposite");
        }
        else {
            System.out.println("FAILED test setOpposite");
        }

        //Overall score
        System.out.println("PASSED " + testsPassed + "/7 tests!");
    }

    /****************
     * Function: Metadata Test
     * Purpose: Tests the Metadata object and all its functions
     * Author: Austin Bevacqua
     * Last Modified: 31/10/20
     ******************/
    public static void metadataTest() {
        int testsPassed = 0;
        Metadata newMetadata = new Metadata();

        //Tests hasAssetInfo + hasTrades + hasAssets
        if (!newMetadata.hasAssetInfo() && !newMetadata.hasTrades() && !newMetadata.hasAssets()) {
            testsPassed++;
            System.out.println("PASSED Accessor tests 1");
        }
        else {
            System.out.println("FAILED Accessor tests 1");
        }

        //Tests getAssetInfoFileName + getTradeFileName + getAssetFileName
        if ((newMetadata.getAssetInfoFileName().equals("<NOT FOUND>")) && (newMetadata.getTradeFileName().equals("<NOT FOUND>")) && (newMetadata.getAssetFileName().equals("<NOT FOUND>"))) {
            testsPassed++;
            System.out.println("PASSED Accessor tests 2");
        }
        else {
            System.out.println("FAILED Accessor tests 2");
        }

        newMetadata.setHasAssetinfo();
        newMetadata.setHasAssets();
        newMetadata.setHasTrades();

        //Tests setHasAssetinfo + setHasAssets + setHasTrades
        if (newMetadata.hasAssetInfo() && newMetadata.hasTrades() && newMetadata.hasAssets()) {
            testsPassed++;
            System.out.println("PASSED Mutator tests 1");
        }
        else {
            System.out.println("FAILED Mutator tests 1");
        }

        newMetadata.setAssetInfoFileName("ONE");
        newMetadata.setTradeFileName("TWO");
        newMetadata.setAssetFileName("THREE");

        //Tests setAssetInfoFileName + setTradeFileName + setAssetFileName
        if ((newMetadata.getAssetInfoFileName().equals("ONE")) && (newMetadata.getTradeFileName().equals("TWO")) && (newMetadata.getAssetFileName().equals("THREE"))) {
            testsPassed++;
            System.out.println("PASSED Mutator tests 2");
        }
        else {
            System.out.println("FAILED Mutator tests 2");
        }

        //Overall Score
        System.out.println("PASSED " + testsPassed + "/4 tests!");
    }

    /****************
     * Function: Edge Test
     * Purpose: Tests the DSAGraph object and all its functions
     * Author: Austin Bevacqua
     * Last Modified: 31/10/20
     ******************/
    public static void graphTest() {
        int testsPassed = 0;
        DSALinkedList ll;
        DSAGraph newGraph;
        newGraph = new DSAGraph();

        //Test isEmpty
        if(newGraph.isEmpty()) {
            testsPassed++;
            System.out.println("PASSED isEmpty test 1");
        }
        else {
            System.out.println("FAILED isEmpty test 1");
        }

        newGraph.addVertex("VertexOne");
        newGraph.addVertex("VertexTwo", "ValueTwo");

        //Test isEmpty #2
        if(!newGraph.isEmpty()) {
            testsPassed++;
            System.out.println("PASSED isEmpty test 2");
        }
        else {
            System.out.println("FAILED isEmpty test 2");
        }

        //Test addVertex
        if((newGraph.hasVertex("VertexOne")) && (newGraph.hasVertex("VertexTwo")) && (!newGraph.hasVertex("NOTHAVE"))) {
            testsPassed++;
            System.out.println("PASSED addVertex test");
        }
        else {
            System.out.println("FAILED addVertex test");
        }

        //Test getVertexCount
        if(newGraph.getVertexCount() == 2) {
            testsPassed++;
            System.out.println("PASSED getVertexCount test");
        }
        else {
            System.out.println("FAILED getVertexCount test");
        }

        //Test getVertex
        if(newGraph.getVertex("VertexTwo").getValue().equals("ValueTwo")) {
            testsPassed++;
            System.out.println("PASSED getVertex test 1");
        }
        else {
            System.out.println("FAILED getVertex test 1");
        }

        //Test getVertex #2
        try {
            newGraph.getVertex("NOTHAVE");
            System.out.println("FAILED getVertex test 2");
        }
        catch (NoSuchElementException e) {
            testsPassed++;
            System.out.println("PASSED getVertex test 2");
        }

        newGraph.ignoreVertex("VertexOne");
        //Test ignoreVertex
        if(newGraph.getVertex("VertexOne").getIgnored()) {
            testsPassed++;
            System.out.println("PASSED ignoreVertex test");
        }
        else {
            System.out.println("FAILED ignoreVertex test");
        }

        newGraph.acknowledgeVertex("VertexOne");
        //Test acknowledgeVertex
        if(!newGraph.getVertex("VertexOne").getIgnored()) {
            testsPassed++;
            System.out.println("PASSED acknowledgeVertex test");
        }
        else {
            System.out.println("FAILED acknowledgeVertex test");
        }

        newGraph.setVertexValue("ValueOne", "VertexOne");

        //Test setVertexValue
        if(newGraph.getVertex("VertexOne").getValue().equals("ValueOne")) {
            testsPassed++;
            System.out.println("PASSED setVertexValue test");
        }
        else {
            System.out.println("FAILED setVertexValue test");
        }

        newGraph.addEdge("VertexOne", "VertexTwo", "OneTwo");
        newGraph.addEdge("VertexFour", "VertexThree", "ThreeFour");
        newGraph.addBidirectionalEdges("VertexFive", "VertexSix", "FiveSix", "SixFive");

        //Test addEdge
        try {
            newGraph.getEdge("ThreeFour");
            testsPassed++;
            System.out.println("PASSED addEdge test");
        }
        catch (NoSuchElementException e) {
            System.out.println("FAILED addEdge test");
        }

        //Test getEdge
        try {
            newGraph.getEdge("DOESNOTEXIST");
            System.out.println("FAILED getEdge test");
        }
        catch (NoSuchElementException e) {
            testsPassed++;
            System.out.println("PASSED getEdge test");
        }

        //Test getEdgeCount
        if (newGraph.getEdgeCount() == 4) {
            testsPassed++;
            System.out.println("PASSED getEdgeCount test");
        }
        else {
            System.out.println("FAILED getEdgeCount test");
        }

        //Test isAdjacent
        if((newGraph.isAdjacent("VertexTwo", "VertexOne")) && (!newGraph.isAdjacent("VertexOne", "VertexThree"))) {
            testsPassed++;
            System.out.println("PASSED isAdjacent test");
        }
        else {
            System.out.println("FAILED isAdjacent test");
        }

        newGraph.addEdge("VertexOne", "VertexThree", "OneThree");
        ll = newGraph.getAdjacent("VertexOne");

        //Test getAdjacent
        if (ll.getCount() == 2) {
            testsPassed++;
            System.out.println("PASSED getAdjacent test");
        }
        else {
            System.out.println("FAILED getAdjacent test");
        }

        //Overall score
        System.out.println("PASSED " + testsPassed + "/14 tests!");
    }
}
        

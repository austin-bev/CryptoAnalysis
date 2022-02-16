/****************
 * Class: FileIO Test Harness
 * Purpose: Tests the functions of the static FileIO class
 * Author: Austin Bevacqua
 * Last Modified: 31/10/20
 ******************/

import java.util.*;

public class UnitTestFileIO {

    public static void main(String[] args) {
        DSAGraph theGraph, errorGraph;
        int testsPassed = 0;
        theGraph = FileIO.readAssetData("assetdata.json");

        //Test Read Asset
        if (theGraph != null) {
            testsPassed++;
            System.out.println("PASSED Read Asset Test (2.1)");
        } else {
            System.out.println("FAILED Read Asset Test (2.1)");
        }

        //Test Format graph
        if ((theGraph.hasVertex("BTC")) && (theGraph.hasVertex("ETH"))) {
            try {
                theGraph.getEdge("BTCETH");
                testsPassed++;
                System.out.println("PASSED Format Graph Test (2.2)");
            }
            catch(NoSuchElementException e) {
                System.out.println("FAILED Format Graph Test (2.2)");
            }
        }
        else {
            System.out.println("FAILED Format Graph Test (2.2)");
        }

        //Test read trade data
        FileIO.readTradeData("tradedata.json", theGraph);
        if (theGraph.getMetadata().hasTrades()) {
            testsPassed++;
            System.out.println("PASSED Read Trade data Test (2.3)");
        }
        else {
            System.out.println("FAILED Read Trade data Test (2.3)");
        }

        //Test Add data to trades
        if(theGraph.getEdge("BTCETH").getValue() != null) {
            testsPassed++;
            System.out.println("PASSED Add Data to Trades Test (2.4)");
        }
        else {
            System.out.println("FAILED Add Data to Trades Test (2.4)");
        }
        FileIO.readAssetInfo("asset_info.csv", theGraph);

        //Test read asset info
        if (theGraph.getMetadata().hasAssetInfo()) {
            testsPassed++;
            System.out.println("PASSED Read Asset Info Test (2.5)");
        }
        else {
            System.out.println("FAILED Read Asset Info Test (2.5)");
        }

        //Test add data to assets
        if(theGraph.getVertex("BTC").getValue() != null) {
            testsPassed++;
            System.out.println("PASSED Add Data to Assets Test (2.6)");
        }
        else {
            System.out.println("FAILED Add Data to Assets Test (2.6)");
        }

        System.out.println("~~Error below is expected~~");
        errorGraph = FileIO.readAssetData("FOO BAR");

        //Test Incorrect file
        if (errorGraph == null) {
            try {
                errorGraph = FileIO.readAssetData("tradedata.json");
                System.out.println("FAILED Incorrect File Test (2.7)");
            }
            catch (IllegalArgumentException e) {
                testsPassed++;
                System.out.println("PASSED Incorrect File Test (2.7)");
            }
            errorGraph = new DSAGraph();
            try {
                FileIO.readTradeData("assetdata.json", errorGraph);
                System.out.println("FAILED Incorrect File Test (2.7)");
            }
            catch (IllegalArgumentException e) {
                testsPassed++;
                System.out.println("PASSED Incorrect File Test 1 (2.7)");
            }
            try {
                FileIO.readAssetInfo("assetdata.json", errorGraph);
                System.out.println("FAILED Incorrect File Test 2 (2.7)");
            }
            catch (IllegalArgumentException e) {
                testsPassed++;
                System.out.println("PASSED Incorrect File Test 3 (2.7)");
            }
        }

        //Overall Score
        System.out.println("\nPassed " + testsPassed + "/9 tests!");
    }
}

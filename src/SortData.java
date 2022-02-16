/*********************
 * Class: SortData
 AUTHOR: Austin Bevacqua
 LAST MODIFIED: 31/05/2020
 PURPOSE: A static class that provides functionality for sorting edges and nodes in a graph by their stored values
 ************************/
import java.util.Iterator;

public class SortData {
    /**********************
     * Function: Trade By Price
     * Purpose: Organises all trades in a graph by price, outputting the 10 highest to the screen
     * Author: Austin Bevacqua
     * Date: 27/10/20
     ***********************/
    public static void tradebyPrice(DSAGraph theGraph) {
        DSAHeap sortedTrades = null;
        Iterator itr;
        TradeInfo currTrade;
        sortedTrades = new DSAHeap(theGraph.getEdgeCount());
        itr = theGraph.getEdges().iterator();

        // Iterates trough every trade (edge) in the graph, adding each element to a heap
        // By setting the price as the priority, it maintain an order while elements are added
        while (itr.hasNext()) {
            currTrade = (TradeInfo) ((DSAGraphEdge) itr.next()).getValue();
            if (currTrade != null)
                sortedTrades.insert(currTrade.getPrice(), currTrade);
        }
        //The top 10 elements are removed from the heap and printed; there are our 10 highest edges
        UserInterface.println("\nTop 10 Trades by price:");
        for (int i = 1; i <= 10; i++) {
            currTrade = (TradeInfo) sortedTrades.delete();
            UserInterface.println(i + ":" + currTrade.getSymbol() + "(" + currTrade.getPrice() + ")");
        }
    }
    /**********************
     * Function: Trade By Price Change
     * Purpose: Organises all trades in a graph by price change, outputting the 10 highest to the screen
     * Author: Austin Bevacqua
     * Date: 27/10/20
     ***********************/
    public static void tradebyPriceChange(DSAGraph theGraph) {
        //FUNCTION WORKS IDENTICALLY TO tradeByPrice, except which value the data is sorted by is changed
        DSAHeap sortedTrades = null;
        Iterator itr;
        TradeInfo currTrade;
        sortedTrades = new DSAHeap(theGraph.getEdgeCount());
        itr = theGraph.getEdges().iterator();

        while (itr.hasNext()) {
            currTrade = (TradeInfo) ((DSAGraphEdge) itr.next()).getValue();
            if (currTrade != null)
                sortedTrades.insert(currTrade.getPriceChange(), currTrade);
        }
        UserInterface.println("\nTop 10 Trades by price change:");
        for (int i = 1; i <= 10; i++) {
            currTrade = (TradeInfo) sortedTrades.delete();
            UserInterface.println(i + ":" + currTrade.getSymbol() + "(" + currTrade.getPriceChange() + ")");
        }
    }
    /**********************
     * Function: Trade By Price Change
     * Purpose: Organises all trades in a graph by price change, outputting the 10 highest to the screen
     * Author: Austin Bevacqua
     * Date: 27/10/20
     ***********************/
    public static void tradebyPriceChangePercent(DSAGraph theGraph) {
        //FUNCTION WORKS IDENTICALLY TO tradeByPrice, except which value the data is sorted by is changed
        DSAHeap sortedTrades = null;
        Iterator itr;
        TradeInfo currTrade;
        sortedTrades = new DSAHeap(theGraph.getEdgeCount());
        itr = theGraph.getEdges().iterator();

        while (itr.hasNext()) {
            currTrade = (TradeInfo) ((DSAGraphEdge) itr.next()).getValue();
            if (currTrade != null)
                sortedTrades.insert(currTrade.getChangePercent(), currTrade);
        }
        UserInterface.println("\nTop 10 Trades by price change percent:");
        for (int i = 1; i <= 10; i++) {
            currTrade = (TradeInfo) sortedTrades.delete();
            UserInterface.println(i + ":" + currTrade.getSymbol() + "(" + currTrade.getChangePercent() + "%)");
        }
    }

    /**********************
     * Function: Trade By Volume
     * Purpose: Organises all trades in a graph by volume, outputting the 10 highest to the screen
     * Author: Austin Bevacqua
     * Date: 27/10/20
     ***********************/
    public static void tradebyVolume(DSAGraph theGraph) {
        //FUNCTION WORKS SIMILARLY TO ABOVE FUNCTIONS, EXCEPT SORTED BY DIFFERENT VALUE
        //Function also removes duplicate entries from being printed
        // E.g. BTCETH will have the same volume as ETHBTC
        DSAHeap sortedTrades = null;
        Iterator itr;
        TradeInfo currTrade;
        double currVolume, lastVolume = 0.0;
        sortedTrades = new DSAHeap(theGraph.getEdgeCount());
        itr = theGraph.getEdges().iterator();

        while (itr.hasNext()) {
            currTrade = (TradeInfo) ((DSAGraphEdge) itr.next()).getValue();
            if (currTrade != null)
                sortedTrades.insert(currTrade.getVolume(), currTrade);
        }
        UserInterface.println("\nTop 10 Trades by volume:");
        for (int i = 1; i <= 10; i++) {
            currTrade = (TradeInfo) sortedTrades.delete();
            currVolume = (currTrade.getVolume());
            //If the last volume is identical the the current volume, we iterate to the next value and print that instead
            if (lastVolume == currVolume) {
                currTrade = (TradeInfo) sortedTrades.delete();
                currVolume = (currTrade.getVolume());
            }
            UserInterface.println(i + ":" + currTrade.getSymbol() + "(" + currVolume + ")");
            lastVolume = currVolume;
        }
    }
        /**********************
         * Function: Trade By Count
         * Purpose: Organises all trades in a graph by volume, outputting the 10 highest to the screen
         * Author: Austin Bevacqua
         * Date: 27/10/20
         ***********************/
        public static void tradebyCount(DSAGraph theGraph) {
            //FUNCTION WORKS IDENTICALLY TO tradebyVolume, except data is being sorted by count
            DSAHeap sortedTrades = null;
            Iterator itr;
            TradeInfo currTrade;
            double currCount, lastCount = 0.0;
            sortedTrades = new DSAHeap(theGraph.getEdgeCount());
            itr = theGraph.getEdges().iterator();

            while (itr.hasNext()) {
                currTrade = (TradeInfo) ((DSAGraphEdge) itr.next()).getValue();
                if (currTrade != null)
                    sortedTrades.insert(currTrade.getCount(), currTrade);
            }
            UserInterface.println("\nTop 10 Trades by volume:");
            for (int i = 1; i <= 10; i++) {
                currTrade = (TradeInfo) sortedTrades.delete();
                currCount  = (currTrade.getCount());
                //If the last volume is identical the the current volume, we iterate to the next value and print that instead
                if (lastCount == currCount){
                    currTrade = (TradeInfo) sortedTrades.delete();
                    currCount  = (currTrade.getCount());
                }
                UserInterface.println(i + ":" + currTrade.getSymbol() + "(" + currCount + ")");
                lastCount = currCount;
            }
    }

    /**********************
     * Function: Asset By Price
     * Purpose: Organises all trades in a graph by price, outputting the 10 highest to the screen
     * Author: Austin Bevacqua
     * Date: 29/10/20
     ***********************/
    public static void assetbyPrice(DSAGraph theGraph) {
        DSAHeap sortedTrades = null;
        Iterator itr;
        AssetInfo currAsset;

        sortedTrades = new DSAHeap(theGraph.getVertexCount());
        itr = theGraph.getVerticies().iterator();

        // Iterates trough every asset (vertex) in the graph, adding each element to a heap
        // By setting the price as the priority, it maintain an order while elements are added
        while (itr.hasNext()) {
            currAsset = (AssetInfo) ((DSAGraphVertex) itr.next()).getValue();
            if (currAsset != null)
                sortedTrades.insert(currAsset.getPrice(), currAsset);
        }
        //The top 10 elements are removed from the heap and printed; there are our 10 highest vertexes
        UserInterface.println("\nTop 10 Assets by price:");
        for (int i = 1; i <= 10; i++) {
            currAsset = (AssetInfo) sortedTrades.delete();
            UserInterface.println(i + ":" + currAsset.getName() + "(" + currAsset.getLabel() + ") - " + currAsset.getPrice());
        }
    }

    /**********************
     * Function: Asset By Price Change
     * Purpose: Organises all trades in a graph by price, outputting the 10 highest to the screen
     * Author: Austin Bevacqua
     * Date: 27/10/20
     ***********************/
    public static void assetbyPriceChange(DSAGraph theGraph) {
        //FUNCTION WORKS IDENTICALLY TO assetbyPrice, except which value the data is sorted by is changed
        DSAHeap sortedTrades = null;
        Iterator itr;
        AssetInfo currAsset;

        sortedTrades = new DSAHeap(theGraph.getVertexCount());
        itr = theGraph.getVerticies().iterator();

        while (itr.hasNext()) {
            currAsset = (AssetInfo) ((DSAGraphVertex) itr.next()).getValue();
            if (currAsset != null)
                sortedTrades.insert(currAsset.getChange(), currAsset);
        }
        UserInterface.println("\nTop 10 Assets by price change:");
        for (int i = 1; i <= 10; i++) {
            currAsset = (AssetInfo) sortedTrades.delete();
            UserInterface.println(i + ":" + currAsset.getName() + "(" + currAsset.getLabel() + ") - " + currAsset.getChange() + "%");
        }
    }
    /**********************
     * Function: Asset By Volume
     * Purpose: Organises all trades in a graph by price, outputting the 10 highest to the screen
     * Author: Austin Bevacqua
     * Date: 29/10/20
     ***********************/
    public static void assetbyVolume(DSAGraph theGraph) {
        //FUNCTION WORKS IDENTICALLY TO assetbyPrice, except which value the data is sorted by is changed
        DSAHeap sortedTrades = null;
        Iterator itr;
        AssetInfo currAsset;

        sortedTrades = new DSAHeap(theGraph.getVertexCount());
        itr = theGraph.getVerticies().iterator();

        while (itr.hasNext()) {
            currAsset = (AssetInfo) ((DSAGraphVertex) itr.next()).getValue();
            if (currAsset != null)
                sortedTrades.insert(currAsset.getVolume(), currAsset);
        }
        UserInterface.println("\nTop 10 Assets by volume:");
        for (int i = 1; i <= 10; i++) {
            currAsset = (AssetInfo) sortedTrades.delete();
            UserInterface.println(i + ":" + currAsset.getName() + "(" + currAsset.getLabel() + ") - " + currAsset.getVolume());
        }
    }

    /**********************
     * Function: Asset By Cap
     * Purpose: Organises all trades in a graph by market cap, outputting the 10 highest to the screen
     * Author: Austin Bevacqua
     * Date: 27/10/20
     ***********************/
    public static void assetbyCap(DSAGraph theGraph) {
        //FUNCTION WORKS IDENTICALLY TO assetbyPrice, except which value the data is sorted by is changed
        DSAHeap sortedTrades = null;
        Iterator itr;
        AssetInfo currAsset;

        sortedTrades = new DSAHeap(theGraph.getVertexCount());
        itr = theGraph.getVerticies().iterator();

        while (itr.hasNext()) {
            currAsset = (AssetInfo) ((DSAGraphVertex) itr.next()).getValue();
            if (currAsset != null)
                sortedTrades.insert(currAsset.getCap(), currAsset);
        }
        UserInterface.println("\nTop 10 Assets by price change:");
        for (int i = 1; i <= 10; i++) {
            currAsset = (AssetInfo) sortedTrades.delete();
            UserInterface.println(i + ":" + currAsset.getName() + "(" + currAsset.getLabel() + ") - " + currAsset.getCap());
        }
    }
}
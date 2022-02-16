/*********************
 * Class: Json Parser
 AUTHOR: Austin Bevacqua
 LAST MODIFIED: 31/05/2020
 PURPOSE: A static class that provides functionality reading/formatting various specific .json files
 ************************/

public class JsonParser {

    /********************
    SUBMODULE: ParseTradeData
    AUTHOR: Austin Bevacqua
    ASSERTION: Parses the trade .json file into individual trades and adds them to a graph
    DATE MODIFIED: 30/10/2020
    ********************/
    public static void parseTradeData(String line, DSAGraph theGraph) {
        TradeInfo currTrade,oppositeTrade;
        DSAGraphEdge foundEdge;
        //Splits every value in the json file by { and } characters.
        //In json files, individual elements are seperated by these characters
        for (String retval : line.split("\\{|\\}")) {
            //Some of the split strings will be blank or ",".
            //A quick check is done to ensure the split line is not either of these values.
            if (!retval.equals("") && (!retval.equals(","))) {
                currTrade = new TradeInfo(retval);
                oppositeTrade = new TradeInfo(currTrade);

                //If the COUNT of the trade is currently equal to 0, the trade does not exist int he graph
                //Any trade which has a 0 count is currently not trading.
                if (currTrade.getCount() != 0) {
                    foundEdge = theGraph.getEdge(currTrade.getSymbol());
                    //We find the trade in the graph and update its value to the parsed values
                    foundEdge.setValue(currTrade);
                    //Afterward, we find the inverse of the data and set it as the inverse trade's value.
                    oppositeTrade.invert();
                    foundEdge.getOpposite().setValue(oppositeTrade);
                }
            }
        }
    }

    /********************
    SUBMODULE: ParseAssetData
    AUTHOR: Austin Bevacqua
    ASSERTION: Parses the asset .json file into a format that can be separated by commas
    DATE MODIFIED: 04/10/2020
    ********************/
    public static DSAGraph parseAssetData(String parsedLine) {
        DSAGraph newGraph = new DSAGraph();

        //Splits every line by { or } characters, as individual elements in the .json will be separated by these characters
        for (String retval : parsedLine.split("\\{|\\}")) {
            //If the split line is not a blank character or a comma, we can parse the line into an edge.
            if (!retval.equals("") && (!retval.equals(","))) {
                parseEdge(retval, newGraph);
            }
        }

        return newGraph;
    }


    /********************
    SUBMODULE: formatAssetData
    AUTHOR: Austin Bevacqua
    ASSERTION: Removes all unnecessary data from an asset data file, formatting it into a String we can split by commas.
    DATE MODIFIED: 04/10/2020
    ********************/
    public static String formatAssetData(String inData) {
        String newString;
        //The "opening" of the data is removed, as it does not contain any useful information for the scope of this algorithm. (E.g. timezone)
        newString = inData.replaceAll("\\{\"timezone(.*?)symbols\":", "");

        /*Some of the classfields of the .json file are arrays labeled "filters", "orderTypes" and "permissions".
        The array values are separated by commas, making parsing by commas difficult. To solve this, we remove all the array data from the dataset*/
        newString = newString.replaceAll("\"filters(.*?)],|\"orderTypes(.*?)],|\"permissions(.*?)]", "");

        // Removes all remaining "[" and "]" characters
        newString = newString.replaceAll("\\[|\\]", "");

        //The remaining, non removed data should be comma separated values that are separated by the ":" character.
        return newString;
    }
    
    /********************
    SUBMODULE: parseEdge
    AUTHOR: Austin Bevacqua
    ASSERTION: Splits a line of values by the ':' and ',' characters, and adds the information
                to the graph as an edge.
    DATE MODIFIED: 04/10/2020
    ********************/
    public static void parseEdge(String retval, DSAGraph theGraph) {
        String[] colonVal = new String[14];
        String[] split = new String[2];
        String baseAsset = null;
        String quoteAsset = null;
        String status = null;

        //Values within a .json file are separated by commas, so we split the values by the coma character.
        colonVal =  retval.split(",");
        for (int i = 0; i < 14; i++) {
            //All quotation marks within the file are completely removed. This is so we can parse the data into integers and doubles if necessary.
            //We also split the values by ":", as .json files separate classfield names and values by ":"
            split = colonVal[i].replaceAll("\"", "").split(":");
            switch(split[0]) {
                case "status":
                    status = split[1];
                break;
                case "baseAsset":
                    baseAsset = split[1];
                break;
                case "quoteAsset":
                    quoteAsset = split[1];
                break;
            }
        }
        //Once all necessary values have been extracted, we parse the values as an edge into the graph
        addParsedEdge(baseAsset, quoteAsset, status, theGraph);
    }

    /********************
    SUBMODULE: addParsedEdge
    AUTHOR: Austin Bevacqua
    ASSERTION: Validates parameters and adds a parsed edge to the graph
    DATE MODIFIED: 30/10/2020
    ********************/
    private static void addParsedEdge(String baseAsset, String quoteAsset, String status, DSAGraph theGraph) {
        //First, we validate that the imported values are not null.
        if ((baseAsset != null) && (quoteAsset != null)) {
            //We then validate that the status of the trade is "TRADING" if the trade is "BREAK", we do not want to import it
            if (status.equals("TRADING")) {
                //The edge is added to the graph.
                theGraph.addBidirectionalEdges(baseAsset, quoteAsset, baseAsset+quoteAsset, quoteAsset+baseAsset);
            }
        }
    }
}
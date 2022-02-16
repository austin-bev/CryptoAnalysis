/***********************
 Menu Class
 Purpose: Static class with functions which enable the viewing of data through "report Mode"
    or viewing / modifying data through an interactive menu.
 Last Modified: 31/10/20
 Author: Austin Bevacqua
 ************************/
import java.util.*;

public class Menu {
    /****************
     Class Report Mode
     Purpose: An non-interactive environment which will import the asset and trade data files,
        and then print basic statistics based on the dataset
     Date: 14/10/20
     Author: Austin Bevacqua
     ****************/
    public static void reportMode(String[] args) {
        DSAGraph theGraph = null;
        try {
            theGraph = FileIO.readAssetData(args[1]);
            //If the imported file is invalid, the graph will be null
            if (theGraph != null) {
                //If the file has been imported successfully, general information is printed to the screen
                UserInterface.println("Number of currencies on the BTC Network: " + theGraph.getAdjacent("BTC").getCount());
                UserInterface.println("Number of currencies on the ETH Network: " + theGraph.getAdjacent("ETH").getCount());
                //If more than one file was specified, we try and import the trade data
                if (args.length > 2) {
                    FileIO.readTradeData(args[2], theGraph);
                    //If it was successfully imported, print out the 10 most valuable trades
                    if (theGraph.getMetadata().hasTrades())
                        SortData.tradebyPrice(theGraph);
                }
                //If there were 3 files specified, we try and imported the asset info file
                if (args.length == 4) {
                    FileIO.readAssetInfo(args[3], theGraph);
                    //If it was successfully imported, print out the 10 most valuable assets
                    if (theGraph.getMetadata().hasAssetInfo())
                        SortData.assetbyPrice(theGraph);
                }
                UserInterface.println("\nNumber of currencies: " + theGraph.getVertexCount());
                UserInterface.println("Number of Trades: " + (theGraph.getEdgeCount() / 2));

            }
        }
        catch(IllegalArgumentException e) {
            UserInterface.println(e.getMessage());
        }
    }

    /******************
     * Function: interactiveMode
     * Purpose: Initalises the interactive mode of the program by prompting the user to import an asset data file
     * Author: Austin Bevacqua
     * Date: 26/10/20
     *******************/
    public static void interactiveMode() {
        DSAGraph theGraph = null;
        String fileName;
        do {
            fileName = UserInterface.userInput("Enter a name for the ASSET DATA (e.g. assetdata.json)");
            theGraph = FileIO.readAssetData(fileName);
            // The graph will only be null if an error has occurred while loading the file
        } while (theGraph == null);
        // Once the graph is successfully loaded, the main menu with options is shown.
        mainMenu(theGraph);
    }

    /********************
     Function: Main menu
     Purpose: Gives a list of options which the user can select from. Each option edits or prints data from the graph
     Author: Austin Bevacqua
     Date: 26/10/20
     *******************/
    private static void mainMenu(DSAGraph theGraph) {
        int userSelection;
        do {
            printMenuHeader(theGraph);
            //The user is given a selection of options to perform analysis / modifications to the graph
            userSelection = UserInterface.userInput(0, 8, "(1)Import Files\n(2)Display an asset\n(3)Display a trade\n(4)Find trade routes\n(5)Set asset filter\n(6)Asset overview\n(7)Trade overview\n(8)Save\n(0)Exit");
            switch (userSelection) {
                case 1:
                    theGraph = fileImportMenu(theGraph);
                break;
                case 2:
                    displayAssetMenu(theGraph);
                break;
                case 3:
                    displayTradeMenu(theGraph);
                break;
                case 4:
                    tradePathMenu(theGraph);
                break;
                case 5:
                    assetFilterMenu(theGraph);
                break;
                case 6:
                    assetOverview(theGraph);
                break;
                case 7:
                    tradeOverview(theGraph);
                break;
                case 8:
                    saveMenu(theGraph);
                break;
            }
        }while(userSelection != 0); //Once the user selects 0, the loop ends and the program stops
    }

    /*************
     * Function: Print Menu Header
     * Purpose: Prints out basic information related to the graph above the menu choice
     * Author: Austin Bevacqua
     * Date: 26/10/20
     *************/
    private static void printMenuHeader(DSAGraph theGraph) {
        UserInterface.println("\nCURRENT GRAPH:");
        UserInterface.println("    Asset Data File: " + theGraph.getMetadata().getAssetFileName());
        UserInterface.println("    Trade Data File: " + theGraph.getMetadata().getTradeFileName());
        UserInterface.println("    Asset Info File: " + theGraph.getMetadata().getAssetInfoFileName());
        UserInterface.println("\nSelect an option from 0-8:");
    }

    /*************
     * Function: File Import Menu
     * Purpose: A Menu in which the user can select to import a range of files for the program
     * Author: Austin Bevacqua
     * Date: 26/10/20
     *************/
    private static DSAGraph fileImportMenu(DSAGraph theGraph) {
        int selection;
        String filename;
        DSAGraph newGraph;

        //The user is prompted to which file they want to import, and then a filename.
        selection = UserInterface.userInput(1,3, "(1) Asset Data Json <assetdata.json> (Will overwrite everything)\n(2) Trade Data Json <tradedata.json> \n(3) Asset Info csv <asset_info.csv>");
        filename = UserInterface.userInput("Enter a file name");
        try {
            if (selection == 1) {
                theGraph = FileIO.readAssetData(filename);
            } else if (selection == 2) {
                FileIO.readTradeData(filename, theGraph);
            } else {
                FileIO.readAssetInfo(filename, theGraph);
            }
        }
        catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        return theGraph;
    }
    /*************
     * Function: Display Asset Menu
     * Purpose: A Menu in which the user can select to display information about a particular asset (node on a graph)
     * Author: Austin Bevacqua
     * Date: 26/10/20
     *************/
    private static void displayAssetMenu(DSAGraph theGraph) {
        String assetName;
        AssetInfo moreinfo;
        assetName = UserInterface.userInput("Enter the name of the asset: ");
        try {
            theGraph.getVertex(assetName); // Will throw exception if asset does not exist
            UserInterface.println("Asset " + assetName + " found!");
            UserInterface.println("Asset is involved in : " + theGraph.getAdjacent(assetName).getCount() + " trades");

            //If the asset is on the BTC or ETH network, this information is printed to the screen
            checkNetwork(assetName, theGraph, "BTC");
            checkNetwork(assetName, theGraph, "ETH");

            //If the extra file DOES exist, all stored data is printed
            if(theGraph.getMetadata().hasAssetInfo()) {
                moreinfo = (AssetInfo) theGraph.getVertex(assetName).getValue();
                UserInterface.println("\nName: " + moreinfo.getName());
                UserInterface.println("Full Name: " + moreinfo.getLabel());
                UserInterface.println("Price: " + moreinfo.getPrice());
                UserInterface.println("Price Change " + moreinfo.getChange() + "%");
                UserInterface.println("Volume: " + moreinfo.getVolume());
                UserInterface.println("Market Cap: " + moreinfo.getCap());
            }
        }
        catch(NoSuchElementException e) {
            UserInterface.println("Asset " + assetName + " does not exist in the graph");
        }
    }
    /*************
     * Function: Check Network
     * Purpose: Checks whether a particular asset is adjacent to another asset (aka on a "network")
     *          and then prints this information to the screen
     * Author: Austin Bevacqua
     * Date: 26/10/20
     *************/
    private static void checkNetwork(String assetName, DSAGraph theGraph, String network) {
        if (theGraph.isAdjacent(assetName, network))
            UserInterface.println("Asset IS on the " + network +  " Network");
        else
            UserInterface.println("Asset IS NOT on the " + network +  " Network");
    }

    /*************
     * Function: Display Trade Menu
     * Purpose:  A menu in which the user can select to view information about a particular trade (edge of a graph)
     * Author: Austin Bevacqua
     * Date: 26/10/20
     *************/
    public static void displayTradeMenu(DSAGraph theGraph) {
        String tradeName;
        TradeInfo moreinfo;

        tradeName = UserInterface.userInput("Enter the name of the trade: ");
        try {
            theGraph.getEdge(tradeName); // Will throw exception if trade does not exist
            UserInterface.println("Trade " + tradeName + " found!");

            //If an additional trade info file does not exist, this is all the information we have about the trade
            UserInterface.println("FROM: "+ theGraph.getEdge(tradeName).getFrom());
            UserInterface.println("TO: "+ theGraph.getEdge(tradeName).getTo());

            //If the extra file DOES exist, all stored data is printed
            if(theGraph.getMetadata().hasTrades()) {
                moreinfo = (TradeInfo) theGraph.getEdge(tradeName).getValue();
                UserInterface.println("Price Change: " + moreinfo.getPriceChange());
                UserInterface.println("Price Change: " + moreinfo.getChangePercent() + "%");
                UserInterface.println("Average Price: " + moreinfo.getPrice());
                UserInterface.println("Volume: " + moreinfo.getVolume());
                UserInterface.println("Count: " + moreinfo.getCount());
            }
        }
        catch(NoSuchElementException e) {
            UserInterface.println("Trade " + tradeName + " does not exist in the graph");
        }
    }

    /******************
     * Function: Trade Path Menu
     * Purpose: Prints potential paths between nodes in a graph
     *          If trade information exists, paths can be sorted by the sum of their price change
     * Author: Austin Bevacqua
     * Date: 27/10/20
     *******************/
    private static void tradePathMenu(DSAGraph theGraph) {
        String assetOne, assetTwo;
        int maxPathSize, userSelection;
        DSALinkedList allPaths;

        //The user is prompted to enter the name of the beginning and end asset in the trades.
        assetOne = UserInterface.userInput("Enter the name of the ORIGIN asset");
        assetTwo = UserInterface.userInput("Enter the name of the DESTINATION asset");
        //First, we check that the two specified assets are in the graph
        if (theGraph.hasVertex(assetOne) && theGraph.hasVertex(assetTwo)) {
            //If they do exist, we then ask the user to input a maximum length for the paths.
            maxPathSize = UserInterface.userInput(2,5, "Enter the maximum number of nodes to traverse (2-5)");
            //The DSAGraph will then calculate every possible path between these assets within the max length
            //and return this data as a linked list
            allPaths = theGraph.getPaths(assetOne, assetTwo, maxPathSize);

            UserInterface.println("\n" + allPaths.getCount() + " number of routes found");
            if(theGraph.isAdjacent(assetOne, assetTwo))
                UserInterface.println("A direct path exists between " + assetOne + " & " + assetTwo);
            else
                UserInterface.println("A direct path does not exist between " + assetOne + " & " + assetTwo);

            //if an additional trade file has been imported, we have the option of displaying the values by cumulative price change
            if(theGraph.getMetadata().hasTrades()) {
                userSelection = UserInterface.userInput(0,2, "Would you like to:\n(1) Display all paths\n(2) Display top 10 paths by cumulative price change\n(0) Go to main menu");
                if (userSelection == 1)
                    printAllPathsPrice(allPaths);
                else if (userSelection == 2)
                    printTopPaths(allPaths);
            }
            //If there is no extra file, we can just iteratively print every trade
            else {
                userSelection = UserInterface.userInput(0,1, "Would you like to:\n(1) Display all paths\n(0) Go to main menu");
                if (userSelection == 1)
                    printAllPaths(allPaths);
            }
        }
        else {
            UserInterface.println("ERROR: One or more assets do not exist in the graph");
        }
    }
    /*********************
     * Function: Print All Paths price
     * Purpose: Prints all trading paths in a linked list
     * Author: Austin Bevacqua
     * Date: 27/10/20
     ********************/
    private static void printAllPaths(DSALinkedList allPaths) {
        Iterator itr;

        itr = allPaths.iterator();

        //Iterates trough and prints every item of a linked list
        while(itr.hasNext()) {
            UserInterface.println(itr.next() + " ");
        }
    }

    /*********************
     * Function: Print All Paths price
     * Purpose: Prints all trading paths in a linked list
     *          Assumes that linked list contains TradeData items, and prints all elements of the object, including the price
     * Author: Austin Bevacqua
     * Date: 27/10/20
     ********************/
    private static void printAllPathsPrice(DSALinkedList allPaths) {
        Iterator itr;
        RouteData currData;

        itr = allPaths.iterator();
        //Iterates trough and prints every item of a linked list, as well as additional information stored.
        while(itr.hasNext()) {
            currData = (RouteData) itr.next();
            UserInterface.println(currData.getRoute() + " (" + currData.getPrice() + "%)");
        }
    }

    /**********************
     * Function: Print Top Paths
     * Purpose: Given a linked list of trading paths (containing TradeData objects),
     *          Prints out the top 10 items using a heap as the sorting method.
     * Author: Austin Bevacqua
     * Date: 27/102/0
     *********************/
    private static void printTopPaths(DSALinkedList allPaths) {
        Iterator itr;
        DSAHeap newHeap;
        RouteData currData;

        itr = allPaths.iterator();
        newHeap = new DSAHeap(allPaths.getCount());
        // Adds all elements into the heap, using the price as the priority
        // Once removed from the heap, all items will be in sorted order by price.
        while (itr.hasNext()) {
            currData = (RouteData) itr.next();
            newHeap.insert(currData.getPrice(), currData);
        }

        //The first 10 items will be removed off the heap and printed. These will be our 10 highest trades
        for (int i = 1; i <= 10; i++) {
            currData = (RouteData) newHeap.delete();
            System.out.println(i + ": " + currData.getRoute() + " (" + currData.getPrice() + "%)");
        }
    }
    /*******************
     * Function: Asset Filter Menu
     * Purpose: Gives an option to include / exclude certain vertexes from path finding algorithms within a graph
     * Author: Austin Bevacqua
     * Date: 27/10/20
     *******************/
    private static void assetFilterMenu(DSAGraph theGraph) {
        String assetName = null;
        int userSelection;
        try {
            //The user has the option of REMOVING or ADDING an asset to/from the filter
            userSelection = UserInterface.userInput(1,2,"Do you want to\n(1) ADD to the filter\n(2) REMOVE from the filter?");
            assetName = UserInterface.userInput("Enter the name of the asset");
            if (userSelection == 1)
                theGraph.ignoreVertex(assetName);
            else
                theGraph.acknowledgeVertex(assetName);
        }
        //If the specified asset does not exist, a NoSuchElementException will be thrown
        catch (NoSuchElementException e) {
            UserInterface.println("Cannot find asset " + assetName + " in the graph");
        }
    }

    /*********************
     * Function: Asset Overview
     * Purpose: Prints out basic information about the assets (vertexes) of a graph
     *          If additional information is imported, can sort assets by price, market cap, ect....
     * Author: Austin Bevacqua
     * Date: 27/10/20
     ********************/
    private static void assetOverview(DSAGraph theGraph) {
        DSAHeap sortedAssets = null;
        int userselection;

        //Gives a summary of the asset data in the graph, including which assets are connected to the BTC and ETH assets
        UserInterface.println("There are " + theGraph.getVertexCount() + " assets in the graph");
        UserInterface.println("Number of assets on the BTC Network: " + theGraph.getAdjacent("BTC").getCount());
        UserInterface.println("Number of assets on the ETH Network: " + theGraph.getAdjacent("ETH").getCount());
        if (theGraph.getMetadata().hasAssetInfo()) {
            do {
                //The data stores 4 different variables. The user is asked which variable they want to sort by
                userselection = UserInterface.userInput(0, 4, "\nSort data by:\n(1) Price\n(2) Price Change %\n(3) Volume\n(4) Market Cap\n(0) None");
                switch(userselection) {
                    case 1:
                        SortData.assetbyPrice(theGraph);
                    break;
                    case 2:
                        SortData.assetbyPriceChange(theGraph);
                    break;
                    case 3:
                        SortData.assetbyVolume(theGraph);
                    break;
                    case 4:
                        SortData.assetbyCap(theGraph);
                    break;
                }
            }while (userselection != 0);
        }
    }

    /********************
     * Function: Trade Overview
     * Purpose: Prints out basic information about the trades (edges) of a graph
     *          If additional information is imported, can sort all trades by count, price, ect..
     * Author: Austin Bevacqua
     * Date: 27/10/20
     *******************/
    private static void tradeOverview(DSAGraph theGraph) {
        int userselection;
        UserInterface.println("There are " + theGraph.getEdgeCount()/2 + " trades in the graph");
        // If a trade data file has been imported, we can sort the data by various values
        if (theGraph.getMetadata().hasTrades()) {
            do {
                //The data stores 5 different variables. The user is asked which variable they want to sort by
                userselection = UserInterface.userInput(0, 5, "\nSort data by:\n(1) Price\n(2) Price Change\n(3) Price Change %\n(4) Volume\n(5) Count\n(0) None");
                switch(userselection) {
                    case 1:
                        SortData.tradebyPrice(theGraph);
                    break;
                    case 2:
                        SortData.tradebyPriceChange(theGraph);
                    break;
                    case 3:
                        SortData.tradebyPriceChangePercent(theGraph);
                    break;
                    case 4:
                        SortData.tradebyVolume(theGraph);
                    break;
                    case 5:
                        SortData.tradebyCount(theGraph);
                    break;
                }
            }while (userselection != 0);
        }
    }

    public static void saveMenu(DSAGraph theGraph) {
        //NOT IMPLEMENTED
    }


    public static void printUsageError(){
        UserInterface.println("~~Incorrect usage.~~");
        UserInterface.println("    Interactive Mode: \"java cryptoGraph -i\"");
        UserInterface.println("    Report Mode: \"java cryptoGraph -r <asset_file>\"");
        UserInterface.println("    Report Mode: \"java cryptoGraph -r <asset_file> <trade_file>\"");
        UserInterface.println("    Report Mode: \"java cryptoGraph -r <asset_file> <trade_file> <asset_info_file>\"");
    }
}

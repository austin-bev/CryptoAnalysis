This was my submission for the final assessment of COMP1002 (v.1) Data Structures and Algorithms (2021). The task was to create a program which imported and then parsed graph data using data structures. My final mark was 99%

Introduction: 
    The CryptoGraph algorithms main functionality involves reading in various files related to Cryptocurrency, performing analysis and displaying the results to the screen.

Installation / Requirements:
    A valid installation of Java is required

    The algorithm requires a singular file which is acquired at the link
    https://www.binance.com/api/v3/exchangeInfo
    This file will be referred to as the asset data file.
    Included in submission under name assetdata.json

    The program also optionally can import two other files
    https://www.binance.com/api/v3/ticker/24hr
    This file will be referred to as the trade data file
    Included in submission under name tradedata.json

    https://coinmarketcap.com/all/views/all/
    This file, once copied into .csv format, will be referred to as the asset info file.
    Included in submission under name asset_info.csv

Compiling the program:
    Run "javac *.java" to compile the program

Running the program:
    NOTE: files can be renamed; these are the names of the files as of submission
 
    Report mode: 
        "java CryptoGraph -r assetdata.json"
        "java CryptoGraph -r assetdata.json tradedata.json"
        "java CryptoGraph -r assetdata.json tradedata.json assset_info.csv"
    Interactive mode: 
        "java CryptoGraph -i"

    Report mode:
    Basic analysis conducted on the imported files.
    No user input

    Interactive Mode:
    A series of interactive menus where the user can perform analysis or nodification on the imported data

Files:
    Main:
        CryptoGraph - verifies command line arguments and initalise program.
            Requires: Menu
    
    Static Classes:
        Menu -  static class which handles menu items in the report and interactive modes of the algorithm.
            Requires: DSALinkedList, DSAGraph, AssetInfo, TradeInfo, SortData, FileIO, UserInterface, RouteData, Metadata 
        FileIO - Handles the reading and writing of files into the algorithm
            Requires: JsonParser, DSAGraph
        JsonParser - Parses .json files into a graph
            Requires: DSAGraph
        UserInterface - Handles all user input and output
        SortData - Handles the sorting of trade or asset files by specefic data
            Requires: DSAHeap, DSAGraphVertex, DSAGraphEdge, TradeInfo, AssetInfo
    
    Data Structures:
        DSALinkedList - A data structure which represents a list of items, where the item order has no place in memory, but are linked together through classfields. 
        DSALinkedHashTable - A data structure which combines the functionality of a linked list with a hash table. Entries are added to an array with a hash hey, but can be iterated over.
        DSAHeap - A data structure which enters objects into an array with a priority. The data structure ensures that entries with the highest priority get removed first.
        DSAGraph - A data structure which 
            Requires: DSALinkedHashTable, Metadata, DSAGraphVertex, DSAGrapgEdge, RouteData

    Model Classes:
        DSAGraphVertex - Used by DSAGraph to store the verticies in a graph
        DSAGraphEdge - Used by DSAGraph to store the edges in a graph
        AssetInfo - Stores information about the assets/vertexes of a graph
        TradeIngo - Stores information about the trades/edges of a graph
        RouteData - Stores information about the trading routes between nodes
        Metadata - Stores information relating to the imported files in a graph        

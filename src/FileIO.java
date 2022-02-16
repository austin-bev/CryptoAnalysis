/*********************
 * Class: FileIO
AUTHOR: Austin Bevacqua
LAST MODIFIED: 31/05/2020
PURPOSE: A static class that provides functionality for reading various data files into a graph
************************/

import java.io.*;

public class FileIO
{
    /********************
    SUBMODULE: readTradeData
    AUTHOR: Austin Bevacqua
    ASSERTION: Reads a "trade data" json file and parses information into a grapn
    DATE MODIFIED: 31/05/2020
    ********************/
    public static void readTradeData(String fileName, DSAGraph theGraph)
    {
        FileInputStream fileStream = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;

        String line;
        try
        {
            fileStream = new FileInputStream(fileName);
            rdr = new InputStreamReader(fileStream);
            bufRdr = new BufferedReader(rdr);

            line = bufRdr.readLine().replaceAll("\\[|\\]", ""); //Deletes all "[" and "]" characters for easier parsing.
            validateTradeData(line);
            JsonParser.parseTradeData(line, theGraph); //We pass the .json string to function parseAssetData, which will parse the data into a linked list.

            //The graph metadata is updated to show that a trade file has been imported
            theGraph.getMetadata().setHasTrades();
            theGraph.getMetadata().setTradeFileName(fileName);

            fileStream.close(); //Once all file processing has been completed, the file is closed
        }
        catch(IOException e)
        {
            if(fileStream != null)
            {
                try
                {
                    fileStream.close();
                }
                catch(IOException ex2)
                {}
            }
            System.out.println("Error in file processing: " + e.getMessage());
        }
    }

    /********************
    SUBMODULE: readAssetData
    AUTHOR: Austin Bevacqua
    ASSERTION: Reads a "asset data" json file and parses information into a grapn
    DATE MODIFIED: 31/05/2020
    ********************/
    public static DSAGraph readAssetData(String fileName)
    {
        DSAGraph newGraph = null;
        FileInputStream fileStream = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        String parsedLine, line;

        try
        {
            fileStream = new FileInputStream(fileName);
            rdr = new InputStreamReader(fileStream);
            bufRdr = new BufferedReader(rdr);

            line = bufRdr.readLine();
            validateAssetData(line);
            parsedLine = JsonParser.formatAssetData(line);
            newGraph = JsonParser.parseAssetData(parsedLine);

            //The graph metadata is changed to store that we have imported an asset data file
            newGraph.getMetadata().setHasAssets();
            newGraph.getMetadata().setAssetFileName(fileName);

            fileStream.close(); //Once all file processing has been completed, the file is closed
        }
        catch(IOException e)
        {
            if(fileStream != null)
            {
                try
                {
                    fileStream.close();
                }
                catch(IOException ex2)
                {}
            }
            System.out.println("Error in file processing: " + e.getMessage());
        }
        return newGraph;
    }
    /********************
     SUBMODULE: readAssetInfo
     AUTHOR: Austin Bevacqua
     ASSERTION: Reads a "asset info" csv file and parses information into a grapn
     DATE MODIFIED: 31/05/2020
     ********************/
    public static void readAssetInfo(String fileName, DSAGraph theGraph)
    {
        FileInputStream fileStream = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        String currLine, volume, assetName;
        double cap, price, change;
        String[] splitLine;

        String line;
        try
        {
            fileStream = new FileInputStream(fileName);
            rdr = new InputStreamReader(fileStream);
            bufRdr = new BufferedReader(rdr);
            splitLine = new String[12];

            AssetInfo currAsset;

            //We ignore the first two lines. These lines contain metadata about the file and information, and are not needed.
            line = bufRdr.readLine();
            validateAssetInfo(line);
            line = bufRdr.readLine();

            line = bufRdr.readLine();
            while (line != null) {
                //As the imported file is a .csv, we can split the values by commas.
                //As the file is specific to this algorithm, we already know there are 12 different values in a line.
                //CITATION: Regex derived from https://stackabuse.com/regex-splitting-by-character-unless-in-quotes by Scott Robinson (2020)
                splitLine = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)"); //Regex splits values by commas, but leaves values with quotations together

                assetName = splitLine[2];
                cap = Double.parseDouble(splitLine[4]);
                volume = splitLine[8].replaceAll("\"|\\$|,| |\\?", "");
                price = Double.parseDouble(splitLine[5].replaceAll("\"|\\$|,| ", ""));
                change = Double.parseDouble(splitLine[10].replaceAll("%|>| ", ""));

                currAsset = new AssetInfo(assetName, splitLine[1], cap, price, volume, change);
                //Using some of the information, we create a new AssetInfo object, which will hold all the info about the asset in a single object.
                //If the asset already exists in the graph, we add the new object as its "value" parameter
                if (theGraph.hasVertex(assetName)) {
                    theGraph.setVertexValue(currAsset, assetName);
                }
                //If the asset DOES NOT exist, we create the asset.
                else {
                    theGraph.addVertex(assetName, currAsset);
                }
                line = bufRdr.readLine();
            }

            //The graph metadata is updated to show that a asset info file has been imported
            theGraph.getMetadata().setHasAssetinfo();
            theGraph.getMetadata().setAssetInfoFileName(fileName);

            fileStream.close(); //Once all file processing has been completed, the file is closed
        }
        catch(IOException e)
        {
            if(fileStream != null)
            {
                try
                {
                    fileStream.close();
                }
                catch(IOException ex2)
                {}
            }
            System.out.println("Error in file processing: " + e.getMessage());
        }
    }

    /******************
     * Function: Validate Trade Data
     * Purpose: Ensures the correct file is being parsed to the trade data parser
     * Author: Austin Bevacqua
     * Date: 29/10/20
     * ****************/
    private static boolean validateTradeData(String line){
        boolean valid = false;
        if (line.contains("\"priceChangePercent\":")) {
            valid = true;
        }
        else {
            throw new IllegalArgumentException("WRONG FILE: This is not a trade data file. Try tradedata.json");
        }
        return valid;
    }

    /******************
     * Function: Validate Asset Data
     * Purpose: Ensures the correct file is being parsed to the trade data parser
     * Author: Austin Bevacqua
     * Date: 29/10/20
     * ****************/
    private static boolean validateAssetData(String line){
        boolean valid = false;
        if (line.contains("\"timezone\":")) {
            valid = true;
        }
        else {
            throw new IllegalArgumentException("WRONG FILE: This is not a trade data file. Try assetdata.json");
        }
        return valid;
    }

    /******************
     * Function: Validate Asset Info
     * Purpose: Ensures the correct file is being parsed to the trade data parser
     * Author: Austin Bevacqua
     * Date: 29/10/20
     * ****************/
    private static boolean validateAssetInfo(String line){
        boolean valid = false;
        if (line.contains("#Source =")) {
            valid = true;
        }
        else {
            throw new IllegalArgumentException("WRONG FILE: This is not an asset ingo file. Try asset_info.csv");
        }
        return valid;
    }
}
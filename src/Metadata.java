import java.io.Serializable;
/********************
 Inner Graph Metadata Class
 Purpose: Contains metadata about the graph, stored in an object,
    including whether trade information has been stored, the name of imported files, ect...
 Date: 13/10/20
 Author: Austin Bevacqua
 ********************/
public class Metadata implements Serializable {
    private boolean hasAssets;
    private boolean hasTrades;
    private boolean hasAssetInfo;
    private String assetFileName;
    private String tradeFileName;
    private String assetInfoFileName;

    //CONSTRUCTORS
    /*****************
     Default Constructor
     *****************/
    public Metadata() {
        hasAssets = false;
        hasTrades = false;
        hasAssetInfo = false;
        assetFileName = "<NOT FOUND>";
        tradeFileName = "<NOT FOUND>";
        assetInfoFileName = "<NOT FOUND>";
    }

    //ACCESSORS
    /****************
     Has Assets
     **************/
    public boolean hasAssets() {
        return hasAssets;
    }

    /****************
     Has Trades
     **************/
    public boolean hasTrades() {
        return hasTrades;
    }

    /****************
     Has Asset info
     **************/
    public boolean hasAssetInfo() {
        return hasAssetInfo;
    }

    /****************
     Get Asset File Name
     **************/
    public String getAssetFileName() {
        return assetFileName;
    }

    /****************
     Get Trade File Name
     **************/
    public String getTradeFileName() {
        return tradeFileName;
    }

    /****************
     Get Asset Info File Name
     **************/
    public String getAssetInfoFileName() {
        return assetInfoFileName;
    }

    //MUTATORS
    /****************
     Set Has Assets
     **************/
    public void setHasAssets() {
        hasAssets = true;
    }

    /****************
     Set Has Trades
     **************/
    public void setHasTrades() {
        hasTrades = true;
    }

    /****************
     Set Asset Info File
     **************/
    public void setHasAssetinfo() {
        hasAssetInfo = true;
    }

    /****************
     Set Asset File Name
     **************/
    public void setAssetFileName(String inName) {
        assetFileName = inName;
    }

    /****************
     Set  Trade File Name
     **************/
    public void setTradeFileName(String inName) {
        tradeFileName = inName;
    }

    /****************
     Set Asset Info File Name
     **************/
    public void setAssetInfoFileName(String inName) {
        assetInfoFileName = inName;
    }
}

/***********************
 * Class: Asset Info
 * Purpose: An object which stores information regarding specific trades/edges. Used with trade Info json.
 * Author: Austin Bevacqua
 * Last Modified: 31/10/20
 **********************/
public class TradeInfo 
{
    //Private class fields
    private String symbol; //NAME
    private double priceChange; 
    private double priceChangePercent;
    private double weightedAvgPrice; //PRICE were gonna use, SORT
    private double volume; //SORT
    private int count; //SORT

    //CONSTRUCTORS    
    /********************
    Alternate constructor
     Purpose: Will construct a tradeInfo object
     Imports: tradeLine(Parsed .json string)
     Author: Austin Bevacqua
    ********************/
    public TradeInfo(String tradeLine)
    {
        String colonVal[] = new String[21];
        String split[] = new String[2];

        //The parsed .json string will be split into its individual values, seperated by commas
        colonVal =  tradeLine.split(",");
        //The algorithm will then iterate through every value, and store all relevant ones
        for (int i = 0; i < 21; i++) {
            //All quotation marks are removed so data can be parsed into doubles/ints if necessary
            split = colonVal[i].replaceAll("\"", "").split(":");
            switch(split[0]) {
                case "symbol":
                    symbol = split[1];
                break;
                case "weightedAvgPrice":
                    weightedAvgPrice = Double.parseDouble(split[1]);
                break;
                case "volume":
                    volume = Double.parseDouble(split[1]);
                break;
                case "priceChange":
                    priceChange = Double.parseDouble(split[1]);
                break;
                case "priceChangePercent":
                    priceChangePercent = Double.parseDouble(split[1]);
                break;
                case "count":
                    count = Integer.parseInt(split[1]);    
                break;
            }
        }

    }

    /********************
     Copy Constructor
     ********************/
    public TradeInfo(TradeInfo inTrade)
    {
        symbol = inTrade.getSymbol();
        priceChange = inTrade.getPriceChange();
        priceChangePercent = inTrade.getChangePercent();
        weightedAvgPrice = inTrade.getPrice();
        volume = inTrade.getVolume();
        count = inTrade.getCount();
    }

    //ACCESSORS
    /******************
    Get Symbol
    *******************/
    public String getSymbol() {
        return symbol;
    }

    /******************
    Get Price
    *******************/
    public double getPrice() {
        return weightedAvgPrice;
    }
    
    /******************
    Get Volume
    *******************/
    public double getVolume() {
        return volume;
    }
        
    /******************
    Get Count
    *******************/
    public int getCount() {
        return count;
    }
            
    /******************
    Get Price Change
    *******************/
    public double getPriceChange() {
        return priceChange;
    }
            
    /******************
    Get Price Change Percent
    *******************/
    public double getChangePercent() {
        return priceChangePercent;
    }

    //MUTATORS
    /**************
    Invert
     Purpose: Inverts all relevant data to calculate the inverse / opposite trade
     Author: Austin Bevacqua
    ***************/
    public void invert() {
        weightedAvgPrice = 1 / weightedAvgPrice;
        priceChangePercent*= -1;
        priceChange*= -1;
    }

    
}
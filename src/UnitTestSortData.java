/****************
 * Class: Sort Data Test Harness
 * Purpose: An interactive test harness that tests the functions of the SortData object
 * Author: Austin Bevacqua
 * Last Modified: 31/10/20
 ******************/

public class UnitTestSortData {
    public static void main(String[] args) {
        DSAGraph theGraph;
        int userselection;
        //Automatically imports all valid files
        theGraph = FileIO.readAssetData("assetdata.json");
        FileIO.readTradeData("tradedata.json", theGraph);
        FileIO.readAssetInfo("asset_info.csv", theGraph);

        do {
            //Allows the user to view all sorted data until they wish to exit
            //Tests ALL functions in the SortData class if all options are viewed
            userselection = UserInterface.userInput(0, 9, "\nSort asset data by:\n(1) Price\n(2) Price Change %\n(3) Volume\n(4) Market Cap\nSort Trade data by:\n(5) Price\n(6) Price Change\n(7) Price Change %\n(8) Volume\n(9) Count\n(0) None");
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
                case 5:
                    SortData.tradebyPrice(theGraph);
                    break;
                case 6:
                    SortData.tradebyPriceChange(theGraph);
                    break;
                case 7:
                    SortData.tradebyPriceChangePercent(theGraph);
                    break;
                case 8:
                    SortData.tradebyVolume(theGraph);
                    break;
                case 9:
                    SortData.tradebyCount(theGraph);
                    break;
            }
        }while (userselection != 0);
    }
}

/****************
 * Class: Trade Info Test Harness
 * Purpose: Tests the functions of the TradeInfo object
 * Author: Austin Bevacqua
 * Last Modified: 31/10/20
 ******************/
public class TradeInfoTestHarness {
    public static void main(String[] args) {
        String tradeDataSample = "{\"symbol\":\"ETHBTC\",\"priceChange\":\"0.00081300\",\"priceChangePercent\":\"2.494\",\"weightedAvgPrice\":\"0.03345805\",\"prevClosePrice\":\"0.03260100\",\"lastPrice\":\"0.03341300\",\"lastQty\":\"2.41200000\",\"bidPrice\":\"0.03341200\",\"bidQty\":\"24.33500000\",\"askPrice\":\"0.03341300\",\"askQty\":\"21.58800000\",\"openPrice\":\"0.03260000\",\"highPrice\":\"0.03380100\",\"lowPrice\":\"0.03259200\",\"volume\":\"287561.14900000\",\"quoteVolume\":\"9621.23438224\",\"openTime\":1602509401148,\"closeTime\":1602595801148,\"firstId\":197133936,\"lastId\":197260051,\"count\":126116}";
        int testsPassed = 0;
        TradeInfo newInfo = null;

        for (String retval : tradeDataSample.split("\\{|\\}")) {
            //Some of the split strings will be blank or ",".
            //A quick check is done to ensure the split line is not either of these values.
            if (!retval.equals("") && (!retval.equals(","))) {
                newInfo = new TradeInfo(retval);
            }
        }

        //If the object did not initalise properly, a null pointer excpetion will be thrown.
        try {
            //GET SYMBOL TEST
            if(newInfo.getSymbol().equals("ETHBTC")) {
                testsPassed++;
                System.out.println("PASSED getSymbol test");
            }
            else {
                System.out.println("FAILED getSymbol test");
            }

            //GET COUNT TEST
            if(newInfo.getCount() == 126116) {
                testsPassed++;
                System.out.println("PASSED getCount test");
            }
            else {
                System.out.println("FAILED getCount test");
            }

            //GET PRICE CHANGE TEST
            if(newInfo.getPriceChange() == 0.00081300) {
                testsPassed++;
                System.out.println("PASSED getPriceChange test");
            }
            else {
                System.out.println("FAILED getPriceChange test");
            }

            //GET PRICE CHANGE PERCENT TEST
            if(newInfo.getChangePercent() == 2.494) {
                testsPassed++;
                System.out.println("PASSED getChangePercent test");
            }
            else {
                System.out.println("FAILED getChangePercent test");
            }

            //GET PRICE TEST
            if(newInfo.getPrice() == 0.03345805) {
                testsPassed++;
                System.out.println("PASSED getPrice test");
            }
            else {
                System.out.println("FAILED getPrice test");
            }

            //GET VOLUME TEST
            if(newInfo.getVolume() == 287561.14900000) {
                testsPassed++;
                System.out.println("PASSED getVolume test");
            }
            else {
                System.out.println("FAILED getVolume test");
            }

            //OVERALL SCORE
            System.out.println("PASSED " + testsPassed + "/6 tests!");

        }
        catch (NullPointerException e) {
            System.out.println("FAILED Constructor test");
        }
    }
}

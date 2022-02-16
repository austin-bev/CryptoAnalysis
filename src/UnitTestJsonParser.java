/****************
 * Class: JsonParser Test Harness
 * Purpose: Tests the functions of the static JsonParser class
 * Author: Austin Bevacqua
 * Last Modified: 31/10/20
 ******************/

public class UnitTestJsonParser {
    public static void main(String[] args) {
        String tradeDataSample = "{\"symbol\":\"ETHBTC\",\"priceChange\":\"0.00081300\",\"priceChangePercent\":\"2.494\",\"weightedAvgPrice\":\"0.03345805\",\"prevClosePrice\":\"0.03260100\",\"lastPrice\":\"0.03341300\",\"lastQty\":\"2.41200000\",\"bidPrice\":\"0.03341200\",\"bidQty\":\"24.33500000\",\"askPrice\":\"0.03341300\",\"askQty\":\"21.58800000\",\"openPrice\":\"0.03260000\",\"highPrice\":\"0.03380100\",\"lowPrice\":\"0.03259200\",\"volume\":\"287561.14900000\",\"quoteVolume\":\"9621.23438224\",\"openTime\":1602509401148,\"closeTime\":1602595801148,\"firstId\":197133936,\"lastId\":197260051,\"count\":126116}";
        String assetDataSample = "{\"timezone\":\"UTC\",\"serverTime\":1602595789836,\"rateLimits\":[{\"rateLimitType\":\"REQUEST_WEIGHT\",\"interval\":\"MINUTE\",\"intervalNum\":1,\"limit\":1200},{\"rateLimitType\":\"ORDERS\",\"interval\":\"SECOND\",\"intervalNum\":10,\"limit\":100},{\"rateLimitType\":\"ORDERS\",\"interval\":\"DAY\",\"intervalNum\":1,\"limit\":200000}],\"exchangeFilters\":[],\"symbols\":[{\"symbol\":\"ETHBTC\",\"status\":\"TRADING\",\"baseAsset\":\"ETH\",\"baseAssetPrecision\":8,\"quoteAsset\":\"BTC\",\"quotePrecision\":8,\"quoteAssetPrecision\":8,\"baseCommissionPrecision\":8,\"quoteCommissionPrecision\":8,\"orderTypes\":[\"LIMIT\",\"LIMIT_MAKER\",\"MARKET\",\"STOP_LOSS_LIMIT\",\"TAKE_PROFIT_LIMIT\"],\"icebergAllowed\":true,\"ocoAllowed\":true,\"quoteOrderQtyMarketAllowed\":true,\"isSpotTradingAllowed\":true,\"isMarginTradingAllowed\":true,\"filters\":[{\"filterType\":\"PRICE_FILTER\",\"minPrice\":\"0.00000100\",\"maxPrice\":\"100000.00000000\",\"tickSize\":\"0.00000100\"},{\"filterType\":\"PERCENT_PRICE\",\"multiplierUp\":\"5\",\"multiplierDown\":\"0.2\",\"avgPriceMins\":5},{\"filterType\":\"LOT_SIZE\",\"minQty\":\"0.00100000\",\"maxQty\":\"100000.00000000\",\"stepSize\":\"0.00100000\"},{\"filterType\":\"MIN_NOTIONAL\",\"minNotional\":\"0.00010000\",\"applyToMarket\":true,\"avgPriceMins\":5},{\"filterType\":\"ICEBERG_PARTS\",\"limit\":10},{\"filterType\":\"MARKET_LOT_SIZE\",\"minQty\":\"0.00000000\",\"maxQty\":\"8306.52107014\",\"stepSize\":\"0.00000000\"},{\"filterType\":\"MAX_NUM_ALGO_ORDERS\",\"maxNumAlgoOrders\":5},{\"filterType\":\"MAX_NUM_ORDERS\",\"maxNumOrders\":200}],\"permissions\":[\"SPOT\",\"MARGIN\"]}}";
        String assetLine;
        DSAGraph newGraph;
        TradeInfo moreinfo;
        int testsPassed = 0;

        assetLine = JsonParser.formatAssetData(assetDataSample);

        //Test format asset data
        if (assetLine.contains("{\"timezone\":\"UTC\"")) {
            System.out.println("FAILED Test Format Asset Data");
        }
        else {
            testsPassed++;
            System.out.println("PASSED Test Format Asset Data");
        }
        newGraph = JsonParser.parseAssetData(assetLine);
        //Test parse asset data
        if ((newGraph.getVertexCount() == 2) && (newGraph.hasVertex("BTC"))) {
            testsPassed++;
            System.out.println("PASSED Test Parse Asset Data");
        }
        else {
            System.out.println("FAILED Test Parse Asset Data");
        }

        JsonParser.parseTradeData(tradeDataSample.replaceAll("\\[|\\]", ""), newGraph);
        //Test parse trade data
        try {
            moreinfo = (TradeInfo) newGraph.getEdge("ETHBTC").getValue();
            if (moreinfo.getCount() == 126116) {
                testsPassed++;
                System.out.println("PASSED Test Parse Trade Data");
            } else {
                System.out.println("FAILED Test Parse Trade Data");
            }
        }
        catch(NullPointerException e) {
            System.out.println("FAILED Test Parse Trade Data");
        }

        //Overall score
        System.out.println("PASSED " + testsPassed + "/3 Tests!");
    }
}

/******************
 * Class: Route Data
 * Purpose: An object which contains "routes".
 *      Used by DSAGraph to store trade routes when performing path finding algorithms such as findPaths()
 * Author: Austin Bevacqua
 * Date: 30/10/20
 *******************/
public class RouteData {
    //Private class fields
    public String tradeRoute;
    public double tradePrice;

    //CONSTRUCTOR
    /******************
     * Alternate Constructor
     * Purpose: Constructs a TradeData object with the imported parameters
     * Author: Austin Bevacqua
     * Date: 30/10/20
     ******************/
    public RouteData(String inRoute, double inPrice) {
        tradeRoute = inRoute;
        tradePrice = inPrice;
    }

    //ACCESSORS
    public String getRoute() {
        return tradeRoute;
    }

    public double getPrice() {
        return tradePrice;
    }
}

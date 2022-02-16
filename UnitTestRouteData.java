/****************
 * Class: Route Data Test Harness
 * Purpose: Tests the functions of the RouteData object
 * Author: Austin Bevacqua
 * Last Modified: 31/10/20
 ******************/
public class UnitTestRouteData {
    public static void main(String[] args) {
        int testsPassed = 0;
        RouteData data = new RouteData("BTC->ETH", 300);

        //GET PRICE TEST
        if(data.getPrice() == 300) {
            testsPassed++;
            System.out.println("PASSED getPrice test");
        }
        else {
            System.out.println("FAILED getPrice test");
        }
        //GET ROUTE TEST
        if(data.getRoute().equals("BTC->ETH")) {
            testsPassed++;
            System.out.println("PASSED getRoute test");
        }
        else {
            System.out.println("FAILED getRoute test");
        }

        //Overall score
        System.out.println("PASSED " + testsPassed + "/2 tests!");
    }
}

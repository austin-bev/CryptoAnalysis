/****************
 * Class: Asset Info Test Harness
 * Purpose: Tests the functions of the AssetInfo object
 * Author: Austin Bevacqua
 * Last Modified: 31/10/20
 ******************/
public class UnitTestAssetInfo {
    public static void main(String[] args) {
        int testsPassed = 0;
        AssetInfo assetOne, assetTwo;

        assetOne = new AssetInfo("ASSETONE", "ONE", 400000.2, 40302.1, "3201.22", -3.2);
        assetTwo = new AssetInfo("ASSETTWO", "TWO", 696969, 420420, "", 43.2);

        //GET NAME TEST
        if((assetOne.getName().equals("ASSETONE")) && (assetTwo.getName().equals("ASSETTWO"))) {
            testsPassed++;
            System.out.println("PASSED getName test");
        }
        else {
            System.out.println("FAILED getName test");
        }

        //GET LABEL TEST
        if((assetOne.getLabel().equals("ONE")) && (assetTwo.getLabel().equals("TWO"))) {
            testsPassed++;
            System.out.println("PASSED getLabel test");
        }
        else {
            System.out.println("FAILED getLabel test");
        }

        //GET CAP TEST
        if((assetOne.getCap() == 400000.2) && (assetTwo.getCap() == 696969)) {
            testsPassed++;
            System.out.println("PASSED getCap test");
        }
        else {
            System.out.println("FAILED getCap test");
        }

        //GET PRICE TEST
        if((assetOne.getPrice() == 40302.1) && (assetTwo.getPrice() == 420420)) {
            testsPassed++;
            System.out.println("PASSED getPrice test");
        }
        else {
            System.out.println("FAILED getPrice test");
        }

        //GET VOLUME TEST
        if((assetOne.getVolume() == 3201.22) && (assetTwo.getVolume() == 0)) {
            testsPassed++;
            System.out.println("PASSED getVolume test");
        }
        else {
            System.out.println("FAILED getVolume test");
        }

        //GET CHANGE TEST
        if((assetOne.getChange() == -3.2) && (assetTwo.getChange() == 43.2)) {
            testsPassed++;
            System.out.println("PASSED getChange test");
        }
        else {
            System.out.println("FAILED getChange test");
        }

        //Overall score
        System.out.println("PASSED " + testsPassed + "/6 tests!");

;    }
}

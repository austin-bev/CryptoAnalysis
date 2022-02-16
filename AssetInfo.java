/***********************
 * Class: Asset Info
 * Purpose: An object which stores information regarding specific assets. Used with Asset Info csv.
 * Author: Austin Bevacqua
 * Last Modified: 31/10/20
 **********************/

public class AssetInfo {
    //Private classfields
    public String name;
    public String label;
    public double marketCap;
    public double price;
    public double volume;
    public double change;

    //CONSTRUCTORS
    /**************
     * Alternate Constructor
     * Purpose: Creates a new AssetInfo object with imported parameters
     * Author: Austin Bevacqua
     * Date: 29/10/20
     ****************/
    public AssetInfo(String inName, String inLabel, double inCap, double inPrice, String inVol, double inChange) {
        name = inName;
        label = inLabel;
        marketCap = inCap;
        price = inPrice;
        //Some of the data, once parsed, ends up as blank strings.
        //If this happens, we just set the volume to 0 as no data exists.
        if (inVol.equals(""))
            volume = 0;
        else
            volume = Double.parseDouble(inVol);
        change = inChange;
    }

    //ACCESSORS
    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }

    public double getCap() {
        return marketCap;
    }

    public double getPrice() {
        return price;
    }

    public double getVolume() {
        return volume;
    }

    public double getChange() {
        return change;
    }
}

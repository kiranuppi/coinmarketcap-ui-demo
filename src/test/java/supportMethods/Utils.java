package supportMethods;

public class Utils {

    public static NumberRange getMarketCapRange(String marketCapRangeString) {

        NumberRange marketCapRange;

        if (marketCapRangeString.equals("$1 Billion+")) {
            marketCapRange = new NumberRange(1000000000, Double.MAX_VALUE);
        } else if (marketCapRangeString.equals("$100 Million - $1 Billion")) {
            marketCapRange = new NumberRange(100000000, 1000000000);
        } else {
            throw new RuntimeException("Cannot construct PriceValueRange. Not supported marketCapRangeString: " + marketCapRangeString);
        }

        return marketCapRange;
    }

    public static NumberRange getPriceValueRange(String priceRangeString) {

        NumberRange priceRange;

        if (priceRangeString.equals("$100+")) {
            priceRange = new NumberRange(100, Double.MAX_VALUE);
        } else if (priceRangeString.equals("$1 - $100")) {
            priceRange = new NumberRange(1, 100);
        } else {
            throw new RuntimeException("Cannot construct PriceValueRange. Not supported priceRangeString: " + priceRangeString);
        }

        return priceRange;
    }


    public static double get24HrsVolumeValue(String voluemeString) {

        double volume = 0;
        if (voluemeString.equals("$10 Million+")) {
            volume = 10000000;
        } else if (voluemeString.equals("$1 Million+")) {
            volume = 1000000;
        } else if (voluemeString.equals("$100k+")) {
            volume = 100000;
        } else {
            throw new RuntimeException("Cannot construct PriceValueRange. Not supported voluemeString: " + voluemeString);
        }
        return volume;
    }

    public static double getNumberFromFormattedNumber(String numberString) {
        numberString = numberString.replaceAll("[^\\d.]", "");
        return Double.parseDouble(numberString);
    }
}

package christmas.utils;

import java.text.DecimalFormat;

public class Converter {

    public static int toInteger(String input) {
        return Integer.parseInt(input);
    }

    public static String[] splitByMinusOperation(String input) {
        return input.split(Constants.MINUS_OPERATOR);
    }

    public static String toThousandWonFormmat(int value) {
        DecimalFormat decimalFormat = new DecimalFormat(Constants.AMOUNT_FORMAT);
        return decimalFormat.format(value);
    }
}

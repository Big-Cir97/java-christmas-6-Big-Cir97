package christmas.utils;

import java.util.List;

public class Converter {

    public static int toInteger(String input) {
        return Integer.parseInt(input);
    }

    public static String[] splitByMinusOperation(String input) {
        return input.split(Constants.MINUS_OPERATOR);
    }
}
package christmas.validation;

import static christmas.utils.Constants.COMMA;

import christmas.utils.Constants;
import christmas.utils.Converter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputValidator {

    private static final Pattern NUMBER_PATTERN = Pattern.compile("^[0-9]*$");

    public void validateVisitDay(String input) {
        input = removeBlank(input);
        validateDayNumeric(input);
    }

    public Map<String, Integer> validateOrderMenu(String input) {
        input = removeBlank(input);
        List<String> inputSplit = splitComma(input);
        return splitMinusOperator(inputSplit);
    }

    private void validateDayNumeric(String input) {
        if (!NUMBER_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private void validateQuantityNumeric(String input) {
        if (!NUMBER_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private List<String> splitComma(String input) {
        return Arrays.stream(input.split(COMMA))
                .collect(Collectors.toList());
    }

    private Map<String, Integer> splitMinusOperator(List<String> inputSplit) {
        Map<String, Integer> inputStore = new HashMap<>();
        for (String input : inputSplit) {
            String[] minusSplit = input.split(Constants.MINUS_OPERATOR);
            validateQuantityNumeric(minusSplit[1]);
            inputStore.put(minusSplit[0], Converter.toInteger(minusSplit[1]));
        }

        return inputStore;
    }

    private String removeBlank(String input) {
        return input.replaceAll(" ", "");
    }
}

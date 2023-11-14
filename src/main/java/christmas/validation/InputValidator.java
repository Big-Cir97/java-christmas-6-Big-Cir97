package christmas.validation;

import static christmas.utils.Constants.COMMA;

import christmas.utils.Converter;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputValidator {

    private static final Pattern NUMBER_PATTERN = Pattern.compile("^[0-9]*$");

    public void validateVisitDay(String input) {
        input = removeBlank(input);
        validateDayNumeric(input);
    }

    public List<String> validateOrderMenu(String input) {
        input = removeBlank(input);
        List<String> inputSplit = splitComma(input);
        splitMinusOperator(inputSplit);
        return inputSplit;
    }

    private void validateDayNumeric(String input) {
        if (!NUMBER_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private List<String> splitComma(String input) {
        return Arrays.stream(input.split(COMMA))
                .collect(Collectors.toList());
    }

    private void splitMinusOperator(List<String> inputSplit) {
        for (String input : inputSplit) {
            String[] minusSplit = Converter.splitByMinusOperation(input);
            validateQuantityNumeric(minusSplit[1]);
        }
    }

    private void validateQuantityNumeric(String input) {
        if (!NUMBER_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private String removeBlank(String input) {
        return input.replaceAll(" ", "");
    }
}

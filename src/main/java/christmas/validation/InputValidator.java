package christmas.validation;

import java.util.regex.Pattern;

public class InputValidator {

    private static final Pattern NUMBER_PATTERN = Pattern.compile("^[0-9]*$");

    public void validateVisitDay(String input) {
        validateNumeric(input);
    }

    private void validateNumeric(String input) {
        if (!NUMBER_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }
}

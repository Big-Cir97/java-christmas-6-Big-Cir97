package christmas.validation;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputValidatorTest {

    private InputValidator inputValidator;

    @BeforeEach
    void setup() {
        inputValidator = new InputValidator();
    }

    @Test
    @DisplayName("숫자가 아니면 예외를 발생시킨다.")
    void testOnlyNumericPattern() {
        String input = "p";

        assertThatThrownBy(() -> inputValidator.validateVisitDay(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("주문 수량이 숫자가 아니면 예외를 발생시킨다.")
    void testOnlyNumericOrderQuantity() {
        String input = "초코케이크-숫자아님";

        assertThatThrownBy(() -> inputValidator.validateOrderMenu(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}
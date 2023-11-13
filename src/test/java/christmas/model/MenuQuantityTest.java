package christmas.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuQuantityTest {

    @Test
    @DisplayName("주문 개수가 1 미만이면 예외를 발생시킨다.")
    void testMinCount() {
        int minQuantity = 0;

        assertThatThrownBy(() -> new MenuQuantity(minQuantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("주문 개수가 20 초과이면 예외를 발생시킨다.")
    void testMaxCount() {
        int maxQuantity = 21;

        assertThatThrownBy(() -> new MenuQuantity(maxQuantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("[ERROR] 주문 수량은 최대 %d개 입니다. 다시 입력해 주세요.", maxQuantity));
    }
}
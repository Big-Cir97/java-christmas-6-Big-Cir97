package christmas.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderCountTest {

    @Test
    @DisplayName("주문 개수가 1 미만이면 예외를 발생시킨다.")
    void testMinCount() {
        int minCount = 0;

        assertThatThrownBy(() -> new OrderCount(minCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("주문 수량은 최소 1개 입니다.");
    }

    @Test
    @DisplayName("주문 개수가 20 초과이면 예외를 발생시킨다.")
    void testMaxCount() {
        int maxCount = 21;

        assertThatThrownBy(() -> new OrderCount(maxCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("주문 수량은 최대 20개 입니다.");
    }
}
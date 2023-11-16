package christmas.model.order;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuNameTest {

    @Test
    @DisplayName("존재하지 않는 메뉴이면 예외를 발생시킨다.")
    void testHasName() {
        String menu = "똥카레";

        assertThatThrownBy(() -> new MenuName(menu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}
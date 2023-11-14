package christmas.model.calendar;

import static christmas.exception.ErrorType.INVALID_DAY_OUT_OF_RANGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChristmasGeneralEventCalendarTest {

    @Test
    @DisplayName("방문 날짜와 첫 날의 차이를 계산한다.")
    void testGapDay() {
        int visitDay = 25;
        ChristmasEventCalendar christmasEventCalendar = new ChristmasEventCalendar(visitDay);

        int expected = 24;

        assertThat(christmasEventCalendar.calculateVisitDayFromStart()).isEqualTo(expected);
    }

    @Test
    @DisplayName("크리스마스가 아닌 날에 ChristmasEventCalendar 객체 시 예외를 발생시킨다.")
    void testCheckChristmas() {
        int visitDay = 26;

        assertThatThrownBy(() -> new ChristmasEventCalendar(visitDay))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }
}
package christmas.model.calendar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GeneralEventCalendarTest {

    @Test
    @DisplayName("지정 날짜는 주말이 아니다.")
    void testDayNotWeekend() {
        GeneralEventCalendar visitDay = new GeneralEventCalendar(25);
        boolean actual = visitDay.isWeekend();

        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("달력에 별이 있는지 확인한다.")
    void testHasStarDay() {
        List<Integer> hasStarDays = List.of(3, 10, 17, 24, 31);

        for (int i = 0; i < hasStarDays.size(); i++) {
            GeneralEventCalendar generalEventCalendar = new GeneralEventCalendar(hasStarDays.get(i));
            assertThat(generalEventCalendar.isSpecialDay()).isTrue();
        }
    }

    @Test
    @DisplayName("존재하지 않은 날짜는 예외를 발생시킨다.")
    void testExistDay() {
        int day = 32;
        assertThatThrownBy(() -> new GeneralEventCalendar(day))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }
}
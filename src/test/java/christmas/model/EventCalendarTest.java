package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.model.calendar.EventCalendar;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventCalendarTest {

    @Test
    @DisplayName("지정 날짜는 주말이 아니다.")
    void testDayNotWeekend() {
        EventCalendar visitDay = new EventCalendar(25);
        boolean actual = visitDay.isWeekDays();

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("달력에 별이 있는지 확인한다.")
    void testHasStarDay() {
        List<Integer> hasStarDays = List.of(3, 10, 17, 24, 31);

        for (int i = 0; i < hasStarDays.size(); i++) {
            EventCalendar eventCalendar = new EventCalendar(hasStarDays.get(i));
            assertThat(eventCalendar.isSpecialDay()).isTrue();
        }
    }

    @Test
    @DisplayName("존재하지 않은 날짜는 예외를 발생시킨다.")
    void testExistDay() {
        int day = 32;
        assertThatThrownBy(() -> new EventCalendar(day))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }
}
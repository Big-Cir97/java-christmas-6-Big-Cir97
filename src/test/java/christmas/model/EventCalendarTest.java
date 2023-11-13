package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.calendar.EventCalendar;
import org.assertj.core.api.Assertions;
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
}
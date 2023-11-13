package christmas.model.calendar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalendarFactoryTest {

    @Test
    @DisplayName("방문 날짜가 26일 이상이면 GeneralEventCalendar를 생성한다.")
    void testCreateGeneralEventCalendar() {
        int visitDay = 31;

        assertThat(CalendarFactory.createCalendar(visitDay))
                .isInstanceOf(GeneralEventCalendar.class);
    }

    @Test
    @DisplayName("방문 날짜가 1일과 25일 사이이면 ChristmasEventCalendar를 생성한다.")
    void testCreateChristmasEventCalendar() {
        int visitDay = 11;

        assertThat(CalendarFactory.createCalendar(visitDay))
                .isInstanceOf(ChristmasEventCalendar.class);
    }
}
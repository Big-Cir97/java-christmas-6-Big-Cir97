package christmas.model.calendar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
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


}
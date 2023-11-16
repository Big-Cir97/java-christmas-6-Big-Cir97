package christmas.model.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.calendar.Calendar;
import christmas.model.calendar.CalendarFactory;
import christmas.model.calendar.ChristmasEventCalendar;
import christmas.model.calendar.GeneralEventCalendar;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChristmasDiscountTest {

    @Test
    @DisplayName("크리스마스 디데이 할인을 받는다.")
    void testDiscountInChristmasDays() {
        int visitDay = 25;
        Calendar calendar = CalendarFactory.createCalendar(visitDay);
        ChristmasDiscount christmasDiscount = new ChristmasDiscount(calendar);

        int expected = 3_400;
        assertThat(christmasDiscount.calculateDiscount()).isEqualTo(expected);
    }

    @Test
    @DisplayName("크리스마스 디데이 할인을 받지 못한다.")
    void testNonDiscountInChristmasDays() {
        int visitDay = 27;

        assertThat(CalendarFactory.createCalendar(visitDay))
                .isInstanceOf(GeneralEventCalendar.class);

        assertThat(CalendarFactory.createCalendar(visitDay))
                .isNotInstanceOf(ChristmasEventCalendar.class);
    }
}
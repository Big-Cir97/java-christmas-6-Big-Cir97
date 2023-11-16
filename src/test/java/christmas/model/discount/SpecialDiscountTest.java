package christmas.model.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.calendar.Calendar;
import christmas.model.calendar.CalendarFactory;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SpecialDiscountTest {

    @Test
    @DisplayName("달력에 별이 있으면 특별 할인을 받는다.")
    void testDiscountSpecial() {
        List<Integer> hasStartDays = List.of(3, 10, 17, 24, 25, 31);

        for (int day : hasStartDays) {
            Calendar calendar = CalendarFactory.createCalendar(day);
            SpecialDiscount specialDiscount = new SpecialDiscount(calendar);
            int expected = 1_000;

            assertThat(specialDiscount.calculateDiscount()).isEqualTo(expected);
        }
    }

    @Test
    @DisplayName("달력에 별이 있으면 특별 할인을 받는다.")
    void testNonDiscountSpecial() {
        List<Integer> notStartDays = List.of(1, 2, 4, 5, 6, 7, 8, 9, 11, 12, 13, 14, 15, 16,
                18, 19, 20, 21, 22, 23, 26, 27, 28, 29, 30);

        for (int day : notStartDays) {
            Calendar calendar = CalendarFactory.createCalendar(day);
            SpecialDiscount specialDiscount = new SpecialDiscount(calendar);
            int expected = 0;

            assertThat(specialDiscount.calculateDiscount()).isEqualTo(expected);
        }
    }
}
package christmas.model.calendar;

import static christmas.enums.CalendarNumber.CHRISTMAS;

import christmas.enums.CalendarNumber;

public class CalendarFactory {

    public static Calendar createCalendar(int day) {
        if (day <= CHRISTMAS.getNumber()) {
            return new ChristmasEventCalendar(day);
        }

        return new EventCalendar(day);
    }
}

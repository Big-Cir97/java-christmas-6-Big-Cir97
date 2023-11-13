package christmas.model.calendar;

import static christmas.enums.CalendarNumber.CHRISTMAS;

public class CalendarFactory {

    public static Calendar createCalendar(int day) {
        if (day <= CHRISTMAS.getNumber()) {
            return new ChristmasEventCalendar(day);
        }

        return new GeneralEventCalendar(day);
    }
}

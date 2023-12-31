package christmas.model.calendar;

import static christmas.model.calendar.enums.CalendarDate.CHRISTMAS;

public class CalendarFactory {

    public static Calendar createCalendar(int day) {
        if (day <= CHRISTMAS.getNumber()) {
            return new ChristmasEventCalendar(day);
        }

        return new GeneralEventCalendar(day);
    }
}

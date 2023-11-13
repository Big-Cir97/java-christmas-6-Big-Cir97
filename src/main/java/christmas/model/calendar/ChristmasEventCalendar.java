package christmas.model.calendar;

import static christmas.enums.CalendarNumber.START_DAY;

import christmas.enums.CalendarNumber;

public class ChristmasEventCalendar extends EventCalendar {
    public ChristmasEventCalendar(int day) {
        super(day);
    }

    public int calculateVisitDayFromStart() {
        int visitDay = getDayOfMonth();
        return visitDay - START_DAY.getNumber();
    }
}

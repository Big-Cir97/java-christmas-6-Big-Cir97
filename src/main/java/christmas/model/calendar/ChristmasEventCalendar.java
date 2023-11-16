package christmas.model.calendar;

import static christmas.exception.ErrorType.INVALID_DAY_OUT_OF_RANGE;
import static christmas.model.calendar.enums.CalendarDate.CHRISTMAS;
import static christmas.model.calendar.enums.CalendarDate.EVENT_MONTH;
import static christmas.model.calendar.enums.CalendarDate.EVENT_YEAR;
import static christmas.model.calendar.enums.CalendarDate.FRIDAY;
import static christmas.model.calendar.enums.CalendarDate.SATURDAY;
import static christmas.model.calendar.enums.CalendarDate.START_DAY;
import static christmas.model.calendar.enums.CalendarDate.SUNDAY;

import java.time.LocalDate;

public class ChristmasEventCalendar extends EventCalendar {

    public ChristmasEventCalendar(int visitDay) {
        super(visitDay);
    }

    protected void validateDayRange(int visitDay) {
        if (visitDay < START_DAY.getNumber() || visitDay > CHRISTMAS.getNumber()) {
            throw new IllegalArgumentException(INVALID_DAY_OUT_OF_RANGE.getMessage());
        }
    }

    public int calculateVisitDayFromStart() {
        int visitDay = getDayOfMonth();
        return visitDay - START_DAY.getNumber();
    }
}

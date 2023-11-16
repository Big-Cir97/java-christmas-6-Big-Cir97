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
import java.time.YearMonth;

public class GeneralEventCalendar extends EventCalendar {

    public GeneralEventCalendar(int visitDay) {
        super(visitDay);
    }

    protected void validateDayRange(int visitDay) {
        YearMonth yearMonth = YearMonth.of(EVENT_YEAR.getNumber(), EVENT_MONTH.getNumber());
        if (visitDay < START_DAY.getNumber() || visitDay > yearMonth.atEndOfMonth().getDayOfMonth()) {
            throw new IllegalArgumentException(INVALID_DAY_OUT_OF_RANGE.getMessage());
        }
    }
}

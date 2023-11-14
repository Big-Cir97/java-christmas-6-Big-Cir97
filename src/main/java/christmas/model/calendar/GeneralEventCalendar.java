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

public class GeneralEventCalendar implements Calendar {

    private final LocalDate eventDate;

    public GeneralEventCalendar(int visitDay) {
        validate(visitDay);
        this.eventDate = LocalDate.of(EVENT_YEAR.getNumber(), EVENT_MONTH.getNumber(), visitDay);
    }

    @Override
    public int getDayOfMonth() {
        return eventDate.getDayOfMonth();
    }

    public boolean isWeekend() {
        int visitDay = getDayOfWeek();
        return visitDay == FRIDAY.getNumber() || visitDay == SATURDAY.getNumber();
    }

    public boolean isSpecialDay() {
        return isChristmas() || isSunday();
    }

    private void validate(int visitDay) {
        validateDayRange(visitDay);
    }

    private void validateDayRange(int visitDay) {
        YearMonth yearMonth = YearMonth.of(EVENT_YEAR.getNumber(), EVENT_MONTH.getNumber());
        if (visitDay < START_DAY.getNumber() || visitDay > yearMonth.atEndOfMonth().getDayOfMonth()) {
            throw new IllegalArgumentException(INVALID_DAY_OUT_OF_RANGE.getMessage());
        }
    }

    private boolean isChristmas() {
        return getDayOfMonth() == CHRISTMAS.getNumber();
    }

    private boolean isSunday() {
        return getDayOfWeek() == SUNDAY.getNumber();
    }

    private int getDayOfWeek() {
        return eventDate.getDayOfWeek().getValue();
    }
}

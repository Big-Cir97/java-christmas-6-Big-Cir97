package christmas.model.calendar;

import static christmas.model.calendar.enums.CalendarDate.CHRISTMAS;
import static christmas.model.calendar.enums.CalendarDate.EVENT_MONTH;
import static christmas.model.calendar.enums.CalendarDate.EVENT_YEAR;
import static christmas.model.calendar.enums.CalendarDate.FRIDAY;
import static christmas.model.calendar.enums.CalendarDate.SATURDAY;
import static christmas.model.calendar.enums.CalendarDate.SUNDAY;

import java.time.LocalDate;

public abstract class EventCalendar implements Calendar {

    protected final LocalDate eventDate;

    public EventCalendar(int visitDay) {
        validate(visitDay);
        this.eventDate = LocalDate.of(EVENT_YEAR.getNumber(), EVENT_MONTH.getNumber(), visitDay);
    }

    protected abstract void validateDayRange(int visitDay);

    @Override
    public boolean isWeekend() {
        int visitDay = getDayOfWeek();
        return visitDay == FRIDAY.getNumber() || visitDay == SATURDAY.getNumber();
    }

    @Override
    public boolean isSpecialDay() {
        return isChristmas() || isSunday();
    }

    @Override
    public int getDayOfMonth() {
        return eventDate.getDayOfMonth();
    }

    private void validate(int visitDay) {
        validateDayRange(visitDay);
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

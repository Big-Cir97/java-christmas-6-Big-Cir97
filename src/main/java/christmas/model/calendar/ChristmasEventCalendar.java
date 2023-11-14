package christmas.model.calendar;

import static christmas.model.calendar.enums.CalendarDate.CHRISTMAS;
import static christmas.model.calendar.enums.CalendarDate.EVENT_MONTH;
import static christmas.model.calendar.enums.CalendarDate.EVENT_YEAR;
import static christmas.model.calendar.enums.CalendarDate.FRIDAY;
import static christmas.model.calendar.enums.CalendarDate.SATURDAY;
import static christmas.model.calendar.enums.CalendarDate.START_DAY;
import static christmas.model.calendar.enums.CalendarDate.SUNDAY;

import java.time.LocalDate;

public class ChristmasEventCalendar implements Calendar {

    private final LocalDate eventDate;

    public ChristmasEventCalendar(int visitDay) {
        validate(visitDay);
        this.eventDate = LocalDate.of(EVENT_YEAR.getNumber(), EVENT_MONTH.getNumber(), visitDay);
    }

    @Override
    public int getDayOfMonth() {
        return eventDate.getDayOfMonth();
    }

    public boolean isWeekend() {
        int visitDay = getDayOfWeek();
        if (visitDay == FRIDAY.getNumber() || visitDay == SATURDAY.getNumber()) {
            return true;
        }
        return false;
    }

    public boolean isSpecialDay() {
        if (isChristmas() || isSunday()) {
            return true;
        }

        return false;
    }

    public int calculateVisitDayFromStart() {
        int visitDay = getDayOfMonth();
        return visitDay - START_DAY.getNumber();
    }

    private void validate(int visitDay) {
        validateDayRange(visitDay);
    }

    private void validateDayRange(int visitDay) {
        if (visitDay < START_DAY.getNumber() || visitDay > CHRISTMAS.getNumber()) {
            throw new IllegalArgumentException("[ERROR] 크리스마스는 26일 이전입니다.");
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

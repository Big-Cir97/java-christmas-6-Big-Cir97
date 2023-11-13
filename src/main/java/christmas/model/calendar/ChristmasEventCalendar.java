package christmas.model.calendar;

import static christmas.enums.CalendarDate.CHRISTMAS;
import static christmas.enums.CalendarDate.EVENT_MONTH;
import static christmas.enums.CalendarDate.EVENT_YEAR;
import static christmas.enums.CalendarDate.FRIDAY;
import static christmas.enums.CalendarDate.SATURDAY;
import static christmas.enums.CalendarDate.START_DAY;
import static christmas.enums.CalendarDate.SUNDAY;

import christmas.enums.CalendarDate;
import java.time.LocalDate;
import java.time.YearMonth;

public class ChristmasEventCalendar implements Calendar {

    private final LocalDate eventDate;

    public ChristmasEventCalendar(int visitDay) {
        validate(visitDay);
        this.eventDate = LocalDate.of(EVENT_YEAR.getNumber(), EVENT_MONTH.getNumber(), visitDay);
    }

    public boolean isWeekDays() {
        int visitDay = getDayOfWeek();
        if (visitDay == FRIDAY.getNumber() || visitDay == SATURDAY.getNumber()) {
            return false;
        }
        return true;
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
        YearMonth yearMonth = YearMonth.of(EVENT_YEAR.getNumber(), EVENT_MONTH.getNumber());
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

    private int getDayOfMonth() {
        return eventDate.getDayOfMonth();
    }
}

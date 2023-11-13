package christmas.model.calendar;

import static christmas.enums.CalendarNumber.CHRISTMAS;
import static christmas.enums.CalendarNumber.EVENT_MONTH;
import static christmas.enums.CalendarNumber.EVENT_YEAR;
import static christmas.enums.CalendarNumber.FRIDAY;
import static christmas.enums.CalendarNumber.SATURDAY;
import static christmas.enums.CalendarNumber.SUNDAY;

import java.time.LocalDate;

public class EventCalendar {
    private LocalDate eventDate;

    public EventCalendar(int day) {
        this.eventDate = LocalDate.of(EVENT_YEAR.getNumber(), EVENT_MONTH.getNumber(), day);
    }

    public boolean isWeekDays() {
        int day = getDayOfWeek();
        if (day == FRIDAY.getNumber() || day == SATURDAY.getNumber()) {
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

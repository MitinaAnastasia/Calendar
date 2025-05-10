import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

/*класс Календарь, в котором хранится переданный на входе год (для удобства, хоть он и не участвует
в заполнении календаря, только в определении високосности и 1 января),
является ли он високосным, в какой день недели 1 января, а также заполненные датами месяцы года,
которые получили из Хранилища календарей по двум признакам*/
public class Calendar {
    private final int year;
    private final boolean isLeapYear;
    private final DayOfWeek januaryDayOfWeek;
    private final int[][] calendarPages;

    public Calendar(int year) {
        this.year = year;
        this.isLeapYear = LocalDate.of(year, 1, 1).isLeapYear();
        this.januaryDayOfWeek = LocalDate.of(year, 1, 1).getDayOfWeek();
        this.calendarPages = CalendarStorage.getCalendarPages(isLeapYear, januaryDayOfWeek);
    }

    public void print() {
        int monthsNumber = 12;
        int weekNumber = 6;
        int dayOfWeekNumber = 7;

        System.out.println("\nВведенный год: " + year);
        System.out.println("\nКалендарь на " + (isLeapYear ? "високосный" : "невисокосный") +
                " год, 1 января: " +
                januaryDayOfWeek.getDisplayName(TextStyle.FULL, Locale.forLanguageTag("ru")));
        System.out.println("\n===========================");

        for (int month = 0; month < monthsNumber; month++) {
            System.out.println("\n" +
                    Month.of(month + 1).getDisplayName(TextStyle.FULL_STANDALONE,
                            Locale.forLanguageTag("ru")) + "\n");

            for (DayOfWeek day : DayOfWeek.values()) {
                System.out.print(day.getDisplayName(TextStyle.SHORT, Locale.forLanguageTag("ru")) + "\t");
            }

            System.out.println();

            for (int week = 0; week < weekNumber; week++) {
                for (int dayOfWeek = 0; dayOfWeek < dayOfWeekNumber; dayOfWeek++) {
                    int day = calendarPages[month][week * dayOfWeekNumber + dayOfWeek];
                    if (day == 0) {
                        System.out.print("  \t");
                    } else {
                        System.out.print(day + "\t");
                    }
                }
                System.out.println();
            }

        }
    }
}

package com.pim.jrgs2526;

public class MyDate {

    public static final String ERR_INVALID_YEAR = "Year value not valid";
    public static final String ERR_INVALID_MONTH = "Month value not valid";
    public static final String ERR_INVALID_DAY = "Day value not valid";
    public static final String ERR_INVALID_DATE = "Invalid date";

    public enum Months {
        JANUARY(1),
        FEBRUARY(2),
        MARCH(3),
        APRIL(4),
        MAY(5),
        JUNE(6),
        JULY(7),
        AUGUST(8),
        SEPTEMBER(9),
        OCTOBER(10),
        NOVEMBER(11),
        DECEMBER(12);

        public final int monthNumber;

        private Months(int monthNumber) {
            this.monthNumber = monthNumber;
        }

        public static Months toMonth( int monthNumber ) {
            for (Months m : values())
                if (m.monthNumber == monthNumber)
                    return m;
            return null;
        }
    }

    private int day;
    private Months month;
    private int year;

    public MyDate() {

    }
    public MyDate(int day, Months month, int year) {
        if (!isValidDate(day, month, year))
            throw new IllegalArgumentException(ERR_INVALID_DATE);
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public Months getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public void setDay(int day) {
        if (!isValidDate(day, month, year))
            throw new IllegalArgumentException(ERR_INVALID_DAY);
        this.day = day;
    }

    public void setMonth(Months month) {
        if (!isValidDate(day, month, year))
            throw new IllegalArgumentException(ERR_INVALID_MONTH);
        this.month = month;
    }

    public void setYear(int year) {
        if (year < 0)
            throw new IllegalArgumentException(ERR_INVALID_YEAR);
        if (!isValidDate(day, month, year))
            throw new IllegalArgumentException(ERR_INVALID_YEAR);
        this.year = year;
    }

    private boolean isValidDate(int day, Months month, int year) {
        if (month == null || year < 0)
            return false;
        int maxDay = getDaysInMonth(month, year);
        return day >= 1 && day <= maxDay;
    }

    private int getDaysInMonth(Months month, int year) {
        switch (month) {
            case APRIL:
            case JUNE:
            case SEPTEMBER:
            case NOVEMBER:
                return 30;
            case FEBRUARY:
                if (isLeapYear(year))
                    return 29;
                return 28;
            default:
                return 31;
        }
    }

    private boolean isLeapYear(int year) {
        if (year % 400 == 0)
            return true;
        if (year % 100 == 0)
            return false;
        return year % 4 == 0;
    }
}

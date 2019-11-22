package com.example.converstationv01;

public class Plan
{

    //start and duration should be given in number of minutes, with start = 0 being 12AM
    //daySpinner is given by a string from 0-6
    public Plan(int day, int start, int duration)
    {
        this.day = day % WEEKDAYS.length;
        this.start = start % MAXTIME;
        this.duration = Math.min(duration, MAXTIME - this.start);
    }

    //This version takes the daySpinner as a string. Capitalization doesn't matter.
    //Invalid strings will default to Saturday
    public Plan(String day, int start, int duration)
    {
        this(dayStringToInt(day), start, duration);
    }

    private int day, start, duration;
    public static final int MAXTIME = 1440;
    public static final String[] WEEKDAYS = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    //Combines this plan with another plan on the same daySpinner.
    //The other plan should be deleted after calling this method
    public void combine(Plan other)
    {
        if(day != other.day) return;

        int newStart = Math.min(start, other.getStartInt());
        int newEnd = Math.max(getEndInt(), other.getEndInt());

        start = newStart;
        duration = newEnd - newStart;
    }

    //Returns true if the two plans on the same daySpinner overlap
    //adjacent counts as overlapping
    public boolean overlaps(Plan other)
    {
        if(day != other.day) return false;
        if(start > other.getEndInt()) return false;
        if(getEndInt() < other.getStartInt()) return false;
        return true;
    }

    //Returns daySpinner as an int from [0, 7)
    public int getDayInt()
    {
        return day;
    }

    //Returns daySpinner as a string
    public String getDayString()
    {
        return(dayIntToString(day));
    }

    //Returns start as an int from [0, 1440)
    public int getStartInt()
    {
        return start;
    }

    //Returns start as a string
    public String getStartString()
    {
        return timeIntToString(start);
    }

    //Returns end as an int from [0, 1440)
    public int getEndInt()
    {
        return start + duration;
    }

    //Returns end as a string
    public String getEndString()
    {
        return timeIntToString(getEndInt());
    }

    public int getDuration() { return duration; }

    //Converts a daySpinner from an int in the range [0, 7) into a string
    public static String dayIntToString(int day)
    {
        return WEEKDAYS[day % WEEKDAYS.length];
    }

    //Converts a daySpinner from a string to an int in the range [0, 7)
    public static int dayStringToInt(String day)
    {
        int dayInt = 0;
        while(dayInt < WEEKDAYS.length - 1 && !day.toLowerCase().equals(WEEKDAYS[dayInt].toLowerCase()))
        {
            dayInt++;
        }
        return dayInt;
    }

    //Converts a time from an int in the range [0, 1440) to a string
    public static String timeIntToString(int time)
    {
        int hour = (time % 720) / 60;
        if(hour == 0) hour = 12;
        int minute = time % 60;
        String amPm = time < 720 ? "AM" : "PM";
        return hour + ":" + (minute < 10 ? "0" : "") + minute + amPm;
    }
}

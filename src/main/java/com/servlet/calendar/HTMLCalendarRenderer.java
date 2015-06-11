package com.servlet.calendar;

import com.yaroslav.other.calendar.MonthCalendar;
import com.yaroslav.other.calendar.view.month.HTMLMonthCalendarRenderer;
import com.yaroslav.other.calendar.view.month.MonthCalendarRenderer;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by employee on 6/11/15.
 */
public class HTMLCalendarRenderer {

    public static String buttonOpenTag = "<a href=\"";
    public static String buttonNexMonthCloseTag = "\" class=\"btn btn-block btn-lg btn-success\">Next</a>";
    public static String buttonPredMonthCloseTag = "\" class=\"btn btn-block btn-lg btn-success\">Pred</a>";
    public static String headerHOpenTag = "<p> <h1 style = \"text-align:center\">";
    public static String headerHCloseTag = "</h1> </p>";

    public static String renderMonthHtmlPageByDate(Calendar date) {
        MonthCalendar monthCalendar = new MonthCalendar(date);

        String result = "";

        MonthCalendarRenderer renderer = new HTMLMonthCalendarRenderer();

        result += headerHOpenTag + date.get(Calendar.YEAR) + " " +
                date.getDisplayName(Calendar.MONTH, Calendar.LONG_FORMAT, Locale.UK) + headerHCloseTag;

        date.set(Calendar.MONTH, date.get(Calendar.MONTH) + 1);
        result += creteButton(date, buttonNexMonthCloseTag);

        date.set(Calendar.MONTH, date.get(Calendar.MONTH) - 2);
        result += creteButton(date, buttonPredMonthCloseTag);

        result += renderer.render(monthCalendar);

        return result;
    }

    private static String creteButton(Calendar date, String buttonCloseTag) {
        String result = buttonOpenTag;
        result += "/index?year=" + date.get(Calendar.YEAR);
        result += "&month=" + date.get(Calendar.MONTH);
        result += buttonCloseTag;

        return result;
    }
}

package com.servlet.calendar;

import com.yaroslav.other.calendar.MonthCalendar;
import com.yaroslav.other.calendar.WeekDayType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Yaroslav on 10.06.2015.
 */
public class MonthCalendarServlet extends HttpServlet {
    private static final boolean FORWARD = true;
    private static final boolean BACKWARD = false;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Calendar calendar = Calendar.getInstance();

        if ((req.getParameter("year") != null) && (req.getParameter("month") != null)) {
            calendar.set(Calendar.YEAR, Integer.valueOf(req.getParameter("year")));
            calendar.set(Calendar.MONTH, Integer.valueOf(req.getParameter("month")));
        }

        HttpSession session = req.getSession();
        session.setAttribute("monthCalendar", new MonthCalendar(calendar));

        session.setAttribute("dateTitle", makePageTitle(calendar));
        session.setAttribute("monthHeader", WeekDayType.values());

        session.setAttribute("prevUrl", getMonthUrl(rollMonth(calendar, BACKWARD)));
        session.setAttribute("nexUrl", getMonthUrl(rollMonth(calendar, FORWARD)));

        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    private Calendar rollMonth(Calendar calendar, boolean up) {
        Calendar rolledCalendar = (Calendar) calendar.clone();
        rolledCalendar.roll(Calendar.MONTH, up);
        return rolledCalendar;
    }

    private String makePageTitle(Calendar calendar) {
        String dateTitle = String.valueOf(calendar.get(Calendar.YEAR));
        dateTitle += " " + calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.UK);
        return dateTitle;
    }

    private static String getMonthUrl(Calendar date) {
        String result = "";
        result += "/index?year=" + date.get(Calendar.YEAR);
        result += "&month=" + date.get(Calendar.MONTH);
        return result;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
            String month = "/index?year=" + req.getParameter("year") + "&" + "month=" + req.getParameter("month");
        resp.sendRedirect(month);
    }
}

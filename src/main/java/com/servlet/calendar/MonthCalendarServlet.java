package com.servlet.calendar;

import com.yaroslav.other.calendar.MonthCalendar;
import com.yaroslav.other.calendar.WeekDayType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Yaroslav on 10.06.2015.
 */
public class MonthCalendarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        Calendar calendar = Calendar.getInstance();

        if ((req.getParameter("year") != null) && (req.getParameter("month") != null)) {
            calendar.set(Calendar.YEAR, Integer.valueOf(req.getParameter("year")));
            calendar.set(Calendar.MONTH, Integer.valueOf(req.getParameter("month")));
        }

        MonthCalendar monthCalendar = new MonthCalendar(calendar);

        session.setAttribute("monthCalendar", monthCalendar);

        String dateTitle = String.valueOf(calendar.get(Calendar.YEAR));
        dateTitle += " " + calendar.getDisplayName(Calendar.MONTH, Calendar.LONG_FORMAT, Locale.UK);

        session.setAttribute("dateTitle", dateTitle);
        session.setAttribute("monthTitle", Arrays.asList(WeekDayType.values()));

        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
        session.setAttribute("nexLink", creteLink(calendar));

        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 2);
        session.setAttribute("predLink", creteLink(calendar));
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    private static String creteLink(Calendar date) {
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

package com.servlet.calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

/**
 * Created by Yaroslav on 10.06.2015.
 */
public class MonthCalendarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Calendar calendar = Calendar.getInstance();

        if ((req.getParameter("year") != null) && (req.getParameter("month") != null)) {
            calendar.set(Calendar.YEAR, Integer.valueOf(req.getParameter("year")));
            calendar.set(Calendar.MONTH, Integer.valueOf(req.getParameter("month")));
        }

        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        out.println(HTMLCalendarRenderer.renderMonthHtmlPageByDate(calendar));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
            String month = "/index?year=" + req.getParameter("year") + "&" + "month=" + req.getParameter("month");
        resp.sendRedirect(month);
    }
}

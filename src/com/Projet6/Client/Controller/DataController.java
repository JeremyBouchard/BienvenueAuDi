package com.Projet6.Client.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DataController extends HttpServlet
{
    public void init() throws ServletException
    {
        super.init();
    }

    public void service (HttpServletRequest req, HttpServletResponse res) throws IOException
    {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html>");
        out.println("<head><title>Date et heure</title></head>");
        out.println("<body>");
        out.println("<p>Date et heure courante:" + new java.util.Date() + "</p>");
        out.println("</body>");
        out.println("</html>");
    }

    public void destroy()
    {
        super.destroy();
    }
}
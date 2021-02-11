package de.unidue.inf.is;

import de.unidue.inf.is.domain.Course;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeliveredServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String errorMessage = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        List<Course> emptyList = new ArrayList<>();
        request.setAttribute("error", errorMessage);
        request.setAttribute("registered", emptyList);
        request.getRequestDispatcher("assignmentPage.ftl").forward(request, response);
    }
}

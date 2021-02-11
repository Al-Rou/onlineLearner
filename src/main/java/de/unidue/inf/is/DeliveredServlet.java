package de.unidue.inf.is;

import de.unidue.inf.is.domain.Course;
import de.unidue.inf.is.utils.DBUtil;

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
        if(!DBUtil.theUser.isEmpty()) {
            List<Course> emptyList = new ArrayList<>();
            request.setAttribute("error", errorMessage);
            request.setAttribute("registered", emptyList);
            request.getRequestDispatcher("/assignmentPage.ftl").forward(request, response);
        }
        else
        {
            errorMessage = "";
            errorMessage += "Access denied: You must login first as an authorized user!";
            List<Course> emptyList = new ArrayList<>();
            request.setAttribute("registered", emptyList);
            request.setAttribute("error", errorMessage);

            request.getRequestDispatcher("/assignmentPage.ftl").forward(request, response);
        }
    }
}

package de.unidue.inf.is;

import de.unidue.inf.is.domain.TaskToShow;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AllTasksServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String errorMessage = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String courseID = request.getParameter("kid");
        int courseIDInt = Integer.parseInt(courseID);
        List<TaskToShow> emptyList = new ArrayList<>();
        emptyList.add(new TaskToShow("SQL",2,"What","Why is SQL needed?",courseIDInt));
        request.setAttribute("error", errorMessage);
        request.setAttribute("kurse", emptyList);
        request.getRequestDispatcher("/allTasksPage.ftl").forward(request,response);
    }
}

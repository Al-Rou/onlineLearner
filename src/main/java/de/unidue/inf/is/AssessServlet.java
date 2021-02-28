package de.unidue.inf.is;

import de.unidue.inf.is.domain.TaskToShow;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AssessServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String errorMessage = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException
    {
        String courseID = request.getParameter("kid");
        int courseIDInt = Integer.parseInt(courseID);
        String deliveryID = request.getParameter("aid");
        int deliveryIDInt = Integer.parseInt(deliveryID);
        List<TaskToShow> emptyList = new ArrayList<>();
        TaskToShow taskToShow = new TaskToShow("Datenbanken", 3, "SQL",
                "Wofür steht SQL?", 20);
        emptyList.add(taskToShow);
        request.setAttribute("registered", emptyList);
        request.setAttribute("error", errorMessage);
        request.getRequestDispatcher("/assessPage.ftl").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException
    {
        MainPageServlet mainPageServlet = new MainPageServlet();
        mainPageServlet.doGet(request, response);
    }
}

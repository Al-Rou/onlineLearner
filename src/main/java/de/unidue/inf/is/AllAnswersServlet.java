package de.unidue.inf.is;

import de.unidue.inf.is.domain.Delivery;
import de.unidue.inf.is.domain.DeliveryToShow;
import de.unidue.inf.is.domain.Task;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AllAnswersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String errorMessage = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        List<Task> list = new ArrayList<>();
        list.add(new Task(7, 62, "SQL", "What is SQL?"));
        List<DeliveryToShow> list2 = new ArrayList<>();
        list2.add(new DeliveryToShow(5, "I don't know!", 8));
        request.setAttribute("error", errorMessage);
        request.setAttribute("aufgabe", list);
        request.setAttribute("abgabe", list2);
        request.getRequestDispatcher("/allAnswersPage.ftl").forward(request,response);
    }
}

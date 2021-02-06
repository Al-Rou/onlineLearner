package de.unidue.inf.is;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String errorMessage = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException
    {
        request.setAttribute("error", errorMessage);
        request.getRequestDispatcher("/newCoursePage.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException
    {
        String newName = request.getParameter("titel");
        String newPass = request.getParameter("pass");
        String newFreeSeats = request.getParameter("seats");
        int newFreeSeatsInt = 0;
        if(!newFreeSeats.isEmpty()) {
            newFreeSeatsInt = Integer.parseInt(newFreeSeats);
        }
        String newDescrip = request.getParameter("descrip");
        if ((newName.length()==0) || (newName.length()>50))
        {
            errorMessage = "";
            errorMessage += "Error: The entered name is either longer than 50 characters or empty!";
            doGet(request, response);
        }
        if ((newFreeSeats.isEmpty()) || (newFreeSeatsInt > 100))
        {
            errorMessage = "";
            errorMessage += "Error: The entered number of free seats cannot be empty or bigger than 100!";
            doGet(request, response);
        }
        if (newDescrip.isEmpty())
        {
            errorMessage = "";
            errorMessage += "Error: The discription cannot remain empty!";
            doGet(request, response);
        }
    }
}

package de.unidue.inf.is;

import de.unidue.inf.is.utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NewTaskServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String errorMessage = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        if(!DBUtil.theUser.isEmpty()) {
            request.setAttribute("error", errorMessage);
            request.getRequestDispatcher("/taskPage.ftl").forward(request, response);
        }
        else
        {
            errorMessage = "";
            errorMessage += "Error: You must login first as an authorized user!";
            request.setAttribute("error", errorMessage);
            request.getRequestDispatcher("/taskPage.ftl").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {

    }
}

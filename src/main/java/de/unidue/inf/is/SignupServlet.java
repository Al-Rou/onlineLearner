package de.unidue.inf.is;

import de.unidue.inf.is.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String errorMessage = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException
    {
        request.setAttribute("error", errorMessage);
        request.getRequestDispatcher("/signupPage.ftl").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String newEmail = request.getParameter("username");
        if (newEmail.isEmpty())
        {
            errorMessage = "";
            errorMessage += "Error: You must enter your email!";
            doGet(request, response);
        }
        if (!(newEmail.contains("@gmail.com")) && !(newEmail.contains("@yahoo.com")))
        {
            errorMessage = "";
            errorMessage += "Error: Either yahoo or gmail accounts are accepted for now!";
            doGet(request, response);
        }
        String newName = request.getParameter("name");
        if (newName.isEmpty())
        {
            errorMessage = "";
            errorMessage += "Error: You must enter your name!";
            doGet(request, response);
        }
        User newUserToAdd = new User(newEmail, newName);
    }
}

package de.unidue.inf.is;

import de.unidue.inf.is.stores.CourseStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //request.getAttribute("mycourse", CourseStore.getResult);
        request.getRequestDispatcher("/mainPage.ftl").forward(request, response);
    }
}

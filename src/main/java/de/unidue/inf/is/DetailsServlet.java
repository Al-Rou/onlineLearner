package de.unidue.inf.is;

import de.unidue.inf.is.domain.CourseWithProducersName;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        List<CourseWithProducersName> courseWithProducersNames = new ArrayList<>();
        request.setAttribute("course", courseWithProducersNames);
        request.getRequestDispatcher("/detailsPage.ftl").forward(request, response);
    }
}

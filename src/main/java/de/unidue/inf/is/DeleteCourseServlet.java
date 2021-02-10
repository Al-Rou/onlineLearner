package de.unidue.inf.is;

import de.unidue.inf.is.domain.Course;
import de.unidue.inf.is.stores.CourseStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeleteCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String errorMessage = "";
    private static CourseStore courseStore = new CourseStore();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String courseID = request.getParameter("kid");
        List<Course> listOfCourse = new ArrayList<>();
        int idInt = 0;
        if(!courseID.equals("null"))
        {
            idInt = Integer.parseInt(courseID);
        }
        if(idInt != 0) {
            List<Integer> list = new ArrayList<>();
            list.add(idInt);
            listOfCourse = courseStore.showMyOwnCourses(list);
            request.setAttribute("registered", listOfCourse);
            request.setAttribute("error", errorMessage);
            request.getRequestDispatcher("deletePage.ftl").forward(request, response);
        }
    }
}

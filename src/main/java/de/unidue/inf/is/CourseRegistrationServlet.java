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

public class CourseRegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String errorMessage = "";
    private static CourseStore courseStore = new CourseStore();
    private static String passToCheck = "";
    private static String enteredPass = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        passToCheck = "";
        String idToRegister = request.getParameter("kid");
        int idToRegisterInt = Integer.parseInt(idToRegister);
        List<Integer> list = new ArrayList<>();
        list.add(idToRegisterInt);
        List<Course> listOfCourse = new ArrayList<>();
        listOfCourse = courseStore.showMyOwnCourses(list);
        passToCheck += listOfCourse.get(0).getEinschreibeSchluessel();
        request.setAttribute("reg", listOfCourse);
        request.setAttribute("error", errorMessage);
        request.getRequestDispatcher("/registerPage.ftl").forward(request, response);
    }
}

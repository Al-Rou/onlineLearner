package de.unidue.inf.is;

import de.unidue.inf.is.domain.Course;
import de.unidue.inf.is.stores.AufgabeStore;
import de.unidue.inf.is.stores.CourseStore;
import de.unidue.inf.is.stores.UserStore;
import de.unidue.inf.is.utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewTaskServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String errorMessage = "";
    private static String errorMessage2 = "";
    private static AufgabeStore aufgabeStore = new AufgabeStore();
    private static int courseIDInt;
    private static UserStore userStore = new UserStore();
    private static CourseStore courseStore = new CourseStore();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        if(!DBUtil.theUser.isEmpty()) {
            String courseID = request.getParameter("kid");
            if (!courseID.isEmpty() && !courseID.equals("null"))
            {
                courseIDInt = Integer.parseInt(courseID);
            }
            List<Integer> list = new ArrayList<>();
            list.add(courseIDInt);
            List<Course> courseListToShow = courseStore.showMyOwnCourses(list);
            request.setAttribute("error", errorMessage2);
            request.setAttribute("course", courseListToShow);
            request.getRequestDispatcher("/taskPage.ftl").forward(request, response);
        }
        else
        {
            errorMessage = "";
            errorMessage += "Error: You must login first as an authorized user!";
            request.setAttribute("error", errorMessage);
            request.getRequestDispatcher("/errorPage.ftl").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        if(userStore.fetchBNummerFromEmail(DBUtil.theUser) == courseStore.giveProducerFromCourseID(courseIDInt)) {
            String taskName = request.getParameter("titel");
            String taskDes = request.getParameter("descrip");
            if (aufgabeStore.insertNewTask(courseIDInt, taskName, taskDes)) {
                MainPageServlet mainPageServlet = new MainPageServlet();
                mainPageServlet.doGet(request, response);
            } else {
                errorMessage2 = "";
                errorMessage2 += "Error: Something is wrong with database! Try again later!";
                doGet(request, response);
            }
        }
        else
        {
            errorMessage2 = "";
            errorMessage2 += "Error: Only the producer of a course is authorized to define a task!";
            doGet(request, response);
        }
    }
}

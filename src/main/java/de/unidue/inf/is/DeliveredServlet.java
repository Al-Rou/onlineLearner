package de.unidue.inf.is;

import de.unidue.inf.is.domain.Course;
import de.unidue.inf.is.domain.Task;
import de.unidue.inf.is.domain.TaskToShow;
import de.unidue.inf.is.stores.AufgabeStore;
import de.unidue.inf.is.stores.CourseStore;
import de.unidue.inf.is.utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeliveredServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String errorMessage = "";
    private static int courseIDInt;
    private static int taskIDInt;
    private CourseStore courseStore = new CourseStore();
    private AufgabeStore aufgabeStore = new AufgabeStore();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        if(!DBUtil.theUser.isEmpty()) {
            errorMessage = "";
            String courseID = request.getParameter("kid");
            String taskID = request.getParameter("anummer");
            if(!courseID.isEmpty() && !courseID.equals("null"))
            {
                courseIDInt = Integer.parseInt(courseID);
            }
            List<Integer> list1 = new ArrayList<>();
            list1.add(courseIDInt);
            if(!taskID.isEmpty() && !taskID.equals("null"))
            {
                taskIDInt = Integer.parseInt(taskID);
            }
            List<Course> courseList = courseStore.showMyOwnCourses(list1);
            List<Task> taskList = aufgabeStore.fetchTasksFromCourseID(courseIDInt);
            List<TaskToShow> taskToShowList = new ArrayList<>();
            for (int i=0; i < taskList.size(); i++)
            {
                if(taskList.get(i).getaNummer() == taskIDInt)
                {
                    taskToShowList.add(new TaskToShow(courseList.get(0).getName(),
                            taskIDInt, taskList.get(i).getName(), taskList.get(i).getBeschreibung()));
                }
            }
            request.setAttribute("error", errorMessage);
            request.setAttribute("registered", taskToShowList);
            request.getRequestDispatcher("/assignmentPage.ftl").forward(request, response);
        }
        else
        {
            errorMessage = "";
            errorMessage += "Access denied: You must login first as an authorized user!";
            List<Course> emptyList = new ArrayList<>();
            request.setAttribute("registered", emptyList);
            request.setAttribute("error", errorMessage);

            request.getRequestDispatcher("/assignmentPage.ftl").forward(request, response);
        }
    }
}

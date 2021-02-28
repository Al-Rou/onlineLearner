package de.unidue.inf.is;

import de.unidue.inf.is.domain.Task;
import de.unidue.inf.is.domain.TaskToShow;
import de.unidue.inf.is.stores.AufgabeStore;
import de.unidue.inf.is.stores.CourseStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AllTasksServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String errorMessage = "";
    private static CourseStore courseStore = new CourseStore();
    private static AufgabeStore aufgabeStore = new AufgabeStore();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String courseID = request.getParameter("kid");
        int courseIDInt = Integer.parseInt(courseID);
        String courseName = courseStore.fetchNameFromCourseID(courseIDInt);
        List<Task> list = aufgabeStore.fetchTasksFromCourseID(courseIDInt);
        List<TaskToShow> listToShow = new ArrayList<>();
        for(int i = 0; i < list.size(); i++)
        {
            listToShow.add(new TaskToShow(courseName, list.get(i).getaNummer(),
                    list.get(i).getName(), list.get(i).getBeschreibung(), courseIDInt));
        }
        //emptyList.add(new TaskToShow("SQL",2,"What","Why is SQL needed?",courseIDInt));
        request.setAttribute("courseName", courseName);
        request.setAttribute("error", errorMessage);
        request.setAttribute("kurse", listToShow);
        request.getRequestDispatcher("/allTasksPage.ftl").forward(request,response);
    }
}

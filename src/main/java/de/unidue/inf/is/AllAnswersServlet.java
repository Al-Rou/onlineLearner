package de.unidue.inf.is;

import de.unidue.inf.is.domain.Delivery;
import de.unidue.inf.is.domain.DeliveryToShow;
import de.unidue.inf.is.domain.Task;
import de.unidue.inf.is.domain.TaskToShow;
import de.unidue.inf.is.stores.AufgabeStore;
import de.unidue.inf.is.stores.CourseStore;
import de.unidue.inf.is.stores.EinreichenStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AllAnswersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String errorMessage = "";
    private static AufgabeStore aufgabeStore = new AufgabeStore();
    private static CourseStore courseStore = new CourseStore();
    private static EinreichenStore einreichenStore = new EinreichenStore();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String courseID = request.getParameter("kid");
        int courseIDInt = Integer.parseInt(courseID);
        String courseName = courseStore.fetchNameFromCourseID(courseIDInt);
        String taskID = request.getParameter("anummer");
        int taskIDInt = Integer.parseInt(taskID);
        List<Task> list = aufgabeStore.fetchTaskFromAufgabeNummer(courseIDInt, taskIDInt);
        List<TaskToShow> list1ToShow = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            list1ToShow.add(new TaskToShow(courseName, taskIDInt, list.get(i).getName(), list.get(i).getBeschreibung(), courseIDInt));
        }
        List<Delivery> list3 = einreichenStore.fetchAllAnswers(courseIDInt, taskIDInt);
        List<DeliveryToShow> list2 = new ArrayList<>();
        for (int i = 0; i < list3.size(); i++)
        {
            list2.add(new DeliveryToShow(list3.get(i).getaID(),
                    list3.get(i).getAbgabeText(), courseIDInt, taskIDInt));
        }
        request.setAttribute("error", errorMessage);
        request.setAttribute("aufgabe", list1ToShow);
        request.setAttribute("abgabe", list2);
        request.getRequestDispatcher("/allAnswersPage.ftl").forward(request,response);
    }
}

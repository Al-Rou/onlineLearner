package de.unidue.inf.is;

import de.unidue.inf.is.domain.Delivery;
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

public class AssessServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String errorMessage = "";
    private static AufgabeStore aufgabeStore = new AufgabeStore();
    private static CourseStore courseStore = new CourseStore();
    private static EinreichenStore einreichenStore = new EinreichenStore();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException
    {
        String courseID = request.getParameter("kid");
        int courseIDInt = Integer.parseInt(courseID);
        String courseName = courseStore.fetchNameFromCourseID(courseIDInt);
        String deliveryID = request.getParameter("aid");
        int deliveryIDInt = Integer.parseInt(deliveryID);
        String taskID = request.getParameter("anummer");
        int taskIDInt = Integer.parseInt(taskID);
        List<Task> list = aufgabeStore.fetchTaskFromAufgabeNummer(courseIDInt, taskIDInt);
        List<TaskToShow> listToShow = new ArrayList<>();
        listToShow.add(new TaskToShow(courseName, taskIDInt, list.get(0).getName(),
                list.get(0).getBeschreibung(), courseIDInt));
        //TaskToShow taskToShow = new TaskToShow("Datenbanken", 3, "SQL",
                //"Wofür steht SQL?", 20);
        Delivery deliveryToShow = einreichenStore.fetchAbgabeTextFromAbgabeID(deliveryIDInt);
        request.setAttribute("registered", listToShow);
        request.setAttribute("textforassess", deliveryToShow);
        request.setAttribute("error", errorMessage);
        request.getRequestDispatcher("/assessPage.ftl").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException
    {
        MainPageServlet mainPageServlet = new MainPageServlet();
        mainPageServlet.doGet(request, response);
    }
}

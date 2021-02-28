package de.unidue.inf.is;

import de.unidue.inf.is.domain.Delivery;
import de.unidue.inf.is.domain.Task;
import de.unidue.inf.is.domain.TaskToShow;
import de.unidue.inf.is.stores.*;
import de.unidue.inf.is.utils.DBUtil;

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
    private static UserStore userStore = new UserStore();
    private static BewertenStore bewertenStore = new BewertenStore();
    private static int deliveryIDInt;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException
    {
        String courseID = request.getParameter("kid");
        int courseIDInt = Integer.parseInt(courseID);
        String courseName = courseStore.fetchNameFromCourseID(courseIDInt);
        String deliveryID = request.getParameter("aid");
        deliveryIDInt = Integer.parseInt(deliveryID);
        String taskID = request.getParameter("anummer");
        int taskIDInt = Integer.parseInt(taskID);
        List<Task> list = aufgabeStore.fetchTaskFromAufgabeNummer(courseIDInt, taskIDInt);
        List<TaskToShow> listToShow = new ArrayList<>();
        listToShow.add(new TaskToShow(courseName, taskIDInt, list.get(0).getName(),
                list.get(0).getBeschreibung(), courseIDInt));
        Delivery delivery = einreichenStore.fetchAbgabeTextFromAbgabeID(deliveryIDInt);
        List<Delivery> secondListToShow = new ArrayList<>();
        secondListToShow.add(delivery);
        request.setAttribute("registered", listToShow);
        request.setAttribute("textforassess", secondListToShow);
        request.setAttribute("error", errorMessage);
        request.getRequestDispatcher("/assessPage.ftl").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException
    {
        String grade = request.getParameter("grade");
        int gradeInt = Integer.parseInt(grade);
        String comment = request.getParameter("answer");
        if (bewertenStore.insertGrade(userStore.fetchBNummerFromEmail(DBUtil.theUser),
                deliveryIDInt, gradeInt, comment)) {
            MainPageServlet mainPageServlet = new MainPageServlet();
            mainPageServlet.doGet(request, response);
        }
        else
        {
            errorMessage = "";
            errorMessage += "Error: Something is wrong with database. Try again later!";
            doGet(request, response);
        }
    }
}

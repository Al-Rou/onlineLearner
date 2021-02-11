package de.unidue.inf.is;

import de.unidue.inf.is.domain.Course;
import de.unidue.inf.is.domain.Task;
import de.unidue.inf.is.domain.TaskToShow;
import de.unidue.inf.is.stores.AufgabeStore;
import de.unidue.inf.is.stores.CourseStore;
import de.unidue.inf.is.stores.EinreichenStore;
import de.unidue.inf.is.stores.UserStore;
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
    private UserStore userStore = new UserStore();
    private EinreichenStore einreichenStore = new EinreichenStore();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        if(!DBUtil.theUser.isEmpty()) {
            //if(einreichenStore.fetchAbgabeID(userStore.fetchBNummerFromEmail(DBUtil.theUser),
                    //courseIDInt, taskIDInt) == 0) {
                errorMessage = "";
                String courseID = request.getParameter("kid");
                String taskID = request.getParameter("anummer");
                if (!courseID.isEmpty() && !courseID.equals("null")) {
                    courseIDInt = Integer.parseInt(courseID);
                }
                List<Integer> list1 = new ArrayList<>();
                list1.add(courseIDInt);
                if (!taskID.isEmpty() && !taskID.equals("null")) {
                    taskIDInt = Integer.parseInt(taskID);
                }
                if(einreichenStore.fetchAbgabeID(userStore.fetchBNummerFromEmail(DBUtil.theUser),
                    courseIDInt, taskIDInt) == 0) {
                    List<Course> courseList = courseStore.showMyOwnCourses(list1);
                    List<Task> taskList = aufgabeStore.fetchTasksFromCourseID(courseIDInt);
                    List<TaskToShow> taskToShowList = new ArrayList<>();
                    for (int i = 0; i < taskList.size(); i++) {
                        if (taskList.get(i).getaNummer() == taskIDInt) {
                            taskToShowList.add(new TaskToShow(courseList.get(0).getName(),
                                    taskIDInt, taskList.get(i).getName(), taskList.get(i).getBeschreibung(), courseIDInt));
                        }
                    }
                    request.setAttribute("error", errorMessage);
                    request.setAttribute("registered", taskToShowList);
                    request.getRequestDispatcher("/assignmentPage.ftl").forward(request, response);
                }
                else
                {
                    errorMessage = "";
                    errorMessage += "Error: You have already submitted your answer to this task!";
                    request.setAttribute("error", errorMessage);
                    List<TaskToShow> emptyListToShow = new ArrayList<>();
                    request.setAttribute("registered", emptyListToShow);
                    request.getRequestDispatcher("/assignmentPage.ftl").forward(request, response);
                }
            //}
            /*else
            {
                errorMessage = "";
                errorMessage += "Error: You have already submitted your answer to this task!";
                request.setAttribute("error", errorMessage);
                List<TaskToShow> emptyListToShow = new ArrayList<>();
                request.setAttribute("registered", emptyListToShow);
                request.getRequestDispatcher("/assignmentPage.ftl").forward(request, response);
            }*/
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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String answerText = request.getParameter("answer");
        if(!answerText.isEmpty() && !answerText.equals("null"))
        {
            if(einreichenStore.insertText(answerText, courseIDInt, taskIDInt, userStore.fetchBNummerFromEmail(DBUtil.theUser)))
            {
                MainPageServlet mainPageServlet = new MainPageServlet();
                mainPageServlet.doGet(request, response);
            }
            else
            {
                errorMessage = "";
                errorMessage += "Error: Something is wrong with database! Try again later!";
                doGet(request, response);
            }
        }
        else
        {
            doGet(request, response);
        }
    }
}

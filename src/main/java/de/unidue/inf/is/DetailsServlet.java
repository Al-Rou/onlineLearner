package de.unidue.inf.is;

import de.unidue.inf.is.domain.Course;
import de.unidue.inf.is.domain.CourseWithProducersName;
import de.unidue.inf.is.stores.CourseStore;
import de.unidue.inf.is.stores.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static CourseStore courseStore = new CourseStore();
    private static UserStore userStore = new UserStore();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String courseID = request.getParameter("kid");
        int intCourseID = Integer.parseInt(courseID);
        List<Integer> list1 = new ArrayList<>();
        list1.add(intCourseID);
        List<Course> list2 = courseStore.showMyOwnCourses(list1);
        List<CourseWithProducersName> list3 = new ArrayList<>();
        for (int i=0; i < list2.size(); i++)
        {
            list3.add(new CourseWithProducersName(list2.get(i).getkID(),list2.get(i).getName(),
                    userStore.fetchNameFromBNummer(list2.get(i).getErsteller()), list2.get(i).getFreiePlaetze(),
                    list2.get(i).getBeschreibungsText()));
        }
        //List<CourseWithProducersName> courseWithProducersNames = new ArrayList<>();
        request.setAttribute("course", list3);
        request.getRequestDispatcher("/detailsPage.ftl").forward(request, response);
    }
}

package de.unidue.inf.is;

import de.unidue.inf.is.domain.Course;
import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.stores.CourseStore;
import de.unidue.inf.is.stores.LoginStore;
import de.unidue.inf.is.stores.RegistrationStore;
import de.unidue.inf.is.stores.UserStore;
import de.unidue.inf.is.utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainPageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static CourseStore courseStore = new CourseStore();
    private static LoginStore loginStore = new LoginStore();
    private static RegistrationStore registrationStore = new RegistrationStore();
    private static UserStore userStore = new UserStore();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

            if (!loginStore.userAuthenticated(DBUtil.theUser).isEmpty()) {
                if (!courseStore.showCourse().isEmpty()) {
                    request.setAttribute("mycourse", courseStore.showCourse());
                    List<Integer> listOfCourseIDs = new ArrayList<>();
                    listOfCourseIDs = registrationStore.fetchCourseIDFromUserID(userStore.fetchBNummerFromEmail(DBUtil.theUser));
                    request.setAttribute("myowncourse", courseStore.showMyOwnCourses(listOfCourseIDs));
                }
                else {
                    request.setAttribute("mycourse", "There is no course available at all!");
                    request.setAttribute("myowncourse", "There is no course available at all!");
                }
            }
            else
            {
                List<User> kosUser = new ArrayList<>();
                request.setAttribute("myowncourse", kosUser);
                List<Course> kosCourse = new ArrayList<>();
                request.setAttribute("mycourse", kosCourse);
            }
        request.getRequestDispatcher("/mainPage.ftl").forward(request, response);
    }
}

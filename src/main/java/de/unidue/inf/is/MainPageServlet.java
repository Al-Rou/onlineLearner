package de.unidue.inf.is;

import de.unidue.inf.is.domain.Course;
import de.unidue.inf.is.stores.CourseStore;
import de.unidue.inf.is.stores.LoginStore;
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        if (!courseStore.showCourse().isEmpty()) {
            request.setAttribute("mycourse", courseStore.showCourse());
            request.setAttribute("myowncourse", loginStore.userAuthenticated(DBUtil.theUser));
        }
        else {
            request.setAttribute("mycourse", "There is no course available at all!");
            request.setAttribute("myowncourse", "There is no course available at all!");
        }
        request.getRequestDispatcher("/mainPage.ftl").forward(request, response);
    }
}

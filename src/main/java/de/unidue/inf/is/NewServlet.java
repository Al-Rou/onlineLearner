package de.unidue.inf.is;

import java.io.IOException;

public final class NewServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("hellosayers", "This is just a hi,");
        request.getRequestDispatcher("/new.ftl").forward(request, response);
    }
}

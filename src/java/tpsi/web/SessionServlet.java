package tpsi.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "SessionServlet", urlPatterns = {"/session"})
public class SessionServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("wizyty") == null) {
            session.setAttribute("wizyty", 1);
        } else {
            session.setAttribute("wizyty", (Integer) session.getAttribute("wizyty") + 1);
        }

        if (session.getAttribute("listaStudentow") == null) {
            List<Student> studenci = new ArrayList<>();
            session.setAttribute("listaStudentow", studenci);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        request.getRequestDispatcher("session.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        processRequest(request, response);

        List<Student> studenci;
        studenci = (ArrayList<Student>) session.getAttribute("listaStudentow");

        if (studenci == null) {
            studenci = new ArrayList<>();
        }

        studenci.add(new Student(request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("email")));
        
        session.setAttribute("listaStudentow", studenci);
        request.getRequestDispatcher("session.jsp").forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }//

}

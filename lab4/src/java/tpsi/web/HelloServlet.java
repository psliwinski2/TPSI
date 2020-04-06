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

@WebServlet(name = "HelloServlet", urlPatterns = {"/hello"})
public class HelloServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        Person p1 = new Person(request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("email"));
        request.setAttribute("p1", p1);
        
        List<String> dni = new ArrayList<>();
        dni.add("Poniedziałek");
        dni.add("Wtorek");
        dni.add("Środa");
        dni.add("Czwartek");
        dni.add("Piątek");

        request.setAttribute("dniTygodnia", dni);

        request.getRequestDispatcher("hello.jsp").forward(request, response);

    }
}

//1.Porównaj działanie obu wersji strony: pierwszej, z wyrażeniem ${imie} i drugiej, z tagiem c:out.
//W obu wersjach osiąga się te same wyniki, wyrażenia EL są mniej skomplikowane, natomiast z biblioteką JSTL można wykonać
//bardziej skomplikowane operacje.
//2.Co się stanie w obu wersjach, jeśli jako imię wpiszemy w formularzu tekst ze znacznikami HTML?
//W przypadku prostych wyrażeń EL, treść została zmieniona zgodnie ze znacznikami, w przypadku biblioteki JSTL, tekst wynikowy
//jest w formie niezmienionej.

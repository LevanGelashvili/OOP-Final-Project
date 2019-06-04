
package servlets.front;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/deals"})
public class DealsServlet extends HttpServlet {

    /**
     Finds collection of the deals with some criteria.

     returned html main components:
     1) list of the links to the front.DealServlet (GET) (view of deal),
        found by some criteria.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {


    }
}
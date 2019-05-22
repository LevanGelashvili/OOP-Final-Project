package controllers.front;

import controllers.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DealsController extends Controller {
    /**
     * Creates a controller, usually called from servlet, which is also
     * passed by parameter. servlet method passes taken request and response.
     *
     * @param req
     * @param res
     * @param servlet
     */
    public DealsController(HttpServletRequest req, HttpServletResponse res, HttpServlet servlet) {
        super(req, res, servlet);
    }

    public void index() throws ServletException, IOException {
        dispatchTo("/pages/public/deals.jsp");
    }

    public void show(int id) throws ServletException, IOException {
        request.setAttribute("id", id);
        dispatchTo("/pages/public/deal.jsp");
    }
}

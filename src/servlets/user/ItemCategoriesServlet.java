
package servlets.user;

import controllers.user.ItemCategoryController;
import middlewares.AuthenticatedUser;
import servlets.RoutingConstants;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {RoutingConstants.USER_ITEM_CATEGORIES})
public class ItemCategoriesServlet extends HttpServlet {

    /**
     * Checking if user is authenticated before entering any method.
     *
     * @param request - Request Object for getting user request
     * @param response - Response Object for sending back response
     * @throws ServletException - If some Servlet Exception happens
     * @throws IOException - If Some IOException happens
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        if ((new AuthenticatedUser(request,response)).unauthenticated())
            return;
        super.service(request, response);
    }


    /**
     * @param request - Request Object for getting user request
     * @param response - Response Object for sending back response
     * @throws ServletException - If some Servlet Exception happens
     * @throws IOException - If Some IOException happens
     */
    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
        throws ServletException, IOException {
        (new ItemCategoryController(request, response, this)).index();
    }


    /**
     * @param request - Request Object for getting user request
     * @param response - Response Object for sending back response
     * @throws ServletException - If some Servlet Exception happens
     * @throws IOException - If Some IOException happens
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        (new ItemCategoryController(request, response, this)).store();
    }

}

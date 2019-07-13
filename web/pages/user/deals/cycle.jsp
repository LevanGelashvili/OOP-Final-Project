<%--
  Created by IntelliJ IDEA.
  User: gogotchuri
  Date: 6/21/19
  Time: 1:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="models.Cycle" %>
<%@ page import="models.Deal" %>
<%@ page import="java.util.List" %>
<%@ page import="servlets.RoutingConstants" %>

<%  Cycle cycle = (Cycle) session.getAttribute("cycle");
    List<Deal> deals = cycle.getDeals();
    Deal deal = null;
%>

<html>
  <jsp:include page="/pages/partials/head.jsp">
      <jsp:param name="title" value="Cycle"/>
  </jsp:include>

  <body>
    <jsp:include page="/pages/partials/navbar.jsp"/>
    <div class="cycleContainer">
        <div class="cycles">

                                                            <%-- TODO: BUTTON --%>
            <div class="button">
              <form action="" method="get">
                  <button type="submit">Accept cycle</button>
              </form> 
            </div>


            <%for(int i = 0; i < deals.size() - 1; i++){
                deal = deals.get(i);
                %>
                <div class="deal">
                    <img src="<%=deal.getOwnedItems().get(0).getImages().get(0).getUrl()%>">
                    <div class="info">
                        <a href="${pageContext.request.contextPath}<%=RoutingConstants.USER_SINGLE_DEAL%>"> <%=deal.getTitle()%> </a>
                        <p> <%=deal.getOwnedItems().get(0).getDescription()%> </p>
                        <a href="${pageContext.request.contextPath}<%=RoutingConstants.PUBLIC_PROFILE%>"> View owner's profile </a>
                    </div>
                </div>
                <div class="dealArrow">
                    <img src="https://www.stickpng.com/assets/images/58f8bcf70ed2bdaf7c128307.png">
                </div>
            <%}%>
            <%deal = deals.get(deals.size() - 1);%>
            <div class="deal">
                <img src="<%=deal.getOwnedItems().get(0).getImages().get(0).getUrl()%>">
                <div class="info">
                    <a href="${pageContext.request.contextPath}<%=RoutingConstants.USER_SINGLE_DEAL%>"> <%=deal.getTitle()%> </a>
                    <p> <%=deal.getOwnedItems().get(0).getDescription()%> </p>
                    <a href="${pageContext.request.contextPath}<%=RoutingConstants.PUBLIC_PROFILE%>"> View owner's profile </a>
                </div>
            </div>
            <div class="dealUpArrow">
                <img src="https://imageog.flaticon.com/icons/png/512/20/20901.png?size=1200x630f&pad=10,10,10,10&ext=png&bg=FFFFFFFF" alt="up arrow">
            </div>

                                                                <%--
            <div class="deal">
              <img src="https://media.istockphoto.com/photos/green-apple-picture-id475190419?k=6&m=475190419&s=612x612&w=0&h=G01aHVafnPd01ugi6dmJKtNHS-nz0GrQAbpzDcjuXI0=" alt="green-apple-picture-id475190419?k">
              <div class="info">
                  <a href=""> deal name1 </a>
                  <p> Lorem ipsum dolor, sit amet consectetur adipisicing elit. Libero iusto repellendus voluptates deleniti, atque autem laudantium rem illum laborum quisquam. </p>
                  <a href=""> View user1's profile </a>
              </div>
            </div>

            <div class="dealArrow">
              <img src="https://www.stickpng.com/assets/images/58f8bcf70ed2bdaf7c128307.png">
            </div>
            <div class="deal">
              <img src="https://media.istockphoto.com/photos/green-apple-picture-id475190419?k=6&m=475190419&s=612x612&w=0&h=G01aHVafnPd01ugi6dmJKtNHS-nz0GrQAbpzDcjuXI0=" alt="green-apple-picture-id475190419?k" >
              <div class="info">
                  <a href=""> deal name2 </a>
                  <p> Lorem ipsum dolor, sit amet consectetur adipisicing elit. Libero iusto repellendus voluptates deleniti, atque autem laudantium rem illum laborum quisquam. </p>
                  <a href=""> View user2's profile </a>
              </div>
            </div>
            <div class="dealArrow">
              <img src="https://www.stickpng.com/assets/images/58f8bcf70ed2bdaf7c128307.png">
            </div>
            <div class="deal">
              <img src="https://media.istockphoto.com/photos/green-apple-picture-id475190419?k=6&m=475190419&s=612x612&w=0&h=G01aHVafnPd01ugi6dmJKtNHS-nz0GrQAbpzDcjuXI0=" alt="green-apple-picture-id475190419?k" >
              <div class="info">
                <a href=""> deal name3 </a>
                <p> Lorem ipsum dolor, sit amet consectetur adipisicing elit. Libero iusto repellendus voluptates deleniti, atque autem laudantium rem illum laborum quisquam. </p>
                <a href=""> View user3's profile </a>
              </div>
            </div>

            <div class="dealUpArrow">
              <img src="https://imageog.flaticon.com/icons/png/512/20/20901.png?size=1200x630f&pad=10,10,10,10&ext=png&bg=FFFFFFFF" alt="up arrow">
            </div>
            --%>
        </div>
    </div>


    <jsp:include page="/pages/partials/footer.jsp"/>
  </body>
</html>

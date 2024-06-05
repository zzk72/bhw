<%--
  Created by IntelliJ IDEA.
  User: 张珍奎
  Date: 2024/3/21
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
  <jsp:include page="CSS/header.css"/>
</style>
<!-- navbar.jsp -->
<nav class="container-fluid">
  <ul>
    <li><strong>景区浏览</strong></li>
    <li>
      <form id="Home" action="viewpoints" method="get">
        <input type="hidden" name="action" value="showHome" />
        <input type="submit" value="首页" />
      </form>
    </li>
    <li>
      <form id="ServiceStatusForm" action="viewpoints" method="get">
        <input type="hidden" name="action" value="viewServices" />
        <input type="submit" value="详情" />
      </form>
    </li>
    <li>
      <form id="orders" action="showOrders" method="get">
        <input type="hidden" name="action" value="showOrders" />
        <input type="submit" value="Orders" />
      </form>
    </li>
  </ul>
</nav>

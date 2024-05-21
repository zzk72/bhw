<%--
  Created by IntelliJ IDEA.
  User: 张珍奎
  Date: 2024/4/5
  Time: 23:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
  <jsp:include page="CSS/header.css"/>
</style>
<!-- navbar.jsp -->
<nav class="container-fluid">
  <ul>
    <li><strong>科目1考试系统</strong></li>
    <li>
      <form id="Home" action="que" method="get">
        <input type="hidden" name="action" value="first_que" />
        <input type="submit" value="首页" />
      </form>
    </li>
    <li>
      <form id="favoriteForm" action="que" method="get">
        <input type="hidden" name="action" value="getScore" />
        <input type="submit" value="提交" />
      </form>
    </li>
    <li><a href="https://github.com/zzk72" role="button" id="connect">联系我们</a></li>
  </ul>
</nav>


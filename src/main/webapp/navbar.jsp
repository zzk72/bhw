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
    <li><strong>MP3播放器</strong></li>
    <li>
      <form id="Home" action="favorite" method="post">
        <input type="hidden" name="action" value="showHome" />
        <input type="submit" value="首页" />
      </form>
    </li>
    <li>
      <form id="favoriteForm" action="favorite" method="post">
        <input type="hidden" name="action" value="showFavorites" />
        <input type="submit" value="我的收藏" />
      </form>
    </li>
    <li><a href="https://github.com/zzk72" role="button" id="connect">联系我们</a></li>
  </ul>
</nav>


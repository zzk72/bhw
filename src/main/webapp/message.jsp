<%--
  Created by IntelliJ IDEA.
  User: 张珍奎
  Date: 2024/3/7
  Time: 17:0
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>信息展示</title>
    <style>
        .info-text {
            text-align: center;
            font-size: 24px;
            font-weight: bold;
        }
    </style>
</head>
<body>
<%
    String info = (String)request.getAttribute("info");
    if (info != null) {
%>
<div class="info-text">
    <%= info %>
</div>
<%
    }
%>
</body>
</html>
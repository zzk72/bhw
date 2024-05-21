<%--
  Created by IntelliJ IDEA.
  User: 张珍奎
  Date: 2024/3/8
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome Page</title>
    <style>
        body {
            background-image: url('https://zzk-host-1319605683.cos.ap-beijing.myqcloud.com/1305448.jpg'); /* 背景图片的路径 */
            background-color: #f0f0f0; /* 如果图片加载失败或无法显示，背景颜色作为备用 */
            font-family: Arial, sans-serif;
            color: #333;
            margin: 0;
            padding: 0; /* 重置默认的内外边距 */
            display: flex; /* 使body元素以弹性盒子模型显示 */
            flex-direction: column; /* 设置主轴方向为垂直方向 */
            justify-content: center; /* 使内容在垂直方向上居中 */
            align-items: center; /* 使内容在水平方向上居中 */
            height: 100vh; /* 使body元素占满整个视口 */
            background-size: cover; /* 让背景图片充满整个body */
            background-position: center; /* 使背景图片居中 */
        }


        h1 {
            text-align: center;
            color: #dee4cd;
        }

        p {
            text-align: center;
            margin-top: 20px;
            color: #dee4cd;
        }
        button{
            flex: 1;
            /*margin-right: 100px;*/
            padding: 12px 24px;
            font-size: 16px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            max-width: 100px;

        }
        .container {
            /*display: inline-block; !* 设置为内联块元素，使其可以并排摆放 *!*/
            margin-bottom: 10px; /* 设置底部间距 */
            justify-content: center;
        }

    </style>
</head>
<body>
<div >
    <%-- 从session中获取用户名 --%>
    <%
        String username = (String) session.getAttribute("username");
        String info = (String) session.getAttribute("info");
        info="submit success!";
        int score = (int) session.getAttribute("score");
        if (info != null) {
    %>
    <h1 ><%= info%><h1>
    <%
        }
    %>
</div>
<div class="container">

    <%-- 如果用户名存在，则显示欢迎消息 --%>
<%--    <h1>Login Success!</h1>--%>
    <% if (username != null) { %>
    <h1> <%= username %> Score:<%= score%> </h1>
    <% } else { %>
    <p>Please log in first.</p>
    <% } %>

</div>
<br><br>
<div class="container">
    <button onclick="window.location.href='login.jsp'" class="button">Logout</button>
</div>


</body>
</html>

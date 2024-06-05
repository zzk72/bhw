<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 张珍奎
  Date: 2024/3/8
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>checkin Page</title>
  <style>
    body {
      font-family: Arial, sans-serif;

      padding: 20px;
      background-image: url('https://zzk-host-1319605683.cos.ap-beijing.myqcloud.com/12085707_20944201.svg'); /* 背景图片的路径 */
      background-color: #f0f0f0;
      height: 100vh; /* 使body元素占满整个视口 */
      background-size: cover;
      background-position: center; /* 使背景图片居中 */
    }

    .content {
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      width: 100%; /* 确保内容宽度占满容器 */
      font-size: 18px;

    }



    .container {
      max-width: 400px;
      margin: auto; /* 让容器水平居中 */
      background-color: #fff;
      padding: 20px;
      border-radius: 5px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);

      display: flex; /* 使用Flexbox布局 */
      flex-direction: column; /* 设置主轴方向为垂直方向 */
      justify-content: center; /* 使容器在垂直方向上居中 */
      align-items: center; /* 使容器在水平方向上居中 */
      height: 50vh; /* 设置容器高度为50%视口高度 */

    }



    .form-group {
      margin-bottom: 15px;
      width: 100%;
    }

    .form-group label {
      display: block;
      font-weight: bold;
      margin-bottom: 5px;
    }

    .form-group input {
      width: 100%;
      padding: 8px;
      border-radius: 3px;
      border: 1px solid #ccc;
    }

    .button-group {
      text-align: center;
      font-size: 25px;
    }

    .button-group button {
      min-width: 80px;
      padding: 10px 10px; /* 设置按钮的内边距 */
      border: none;
      border-radius: 3px;
      cursor: pointer;
    }

    .button-group .register {
      background-color: #4CAF50;
      color: #fff;
    }

    .button-group .login {
      background-color: #007BFF;
      color: #fff;
    }
    p {
      text-align: center;
      margin-top: 20px;
      color: #0c0c0c;
    }
    #login-title {
      text-align: center;
      color: #000080;
      font-size: 25px;
    }
  </style>
</head>
<body>
<%
  String info = (String) session.getAttribute("Info");
  if (info != null) {
%>
<p><%=info%></p>
<%
  }
%>
<div class="container">
  <div class="content">
    <h1 id="login-title">checkin</h1>
    <!-- 创造间隔 -->
    <div style="height: 30px;"></div>
    <form action="checkin" method="get">
      <input type="hidden" name="action" value="checkin">
      <div class="form-group">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>
      </div>
      <!-- 创造间隔 -->
      <div class="form-group">
        <label for="viewpointId">Viewpoint:</label>
        <select id="viewpointId" name="viewpointId" required>
          <option value="">Select a viewpoint</option>
          <jsp:useBean id="viewpointList" scope="session" type="java.util.List"/>
          <c:forEach items="${viewpointList}" var="viewpoint">
            <option value="${viewpoint.id}">${viewpoint.name}</option>
          </c:forEach>
        </select>
      </div>
      <!-- 创造间隔 -->
      <div style="height: 30px;"></div>
      <div class="button-group">
        <button type="submit" class="checkin">Checkin</button>
      </div>

    </form>

  </div>
</div>

</body>
</html>

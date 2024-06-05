<%--
  Created by IntelliJ IDEA.
  User: 张珍奎
  Date: 2024/6/4
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户注册页面</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-image: url('https://zzk-host-1319605683.cos.ap-beijing.myqcloud.com/3232487_43916.svg'); /* 背景图片的路径 */
            background-color: #f0f0f0;
            height: 100vh; /* 使body元素占满整个视口 */
            background-size: cover;
            background-position: center; /* 使背景图片居中 */
        }

        h1 {
            text-align: center;
            color: #000080;
        }

        .form-table {
            margin: 0 auto;
            width: 450px;
            border-collapse: collapse;
        }

        .form-table td {
            padding: 5px;
            vertical-align: middle;
        }

        .form-table td:first-child {
            text-align: right;
            width: 150px;
        }

        .form-table input[type="text"],
        .form-table input[type="password"] {
            width: 280px;
            padding: 5px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        .form-table input[type="submit"],
        .form-table input[type="reset"] {
            padding: 5px 10px;
            border-radius: 5px;
            border: none;
            background-color: #4CAF50; /* Green */
            color: white;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
        }

        .form-table input[type="reset"] {
            background-color: #3e74e8; /* Red */
        }
        btn-box{
            text-align: center;
        }
        .container {
            max-width: 500px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .container {
            max-width: 500px;
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
    </style>
</head>
<body>
<div class="container">
    <h1>Register Page</h1>
    <!-- 创造间隔 -->
    <div style="height: 50px;"></div>
    <form action="user" method="post" >
        <table class="form-table">
            <!--用户名行-->
            <tr>
                <td>Username:</td>
                <td>
                    <input type="text" name="username">
                </td>
            </tr>
            <!--密码行-->
            <tr>
                <td>
                    <div style="height: 20px;"></div>
                    Password:
                </td>
                <td>
                    <input type="password" name="password">
                </td>
            </tr>
            <!--确认密码行-->
            <tr>
                <td>
                    <div style="height: 20px;"></div>
                    Confirm Password:
                </td>
                <td>
                    <input type="password" name="repassword">
                </td>
            </tr>
            <!--邮箱行-->
            <tr>
                <td>
                    <div style="height: 20px;"></div>
                    Email:
                </td>
                <td>
                    <input type="text" name="email">
                </td>
            <!--提交 重置行-->
<%--            <tr>--%>
<%--                <td>--%>
<%--                    <div style="height: 30px;"></div>--%>
<%--                    <div class="btn-box">--%>
<%--                        <input type="submit" value="Register">--%>
<%--                        <input type="reset" value="Reset">--%>
<%--                    </div>--%>
<%--                </td>--%>
<%--            </tr>--%>
            <tr>
                <td colspan="2" class="btn-box"  >
                    <div style="height: 30px;"></div>
                    <input type="submit" value="Register">
                    <input type="reset" value="Reset">
                </td>
            </tr>
            <!--设置action参数-->
            <input type="hidden" name="action" value="register">
        </table>
    </form>
</div>
</body>
</html>
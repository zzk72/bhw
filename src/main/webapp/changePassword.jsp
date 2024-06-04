<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Change Password</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-image: url('https://zzk-host-1319605683.cos.ap-beijing.myqcloud.com/3232487_43916.svg');
            background-color: #f0f0f0;
            height: 100vh;
            background-size: cover;
            background-position: center;
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
            background-color: #4CAF50;
            color: white;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
        }

        .form-table input[type="reset"] {
            background-color: #3e74e8;
        }

        .btn-box {
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
            margin: auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 50vh;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Change Password</h1>
    <div style="height: 20px;"></div>
    <form action="user" method="post">
        <table class="form-table">
            <tr>
                <td>Username:</td>
                <td>
                    <input type="text" name="username" required>
                </td>
                <td>
                    <input type="submit" value="Send Email" onclick="this.form.action='user?action=sendCode'; this.form.method='post';">
                </td>
            </tr>
        </table>
    </form>

    <div style="height: 30px;"></div>

    <form action="user" method="post">
        <table class="form-table">
            <tr>
                <td>Email:</td>
                <td>
                    <input type="text" name="email" value="<%= session.getAttribute("email") != null ? session.getAttribute("email") : "" %>" readonly>
                </td>
            </tr>
            <tr>
                <td>Verification Code:</td>
                <td>
                    <input type="text" name="code" required>
                </td>
            </tr>
            <tr>
                <td>New Password:</td>
                <td>
                    <input type="password" name="newPassword" required>
                </td>
            </tr>
            <tr>
                <td colspan="2" class="btn-box">
                    <div style="height: 30px;"></div>
                    <input type="submit" value="Change Password">
                    <input type="reset" value="Reset">
                </td>
            </tr>
            <input type="hidden" name="action" value="changePassword">
        </table>
    </form>
</div>
</body>
</html>

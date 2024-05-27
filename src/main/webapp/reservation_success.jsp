<%--
  Created by IntelliJ IDEA.
  User: 张珍奎
  Date: 2024/5/27
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>预约成功</title>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            // 检查浏览器是否支持通知
            if (!("Notification" in window)) {
                alert("This browser does not support desktop notifications.");
            } else if (Notification.permission === "granted") {
                // 如果用户已经同意显示通知
                notifyUser();
            } else if (Notification.permission !== "denied") {
                // 否则请求用户同意
                Notification.requestPermission().then(function (permission) {
                    if (permission === "granted") {
                        notifyUser();
                    }
                });
            }

            function notifyUser() {
                var notification = new Notification("预约成功", {
                    body: "您的预约已成功！感谢您的使用。",
                    icon: "path/to/icon.png" // 可选：通知图标的路径
                });

                notification.onclick = function() {
                    window.location.href = "viewpoints"; // 用户点击通知时重定向到景区浏览页面
                };
            }
        });
    </script>
</head>
<body>
<h1>预约成功</h1>
<p>您的预约已成功！</p>
<a href="viewpoints">返回景区浏览页面</a>
</body>
</html>

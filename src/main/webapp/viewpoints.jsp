<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">
    <style>
        /*https://zzk-host-1319605683.cos.ap-beijing.myqcloud.com/1305448.jpg*/
        body {
            background-color: #f0f0f0;
            color: #141313;
            padding-top: 60px;
            background-image: url('https://pic4.sucaisucai.com/12/09/12209944_2.jpg');
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            min-height: 100vh;
        }
        .viewpoint-item {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 1rem;
            padding: 1rem;
            background-color: #120947;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            max-width: 1000px;
        }
        .viewpoint-info {
            flex: 1;
        }
        .viewpoint-image {
            width: 120px;
            height: auto;
            margin-left: 1rem;
        }
        video {
            width: 65%;
            margin-top: 0.5rem;
            display: block;
            margin-left: auto;
            margin-right: auto;
        }
        button, input[type="submit"] {
            max-width: 70px;
            padding: 0.5rem;
            margin-top: 0.5rem;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 10px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            display: block;
            margin-left: auto;
            margin-right: auto;
        }
        button:hover {
            background-color: #0056b3;
        }
        article h3 {
            margin: 0.5rem 0;
        }
        footer {
            text-align: center;
            padding: 1rem 0;
        }
        .dark-text {
            color: #000;
        }
        .content-wrapper {
            display: flex;
            justify-content: space-between;
        }
        .main-content {
            top: 100px;
            max-width: 80%;
            min-width: 80%;
            padding-left: 30%;
        }
    </style>
    <title>景区景点浏览</title>
</head>

<body>

<jsp:include page="navbar.jsp"/> <!-- 引入导航栏 -->

<div class="content-wrapper">
    <main class="main-content">
        <div class="grid">
            <section>
                <p class="dark-text">欢迎浏览景区景点信息。</p>
                <div class="viewpoint-list">
                    <jsp:useBean id="viewpoints" scope="request" type="java.util.List"/>
                    <c:forEach items="${viewpoints}" var="viewpoint">
                        <article class="viewpoint-item">
                            <div class="viewpoint-info">
                                <h3>${viewpoint.name}</h3>
                                <p>${viewpoint.text}</p>
                                <div class="video-container" style="margin-top: 10px;">
<%--                                    <video src="${viewpoint.video}" controls></video>--%>
                                    <iframe src="${viewpoint.video}" width="800" height="400" title="zzk video" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
                                </div>
                                <form action="reserve" method="post" style="margin-top: 10px;">
                                        <%--@declare id="reservationtime"--%><input type="hidden" name="viewpointId" value="${viewpoint.id}">
                                    <label for="reservationTime">预约时间：</label>
                                    <input type="datetime-local" name="reservationTime" required>
                                    <input type="hidden" name="userId" value="${sessionScope.user.id}">
                                    <div class="button-container" style="margin-top: 10px; display: flex; gap: 10px;">
                                        <button type="submit" style="margin: 0;">预约</button>
                                        <button type="button" onclick="location.href='viewpoints?action=viewServices&viewpointId=${viewpoint.id}'" style="margin: 0;">查看详情</button>
                                    </div>
                                </form>
                            </div>
                        </article>

                    </c:forEach>
                </div>
            </section>
        </div>
    </main>
</div>

<jsp:include page="footer.jsp"/> <!-- 引入页脚 -->

</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: Zhang Zhenkui
  Date: 2024/6/4
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">
    <style>
        body {
            background-color: #f0f0f0;
            color: #141313;
            padding-top: 60px;
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            min-height: 100vh;
        }
        .service-item {
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
        .service-info {
            flex: 1;
        }
        h4 {
            margin: 0.5rem 0;
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
    <title>Viewpoint Details</title>
</head>

<body>

<jsp:include page="navbar.jsp"/> <!-- Include navbar -->

<div class="content-wrapper">
    <main class="main-content">
        <div class="grid">
            <section>
                <h2 class="dark-text">Viewpoint Information</h2>
                <h2 class="dark-text">Viewpoint Details</h2>

                <div class="service-list">
                    <article class="viewpoint-item">
                        <img src="${viewpoint.image}" alt="${viewpoint.name}" class="viewpoint-image">
                        <div class="viewpoint-info">
                            <h3>${viewpoint.name}</h3>
                            <p>${viewpoint.text}</p>
                            <p>Queue Status: ${viewpoint.queueStatus} people</p>
                            <p>Air Quality: ${airQuality}</p>
                            <p>Weather: ${weatherInfo} Temperature: ${temperature}℃</p>
                        </div>
                    </article>
                    <h2 class="dark-text">Viewpoint Service Stations</h2>
                    <c:if test="${empty services}">
                        <p>No service stations available</p>
                    </c:if>
                    <!--如果有service，就显示service-->
                    <c:if test="${not empty services}">
                        <c:forEach items="${services}" var="service">
                            <article class="service-item">
                                <div class="service-info">
                                    <h4>${service.name}</h4>
                                    <p>${service.description}</p>
                                    <p>Type: ${service.type}</p>
                                </div>
                            </article>
                        </c:forEach>
                    </c:if>

                    <h2 class="dark-text">Viewpoint Reviews</h2>
                    <c:if test="${empty reviews}">
                        <p>No reviews available</p>
                    </c:if>
                    <c:if test="${not empty reviews}">
                        <c:forEach items="${reviews}" var="review">
                            <article class="service-item">
                                <div class="service-info">
                                    <p>comment: ${review}</p>
                                </div>
                            </article>
                        </c:forEach>
                    </c:if>
                </div>
            </section>
        </div>
    </main>
</div>

<jsp:include page="footer.jsp"/> <!-- Include footer -->

</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: Zhang Zhenkui
  Date: 2024/6/5
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
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
      background-image: url('https://pic4.sucaisucai.com/12/09/12209944_2.jpg');
      background-size: cover;
      background-position: center;
      background-attachment: fixed;
      min-height: 100vh;
    }
    .order-item {
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
    .order-info {
      flex: 1;
    }
    .order-image {
      width: 120px;
      height: auto;
      margin-left: 1rem;
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
  <title>Orders</title>
</head>
<body>
<jsp:include page="navbar.jsp"/> <!-- Include navbar -->

<div class="content-wrapper">
  <main class="main-content">
    <div class="grid">
      <section>
        <p class="dark-text">Your Orders</p>
        <div class="order-list">
          <jsp:useBean id="orders" scope="request" type="java.util.List"/>
          <c:forEach items="${orders}" var="order">
            <article class="order-item">
              <div class="order-info">
                <h3>Order ID: ${order.id}</h3>
                <p>景点: ${order.viewpoint.name}</p>
                <p>状态: ${order.status}</p>
                <p>预约时间: ${order.reservationTime}</p>
              </div>
            </article>
          </c:forEach>
        </div>
      </section>
    </div>
  </main>
</div>
<jsp:include page="footer.jsp"/> <!-- Include footer -->
</body>
</html>

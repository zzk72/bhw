<%--
  Created by IntelliJ IDEA.
  User: 张珍奎
  Date: 2024/4/5
  Time: 9:53
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="com.example.sign.entity.User" %>
<%@ page import="com.example.sign.entity.Question" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.sign.dao.UserDao" %>

<!DOCTYPE html>
<html lang="zh">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">
  <style>
    body {
      background-color: #f0f0f0;
      color: #141313; /* 更深的字体颜色提高可读性 */
      padding-top: 60px; /* 根据导航栏的实际高度调整 */
      background-image: url('https://pic4.sucaisucai.com/12/09/12209944_2.jpg'); /* 背景图片的路径 */
      background-size: cover; /* 背景图片覆盖整个元素 */
      background-position: center; /* 背景图片居中显示 */
      background-attachment: fixed; /* 背景图片固定，不随内容滚动 */
      min-height: 100vh; /* 使body至少占满整个视口高度 */
    }

    .question-item {
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

    .question-info {
      flex: 1; /* 让音乐信息占据剩余空间 */
    }

    /* 收藏button和form样式 */
    button, input[class="fav_btn"] {
      max-width: 70px; /* 收藏按钮的最大宽度，避免过宽 */
      padding: 0.5rem;
      margin-top: 0.5rem;
      background-color: #007bff;
      color: white;
      border: none;
      border-radius: 10px;
      cursor: pointer;
      transition: background-color 0.3s ease;
      display: block; /* 让按钮成为块级元素，便于居中 */
      margin-left: auto; /* 与右边距结合使用，实现居中 */
      margin-right: auto;
    }

    button:hover {
      background-color: #0056b3; /* 按钮悬停效果 */
    }

    article h3 {
      margin: 0.5rem 0; /* 调整标题的上下边距 */
    }

    footer {
      text-align: center;
      padding: 1rem 0;
    }

    .dark-text {
      color: #000; /* 更深的字体颜色，这里使用黑色作为示例 */
    }

    /*设置主侧边栏布局*/
    .content-wrapper {
      display: flex;
      justify-content: space-between;
      /*align-items: center;*/
    }

    .main-content {
      Top: 100px;
      max-width:80%;
      min-width: 80%;
      padding-left: 30%;
      /*flex: 60%; !* 让主内容区域占据剩余的空间 *!*/
      /* 需要的话可以添加一些样式，比如内边距 */
      /*padding-right: 100px; !* 给侧边栏留出一些空间 *!*/
      /*居中*/

    }
  </style>

  <title>科目一考试</title>

</head>
<body>

<jsp:include page="que_header.jsp"/> <!-- 引入导航栏 -->

<div class="content-wrapper">

  <main class="main-content">
    <div class="grid">
      <section>
        <p class="dark-text">开始进行科目一模拟考试，祝你好运。</p>
        <div class="question-list">
          <!-- 使用JSP useBean标签从session获取currentQuestion对象 -->
          <jsp:useBean id="currentQuestion" class="com.example.sign.entity.Question" scope="session"/>
          <jsp:useBean id="username" class="java.lang.String" scope="session"/>
          <% session.setAttribute("session_id", session.getId()); %>
          <!-- 显示当前问题 -->
          <h3>${username}，请回答以下问题：</h3>
          <article class="question-item" style="display: flex; flex-direction: column; align-items: center; justify-content: space-around;">
            <h3>Q${currentQuestion.question_id} ${currentQuestion.question}</h3>

            <div class="content-row" style="display: flex; justify-content: space-around; width: 100%; align-items: center;">
              <img src="${currentQuestion.image_url}" alt="题目图像" class="question-image" style="width: 40%; height: auto;">
              <div class="options" style="display: flex; flex-direction: column; justify-content: center; width: 40%;">
                <form id="question-form"  action="que" method="get" >
                  <div>
                    <input type="radio" id="optionA" name="user_answer" value="A"
                    ${currentQuestion.user_answer == 'A' ? 'checked' : ''}>
                    <label for="optionA">A:${currentQuestion.optionA}</label>
                    <c:if test="${currentQuestion.user_answer == 'A'}">
                      <span style="color: green;">(Answered)</span>
                    </c:if>
                  </div>
                  <div>
                    <input type="radio" id="optionB" name="user_answer" value="B"
                    ${currentQuestion.user_answer == 'B' ? 'checked' : ''}>
                    <label for="optionB">B:${currentQuestion.optionB}</label>
                    <c:if test="${currentQuestion.user_answer == 'B'}">
                      <span style="color: green;">(Answered)</span>
                    </c:if>
                  </div>
                  <div>
                    <input type="radio" id="optionC" name="user_answer" value="C"
                    ${currentQuestion.user_answer == 'C' ? 'checked' : ''}>
                    <label for="optionC">C:${currentQuestion.optionC}</label>
                    <c:if test="${currentQuestion.user_answer == 'C'}">
                      <span style="color: green;">(Answered)</span>
                    </c:if>
                  </div>
                  <div>
                    <input type="radio" id="optionD" name="user_answer" value="D"
                    ${currentQuestion.user_answer == 'D' ? 'checked' : ''}>
                    <label for="optionD">D:${currentQuestion.optionD}</label>
                    <c:if test="${currentQuestion.user_answer == 'D'}">
                      <span style="color: green;">(Answered)</span>
                    </c:if>
                  </div>
                  <input type="hidden" name="question_id" value="${currentQuestion.question_id}">
                  <input type="hidden" name="user_id" value="${sessionScope.user_id}">
                  <input type="hidden" name="action" value="submit_answer">
                </form>
              </div>
            </div>

            <button type="submit" form="question-form" style="margin-top: 20px;">确定</button>
          </article>

          <div class="navigation-buttons" style="display: flex; justify-content: space-between; width: 100%; margin-top: 20px;">
            <form action="que" method="get" style="display: flex;">
              <input type="hidden" name="action" value="prevQuestion">
              <button type="submit">
                <img src="https://zzk-host-1319605683.cos.ap-beijing.myqcloud.com/back_arrow_5821.png">
              </button>
            </form>
            <form action="que" method="get" style="display: flex;">
              <input type="hidden" name="action" value="nextQuestion">
              <button type="submit">
                <img src="https://zzk-host-1319605683.cos.ap-beijing.myqcloud.com/forwardarrow_haciaadelante_4836.png">
              </button>
            </form>
          </div>

        </div>
      </section>
    </div>
  </main>


</div>

<jsp:include page="footer.jsp"/> <!-- 引入页脚 -->

<script>
  document.addEventListener('DOMContentLoaded', function () {
    var sideInputs = document.querySelectorAll('.side_input');
    sideInputs.forEach(function(input) {
      input.addEventListener('mouseenter', function() {
        this.style.backgroundColor = '#007bff'; // 蓝色
        this.style.color = '#ffffff'; // 文字颜色白色
      });
      input.addEventListener('mouseleave', function() {
        this.style.backgroundColor = '#f0f4f8'; // 原始背景色
        this.style.color = '#0f0934'; // 原始文字颜色
      });
    });
  });

</script>

</body>
</html>


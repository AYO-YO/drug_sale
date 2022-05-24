<%--
  Created by IntelliJ IDEA.
  User: 16584
  Date: 2022/5/24
  Time: 19:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>帆帆大药房 - 你的家庭药师</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="https://cdn.staticfile.org/twitter-bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.staticfile.org/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/5.1.3/js/bootstrap.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/5.1.3/js/bootstrap.bundle.js"></script>
    <script src="js/index.js"></script>
    <link href="css/init.css" rel="stylesheet">
    <link href="font/iconfont.css" rel="stylesheet">
    <link href="css/thisWeb.css" rel="stylesheet">
    <link href="css/css_index.css" rel="stylesheet">
    <script>
        function scrollBottom() {
            let tops = document.getElementsByClassName('return_top')[0];
            let clients = window.innerHeight;
            let scrollTop = document.documentElement.scrollTop;
            let wholeTop = document.documentElement.scrollHeight;
            if (clients + scrollTop >= wholeTop / 1.1) {
                tops.style.transform = "scale(1.2)";
            } else {
                tops.style.transform = "";
            }
        }

        window.onscroll = scrollBottom;
    </script>
</head>
<body id="bd_index">
<header id="headerContent"></header>
<main id="mainContent" style="margin-top: 60px;"></main>
<footer id="footerContent"></footer>
<div id="commonContent"></div>
<a class="return_top iconfont icon-fanhuidingbu" href="#" onclick="scroll(0,0)" title="点击返回顶部"></a>
</body>
<script>
    loadIndex();
</script>
</html>
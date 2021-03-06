<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>Header</title>
</head>
<body>
<div data-bs-dismiss="alert" id="liveAlertPlaceholder"></div>
<header class="header">
    <nav class="top_bar">
        <img alt="" class="logo" src="${pageContext.request.contextPath}/img/logo.png">
        <ul class="top_nav_bar">
            <li id="btn_goto_index">首页</li>
            <li id="btn_goto_store">药库</li>
            <li>家庭常备</li>
        </ul>
        <div class="user_control_center">
            <a class="user_handle" data-bs-target="#registerModal" data-bs-toggle="modal">新用户注册</a>
            <a class="user_handle" data-bs-target="#loginModal" data-bs-toggle="modal">用户登录</a>
        </div>
        <button class="user_notice iconfont icon-lingdang" data-bs-container="body"
                data-bs-content="暂无通知" data-bs-placement="bottom" data-bs-toggle="popover"
                title="通知">
        </button>
        <div class="search">
            <input class="ser_text" id="ser_text" name="ser_text" type="text">
            <button class="iconfont icon-sousuo ser_but" id="ser_but" name="ser_button" type="submit"></button>
        </div>
    </nav>
    <script>
        $(function () {
            $("[data-bs-toggle='popover']").popover();
        });

        $("#btn_goto_store").click(function () {
            console.log("跳转药品库");
            let user = sessionStorage.getItem('user_name');
            if (user != null) {
                $("#mainContent").load("${pageContext.request.contextPath}/jsp/store.jsp");
                document.title = "药品库 - 帆帆大药房";
            } else {
                alert('请先登录！', 'danger');
            }
        });

        $("#btn_goto_index").click(function () {
            console.log("跳转首页")
            $("#mainContent").load("${pageContext.request.contextPath}/jsp/main.jsp");
            document.title = "帆帆大药房 - 你的家庭药师";
        });
        getUser();
    </script>
</header>
</body>
</html>
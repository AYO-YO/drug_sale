<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<div aria-hidden="true" aria-labelledby="myModalLabel" class="modal fade" id="loginModal" role="dialog"
     tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">
                    用户登录
                </h4>
                <button aria-hidden="true" class="close" data-bs-dismiss="modal" type="button">
                    &times;
                </button>
            </div>
            <div class="modal-body">
                <form action="${pageContext.request.contextPath}/LoginCheck" method="post" role="form">
                    <div class="mb-3">
                        <label class="form-label" for="user">用户名</label>
                        <input class="form-control" id="user" name="user" placeholder="请输入用户名" type="text">
                    </div>
                    <div class="mb-3">
                        <label class="form-label" for="pwd">密码</label>
                        <input class="form-control" id="pwd" name="pwd" placeholder="请输入密码" type="password">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-secondary" data-bs-dismiss="modal" type="button">关闭</button>
                <button class="btn btn-primary" id="btn_login_submit" onclick="login()" type="button">
                    登录
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div aria-hidden="true" aria-labelledby="myModalLabel" class="modal fade" id="registerModal" role="dialog"
     tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="registerModalLabel">
                    用户注册
                </h4>
                <button aria-label="Close" class="close" data-bs-dismiss="modal" type="button">
                    &times;
                </button>
            </div>
            <div class="modal-body">
                <form action="${pageContext.request.contextPath}/Register" method="post" role="form">
                    <div class="mb-3">
                        <label class="form-label" for="reg_user">用户名</label>
                        <input class="form-control" id="reg_user" name="user" placeholder="请输入用户名" type="text">
                    </div>
                    <div class="mb-3">
                        <label class="form-label" for="reg_pwd">密码</label>
                        <input class="form-control" id="reg_pwd" name="pwd" placeholder="请输入密码" type="password">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-secondary" data-bs-dismiss="modal" type="button">关闭</button>
                <button class="btn btn-primary" id="btn_reg_submit" onclick="reg()" type="button">
                    注册
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div id="liveAlertPlaceholder"></div>
</body>
</html>
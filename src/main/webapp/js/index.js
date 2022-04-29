function handleDots() {
    let dots = document.getElementsByClassName('li_dot');
    for (let j = 0; j < 5; j++) {
        dots[j].onmouseenter = function () {
            for (let i = 0; i < 5; i++) {
                dots[i].className = 'li_dot';
            }
            dots[j].className += " checked_dot";
        }
    }
}

function alert(message, type) {
    let wrapper = document.querySelector('body');
    let messageBox = document.createElement('div');
    messageBox.role = 'alert';
    messageBox.innerHTML = message + '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>'
    wrapper.insertBefore(messageBox, wrapper.children[0]);
    messageBox.className = 'alert alert-' + type + ' alert-dismissible fade show';
    window.setTimeout(function () {
        messageBox.className = 'alert alert-' + type + ' alert-dismissible fade hide';
    }, 2000);//显示的时间
    window.setTimeout(function () {
        messageBox.remove();
    }, 3000);
}

function loadIndex() {
    $(document).ready(function () {
        $("#commonContent").load("html/common.html");
        $("#headerContent").load("html/header.html");
        $("#mainContent").load("html/main.html");
        $("#footerContent").load("html/footer.html");
    });
}


function exit() {
    sessionStorage.removeItem('userName');
    window.location.reload();
}

function getUser() {
    let userName = sessionStorage.getItem('userName');
    if (userName != null) {
        console.log('用户' + userName + '已登录。')
        let parent = document.querySelector('.user_control_center');
        console.log(parent);
        let nodes = parent.children;
        let l = nodes.length
        for (let i = 0; i < l; i++) {
            parent.removeChild(nodes[0]);
        }

        let exitBtn = document.createElement('a');
        exitBtn.className = 'user_handle';
        exitBtn.innerHTML = '退出';
        exitBtn.onclick = exit;
        parent.appendChild(exitBtn);

        let userBtn = document.createElement('a');
        userBtn.className = 'user_handle';
        userBtn.innerHTML = userName;

        // TODO: 用户登录后显示用户名，后续点击用户名应跳转到个人中心页面
        userBtn.onclick = exit;
        parent.appendChild(userBtn);

        let cartBtn = document.createElement('a');
        cartBtn.className = 'user_handle';
        cartBtn.innerHTML = '我的药箱';
        cartBtn.id = 'my_drug_btn';
        parent.appendChild(cartBtn);
    } else
        $("#mainContent").load("html/main.html");
}

function login() {
    let oUser = document.querySelector("#user").value;
    let oPwd = document.querySelector("#pwd").value;

    let httpRequest = new XMLHttpRequest();//第一步：创建需要的对象
    httpRequest.open('POST', './LoginCheck', true); //第二步：打开连接
    httpRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");//设置请求头 注：post方式必须设置请求头（在建立连接后设置请求头）
    httpRequest.send('user=' + oUser + '&pwd=' + oPwd);//发送请求 将情头体写在send中
    /**
     * 获取数据后的处理程序
     */
    httpRequest.onreadystatechange = function () {//请求后的回调接口，可将请求成功后要执行的程序写在其中
        if (httpRequest.readyState === 4 && httpRequest.status === 200) {//验证请求是否发送成功
            let json = httpRequest.responseText;//获取到服务端返回的数据
            console.log(json);
            if (parseInt(json) !== -1) {
                alert(oUser + ', 欢迎你！', 'success');
                sessionStorage.setItem('userName', oUser);
                sessionStorage.setItem('userId', json);
                document.querySelector("#loginModal > div > div > div.modal-footer > button.btn.btn-secondary").click();
                getUser();
            } else {
                alert("用户名或密码错误！", 'danger');
            }
        }
    };
}

function reg() {
    let oUser = document.querySelector("#reg_user").value;
    let oPwd = document.querySelector("#reg_pwd").value;
    let httpRequest = new XMLHttpRequest();//第一步：创建需要的对象
    httpRequest.open('POST', './Register', true); //第二步：打开连接
    httpRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");//设置请求头 注：post方式必须设置请求头（在建立连接后设置请求头）
    httpRequest.send('user=' + oUser + '&pwd=' + oPwd);//发送请求 将情头体写在send中
    /**
     * 获取数据后的处理程序
     */
    httpRequest.onreadystatechange = function () {//请求后的回调接口，可将请求成功后要执行的程序写在其中
        if (httpRequest.readyState === 4 && httpRequest.status === 200) {//验证请求是否发送成功
            let json = httpRequest.responseText;//获取到服务端返回的数据
            console.log(json);
            if (json) {
                alert(oUser + ', 注册成功！', 'success');
                document.querySelector("#registerModal > div > div > div.modal-footer > button.btn.btn-secondary").click();
            } else {
                alert("注册失败！", 'warning');
            }
        }
    };
}

function getMeds() {
    console.log("函数执行");
    let httpRequest = new XMLHttpRequest();//第一步：创建需要的对象
    httpRequest.open('POST', './GetMedicines', true); //第二步：打开连接
    httpRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");//设置请求头 注：post方式必须设置请求头（在建立连接后设置请求头）
    httpRequest.send();
    /**
     * 获取数据后的处理程序
     */
    httpRequest.onreadystatechange = function () {//请求后的回调接口，可将请求成功后要执行的程序写在其中
        if (httpRequest.readyState === 4 && httpRequest.status === 200) {//验证请求是否发送成功
            let json = httpRequest.responseText;//获取到服务端返回的数据
            console.log(json);
            json = JSON.parse(json);
            console.log(json);
            let user = sessionStorage.getItem('userId');
            let tab = document.querySelector("#store_list");
            for (let meds of json) {
                let s = '<tr id="cart_row_' + meds["id"] + '">';
                s += "<td>" + meds['id'] + "</td>";
                s += "<td>" + meds['name'] + "</td>";
                s += "<td>" + meds['price'] + "</td>";
                s += "<td>" + meds['stock'] + "</td>";
                s += "<td>" + meds['date'] + "</td>";
                s += "<td>" + meds['life'] + "</td>";
                // s += "<td><a class=\"btn_add_cart\" href=\"AddCart?medid=" + meds['id'] + "&userid=" + user + "\">加入购物车</a></td>";
                s += "</tr>";
                tab.innerHTML += s;
                let oRow = document.querySelector("#cart_row_" + meds["id"]);
                let oA = document.createElement("a");
                oA.innerHTML = "加入购物车";
                oA.className = "btn_add_cart";
                oA.onclick = function () {
                    alert('加入购物车', 'warning')
                    let httpRequest = new XMLHttpRequest();//第一步：创建需要的对象
                    httpRequest.open('POST', './AddCart', true); //第二步：打开连接
                    httpRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");//设置请求头 注：post方式必须设置请求头（在建立连接后设置请求头）
                    httpRequest.send('medid=' + meds['id'] + '&userid=' + user);//发送请求 将情头体写在send中
                    /**
                     * 获取数据后的处理程序
                     */
                    httpRequest.onreadystatechange = function () {//请求后的回调接口，可将请求成功后要执行的程序写在其中
                        if (httpRequest.readyState === 4 && httpRequest.status === 200) {//验证请求是否发送成功
                            let json = httpRequest.responseText;//获取到服务端返回的数据
                            console.log(json);
                            console.log(typeof json)
                            if (json === "true") {
                                alert("加入购物车成功！", "success");
                            } else alert("不可重复加购！", "warning");
                        }
                    };
                }
                let oTd = document.createElement('td');
                oTd.appendChild(oA);
                oRow.appendChild(oTd);
            }
        }
    };
}
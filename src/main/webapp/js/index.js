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
    sessionStorage.removeItem('user_name');
    window.location.reload();
}

function getUser() {
    let user_name = sessionStorage.getItem('user_name');
    if (user_name != null) {
        console.log('用户' + user_name + '已登录。')
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
        userBtn.innerHTML = user_name;

        // TODO: 用户登录后显示用户名，后续点击用户名应跳转到个人中心页面
        userBtn.onclick = exit;
        parent.appendChild(userBtn);

        let cartBtn = document.createElement('a');
        cartBtn.className = 'user_handle';
        cartBtn.innerHTML = '我的药箱';
        cartBtn.onclick = function () {
            $("#mainContent").load("html/cart.html");
        }
        cartBtn.id = 'my_drug_btn';
        parent.appendChild(cartBtn);
    } else $("#mainContent").load("html/main.html");
}

function doRequest(url, data, method = 'POST') {
    let httpRequest = new XMLHttpRequest(); // 创建对象
    httpRequest.open(method, url, true); // 打开链接
    httpRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    httpRequest.send(data);
    return httpRequest;
}

function login() {
    let oUser = document.querySelector("#user").value;
    let oPwd = document.querySelector("#pwd").value;

    let httpRequest = doRequest('./LoginCheck', 'user=' + oUser + '&pwd=' + oPwd, 'POST');//第一步：创建需要的对象

    /**
     * 获取数据后的处理程序
     */
    httpRequest.onreadystatechange = function () {//请求后的回调接口，可将请求成功后要执行的程序写在其中
        if (httpRequest.readyState === 4 && httpRequest.status === 200) {//验证请求是否发送成功
            let json = httpRequest.responseText;//获取到服务端返回的数据
            console.log(json);
            if (parseInt(json) !== -1) {
                alert(oUser + ', 欢迎你！', 'success');
                sessionStorage.setItem('user_name', oUser);
                sessionStorage.setItem('user_id', json);
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
            let user = sessionStorage.getItem('user_id');
            let tab = document.querySelector("#store_list");
            for (let meds of json) {
                let oRow = document.createElement('tr');
                tab.appendChild(oRow);

                let tdId = document.createElement('td');
                oRow.appendChild(tdId);
                tdId.innerHTML = meds['id'];

                let tdName = document.createElement('td');
                oRow.appendChild(tdName);
                tdName.innerHTML = meds['name'];

                let tdPrice = document.createElement('td');
                oRow.appendChild(tdPrice);
                tdPrice.innerHTML = meds['price'];

                let tdStock = document.createElement('td');
                oRow.appendChild(tdStock);
                tdStock.innerHTML = meds['stock'];

                let tdData = document.createElement('td');
                oRow.appendChild(tdData);
                tdData.innerHTML = meds['date'];

                let tdLife = document.createElement('td');
                oRow.appendChild(tdLife);
                tdLife.innerHTML = meds['life'];

                let aAddCart = document.createElement("a");
                aAddCart.innerHTML = "加入购物车";
                aAddCart.className = "btn_add_cart";
                aAddCart.onclick = function () {
                    let httpRequest = new XMLHttpRequest();//第一步：创建需要的对象
                    httpRequest.open('POST', './AddCart', true); //第二步：打开连接
                    httpRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");//设置请求头 注：post方式必须设置请求头（在建立连接后设置请求头）
                    httpRequest.send('medid=' + meds['id'] + '&user_id=' + user);//发送请求 将情头体写在send中
                    /**
                     * 获取数据后的处理程序
                     */
                    httpRequest.onreadystatechange = function () {//请求后的回调接口，可将请求成功后要执行的程序写在其中
                        if (httpRequest.readyState === 4 && httpRequest.status === 200) {//验证请求是否发送成功
                            let json = httpRequest.responseText;//获取到服务端返回的数据
                            console.log(json.length);
                            console.log(json);
                            if (json === 'true') alert("加入购物车成功！", "success"); else alert("不可重复加购！", "warning");
                        }
                    };
                }
                let tdA = document.createElement('td');
                tdA.appendChild(aAddCart);
                oRow.appendChild(tdA);
            }
        }
    };
}

function getCart() {
    let user_id = sessionStorage.getItem('user_id');
    let httpRequest = doRequest('./GetCart', "user_id=" + user_id, 'POST');
    httpRequest.onreadystatechange = function () {
        if (httpRequest.readyState === 4 && httpRequest.status === 200) {
            let res = httpRequest.responseText;
            console.log(res);
            let target = document.querySelector('#cart_list');
            let json = JSON.parse(res)
            for (let row of json) {
                let oTr = document.createElement('tr');
                target.append(oTr);
                let oId = document.createElement('td');
                oId.innerHTML = row['drug_id'];
                oTr.append(oId);
                let oName = document.createElement('td');
                oName.innerHTML = row['name'];
                oTr.append(oName);
                let oPrice = document.createElement('td');
                oPrice.innerHTML = row['price'];
                oTr.append(oPrice);

                let oNum = document.createElement('td');
                let inpNum = document.createElement('input');
                inpNum.type = 'number';
                inpNum.value = row['num'];
                inpNum.disabled = true;
                let btnRedu = document.createElement('button');
                btnRedu.innerHTML = '-';
                btnRedu.onclick = function () {
                    // TODO: 后续需将数据同步至数据库
                    inpNum.value = (parseInt(inpNum.value) - 1) + ""
                }
                let btnAdd = document.createElement('button');
                btnAdd.innerHTML = '+';
                btnAdd.onclick = function () {
                    inpNum.value = (parseInt(inpNum.value) + 1) + ""
                }
                oNum.append(btnRedu);
                oNum.appendChild(inpNum);
                oNum.append(btnAdd);
                oTr.append(oNum);

                let oAllPrice = document.createElement('td');
                oAllPrice.innerHTML = parseInt(inpNum.value) * row['price'] + '';
                oTr.append(oAllPrice);

                inpNum.onchange = function () {
                    oAllPrice.innerHTML = parseInt(inpNum.value) * row['price'] + '';
                }

                let oHandle = document.createElement('td');

                let aDel = document.createElement('a');
                aDel.className = 'user_handle';
                aDel.onclick = function () {
                    let drug_id = row['drug_id'];
                    let httpRequest = doRequest('./DelCart', 'user_id=' + user_id + '&drug_id=' + drug_id, 'POST');
                    httpRequest.onreadystatechange = function () {
                        if (httpRequest.readyState === 4 && httpRequest.status === 200) {//验证请求是否发送成功
                            let res = httpRequest.responseText;//获取到服务端返回的数据
                            console.log(res);
                            if (res === "true") {
                                alert("删除成功！", "success");
                                $("#mainContent").load("html/cart.html");
                            } else alert("删除失败！", "warning");
                        }
                    }
                }
                aDel.innerHTML = '删除';
                oHandle.append(aDel);

                let aBuy = document.createElement('a');
                aBuy.className = 'user_handle';
                aBuy.innerHTML = '购买';
                aBuy.onclick = function () {
                    // TODO: 购买功能未实现
                    alert('暂不支持', 'warning');
                }
                oHandle.append(aBuy);
                oTr.append(oHandle);
            }
        }
    }
}
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<table class="table table-hover">
    <thead>
    <tr>
        <th>药品id</th>
        <th>药品名称</th>
        <th>单价</th>
        <th>数量</th>
        <th>总计</th>
        <th></th>
    </tr>
    </thead>
    <tbody id="cart_list"></tbody>
</table>
<script>
    getCart();
</script>
</body>
</html>